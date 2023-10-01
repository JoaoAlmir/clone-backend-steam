package br.com.quixada.ufc.steambackend.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import br.com.quixada.ufc.steambackend.model.Profile;

@SpringBootApplication
public class StartData {
	public static void createProfiles() throws IOException{
		List<Profile> profileList = new ArrayList<Profile>();
		profileList.add(new Profile("Gibson Antoniento", "gib@gmail.com", "gibzin", "Acre"));
		profileList.add(new Profile("Cleilton Nunes", "cleilton_nunes@gmail.com", "yuzuo09", "Mato Grosso do Sul"));
		profileList.add(new Profile("Maria Helena", "maria_helena@gmail.com", "maryh", "Ceará"));
		profileList.add(new Profile("Luis Eduardo", "lu_eduardo@gmail.com", "dudu09", "Rio Grande do Sul"));
		profileList.add(new Profile("João Cleber", "joao@gmail.com", "clebso", "Alagoas"));
		profileList.add(new Profile("Kaique Freitas", "kaique_f@gmail.com", "kf1", "Ceará"));
		profileList.add(new Profile("Estela Gonsalves", "estela_gonsalves@gmail.com", "estel12", "Minas Gerais"));
		profileList.add(new Profile("Jefferson Antonieto", "jeff@gmail.com", "jeffson", "Paraíba"));
		profileList.add(new Profile("Rômulo Gomes", "romis@gmail.com", "romis", "Pernambuco"));
		profileList.add(new Profile("Nonato Lopes", "lopes@gmail.com", "nonato283", "Espírito Santo"));

		createCSV(profileList);
	}

	public static void createCSV(List<Profile> profileList) throws IOException{
		CsvMapper mapper = new CsvMapper();

		CsvSchema schema = mapper.schemaFor(Profile.class).withHeader();
		
		mapper.writer(schema).writeValue(new File("steambackend/output/data.csv"), profileList);
	}

	public static void main(String[] args) throws IOException{
		createProfiles();
	}
}