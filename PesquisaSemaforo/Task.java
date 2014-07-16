package PesquisaSemaforo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Task implements Runnable {

	private List<File> caminhos;
	private List<File> encontrados;
	private boolean buscaFinalizada;
	private Semaphore semaforo;

	public Task(Semaphore semaforo) {
		caminhos = new ArrayList<File>();
		encontrados = new ArrayList<File>();
		buscaFinalizada = false;
		this.semaforo = semaforo;
	}

	public synchronized File getTask() throws Exception {
		if(caminhos.size() == 0)
			throw new Exception();
		File ret = caminhos.remove(0);
		return ret;
	}

	public synchronized void setTask(File adicionar) {
		caminhos.add(adicionar);
	}

	public void arquivoAchado(File encontrado) {
		encontrados.add(encontrado);
		if(caminhos.size() == 0 && semaforo.availablePermits() == 6) {
			buscaFinalizada = true;
		}
	}

	public boolean buscaFinalizada() {
		return buscaFinalizada;
	}

	public int size() {
		return caminhos.size();
	}

	@Override
	public void run() {
		while (!buscaFinalizada) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}

}
