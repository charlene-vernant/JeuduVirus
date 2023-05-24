public class Cellule extends Element {
    /**
     * ATTRIBUTS
     */
    protected boolean immunise;
    protected boolean malade;
    protected int nbToursInfecte;

    static public Cellule fusionne(Cellule c1, Cellule c2) {
        if (c1.isImmunise() && !c2.isImmunise()) {
            return c2;
        }

        if (c2.isImmunise() && !c1.isImmunise()) {
            return c1;
        }

        //on prend par défaut la cellule malade

        if (c1.isMalade() && !c2.isMalade()) {
            return c1;
        }

        if (c2.isMalade() && !c1.isMalade()) {
            return c2;
        }

        //si les deux sont malades on prend le plus grand nombre de tours
        if (c1.isMalade() && c2.isMalade()) {
            return (c1.getNbToursInfecte() > c2.getNbToursInfecte()) ? c1 : c2;
        }

        return c1;
    }


    public void setImmunise(boolean immunise) {
        this.immunise = immunise;
    }

    public boolean isImmunise() {
        return this.immunise;
    }

    public boolean isMalade() {
        return this.malade;
    }

    public int getNbToursInfecte() {
        return this.nbToursInfecte;
    }
}