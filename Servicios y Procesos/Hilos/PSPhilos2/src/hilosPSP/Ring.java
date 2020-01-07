package hilosPSP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Ring {

	private int golpes = 0;
	private int tAsalto;
	Timer timer = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			tAsalto--;
			System.out.println("TIEMPO RESTANTE: " + tAsalto + "s");

		}
	});

	public Ring(int tAsalto, boolean tiempo) {
		if (tiempo) {
			this.tAsalto = tAsalto;
			startTimer();
		}
		System.err.println("RING - EMPIEZA EL ASALTO\r\n");
	}

	public synchronized int getGolpes() {
		return golpes;
	}

	public synchronized void setGolpes(int golpes) {
		this.golpes += golpes;
	}

	public synchronized int getTime() {
		return tAsalto;
	}

	public synchronized void setTime(int tAsalto) {
		this.tAsalto = tAsalto;
	}

	public synchronized void startTimer() {
		timer.start();
	}

	public synchronized void stopTimer() {
		timer.stop();
	}

}
