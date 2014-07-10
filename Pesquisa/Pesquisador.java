package Pesquisa;

import java.io.File;

public class Pesquisador implements Runnable {
	
	private Task task;
	private String nomeProcurado;
	private File caminho;
	
	public Pesquisador(String nomeDoArquivo, File caminho, Task task) {
		nomeProcurado = nomeDoArquivo;
		this.task = task;
		new Thread(task);
		this.caminho = caminho;
	}

	@Override
	public void run() {
		System.out.println("Pesquisador iniciado para o caminho " + caminho.getAbsolutePath());
		buscar();
	}
	
	private void buscar() {
		if(caminho.isDirectory()) {
			File[] arquivos = caminho.listFiles();
			for(File atual : arquivos) {
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
