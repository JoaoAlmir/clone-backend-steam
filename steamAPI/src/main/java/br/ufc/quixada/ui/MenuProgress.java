package br.ufc.quixada.ui;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufc.quixada.dao.GameDAO;
import br.ufc.quixada.dao.ProfileDAO;
import br.ufc.quixada.dao.ProgressDAO;
import br.ufc.quixada.entity.Game;
import br.ufc.quixada.entity.Profile;
import br.ufc.quixada.entity.Progress;

import javax.swing.*;

import java.util.List;

@Slf4j
@Component
public class MenuProgress {
	@Autowired
	private ProgressDAO baseProgress;

    @Autowired
    private ProfileDAO baseProfile;  

    @Autowired
    private GameDAO baseGame;

	public void obterProgress(Progress pg) {


        int id_profile = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do perfil"));
		int id_game = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do jogo"));
        int progress_percent = Integer.parseInt(JOptionPane.showInputDialog("Digite o progresso em porcentagem"));
        int minutes_played = Integer.parseInt(JOptionPane.showInputDialog("Digite o tempo de jogo"));
        int trophy_quantity = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de troféus"));

        Profile pr = baseProfile.findById(id_profile).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        Game gm = baseGame.findById(id_game).orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

		pg.setProfile_progress(pr);
        pg.setGame_progress(gm);
        pg.setProgress_percent(progress_percent);
        pg.setMinutes_played(minutes_played);
        pg.setTrophy_quantity(trophy_quantity);
	
	}

	public void listaProgresss(List<Progress> progre) {
		StringBuilder listagem = new StringBuilder();
		for (Progress pg : progre) {
			listagem.append(pg).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum progress encontrado" : listagem);
	}

	public void listaProgress(Progress pfl) {
		JOptionPane.showMessageDialog(null, pfl == null ? "Nenhum progress encontrado" : pfl);
	}

	public void menu() {
		StringBuilder menu = new StringBuilder("Menu Progresss\n")
				.append("1 - Inserir\n")
				.append("2 - Atualizar por id\n")
				.append("3 - Remover por id\n")
				.append("4 - Exibir por id\n")
				.append("5 - Exibir todos\n")
                .append("0 - Menu anterior");
		String opcao = "x";
		do {
			try {
				Progress gm;
				Integer id;
				opcao = JOptionPane.showInputDialog(menu);
				switch (opcao) {
					case "1": // Inserir
						gm = new Progress();
						obterProgress(gm);
						baseProgress.save(gm);
						break;
					case "2": // Atualizar por id
						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id do progresso a ser alterado"));
						gm = baseProgress.findById(id).orElse(null);
						if (gm != null) {
							obterProgress(gm);
							baseProgress.save(gm);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado progresso com o id " + id);
						}
						break;
					case "3": // Remover por id
						id = Integer.valueOf(JOptionPane.showInputDialog("Digite o id do progresso a ser removido"));
						gm = baseProgress.findById(id).orElse(null);
						if (gm != null) {
							baseProgress.deleteById(gm.getId());
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado progresso com o id " + id);
						}
						break;
					case "4": // Exibir por id
						id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do progresso a ser exibido"));
						gm = baseProgress.findById(id).orElse(null);
						if (gm != null) {
							listaProgress(gm);
						} else {
							JOptionPane.showMessageDialog(null, "Não foi encontrado progresso com o id " + id);
						}
						break;
					case "5": // Exibir todos
						listaProgresss(baseProgress.findAll());
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
