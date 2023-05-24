public class Virus  extends Element{
    final int DUREEVIE = 5;

    private int dureeVie;

    public Virus() {
        this.dureeVie = DUREEVIE;
    }

    public void perd1Tour() {
        this.dureeVie--;
    }

    public int getTourVie() {
        return this.dureeVie;
    }

    public void gagne1Tour() {
        this.dureeVie++;
    }

    public String toString() {
        return "V~";
    }

}