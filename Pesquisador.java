package PesquisaConcorrenteEmDisco;

import java.io.File;

public class Pesquisador implements Runnable {
	
	private Task task;
	private String nomeProcurado;
	private File caminho;
	
	public Pesquisador(String nomeDoArquivo, String caminho, Task task) {
		nomeProcurado = nomeDoArquivo;
		this.task = task;
		this.caminho = new File(caminho);
	}

	@Override
	public void run() {
		System.out.println("Pesquisador iniciado para o caminho " + caminho.getAbsolutePath());
		buscar();
	}
	
	private void buscar() {
		if(caminho.isDirectory()) {
			String[] arquivos = caminho.list();
			for(String atual : arquivos) {
				task.setTask(atual);
			}
		}
		else {
			if(caminho.getName().contains(nomeProcurado)) {
				System.out.println("Achado arquivo no local " + caminho.getAbsolutePath());
				task.arquivoAchado();
			}
		}
	}

}
