package PesquisaSemaforo;

import java.io.File;
import java.util.concurrent.Semaphore;

public class Coordenador extends Thread {

	private Task task;
	private String nomeDoArquivo;
	private Semaphore semaphore;

	public Coordenador(String nomeDoArquivo, String caminho) {
		this.nomeDoArquivo = nomeDoArquivo;
		task = new Task();
		new Thread(task).start();
		task.setTask(new File(caminho));
		semaphore = new Semaphore(6);
	}

	public void run() {
		for (int i = 0; i < 12; ++i) {
			new Thread(new Pesquisador(nomeDoArquivo, task, semaphore)).start();
		}
	}
}
