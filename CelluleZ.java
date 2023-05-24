public class CelluleZ extends Cellule {
    
    final int NBTOURS = 1;

    public CelluleZ() {
        super();
        this.immunise = false;    
        this.nbToursInfecte = NBTOURS;
    }

    public String toString() {
        return "cz";
    }

}