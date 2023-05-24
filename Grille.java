import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Grille {
    final int LONGUEUR = 20;
    final int HAUTEUR = 20;

    private Element[][] grille;
    private int longueur;
    private int hauteur;

    public Grille() {
        grille = new Element[HAUTEUR][LONGUEUR];
        this.longueur = LONGUEUR;
        this.hauteur = HAUTEUR;
    }

    public int getLongueur() {
        return this.longueur;
    }

    public boolean resteVirus() {
        int cpt = 0;

        for (int i = 0; i < LONGUEUR; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                if (this.grille[i][j] != null && (this.grille[i][j].getClass() == Virus.class)) {
                    cpt++;
                }
            }
        }

        return cpt != 0;
    }

    public int getHauteur() {
        return this.hauteur;
    }


    public void supprimeVieVirus() {
        for (int i = 0; i < LONGUEUR; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                if (this.grille[i][j] !=null &&  this.grille[i][j].getClass() == Virus.class) {
                    Virus v = (Virus)this.grille[i][j];
                    v.perd1Tour();
                    if (v.getTourVie() == 0) {
                        this.grille[i][j] = null;
                    }else {
                        this.grille[i][j] = v;
                    }
                }
            }
        }
    }

    public String toString() {
        String str = "\n  ";
        int cpt = 1;
        for (int head = 0; head < LONGUEUR; head++) {
            str += " ";
            if (head < 10) {
                str += "0";
            }
            str += head + " ";
        }
        str += "\n00";
        for (int i = 0; i < LONGUEUR; i++) {
            for (int j = 0; j < HAUTEUR; j++) {

                if (this.grille[i][j] == null) {
                    str += " -- ";
                } else {
                    str += " " + grille[i][j] + " ";
                }

                if (cpt == LONGUEUR) {
                    str += "\n";
                    if (i < 9) {
                        str += "0" + (i + 1); //remettre cste
                    } else if (i < 19) {
                        str += i + 1;
                    }
                    cpt = 0;

                }

                cpt++;
            }
        }
        return str;
    }

    public  void deplacerCelluleAleatoire() {
        int cpt = 4;
        while(cpt != 0) {
            int [] cellule = this.findCellule();
            int [] caseDeplacement = this.placeAleatoirement();
            if (isCellule(caseDeplacement[0], caseDeplacement[1])) {
                Cellule celArrive = (Cellule) this.grille[caseDeplacement[0]][caseDeplacement[1]];
                Cellule celDepart = (Cellule) this.grille[caseDeplacement[0]][caseDeplacement[1]];
                Cellule celFinal = Cellule.fusionne(celDepart, celArrive);
                this.grille[cellule[0]][cellule[1]] = null;
                this.grille[caseDeplacement[0]][caseDeplacement[1]] = celFinal;
            } else {
                deplacerElement(cellule[0], cellule[1], caseDeplacement[0], caseDeplacement[1]);
            }

            cpt--;
        }
    }
    public int [] placeAleatoirement() {
        int i = 0;
        int j = 0;
        int x =0;
        int y=0;
        int[] tirage = new int [3];
        tirage[0]=0;
        tirage[1]=-1;
        tirage[2]=1;
        do{
            i = (int) Math.round((Math.random() * (2)));
            j = (int) Math.round((Math.random() * (2)));
            int [] cellule = this.findCellule();
            x=cellule[0]+i;
            y=cellule[1]+j;
        } while((x>=20 || y>=20) || (x<0 || y<0));
        int[] pos = new int [2];
        pos[0]=x;
        pos[1]=y;
        return (pos);
    }

    public int [] findCaseForCellule() {
        int tmpX = 0;
        int tmpY = 0;
        boolean b = false;
        while (!b) {
            tmpX = (int) Math.round((Math.random() * (this.getLongueur() - 1)));
            tmpY = (int) Math.round((Math.random() * (this.getHauteur() - 1)));
            if (this.grille[tmpX][tmpY] == null || this.grille[tmpX][tmpY].getClass() != Virus.class) {
                b = true;
            }
        }
        return new int[]{tmpX, tmpY};
    }

    public int [] findCellule () {
        for (int i = 0; i < this.getLongueur(); i++) {
            for (int j=0; j < this.getHauteur(); j++) {
                if (isCellule(i, j)) {
                    int[] ret = new int[2];
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        }
        return null;
    }

    public boolean isCellule(int x, int y) {
        return this.grille[x][y] != null && (
                (this.grille[x][y].getClass() == CelluleX.class) ||
                (this.grille[x][y].getClass() == CelluleY.class) ||
                (this.grille[x][y].getClass() == CelluleZ.class)
                );
    }
    public boolean isCelluleX(int x, int y) {
        return this.grille[x][y] != null && (
                (this.grille[x][y].getClass() == CelluleX.class));
    }
    public boolean isCelluleY(int x, int y) {
        return this.grille[x][y] != null && (
                (this.grille[x][y].getClass() == CelluleY.class));
    }
    public boolean isCelluleZ(int x, int y) {
        return this.grille[x][y] != null && (
                (this.grille[x][y].getClass() == CelluleZ.class));
    }
    
    public void contamination(Cellule e){
        e.setImmunise(false);
    }

    public boolean caseIsLibre(int i, int j) {
        return this.grille[i][j] == null;
    }


    public void deplacerElement(int xD, int yD, int xA, int yA) {
        if (!isCellule(xD,yD)){
        Virus e = (Virus)this.grille[xD][yD];
            if (isCelluleY(xA,yA)){
                if (((Cellule) grille[xA][yA]).isImmunise()==true){
                    contamination((Cellule)grille[xA][yA]);
                    e.gagne1Tour();
                    System.out.println(e.getTourVie());
                    System.out.println("cellule contaminee!");
                }else if (((Cellule) grille[xA][yA]).isImmunise()==false){
                    this.grille[xA][yA] = e;
                    this.grille[xD][yD] = null;
                    System.out.println("cellule contaminee tuee!");
                }
            }
            else if (isCelluleZ(xA, yA)){
                this.grille[xA][yA] = e;
                this.grille[xD][yD] = null;
                System.out.println("cellule tuee!");
            } 
            else if (isCelluleX(xA, yA)){
                System.out.println("cellule immunisee RAS!");

            } 
            else{
                this.grille[xA][yA] = e;
                this.grille[xD][yD] = null;
            }
        
        }
        else{
            Cellule e = (Cellule)this.grille[xD][yD];
            this.grille[xA][yA] = e;
            this.grille[xD][yD] = null;
        }
        
    

    }

    public void setElementToCase(Element e, int i, int j) {
        this.grille[i][j] = e;
    }

    public Element getElement(Element[][] grille, int ligne, int colonne) {
        return this.grille[ligne][colonne];
    }

    public static int saisieEntier() {
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            String chaine = buff.readLine();
            int num = Integer.parseInt(chaine);
            return num;
        } catch (IOException e) {
            return 0;
        }
    }


}