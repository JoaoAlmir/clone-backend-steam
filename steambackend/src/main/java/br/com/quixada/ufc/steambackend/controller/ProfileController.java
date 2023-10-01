package br.com.quixada.ufc.steambackend.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.quixada.ufc.steambackend.model.Profile;

public class ProfileController {
  public boolean insertProfile(String name, String email, String nickname, String location) throws IOException{
    Profile profile = new Profile(name, email, nickname, location);

    CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(Profile.class);
    ObjectWriter writer = mapper.writer(schema);
    OutputStream outstream = new FileOutputStream("steambackend\\src\\main\\java\\br\\com\\quixada\\ufc\\steambackend\\output\\data.csv", true);
    writer.writeValue(outstream, profile);

    return true;
  }
  
  public int sizeData() throws IOException{
    InputStream is  = new FileInputStream("steambackend/src/main/java/br/com/quixada/ufc/steambackend/output/data.csv");
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    Integer size = 0;
    s = br.readLine();

    while (s != null) {
      s = br.readLine();

      if(s != ""){
        size++;
      }
    }

    br.close();
    return size;
  }

  public boolean convertToJSON(){
    File input = new File("steambackend/src/main/java/br/com/quixada/ufc/steambackend/output/data.csv");
    try {
      CsvMapper csvMapper = new CsvMapper();
      CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
      MappingIterator<Profile> mappingIterator = csvMapper.readerFor(Profile.class).with(csvSchema).readValues(input);
      List<Profile> profiles = mappingIterator.readAll();
      
      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(profiles);
      System.out.println(json);
      return true;
    } catch(Exception e) {
      return false;
    }
  }

  public boolean convertToXML(){
      File input = new File("steambackend/src/main/java/br/com/quixada/ufc/steambackend/output/data.csv");
      try {
      CsvMapper csvMapper = new CsvMapper();
      CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
      MappingIterator<Profile> mappingIterator = csvMapper.readerFor(Profile.class).with(csvSchema).readValues(input);
      List<Profile> profiles = mappingIterator.readAll();

      XmlMapper xmlMapper = new XmlMapper();
      String xml = xmlMapper.writeValueAsString(profiles);
      System.out.println(xml);

      return true;
    } catch(Exception e) {
      return false;
    }

  }

  public boolean compressData(){
    try {
      FileOutputStream fileOut = new FileOutputStream("data.zip");
      ZipOutputStream zipOut = new ZipOutputStream(fileOut);
      
      File inputFile = new File("steambackend\\src\\main\\java\\br\\com\\quixada\\ufc\\steambackend\\output\\data.csv");
      FileInputStream fileIn = new FileInputStream(inputFile);
      
      ZipEntry zipIn = new ZipEntry(inputFile.getName());
      zipOut.putNextEntry(zipIn);
      
      byte[] bytes = new byte[1024];
      int length = fileIn.read(bytes);
      
      while(length >= 0) {
        zipOut.write(bytes, 0, length);
      }

      fileIn.close();
      fileOut.close();
      
      return true;
    } catch (IOException e) {
        return false;
    }
  }

  public String showHash(){
    return "";
  }
}