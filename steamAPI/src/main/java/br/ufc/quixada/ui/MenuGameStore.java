// package br.ufc.quixada.ui;


// import lombok.extern.slf4j.Slf4j;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import br.ufc.quixada.dao.GameStoreDAO;
// import br.ufc.quixada.entity.GameStore;

// import javax.swing.*;

// import java.time.LocalDate;
// import java.util.List;

// @Slf4j
// @Component
// public class MenuGameStore {
// 	@Autowired
// 	private GameStoreDAO baseGameStores;

// 	public void obterGameStore(GameStore pfl) {
// 		String name = JOptionPane.showInputDialog("Name", pfl.getName());
//         String description = JOptionPane.showInputDialog("Description", pfl.getDescription());
//         Double review = Double.parseDouble(JOptionPane.showInputDialog("Review", pfl.getReview()));
//         Double price = Double.parseDouble(JOptionPane.showInputDialog("Price", pfl.getPrice())) ;
//         String developer = JOptionPane.showInputDialog("Developer", pfl.getDeveloper());
//         String publisher = JOptionPane.showInputDialog("Publisher", pfl.getPublisher());
//         LocalDate release_date = LocalDate.parse(JOptionPane.showInputDialog("Release Date", pfl.getRelease_date()));
		
//         pfl.setName(name);
//         pfl.setDescription(description);
//         pfl.setReview(review);
//         pfl.setPrice(price);
//         pfl.setDeveloper(developer);
//         pfl.setPublisher(publisher);
//         pfl.setRelease_date(release_date);
        
// 	}

// 	public void listaGameStores(List<GameStore> gameStoreStores) {
// 		StringBuilder listagem = new StringBuilder();
// 		for(GameStore pfl : gameStoreStores) {
// 			listagem.append(pfl).append("\n");
// 		}
// 		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum gameStoreStore encontrado" : listagem);
// 	}

// 	public void listaGameStore(GameStore pfl) {
// 		JOptionPane.showMessageDialog(null, pfl == null ? "Nenhum gameStoreStore encontrado" : pfl);
// 	}

// 	public void menu() {
// 		StringBuilder menu = new StringBuilder("Menu GameStores\n")
// 			.append("1 - Inserir\n")
// 			.append("2 - Atualizar por id\n")
// 			.append("3 - Remover por id\n")
// 			.append("4 - Exibir por id\n")
// 			.append("5 - Exibir todos\n")
// 			.append("6 - Exibir todos que contém determinado nome\n")
// 			.append("0 - Menu anterior");
// 		char opcao = 'x';
// 		do {
// 			try {
// 				GameStore pfl;
// 				Integer id;
// 				opcao = JOptionPane.showInputDialog(menu).charAt(0);
// 				switch (opcao) {
// 					case '1':     // Mostrar todos os jogos
						
// 						break;
// 					case '2':     // Atualizar por id
// 						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id do gameStoreStore a ser alterado"));
// 						pfl = baseGameStores.findById(id).orElse(null);
// 						if (pfl != null) {
// 							obterGameStore(pfl);
// 							baseGameStores.save(pfl);
// 						} else {
// 							JOptionPane.showMessageDialog(null, "Não foi encontrado gameStoreStore com o id " + id);
// 						}
// 						break;
// 					case '3':     // Remover por id
// 						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id do gameStoreStore a ser removido"));
// 						pfl = baseGameStores.findById(id).orElse(null);
// 						if (pfl != null) {
// 							baseGameStores.deleteById(pfl.getId());
// 						} else {
// 							JOptionPane.showMessageDialog(null, "Não foi encontrado gameStoreStore com o id " + id);
// 						}
// 						break;
// 					case '4':     // Exibir por id
// 						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do gameStoreStore a ser exibido"));
// 						pfl = baseGameStores.findById(id).orElse(null);
// 						if (pfl != null) {
// 							listaGameStore(pfl);
// 						} else {
// 							JOptionPane.showMessageDialog(null, "Não foi encontrado gameStoreStore com o id " + id);
// 						}
// 						break;
// 					case '5':     // Exibir todos
// 						listaGameStores(baseGameStores.findAll());
// 						break;
// 					case '0':     // Sair
// 						break;
// 					default:
// 						JOptionPane.showMessageDialog(null, "Opção Inválida");
// 						break;
// 				}
// 			} catch (Exception e) {
// 				log.error(e.getMessage(), e);
// 				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
// 			}

// 		} while(opcao != '0');
// 	}
// }
