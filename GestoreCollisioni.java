
public class GestoreCollisioni {

	public static boolean controllaCollisione(Giocatore ombrello, Goccia goccia) {
		
		if(ombrello.getBordi().intersects(goccia.getBordi())) {
			return true; 
		}
		return false;
	}
}
