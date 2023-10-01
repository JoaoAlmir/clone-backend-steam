package br.com.quixada.ufc.steambackend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.quixada.ufc.steambackend.controller.ProfileController;

public class Menu {
  ProfileController control = new ProfileController();
  InputStream is = System.in;
  InputStreamReader isr = new InputStreamReader(is);
  BufferedReader br = new BufferedReader(isr);

  public void insertProfile() throws IOException{
    System.out.println("----------Cadastrar perfil----------");
    System.out.print("Digite seu nome: ");
    String name = br.readLine();
    System.out.print("Digite seu email: ");
    String email = br.readLine();
    System.out.print("Digite seu nickname: ");
    String nickname = br.readLine();
    System.out.print("Digite seu estado: ");
    String location = br.readLine();
    System.out.println("---------------------------------");
    
    Boolean verify = control.insertProfile(name, email, nickname, location);

    if(verify){
      System.out.println("Perfil inserido com sucesso.");
    }else{
      System.out.println("Houve um erro, realize uma nova tentativa.");
    }

    start();
  }

  public void sizeData() throws IOException{
    System.out.println("A quantidade de perfis é: " + 
    control.sizeData());

    start();
  }

  public void convertToJSON() throws NumberFormatException, IOException{
    Boolean verify = control.convertToJSON();

    if(verify){
      System.out.println("Conversão para JSON realizada com sucesso.");
    }else{
      System.out.println("Houve um erro, realize uma nova tentativa.");
    }
    
    start();
  }

  public void convertToXML() throws NumberFormatException, IOException{
    Boolean verify = control.convertToXML();

    if(verify){
      System.out.println("Conversão para XML realizada com sucesso.");
    }else{
      System.out.println("Houve um erro, realize uma nova tentativa.");
    }
    
    start();
  }

  public void compressData() throws NumberFormatException, IOException{
    Boolean verify = control.compressData();

    if(verify){
      System.out.println("Dados comprimidos com sucesso.");
    }else{
      System.out.println("Houve um erro, realize uma nova tentativa.");
    }
    
    start();
  }

  public void showHash() throws NumberFormatException, IOException{
    System.out.println("O hash dos dados é: " + control.showHash());

    start();
  }

  public void start() throws NumberFormatException, IOException{
    System.out.println("----------Menu Inicial----------");
    System.out.println("\t1 - Cadastrar perfil");
    System.out.println("\t2 - Quantidade de perfis");
    System.out.println("\t3 - Converter os dados em JSON");
    System.out.println("\t4 - Converter os dados em XML");
    System.out.println("\t5 - Compactar os dados");
    System.out.println("\t6 - Mostrar hash SHA256 dos dados");
    System.out.println("\t7 - Sair");
    System.out.println("---------------------------------\n");

    Boolean exec = true;

    do{
      System.out.print("Digite a opção desejada: ");
      Integer option = Integer.parseInt(br.readLine());

      switch(option){
        case 1: insertProfile(); break;
        case 2: sizeData(); break;
        case 3: convertToJSON(); break;
        case 4: convertToXML(); break;
        case 5: compressData(); break;
        case 6: showHash(); break;
        case 7: exec = false; break;
      }
    }while(exec);
  }
}