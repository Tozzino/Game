import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Game extends Canvas implements KeyListener, Runnable, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private static final int larghezza = 1280;
	private static final int altezza = 720;
	private static final String nome_gioco = "Tozzino's Game";
		
	BufferedImage sfondo = null;
	BufferedImage topo = null;
	BufferedImage ombrello = null;
	BufferedImage goccia = null;
	
	private boolean giocoAttivo = false;
	private Topo ogg_topo;
	private Giocatore giocatore;
	private Pioggia	pioggia;
	
	public Game() {
		
		caricaRisorse();
		iniziaGioco();
		
	};
	
	public static void main(String[] args) {
		
	Game gioco = new Game();
		
	JFrame finestra_gioco = new JFrame(nome_gioco);
	Dimension dimensione_finestra = new Dimension(larghezza, altezza);
		
	finestra_gioco.setPreferredSize(dimensione_finestra);
	finestra_gioco.setMaximumSize(dimensione_finestra);
	finestra_gioco.setResizable(false);
	finestra_gioco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	finestra_gioco.add(gioco);
	finestra_gioco.addKeyListener(gioco);
	
	gioco.addMouseMotionListener(gioco);;
	
	finestra_gioco.pack();
	finestra_gioco.setVisible(true);
	
	Thread thread_gioco = new Thread(gioco);
	thread_gioco.start();
	
			}
	
	private void iniziaGioco() {
		giocatore = new Giocatore (ombrello, 0, 150, 150, this);
		ogg_topo = new Topo (topo, 100, 150, 100, 550, this);
		ogg_topo.start();
		pioggia = new Pioggia(goccia, 10, 300, this);
		pioggia.start();
		
	}

	private void caricaRisorse() {
		
		CaricatoreImmagini loader = new CaricatoreImmagini();
		sfondo = loader.caricaImmagine("/immagini/sfondo.jpg");
		topo = loader.caricaImmagine("/immagini/topo.png");
		ombrello = loader.caricaImmagine("/immagini/Ombrello.png"); 
		goccia = loader.caricaImmagine("/immagini/goccia.png");
	 
		System.out.println("Risorse caricate!");
	}
	
	private void disegna() {
		
		BufferStrategy buffer = this.getBufferStrategy();
			if(buffer == null) {
				createBufferStrategy(2);
				return;
			}
		Graphics g = buffer.getDrawGraphics();
		
		g.drawImage(sfondo, 0, 0, larghezza, altezza, this);
		
		ogg_topo.disegna(g);
		giocatore.disegna(g);
		pioggia.disegna(g);
		
		g.dispose();
		buffer.show();
	}

	private void aggiorna() {
		ArrayList<Goccia> gocce = pioggia.getGocce();
		
		for(Goccia goccia : gocce) {
			if (GestoreCollisioni.controllaCollisione(giocatore, goccia)) {
				gocce.remove(goccia);
				break;
			}
		}
	}
	
	@Override
	public void run() {
		giocoAttivo = true;
		
		while(giocoAttivo) {
			aggiorna();
			disegna();
		}
	}	
	
	public int getLarghezza() {
		return larghezza;
	}
	
	public int getAltezza() {
		return altezza;
	}	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch(keycode) {
		case KeyEvent.VK_LEFT:
		giocatore.spostaSinistra();
		break;
		case KeyEvent.VK_RIGHT:
		giocatore.spostaDestra();
		break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		int posizione = e.getPoint().x - (giocatore.getLarghezza()/2);
			if (posizione>=0 && (posizione + giocatore.getLarghezza()) <= larghezza) {
				giocatore.setX(posizione);
			}
		System.out.println("Mouse moved!");
		
	}
	
}
