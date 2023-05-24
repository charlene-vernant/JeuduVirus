import java.io.*;

public class Main {


    public static void main(String[] args) {
        int rep=1;
        while (rep != 0) {
            System.out.println("\nVoulez-vous:\n0\tQuitter\n1\tJouer");
            rep = saisieEntier();
            switch(rep) {
                case 0 :
                    System.out.println("au revoir");
                    System.exit(0);
                    break;
                case 1 : 
                    Game game = new Game();
                    game.update();
                    break;
                default : 
                    break;    
            }
        }
        
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
    
}

