package br.ufc.quixada.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import br.ufc.quixada.dao.GameDAO;
import br.ufc.quixada.dao.ProfileDAO;
import br.ufc.quixada.entity.Game;
import br.ufc.quixada.entity.Profile;
import jakarta.transaction.Transactional;

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

		String name = JOptionPane.showInputDialog("Name", pfl.getName());
		String email = JOptionPane.showInputDialog("Email", pfl.getEmail());
		String nick_name = JOptionPane.showInputDialog("Nick Name", pfl.getNick_name());
		String local = JOptionPane.showInputDialog("Local", pfl.getLocal());

		int level = Integer.parseInt(JOptionPane.showInputDialog("Level", pfl.getLevel()));
		pfl.setName(name);
		pfl.setEmail(email);
		pfl.setNick_name(nick_name);
		pfl.setLocal(local);
		pfl.setLevel(level);
	}

	public void addGame(Profile pfl) {
		try {

			List<Game> games = baseGames.findAll();
			StringBuilder menu = new StringBuilder("Adicionar jogo\n");
			for (Game gm : games) {
				menu.append(gm.getId()).append(" - ").append(gm.getName()).append("\n");
			}
			Integer id = Integer.valueOf(JOptionPane.showInputDialog(menu));
			Game gm = baseGames.findById(id).orElse(null);
			if (gm != null) {
				pfl.getLib().add(gm);
				baseProfiles.save(pfl);
			} else {
				JOptionPane.showMessageDialog(null, "Não foi encontrado jogo com o id " + id);
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(null, "Esse perfil já possui esse jogo");
		}

	}

	public void addWishList(Profile pfl) {
		try {

			List<Game> games = baseGames.findAll();
			StringBuilder menu = new StringBuilder("Adicionar jogo\n");
			for (Game gm : games) {
				menu.append(gm.getId()).append(" - ").append(gm.getName()).append("\n");
			}
			Integer id = Integer.valueOf(JOptionPane.showInputDialog(menu));
			Game gm = baseGames.findById(id).orElse(null);
			if (gm != null) {
				pfl.getWishlist().add(gm);
				baseProfiles.save(pfl);
			} else {
				JOptionPane.showMessageDialog(null, "Não foi encontrado jogo com o id " + id);
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(null, "Esse perfil já possui esse jogo");
		}

	}

	public void addFriend(Profile pfl) {
		try {

			List<Profile> profiles = baseProfiles.findAll();
			StringBuilder menu = new StringBuilder("Adicionar amigo\n");
			for (Profile p : profiles) {
				if (p.getId() != pfl.getId())
					menu.append(p.getId()).append(" - ").append(p.getName()).append("\n");
			}
			Integer id = Integer.valueOf(JOptionPane.showInputDialog(menu));
			Profile p = baseProfiles.findById(id).orElse(null);
			if (p != null) {
				if (p.getId() == pfl.getId()) {
					JOptionPane.showMessageDialog(null, "Não é possível adicionar o próprio perfil");
				} else {
					pfl.getFriends().add(p);
					baseProfiles.save(pfl);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Não foi encontrado amigo com o id " + id);
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(null, "Esse perfil já possui esse amigo");
		}

	}

	public void removeGame(Profile pfl) {
		try {
			List<Game> games = baseGames.findAll();
			StringBuilder menu = new StringBuilder("Remover jogo\n");
			for (Game gm : games) {
				if (pfl.getLib().contains(gm)) {
					menu.append(gm.getId()).append(" - ").append(gm.getName()).append("\n");
				}
			}
			Integer id = Integer.valueOf(JOptionPane.showInputDialog(menu));
			Game gm = baseGames.findById(id).orElse(null);
			if (gm != null) {
				pfl.getLib().remove(gm);
				baseProfiles.save(pfl);
			} else {
				JOptionPane.showMessageDialog(null, "Não foi encontrado jogo com o id " + id);
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(null, "Erro na remoção do jogo");
		}
	}

	public void removeWishList(Profile pfl) {
		try {
			List<Game> games = baseGames.findAll();
			StringBuilder menu = new StringBuilder("Remover jogo\n");
			for (Game gm : games) {
				if (pfl.getWishlist().contains(gm)) {
					menu.append(gm.getId()).append(" - ").append(gm.getName()).append("\n");
				}
			}
			Integer id = Integer.valueOf(JOptionPane.showInputDialog(menu));
			Game gm = baseGames.findById(id).orElse(null);
			if (gm != null) {
				pfl.getWishlist().remove(gm);
				baseProfiles.save(pfl);
			} else {
				JOptionPane.showMessageDialog(null, "Não foi encontrado jogo com o id " + id);
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(null, "Erro na remoção do jogo");
		}
	}

	public void removeFriends(Profile pfl) {
		try {
			List<Profile> profiles = baseProfiles.findAll();
			StringBuilder menu = new StringBuilder("Remover amigo\n");
			for (Profile p : profiles) {

				if (pfl.getFriends().contains(p)) {
					menu.append(p.getId()).append(" - ").append(p.getName()).append("\n");
				}
			}
			pfl.getFriends().clear();
			baseProfiles.save(pfl);

		} catch (InvalidDataAccessApiUsageException e) {
			log.error(e.getMessage(), e);
			JOptionPane.showMessageDialog(null, "Erro na remoção do amigo");
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
				.append("7 - Exibir games da biblioteca pelo preço menor ou igual\n")
				.append("8 - Exibir games da wishlist do profile pelo gênero\n")
				.append("9 - Exibir amigos pelo nickname\n")
				.append("10 - Exibir amigos pelo local\n")
				.append("11 - Exibir amigos pelo level maior ou igual\n")
				.append("12 - Exibir quantidade de jogos na lista de favoritos\n")
				.append("13 - Exibir quantidade de amigos\n")
				.append("14 - Exibir quantidade de jogos na biblioteca\n")
				.append("15 - Adicionar jogo a biblioteca\n")
				.append("16 - Adicionar jogo a wishlist\n")
				.append("17 - Adicionar amigo\n")
				.append("18 - Remover jogo da biblioteca\n")
				.append("19 - Remover jogo da wishlist\n")
				.append("20 - Remover todos os amigos pelo id\n")
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
							baseProfiles.removeProfileComplete(id);
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
					case "6": // Exibir todos os amigos que contém nome
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						String name_friend = JOptionPane.showInputDialog("Digite o nome do amigo");
						name_friend = "%" + name_friend + "%";
						ProfileLists(baseProfiles.getAllFriendsByName(id, name_friend));
						break;
					case "7": // Exibir games da biblioteca pelo preço menor ou igual
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						Double price = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco"));
						GameList(baseGames.getAllWishListGamesByPriceLess(id, price));
						break;
					case "8": // Exibir games da wishlist do profile pelo gênero
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						String gender = JOptionPane.showInputDialog("Digite o genero");
						GameList(baseGames.getAllWishListGamesByGender(id, gender));
						break;
					case "9": // Exibir amigos pelo nickname
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						String nick_name = JOptionPane.showInputDialog("Digite o nickname");
						nick_name = "%" + nick_name + "%";
						ProfileLists(baseProfiles.getAllFriendsNickName(id, nick_name));
						break;
					case "10": // Exibir amigos pelo local
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						String local = JOptionPane.showInputDialog("Digite o local");
						ProfileLists(baseProfiles.getAllFriendsByLocal(id, local));
						break;
					case "11": // Exibir amigos pelo level maior ou igual
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						int level = Integer.parseInt(JOptionPane.showInputDialog("Digite o level"));
						ProfileLists(baseProfiles.getAllFriendsByLevelMore(id, level));
						break;
					case "12": // Exibir quantidade de jogos na lista de favoritos
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							JOptionPane.showMessageDialog(null,
									"Quantidade de jogos na lista de favoritos: " + pfl.getCountWishList());
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
					case "16": // Adicionar jogo a wishlist
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							addWishList(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "17": // Adicionar amigo
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							addFriend(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "18": // Remover jogo da biblioteca
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							removeGame(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "19": // Remover jogo da wishlist
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							removeWishList(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "20":// Remover amigo
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do profile"));
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							removeFriends(pfl);
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
