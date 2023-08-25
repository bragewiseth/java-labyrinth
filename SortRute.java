import java.util.ArrayList;

public class SortRute extends Rute {
    
    public SortRute(Labyrint lab, int rad, int kol) {
        radnr = rad;
        kolonnenr = kol;
        this.lab = lab;
    }



    @Override
    public void finn(Rute fra, ArrayList<Tuppel> vei, int maks, int id) {
        if( fra == null ) {System.out.println("kan ikke starte i svart rute");}
    }



    public String toString() {
        return " ()";
    }
}
