package br.ufc.quixada.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import br.ufc.quixada.dao.jpa.GameJPADAO;
import br.ufc.quixada.dao.jpa.ProfileJPADAO;
import br.ufc.quixada.dao.mongo.GameMongoDAO;
import br.ufc.quixada.dao.mongo.ProfileMongoDAO;
import br.ufc.quixada.entity.Game;
import br.ufc.quixada.entity.Profile;

import javax.swing.*;
import java.util.List;

@Slf4j
@Component
public class MenuProfile {
	@Autowired
	private ProfileJPADAO baseProfiles;

	// @Autowired
	// private ProfileMongoDAO baseProfiles;

	@Autowired
	private GameJPADAO baseGames;

	// @Autowired
	// private GameMongoDAO baseGames;

	public void createProfile(Profile pfl) {

		String name = JOptionPane.showInputDialog("Name", pfl.getName());
		String email = JOptionPane.showInputDialog("Email", pfl.getEmail());
		String nickName = JOptionPane.showInputDialog("Nick Name", pfl.getNickName());
		String local = JOptionPane.showInputDialog("Local", pfl.getLocal());

		int level = Integer.parseInt(JOptionPane.showInputDialog("Level", pfl.getLevel()));
		pfl.setName(name);
		pfl.setEmail(email);
		pfl.setNickName(nickName);
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
			String id = JOptionPane.showInputDialog(menu);
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

	public void addFriend(Profile pfl) {
		try {

			List<Profile> profiles = baseProfiles.findAll();
			StringBuilder menu = new StringBuilder("Adicionar amigo\n");
			for (Profile p : profiles) {
				if (p.getId() != pfl.getId())
					menu.append(p.getId()).append(" - ").append(p.getName()).append("\n");
			}
			String id = JOptionPane.showInputDialog(menu);
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
			String id = JOptionPane.showInputDialog(menu);
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
				.append("5 - Exibir todos\n----------------\n")
				.append("6 - Adicionar amigo\n")
				.append("7 - Remover amigos do id\n")
				.append("8 - Exibir quantidade de amigos\n")
				.append("9 - Exibir os amigos do id que contem nome\n")
				.append("10 - Exibir amigos pelo nick\n")
				.append("11 - Exibir amigos pelo local\n")
				.append("12 - Exibir amigos pelo level maior ou igual\n----------------\n")
				.append("13 - Adicionar jogo a biblioteca\n")
				.append("14 - Remover jogo da biblioteca\n")
				.append("15 - Exibir quantidade de jogos na biblioteca\n")
				.append("0 - Menu anterior");
		String opcao = "x";
		do {
			try {
				Profile pfl;
				String id;
				opcao = JOptionPane.showInputDialog(menu);
				switch (opcao) {
					case "1": // Inserir
						pfl = new Profile();
						createProfile(pfl);
						baseProfiles.save(pfl);
						break;
					case "2": // Atualizar por id
						id = JOptionPane.showInputDialog("Digite o id do profile a ser alterado");
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							createProfile(pfl);
							baseProfiles.save(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "3": // Remover por id
						id = JOptionPane.showInputDialog("Digite o id do profile a ser removido");
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							baseProfiles.deleteById(id);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "4": // Exibir por id
						id = JOptionPane.showInputDialog("Digite o id do profile a ser exibido");
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
					case "6": // Adicionar amigo
						id = JOptionPane.showInputDialog("Digite o id do profile");
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							addFriend(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "7":// Remover amigo
						id = JOptionPane.showInputDialog("Digite o id do profile");
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							removeFriends(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "8": // Exibir quantidade de amigos
						id = JOptionPane.showInputDialog("Digite o id do profile");
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							JOptionPane.showMessageDialog(null, "Quantidade de amigos: " + pfl.getCountFriends());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "9": // Exibir todos os amigos que contém nome
						id = JOptionPane.showInputDialog("Digite o id do profile");
						String nameFriend = JOptionPane.showInputDialog("Digite o nome do amigo");
						nameFriend = "%" + nameFriend + "%";
						// ProfileLists(baseProfiles.findFriendsByNameIgnoreCaseContaining(id,
						// nameFriend));
						break;
					case "10": // Exibir amigos pelo nickname
						id = JOptionPane.showInputDialog("Digite o id do profile");
						String nickName = JOptionPane.showInputDialog("Digite o nickname");
						nickName = "%" + nickName + "%";
						// ProfileLists(baseProfiles.findFriendsByNickNameIgnoreCaseContaining(id,
						// nickName));
						break;
					case "11": // Exibir amigos pelo local
						id = JOptionPane.showInputDialog("Digite o id do profile");
						String local = JOptionPane.showInputDialog("Digite o local");
						// ProfileLists(baseProfiles.findFriendsByLocal(id, local));
						break;
					case "12": // Exibir amigos pelo level maior ou igual
						id = JOptionPane.showInputDialog("Digite o id do profile");
						int level = Integer.parseInt(JOptionPane.showInputDialog("Digite o level"));
						// ProfileLists(baseProfiles.findFriendsByLevelGreaterThan(id, level));
						break;
					case "13": // Adicionar jogo a biblioteca
						id = JOptionPane.showInputDialog("Digite o id do profile");
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							addGame(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "14": // Remover jogo da biblioteca
						id = JOptionPane.showInputDialog("Digite o id do profile");
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							removeGame(pfl);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado profile com o id " + id);
						}
						break;
					case "15": // Exibir quantidade de jogos na biblioteca
						id = JOptionPane.showInputDialog("Digite o id do profile");
						pfl = baseProfiles.findById(id).orElse(null);
						if (pfl != null) {
							JOptionPane.showMessageDialog(null, "Quantidade de jogos: " + pfl.getCountLib());
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
