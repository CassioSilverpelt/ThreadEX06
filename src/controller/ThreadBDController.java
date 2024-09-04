package controller;

import java.util.concurrent.Semaphore;

public class ThreadBDController extends Thread {
	int tID = (int) threadId();
	private Semaphore farol;
	int ident;
	
	public ThreadBDController (Semaphore farol, int ident) {
		this.farol = farol;
		this.ident = ident;
	}
	
	@Override
	public void run() {
		calcBancodeDados();
	}

	private void calcBancodeDados() {
		try {
			farol.acquire();
			int res = tID % 3;
			if (res == 1) {
				calculos(200, 1000);
				transacaoBD(1000);
				calculos(200, 1000);
				transacaoBD(1000);
			} else if (res == 2) {
				calculos(500, 1500);
				transacaoBD(1500);
				calculos(500, 1500);
				transacaoBD(1500);
				calculos(500, 1500);
				transacaoBD(1500);
			} else {
				calculos(1000, 2000);
				transacaoBD(1500);
				calculos(1000, 2000);
				transacaoBD(1500);
				calculos(1000, 2000);
				transacaoBD(1500);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			farol.release();
		}
	}
	
	private void transacaoBD (int tempo) {
		System.out.println("#" + ident + " está realizando transações no Banco de Dados.");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
	}

	private void calculos (int tempomin, int tempomax) {
		int tempo = (int) ((Math.random()* (tempomax-tempomin)) + tempomin);
		System.out.println("#" + ident + " está realizando cálculos.");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
}
