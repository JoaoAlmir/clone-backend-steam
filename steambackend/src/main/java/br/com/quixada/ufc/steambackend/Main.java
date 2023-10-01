package br.com.quixada.ufc.steambackend;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import br.com.quixada.ufc.steambackend.view.Menu;

public class Main {
  public static void main(String[] args) throws StreamWriteException, DatabindException, IOException, NumberFormatException, NoSuchAlgorithmException {
		SpringApplication.run(Main.class, args);
	
    Menu menu = new Menu();
    menu.start();
    return;
  }
}