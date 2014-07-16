package PesquisaSemaforo;

import java.io.File;
import java.util.concurrent.Semaphore;

public class Coordenador extends Thread {

	private Task task;
	private String nomeDoArquivo;
	private String caminho;

	public Coordenador(String nomeDoArquivo, String caminho) {
		this.nomeDoArquivo = nomeDoArquivo;
		this.caminho = caminho;
		task.setTask(new File(caminho));
	}

	public void run() {
		Semaphore semaphore = new Semaphore(6);
		Task task = new Task(semaphore);
		task.setTask(new File(caminho));
		new Thread(task).start();
		for (int i = 0; i < 12; ++i) {
			new Thread(new Pesquisador(nomeDoArquivo, task, semaphore)).start();
		}
	}
}
