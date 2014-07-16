package PesquisaSemaforo;

import java.io.File;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class Coordenador extends Thread {

	private Task task;
	private String nomeDoArquivo;
	private Semaphore semaphore;
	boolean alive;

	public Coordenador(String nomeDoArquivo, String caminho) {
		this.nomeDoArquivo = nomeDoArquivo;
		semaphore = new Semaphore(6);
		ArrayBlockingQueue<File> queue = new ArrayBlockingQueue<File>(4);
		task = new Task(semaphore, queue);
		new Thread(new Print(queue));
		new Thread(task).start();
		task.setTask(new File(caminho));
		alive = true;
	}

	public void run() {
		for (int i = 0; i < 12; ++i) {
			new Thread(new Pesquisador(nomeDoArquivo, task, semaphore)).start();
		}
	}
}
