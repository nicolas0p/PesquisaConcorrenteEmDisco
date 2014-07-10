package Pesquisa;

import java.io.File;


public class Coordenador extends Thread {
	
	private Task task;
	private String nomeDoArquivo;
	
	public Coordenador(String nomeDoArquivo, String caminho) {
		this.nomeDoArquivo = nomeDoArquivo;
		task = new Task();
		task.setTask(new File(caminho));
		new Thread(task).start();
	}
	
	public void run() {
		while(!task.buscaFinalizada()) {
			if(task.size() > 0) {
				new Thread(new Pesquisador(nomeDoArquivo, task.getTask(), task)).start();
			}
		}
	}

}
