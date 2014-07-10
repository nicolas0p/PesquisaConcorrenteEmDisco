package PesquisaSemaforo;

import java.io.File;
import java.util.concurrent.Semaphore;

public class Pesquisador implements Runnable {
	
	private Task task;
	private String nomeProcurado;
	private File caminho;
	private Semaphore semaphore;
	
	public Pesquisador(String nomeDoArquivo, Task task, Semaphore semaphore) {
		nomeProcurado = nomeDoArquivo;
		this.task = task;
		new Thread(task).start();
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		buscar();
	}
	
	private void buscar() {
		while(!task.buscaFinalizada()) {
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
			}
			
			novaBusca();
			System.out.println("Pesquisador iniciado para o caminho " + caminho.getAbsolutePath());
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
			
			semaphore.release();
		}
	}
	
	private void novaBusca() {
		while(task.size() < 1) {
			caminho = task.getTask();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}

}
