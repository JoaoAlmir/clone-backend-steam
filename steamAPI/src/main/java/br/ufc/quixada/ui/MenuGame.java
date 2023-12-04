package br.ufc.quixada.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufc.quixada.dao.jpa.GameJPADAO;
import br.ufc.quixada.dao.mongo.GameMongoDAO;
import br.ufc.quixada.entity.Game;

import javax.swing.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class MenuGame {
	// @Autowired
	// private GameJPADAO baseGames;

	@Autowired
	private GameMongoDAO baseGames;

	public void obterGame(Game pfl) {
		String name = JOptionPane.showInputDialog("Name", pfl.getName());
		String description = JOptionPane.showInputDialog("Description", pfl.getDescription());
		Double review = Double.parseDouble(JOptionPane.showInputDialog("Review", pfl.getReview()));
		Double price = Double.parseDouble(JOptionPane.showInputDialog("Price", pfl.getPrice()));
		String developer = JOptionPane.showInputDialog("Developer", pfl.getDeveloper());
		String publisher = JOptionPane.showInputDialog("Publisher", pfl.getPublisher());
		LocalDate releaseDate = LocalDate.parse(JOptionPane.showInputDialog("Release Date", pfl.getReleaseDate()));
		String gender = JOptionPane.showInputDialog("Gender", pfl.getGender());

		pfl.setName(name);
		pfl.setGender(gender);
		pfl.setDescription(description);
		pfl.setReview(review);
		pfl.setPrice(price);
		pfl.setDeveloper(developer);
		pfl.setPublisher(publisher);
		pfl.setReleaseDate(releaseDate);
	}

	public void listaGames(List<Game> games) {
		StringBuilder listagem = new StringBuilder();
		for (Game gm : games) {
			listagem.append(gm).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum game encontrado" : listagem);
	}

	public void listaGame(Game pfl) {
		JOptionPane.showMessageDialog(null, pfl == null ? "Nenhum game encontrado" : pfl);
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Games\n")
				.append("1 - Inserir\n")
				.append("2 - Atualizar por id\n")
				.append("3 - Remover por id\n")
				.append("4 - Exibir por id\n")
				.append("5 - Exibir todos\n")
				.append("6 - Exibir todos contendo o nome\n")
				.append("7 - Exibir todos com o preço menor ou igual\n")
				.append("8 - Exibir todos com o desenvolvedor\n")
				.append("9 - Exibir todos com o publicador\n")
				.append("10 - Exibir todos contendo a descricao\n")
				.append("11 - Exibir jogos com um revew bom (>= 7)\n")
				.append("12 - Exibir jogos em um intervalo de preços\n")
				.append("0 - Menu anterior");
		String opcao = "x";
		do {
			try {
				Game gm;
				String id;
				opcao = JOptionPane.showInputDialog(menu);
				switch (opcao) {
					case "1": // Inserir
						gm = new Game();
						obterGame(gm);
						baseGames.save(gm);
						break;
					case "2": // Atualizar por id
						id = JOptionPane.showInputDialog("Digite o id do game a ser alterado");
						gm = baseGames.findById(id).orElse(null);
						if (gm != null) {
							obterGame(gm);
							baseGames.save(gm);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado game com o id " + id);
						}
						break;
					case "3": // Remover por id
						id = JOptionPane.showInputDialog("Digite o id do game a ser removido");
						gm = baseGames.findById(id).orElse(null);
						if (gm != null) {
							baseGames.deleteById(id);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado game com o id " + id);
						}
						break;
					case "4": // Exibir por id
						id = JOptionPane.showInputDialog("Digite o id do game a ser exibido");
						gm = baseGames.findById(id).orElse(null);
						if (gm != null) {
							listaGame(gm);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado game com o id " + id);
						}
						break;
					case "5": // Exibir todos
						listaGames(baseGames.findAll());
						break;
					case "6": // Exibir todos os jogos pelo nome
						String name = JOptionPane.showInputDialog("Digite o nome");
						listaGames(baseGames.findByNameIgnoreCaseContaining(name));
						break;
					case "7": // Exibir todos os jogos com preço menor ou igual
						Double price = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco"));
						listaGames(baseGames.findByPriceLessThan(price));
						break;
					case "8": // Exibir todos os jogos com o desenvolvedor
						String developer = JOptionPane.showInputDialog("Digite o desenvolvedor");
						listaGames(baseGames.findByDeveloperIgnoreCaseContaining(developer));
						break;
					case "9": // Exibir todos os jogos com o publicador
						String publisher = JOptionPane.showInputDialog("Digite o publicador");
						listaGames(baseGames.findByPublisherIgnoreCaseContaining(publisher));
						break;
					case "10": // Exibir todos os jogos contendo a descricao
						String description = JOptionPane.showInputDialog("Digite a descricao");
						listaGames(baseGames.findByDescriptionContaining(description));
						break;
					case "11": // Exibir todos os jogos melhores que 7
						listaGames(baseGames.findByReviewGreaterThan(7));
						break;
					case "12": // Exibir intervalo de precos
						double price1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco inicial"));
						double price2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o preco final"));
						listaGames(baseGames.findByPriceBetween(price1, price2));
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
