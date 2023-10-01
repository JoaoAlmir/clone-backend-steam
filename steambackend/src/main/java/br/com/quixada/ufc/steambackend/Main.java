package br.com.quixada.ufc.steambackend;

import java.io.IOException;

import org.springframework.boot.SpringApplication;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public class Main {
  public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		SpringApplication.run(StartData.class, args);
	
    Menu menu = new Menu();
    menu.start();
    return;
  }
}