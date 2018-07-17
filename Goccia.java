	import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Goccia extends Thread {

	private int x;
	private int y;
	private int larghezza;
	private int altezza;
	BufferedImage img_goccia;
	private boolean attivo;
	private Game main;
	private int velocita;
    
	
	public Goccia(BufferedImage image, int larghezza, int altezza, int x, int y, int velocita, Game main) {
		this.x = x;
		this.y = y;
		this.larghezza = larghezza;
		this.altezza = altezza;
		this.img_goccia = image;
		attivo = true;
		this.main = main;
		this.velocita = velocita;
		this.start();
	}
	
	public void run() {
		attivo = true;
		while(attivo) {
			aggiorna();
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void disegna (Graphics g) {
		g.drawImage(img_goccia, x, y, larghezza, altezza, main);
		
	}

	
	private void aggiorna() {
		y += velocita;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLarghezza() {
		return larghezza;
		
	}
	
	public int getAltezza() {
		return altezza;
	}
	
	public Rectangle getBordi() {
		return new Rectangle(x, y, larghezza, altezza);
	}
	
}
