package br.com.quixada.ufc.steambackend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import br.com.quixada.ufc.steambackend.model.Profile;

@SpringBootApplication
public class SteambackendApplication {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		SpringApplication.run(SteambackendApplication.class, args);

		List<Integer> library = new ArrayList<Integer>();
		library.add(0, 2);
		library.add(1, 4);
		List<Integer> wishlist = new ArrayList<Integer>();
		wishlist.add(0, 3);
		wishlist.add(1, 5);
		List<Integer> friends = new ArrayList<Integer>();
        friends.add(0, 3);
		friends.add(1, 5);
		
		Profile profile = new Profile(1, "Gibson Antoniento", "gib@gmail.com", "gibzin", "acre", library, wishlist, friends, 2);
		List<Profile> profileList = new ArrayList<Profile>();
		profileList.add(profile);
		// System.out.println(perfil.toString());

		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(Profile.class).withHeader();

		String csvData = mapper.writer(schema).writeValueAsString(profile);

		mapper.writer(schema).writeValue(new File("src/main/java/br/com/quixada/ufc/steambackend/output/csvExample.csv"), profileList);

		System.out.println(csvData);
	}

}
