import java.io.*;

class Game {
    final int NB_VIRUS = 10;
    final int NB_X = 5;
    final int NB_Y = 5;
    final int NB_Z = 5;

    private int nbX;
    private int nbY;
    private int nbZ;
    private int nbVirus;

    private Grille grille;

    public Game() {
        System.out.println("Regles du jeu");
        this.nbX = NB_X;
        this.nbY = NB_Y;
        this.nbZ = NB_Z;
        this.nbVirus = NB_VIRUS;
        this.grille = new Grille();
        this.initGame();
        System.out.println(grille);
        this.update();
    
    }

    public void deplacerAleatoirementCellules() {
        this.grille.deplacerCelluleAleatoire();
    }

    public void initGame() {
        this.placerCelluleX();
        this.placerCelluleY();
        this.placerCelluleZ();
        this.placerVirus();
    }

    public void placerCelluleX() {
        for (int i = 0; i < this.nbX; i++) {
            CelluleX cx = new CelluleX();
            placeAleatoire(cx);
        }
    }

    public void placerCelluleY() {
        for (int i = 0; i < this.nbY; i++) {
            CelluleY cy = new CelluleY();
            placeAleatoire(cy);
        }
    }

    public void placerCelluleZ() {
        for (int i = 0; i < this.nbZ; i++) {
            CelluleZ cz = new CelluleZ();
            placeAleatoire(cz);
        }
    }

    public void placerVirus() {
        for (int i = 0; i < this.nbVirus; i++) {
            Virus virus = new Virus();
            placeAleatoire(virus);
        }
    }

    public void placeAleatoire(Element e) {
        int i = 0;
        int j = 0;
        do {
          i = (int) Math.round((Math.random() * (grille.getLongueur()-1)));
          j = (int) Math.round((Math.random() * (grille.getHauteur()-1)));
        } while(!grille.caseIsLibre(i, j)) ;

        grille.setElementToCase(e, i, j);
    }

    public static String saisieChaine() {
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            String chaine = buff.readLine();
            return chaine;
        } catch (IOException e) {
            System.out.println(" impossible de travailler" + e);
            return null;
        }
    }


    public int demanderDeplacementX() {
        System.out.println("Saisir x : ");
        return saisieEntier();
    }

    public int demanderDeplacementY() {
        System.out.println("Saisir y : ");
        return saisieEntier();
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
    
    public void demanderDeplacement() {

        System.out.println("Quel élément voulez vous déplacer");
        System.out.println("x : ");
        int yDepart = this.demanderDeplacementX();
        System.out.println("y : ");
        int xDepart = this.demanderDeplacementY();
        System.out.println("Ou voulez vous le déplacer");
        System.out.println("x : ");
        int yArrive = this.demanderDeplacementX();
        System.out.println("y : ");
        int xArrive = this.demanderDeplacementY();

        this.grille.deplacerElement(xDepart, yDepart, xArrive, yArrive);
    }


    public void update() {
        for (int i=0;i<4;i++){
            this.demanderDeplacement();
        }
        this.deplacerAleatoirementCellules();
        this.grille.supprimeVieVirus();
        System.out.println("Fin du tour");
        System.out.println(this.grille);
        if (this.grille.resteVirus()) {
            this.update();
        } else {
            this.endGame();
        }
    }

    private void endGame() {
        System.out.println("Fin du jeu !!! ");
    }
}

/////////// Ebauche de Fonctions ///////////////

// Fonction pour saisir un entier qui aurait demander à l'utilisateur 
// de saisir un chiffre tant que la valeur rentrée n'en était pas un
/*
    public static int saisieEntier() {
        boolean isValid=false;
        int num;
        while(!isValid){
            try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            String chaine = buff.readLine();
            num = Integer.parseInt(chaine);
            isValid=true;
            return num;
            } catch (IOException e) {
            return 0;
            }
        }
    }*/

    // Demande le déplacement seulement à 1 case autour en haut, bas, gauche ou droite (ne marche pas)
    /*
      public void demanderDeplacement() {
        //int rep;
        System.out.println("Quel élément voulez vous déplacer");
        System.out.println("x : ");
        int xDepart = this.demanderDeplacementX();
        System.out.println("y : ");
        int yDepart = this.demanderDeplacementY();
        System.out.println("Ou voulez vous le déplacer\n1\tDroite\n2\tGauche\n3\tHaut\n4\tBas");
        //rep= saisieEntier();
        int xArrive=xDepart+1;
        int yArrive=yDepart;
        this.grille.deplacerElement(xDepart, yDepart, xArrive, yArrive);
        /*
        if (rep==1){
            this.grille.deplacerElement(xDepart, yDepart, xDepart+1, yDepart);
            System.out.println(this.grille);
        }
        switch(rep){ 
            case 0:
                System.out.println("aaa");
            case 1:
                this.grille.deplacerElement(xDepart, yDepart, xDepart+1, yDepart);
                System.out.println(this.grille);
                break;
            case 2:
                this.grille.deplacerElement(xDepart, yDepart, xDepart-1, yDepart);
                System.out.println(this.grille);
                break;
            case 3:
                this.grille.deplacerElement(xDepart, yDepart, xDepart, yDepart-1);
                System.out.println(this.grille);
                break;
            case 4:
                this.grille.deplacerElement(xDepart, yDepart, xDepart, yDepart+1);
                System.out.println(this.grille);
                break;
            default:
                break;
            
        }*/