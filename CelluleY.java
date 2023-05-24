public class CelluleY extends Cellule {
    
    final int NBTOURS = 4;

    public CelluleY() {
        super();
        this.immunise = true; 
        this.nbToursInfecte = NBTOURS;   
    }

    public String toString() {
        return "cy";
    }
}