import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Pioggia extends Thread{

	private int numeroGocce;
	private int attesa;
	BufferedImage img_goccia;
	Game main;
	private boolean piove;
	private ArrayList <Goccia> gocce;
	Random rand;
	private final int maxVel = 5;
	
	public Pioggia (BufferedImage image, int numeroGocce, int attesa, Game main) {
		this.img_goccia = image;
		this.attesa= attesa;
		this.numeroGocce = numeroGocce;
		this.main = main;
		gocce = new ArrayList<Goccia>();
		rand = new Random();
	}

	public void run( ) {
		 piove = true;
		 while(piove) {
			 for (int i=0; i<numeroGocce; i++) {
				 gocce.add(new Goccia(img_goccia, 20, 50, rand.nextInt(main.getLarghezza()), -50, rand.nextInt(maxVel)+2, main));
			 }
			 
			 try {
				Thread.sleep(attesa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
	}
	
	public void disegna (Graphics g) {
		
		for(int i = 0; i<gocce.size(); i++) {
			Goccia goccia_temp = gocce.get(i);
			goccia_temp.disegna(g);
		}
	}

	public ArrayList<Goccia> getGocce() {
		return gocce;
	}
}
