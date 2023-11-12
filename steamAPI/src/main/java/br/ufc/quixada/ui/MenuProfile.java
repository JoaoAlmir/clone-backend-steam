package br.ufc.quixada.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufc.quixada.dao.GameDAO;
import br.ufc.quixada.dao.ProfileDAO;
import br.ufc.quixada.entity.Game;
import br.ufc.quixada.entity.Profile;

import javax.swing.*;
import java.util.List;

@Slf4j
@Component
public class MenuProfile {
	@Autowired
	private ProfileDAO baseProfiles;

	@Autowired
	private GameDAO baseGames;

	public void createProfile(Profile pfl) {
		// List<Game> games = baseGames.findAll();

		String name = JOptionPane.showInputDialog("Name", pfl.getName());
		String email = JOptionPane.showInputDialog("Email", pfl.getEmail());
		String nick_name = JOptionPane.showInputDialog("Nick Name", pfl.getNick_name());
		String local = JOptionPane.showInputDialog("Local", pfl.getLocal());
		// List<Game> lib = JOptionPane.showInputDialog("Lib", pfl.getLib());
		// List<Game> wishlist = JOptionPane.showInputDialog("Wishlist",
		// pfl.getWishlist());
		// List<Game> friends = JOptionPane.showInputDialog("Friends",
		// pfl.getFriends());

		
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

	public void addGame(Profile pfl) {
		List<Game> games = baseGames.findAll();
		Integer id = (Integer) JOptionPane.showInputDialog(null, "Selecione o game", "Game", JOptionPane.QUESTION_MESSAGE, null, games.toArray(), games.get(0));
		if (id != null) {
			
		}
	}

	public void ProfileLists(List<Profile> profiles) {
		StringBuilder listagem = new StringBuilder();
		for (Profile pfl : profiles) {
			listagem.append(pfl).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum profile encontrado" : listagem);
	}

	public void GameList(List<Game> game) {
		StringBuilder listagem = new StringBuilder();
		for (Game gm : game) {
			listagem.append(gm).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum game encontrado" : listagem);
	}

	public void profileList(Profile pfl) {
		JOptionPane.showMessageDialog(null, pfl == null ? "Nenhum profile encontrado" : pfl);
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Profiles\n")
				.append("1 - Inserir\n")
				.append("2 - Atualizar por id\n")
				.append("3 - Remover por id\n")
				.append("4 - Exibir por id\n")
				.append("5 - Exibir todos\n")
				.append("6 - Exibir os amigos pelo nome\n")
				.append("7 - Exibir games da biblioteca pelo preço\n")
				// .append("8 - Exibir games da biblioteca pelo gênero\n")
				// .append("9 - Exibir amigos pelo nickname\n")
				// .append("10 - Exibir amigos pelo local\n")
				// .append("11 - Exibir amigos pelo level\n")
				.append("12 - Exibir quantidade de jogos na lista de favoritos\n")
				.append("13 - Exibir quantidade de amigos\n")
				.append("14 - Exibir quantidade de jogos na biblioteca\n")
				.append("15 - Adicionar jogo\n")
				.append("0 - Menu anterior");
		String opcao = "x";
		do {
			try {
				Profile pfl;
				Integer id;
				opcao = JOptionPane.showInputDialog(menu);
				switch (opcao) {
					case "1": // Inserir
						pfl = new Profile();
						createProfile(pfl);
						baseProfiles.save(pfl);
						break;
					case "2": // Atualizar por id
						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id do profile a ser alterado"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							createProfile(pfl);
							baseProfiles.save(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "3": // Remover por id
						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id do profile a ser removido"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							baseProfiles.deleteById(pfl.getId());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "4": // Exibir por id
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile a ser exibido"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							profileList(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "5": // Exibir todos
						ProfileLists(baseProfiles.findAll());
						break;
					case "6": // Exibir todos que contém determinado nome
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						String name_friend = JOptionPane.showInputDialog("Digite o nome do amigo");
						ProfileLists(baseProfiles.getAllFriendsByName(id, name_friend));
						break;
					case "7": // Exibir games da biblioteca pelo nome
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						Double price = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco"));
						GameList(baseProfiles.getAllWishListGamesByPrice(id, price));
						break;
					case "8": // Exibir games da biblioteca pelo gênero
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						// String gender = JOptionPane.showInputDialog("Digite o genero");
						// GameList(baseProfiles.getAllWishListGamesByGender(id, gender));
						break;
					case "9": // Exibir amigos pelo nickname
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						// String nick_name = JOptionPane.showInputDialog("Digite o nickname");
						// ProfileLists(baseProfiles.getAllFriendsNickName(id, nick_name));
						break;
					case "10": // Exibir amigos pelo local
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						// String local = JOptionPane.showInputDialog("Digite o local");
						// ProfileLists(baseProfiles.getAllFriendsByLocal(id, local));
						break;
					case "11": // Exibir amigos pelo level
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						// int level = Integer.parseInt(JOptionPane.showInputDialog("Digite o level"));
						// ProfileLists(baseProfiles.getAllFriendsByLevel(id, level));
						break;
					case "12": // Exibir quantidade de jogos na lista de favoritos
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							JOptionPane.showMessageDialog(null, "Quantidade de jogos na lista de favoritos: " + pfl.getCountWishList());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "13": // Exibir quantidade de amigos
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							JOptionPane.showMessageDialog(null, "Quantidade de amigos: " + pfl.getCountFriends());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "14": // Exibir quantidade de jogos na biblioteca
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							JOptionPane.showMessageDialog(null, "Quantidade de jogos: " + pfl.getCountLib());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "15": // Adicionar jogo a biblioteca
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							addGame(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "0": // Sair
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção Inválida");
						break;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}

		} while (!opcao.equals("0"));
	}
}