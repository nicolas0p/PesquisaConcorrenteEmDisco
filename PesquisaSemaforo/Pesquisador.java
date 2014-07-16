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
		this.semaphore = semaphore;
		this.task = task;
	}

	@Override
	public void run() {
		buscar();
	}

	private void buscar() {
		while (!task.buscaFinalizada()) {
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
			}
			novaBusca();
			System.out.println("Pesquisador iniciado para o caminho " + caminho.getAbsolutePath());
			if (caminho.isDirectory()) {
				File[] arquivos = caminho.listFiles();
				if (arquivos != null) {
					for (File atual : arquivos) {
						task.setTask(atual);
					}
				}
			}

			else {
				if (caminho.getName().contains(nomeProcurado)) {
					System.out.println("Achado arquivo no local "
							+ caminho.getAbsolutePath());
					task.arquivoAchado(caminho);
				}
			}

			semaphore.release();
		}
	}

	private void novaBusca() {
		while (task.size() < 1) {
			try {
				caminho = task.getTask();
			} catch (Exception e1) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
			}

		}
	}

}
