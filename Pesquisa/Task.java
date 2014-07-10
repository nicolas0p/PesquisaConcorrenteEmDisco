package Pesquisa;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Task implements Runnable {
	
	private List<File> caminhos;
	private boolean buscaFinalizada;
	
	public Task() {
		caminhos = new ArrayList<File>();
		buscaFinalizada = false;
	}
	
	public synchronized File getTask() {
		File ret = caminhos.remove(0);
		return ret;
	}
	
	public synchronized void setTask(File adicionar) {
		caminhos.add(adicionar);
	}
	
	public void arquivoAchado() {
		buscaFinalizada = true;
	}
	
	public boolean buscaFinalizada() {
		return buscaFinalizada;
	}
	
	public int size() {
		return caminhos.size();
	}

	@Override
	public void run() {
		while(!buscaFinalizada) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}

}
