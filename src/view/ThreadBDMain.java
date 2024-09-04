package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBDController;

public class ThreadBDMain {
	public static void main (String[] args) {
		int permit = 1;
		Semaphore farol = new Semaphore(permit);
		
		for (int i = 0; i < 5; i++) {
			
			Thread t = new ThreadBDController(farol, i+1);
			t.start();
		}
		
	}
}
