package br.ufc.quixada.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufc.quixada.dao.jpa.GameJPADAO;
import br.ufc.quixada.dao.jpa.ProfileJPADAO;
import br.ufc.quixada.dao.jpa.ProgressJPADAO;
import br.ufc.quixada.dao.mongo.GameMongoDAO;
import br.ufc.quixada.dao.mongo.ProfileMongoDAO;
import br.ufc.quixada.dao.mongo.ProgressMongoDAO;
import br.ufc.quixada.entity.Game;
import br.ufc.quixada.entity.Profile;
import br.ufc.quixada.entity.Progress;

import javax.swing.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class MenuProgress {
	// @Autowired
	// private ProgressJPADAO baseProgress;

	@Autowired
	private ProgressMongoDAO baseProgress;

	// @Autowired
	// private ProfileJPADAO baseProfile;

	@Autowired
	private ProfileMongoDAO baseProfile;

	// @Autowired
	// private GameJPADAO baseGame;

	@Autowired
	private GameMongoDAO baseGame;

	public void obterProgress(Progress pg) {
		String idProfile = JOptionPane.showInputDialog("Digite o id do perfil");
		String idGame = JOptionPane.showInputDialog("Digite o id do jogo");
		int progressPercent = Integer.parseInt(JOptionPane.showInputDialog("Digite o progresso em porcentagem"));
		int minutesPlayed = Integer.parseInt(JOptionPane.showInputDialog("Digite os minutos jogados"));
		int trophyQuantity = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de troféus"));

		Profile pr = baseProfile.findById(idProfile).orElse(null);
		Game gm = baseGame.findById(idGame).orElse(null);

		pg.setProfile(pr);
		pg.setGame(gm);
		pg.setProgressPercent(progressPercent);
		pg.setMinutesPlayed(minutesPlayed);
		pg.setTrophyQuantity(trophyQuantity);
	}

	public void listAllProgress(List<Progress> progre) {
		StringBuilder listagem = new StringBuilder();
		for (Progress pg : progre) {
			listagem.append(pg).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum progress encontrado" : listagem);
	}

	public void listAllProgressSimple(List<Progress> progre) {
		StringBuilder listagem = new StringBuilder();
		for (Progress pg : progre) {
			listagem.append(pg.getProfile().getName()).append("(" + pg.getProfile().getId() + ")").append(" - ")
					.append(pg.getGame().getName()).append("(" + pg.getGame().getId() + ")").append(" - ")
					.append(pg.getProgressPercent()).append("% - ")
					.append(pg.getTrophyQuantity()).append(" Troféus").append(" - ").append(pg.getMinutesPlayed())
					.append(" minutos jogados\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum progress encontrado" : listagem);
	}

	public void listProgress(Progress pfl) {
		JOptionPane.showMessageDialog(null, pfl == null ? "Nenhum progress encontrado" : pfl);
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Progresss\n")
				.append("1 - Inserir\n")
				.append("2 - Atualizar por id\n")
				.append("3 - Remover por id\n")
				.append("4 - Exibir por id\n")
				.append("5 - Exibir todos\n")
				.append("6 - Exibir todos os perfis com jogos 100% completos\n")
				.append("7 - Exibir todos sem nenhum trofeu\n")
				.append("8 - Exibir todos progressos pelo id do jogador\n")
				.append("9 - Exibir todos progressos pelo id do jogo\n")
				.append("8 - Exibir todos progressos com minutos maiores ou iguais\n")
				.append("0 - Menu anterior");
		String opcao = "x";
		do {
			try {
				Progress gm;
				String id;
				opcao = JOptionPane.showInputDialog(menu);
				switch (opcao) {
					case "1": // Inserir
						gm = new Progress();
						obterProgress(gm);
						baseProgress.save(gm);
						break;
					case "2": // Atualizar por id
						id = JOptionPane.showInputDialog("Digite o id do progresso a ser alterado");
						gm = baseProgress.findById(id).orElse(null);
						if (gm != null) {
							obterProgress(gm);
							baseProgress.save(gm);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado progresso com o id " + id);
						}
						break;
					case "3": // Remover por id
						id = JOptionPane.showInputDialog("Digite o id do progresso a ser removido");
						gm = baseProgress.findById(id).orElse(null);
						if (gm != null) {
							baseProgress.deleteById(gm.getId());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado progresso com o id " + id);
						}
						break;
					case "4": // Exibir por id
						id = JOptionPane.showInputDialog("Digite o id do progresso");
						gm = baseProgress.findById(id).orElse(null);
						if (gm != null) {
							listProgress(gm);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado progresso com o id " + id);
						}
						break;
					case "5": // Exibir todos
						listAllProgress(baseProgress.findAll());
						break;
					case "6": // Exibir todos os perfis com jogos 100% completos
						listAllProgressSimple(baseProgress.findByProgressPercent(100));
						break;
					case "7": // Exibir todos sem nenhum trofeu
						listAllProgressSimple(baseProgress.findByTrophyQuantity(0));
						break;
					case "8": // Exibir todos progressos pelo id do jogador
					String idPf = JOptionPane.showInputDialog("Digite o id do profile");
					listAllProgressSimple(baseProgress.findByProfile_Id(idPf));
					break;

					case "9": // Exibir todos progressos pelo id do jogo
					String idGm = JOptionPane.showInputDialog("Digite o id do game");
					listAllProgressSimple(baseProgress.findByGame_Id(idGm));
					break;

					case "10":
						Integer minutes = Integer.parseInt(JOptionPane.showInputDialog("Digite o tempo de jogo"));
						listAllProgressSimple(baseProgress.findByMinutesPlayedGreaterThan(minutes));
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
