package br.ufc.quixada.ui;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// import br.ufc.quixada.dao.GameDAO;
import br.ufc.quixada.dao.ProfileDAO;
// import br.ufc.quixada.entity.Game;
import br.ufc.quixada.entity.Profile;

import javax.swing.*;
import java.util.List;

@Slf4j
@Component
public class MenuProfile {
	@Autowired
	private ProfileDAO baseProfiles;

	// @Autowired
	// private GameDAO baseGames;

	public void createProfile(Profile pfl) {
		// List<Game> games = baseGames.findAll();

		String name = JOptionPane.showInputDialog("Name", pfl.getName());
		String email = JOptionPane.showInputDialog("Email", pfl.getEmail());
		String nick_name = JOptionPane.showInputDialog("Nick Name", pfl.getNick_name());
		String local = JOptionPane.showInputDialog("Local", pfl.getLocal());
		// List<Game> lib = JOptionPane.showInputDialog("Lib", pfl.getLib());
		// List<Game> wishlist = JOptionPane.showInputDialog("Wishlist", pfl.getWishlist());
		// List<Game> friends = JOptionPane.showInputDialog("Friends", pfl.getFriends());
		
		// List<Game> lib = (List<Game>)JOptionPane.showInputDialog(null, "Selecione um game", 
		// 	"Games", JOptionPane.PLAIN_MESSAGE, null, games.toArray(), pfl.getLib());
		int level = Integer.parseInt(JOptionPane.showInputDialog("Level", pfl.getLevel()));
		pfl.setName(name);
		pfl.setEmail(email);
		pfl.setNick_name(nick_name);
		pfl.setLocal(local);
		// pfl.setLib(lib);
		// pfl.setWishlist(wishlist);
		// pfl.setFriends(friends);
		pfl.setLevel(level);
	}

	public void listaProfiles(List<Profile> profiles) {
		StringBuilder listagem = new StringBuilder();
		for(Profile pfl : profiles) {
			listagem.append(pfl).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum profile encontrado" : listagem);
	}

	public void listaProfile(Profile pfl) {
		JOptionPane.showMessageDialog(null, pfl == null ? "Nenhum profile encontrado" : pfl);
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Profiles\n")
			.append("1 - Inserir\n")
			.append("2 - Atualizar por id\n")
			.append("3 - Remover por id\n")
			.append("4 - Exibir por id\n")
			.append("5 - Exibir todos\n")
			.append("6 - Exibir todos que contém determinado nome\n")
			.append("0 - Menu anterior");
		String opcao = "x";
		do {
			try {
				Profile pfl;
				Integer id;
				opcao = JOptionPane.showInputDialog(menu);
				switch (opcao) {
					case "1":     // Inserir
						pfl = new Profile();
						createProfile(pfl);
						baseProfiles.save(pfl);
						break;
					case "2":     // Atualizar por id
						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id do profile a ser alterado"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							createProfile(pfl);
							baseProfiles.save(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "3":     // Remover por id
						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id do profile a ser removido"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							baseProfiles.deleteById(pfl.getId());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "4":     // Exibir por id
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile a ser exibido"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							listaProfile(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "5":     // Exibir todos
						listaProfiles(baseProfiles.findAll());
						break;
					// case "6":     // Exibir por determinado nome
						// String name = JOptionPane.showInputDialog("Digite o nome do profile a ser exibido");
					// 	break;
					case "0":     // Sair
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida");
						break;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}

		} while(!opcao.equals("0"));
	}
}
