package PesquisaSemaforo;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

public class Print implements Runnable {
	
	private ArrayBlockingQueue<File> queue;
	
	public Print(ArrayBlockingQueue<File> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while(true) { //TODO arrumar isso
		try {
			File file = queue.remove();
			System.out.println("Arquivo achado em: " + file.getAbsolutePath());
		} catch(NoSuchElementException e) {
		}
	}
	}
	
}
