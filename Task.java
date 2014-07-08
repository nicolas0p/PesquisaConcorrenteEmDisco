package PesquisaConcorrenteEmDisco;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task {
	
	private List<String> caminhos;
	private boolean buscaFinalizada;
	private Lock lock;
	
	public Task() {
		caminhos = new ArrayList<String>();
		buscaFinalizada = false;
		lock = new ReentrantLock();
	}
	
	public String getTask() {
		lock.lock();
		String ret = caminhos.remove(0);
		lock.unlock();
		return ret;
	}
	
	public void setTask(String adicionar) {
		lock.lock();
		caminhos.add(adicionar);
		lock.unlock();
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

}
