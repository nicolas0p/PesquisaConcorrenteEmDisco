package PesquisaSemaforo;

import java.io.File;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Coordenador extends Thread {

	private Task task;
	private String nomeDoArquivo;
	private Semaphore semaphore;
	boolean alive;

	public Coordenador(String nomeDoArquivo, String caminho) {
		this.nomeDoArquivo = nomeDoArquivo;
		semaphore = new Semaphore(6);
		task = new Task(this, semaphore);
		new Thread(task).start();
		task.setTask(new File(caminho));
		alive = true;
		
	}

	public void run() {
		for (int i = 0; i < 12; ++i) {
			new Thread(new Pesquisador(nomeDoArquivo, task, semaphore)).start();
		}
		while(alive) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	public void resultadoDaPesquisa(List<File> encontrados) {
		System.out.println("==========================================PESQUISA FINALIZADA============================================");
		for(File actual : encontrados) {
			System.out.println("Arquivo encontrado em:" + actual.getAbsolutePath());
		}
		alive = false;
	}
}
