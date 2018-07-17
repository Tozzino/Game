import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Topo extends Thread {

	private int larghezza;
	private int altezza;
	private int x;
	private int y;
	private boolean attivo;
	private int velocita = 8;
	private Game main;
	private int max_velocita = 10;
	private int min_velocita = 8;
	BufferedImage img_topo;
	
	public Topo(BufferedImage image, int larghezza, int altezza, int x, int y, Game main) {
		
		this.x = x;
		this.y = y;
		this.larghezza = larghezza;
		this.altezza = altezza;
		this.img_topo = image;
		attivo = true;
		this.main = main;
	}
	
	public void run() {
		attivo = true;
		while(attivo) {
			aggiorna();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void aggiorna() {
		
		Random rnd = new Random();
			if (this.x < 0) {
				velocita = min_velocita + rnd.nextInt(max_velocita);
			}
			
			else if (this.x >= main.getLarghezza() - this.larghezza) {
				velocita = rnd.nextInt(max_velocita)+1;
				velocita *= -1;
			}
			
		x += velocita;
	}
	
	public void disegna (Graphics g) {
		g.drawImage(img_topo, x, y, larghezza, altezza, main);
	}

	public boolean isAttivo() {
		return attivo;
	}
	
	public void setX(int valore) {
		this.x = valore;	
	}
	
	public void setY(int valore) {
		this.y = valore;	
	}
	
	public void setLarghezza(int valore) {
		this.larghezza = valore;	
	}
	
	public void setAltezza(int valore) {
		this.altezza = valore;	
	}
	
	public void setAttivo(boolean valore) {
		this.attivo = valore;
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
	
}
