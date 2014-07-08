package PesquisaConcorrenteEmDisco;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		//String nomeDoArquivo = JOptionPane.showInputDialog("Digite o nome, ou parte dele, do arquivo que deseja encontrar");
		//String diretorioDePesquisa = JOptionPane.showInputDialog("Digite onde deseja que a pesquisa seja feita");
		String nomeDoArquivo = "Pregadores kadosh 2014 doc.doc";
		String diretorioDePesquisa = "/home/nicolas/Downloads";
		Coordenador coord = new Coordenador(nomeDoArquivo, diretorioDePesquisa);
		coord.start();
	}

}
