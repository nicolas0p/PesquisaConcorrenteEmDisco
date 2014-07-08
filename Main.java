package PesquisaConcorrenteEmDisco;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		String nomeDoArquivo = JOptionPane.showInputDialog("Digite o nome, ou parte dele, do arquivo que deseja encontrar");
		String diretorioDePesquisa = JOptionPane.showInputDialog("Digite onde deseja que a pesquisa seja feita");
		Coordenador coord = new Coordenador(nomeDoArquivo, diretorioDePesquisa);
		coord.start();
	}

}
