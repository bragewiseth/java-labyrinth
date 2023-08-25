import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Labyrint {
    Rute[][] matrise;
    ArrayList<ArrayList<Tuppel>> veier;

    public Labyrint(String filnavn) {
        veier = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filnavn));
            String[] dimensjoner = sc.nextLine().strip().split(" ");
            matrise = new Rute[Integer.parseInt(dimensjoner[0])][Integer.parseInt(dimensjoner[1])];
            String rute ="";
            
            for(int rad = 0; rad < matrise.length; rad++) {
                rute = sc.nextLine();
                for(int kol = 0; kol < matrise[0].length; kol++) {
                    // Hvit rute
                    if(rute.charAt(kol) == '.') {
                        HvitRute r = new HvitRute(this, rad, kol);
                        boolean rand = false;
                        if( matrise.length-1 == rad || matrise[0].length-1 == kol) {rand = true;}
                        // Opdaterer et nord/sør-par sine naboer
                        if( rad == 0 ) {rand = true;}
                        else {r.nord = matrise[rad-1][kol]; r.nord.syd = r;}
                        // Opdaterer et øst/vest-par sine naboer
                        if( kol == 0 ) {rand = true;}
                        else {r.vest = matrise[rad][kol-1]; r.vest.oest = r;}

                        if(rand) {matrise[rad][kol] = new Aapning(r);} 
                        else {matrise[rad][kol] = r;}
                    }

                    // Svart rute
                    else if(rute.charAt(kol) == '#') {
                        SortRute r = new SortRute(this, rad, kol);
                        // Opdaterer et nord/sør-par sine naboer
                        if( rad > 0 ) { r.nord = matrise[rad-1][kol]; r.nord.syd = r;}
                        // Opdaterer et øst/vest-par sine naboer
                        if( kol > 0 ) { r.vest = matrise[rad][kol-1]; r.vest.oest = r;}               
                        matrise[rad][kol] = r;
                    }
                }
            }
            System.out.println("["+matrise.length + " rader     " + matrise[0].length + " kolonner]");
            System.out.println(this);  
        } catch ( FileNotFoundException e )             {System.err.println("finner ikke fil"); 
        } catch ( NoSuchElementException e )            {System.err.println("Feil i en kolonne");
        } catch ( NumberFormatException e )             {System.err.println("Feil dimensjoner");
        } catch ( ArrayIndexOutOfBoundsException e )    {System.err.println("Feil dimensjoner");
        } catch ( StringIndexOutOfBoundsException e )   {System.err.println("Feil i en rad");} }



    private int finnId = 0;     // Brukes av Aapning til å resette at den er blitt funnet etter hvert kall av finnUtveiFra()

    public void finnUtveiFra(int rad, int kol, int maks) {
        veier.clear();
        matrise[rad][kol].finn(null, new ArrayList<Tuppel>(), maks, finnId);
        finnId++;
    }




    public void sorterVeier() { 
        Collections.sort(
            veier, new Comparator<ArrayList<Tuppel>>(){
                public int compare(ArrayList<Tuppel> a1, ArrayList<Tuppel> a2) {
                return a1.size() - a2.size();
                }
            }
        );
    }



    public String toString(ArrayList<Tuppel> vei) {
        String labyrint = "";

        for(Rute[] rad : matrise) {
            for(Rute r : rad) {
                r.viseSti = false;
                for(Tuppel t : vei) {
                    if(t.rad == r.radnr && t.kol == r.kolonnenr) {r.viseSti = true;}
                }
                labyrint += r;
            }
            labyrint += "\n";
        }
        return labyrint;
    }




    public String toString() {
        String labyrint = "";
        for(Rute[] rad : matrise) {
            for(Rute r : rad) {
                labyrint += r;
            }
            labyrint += "\n";
        }
        return labyrint;
    }
}