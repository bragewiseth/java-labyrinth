import java.util.ArrayList;

public class Aapning extends HvitRute {
    boolean funnet;
    int lagretId = -1;
    // Konstruktøren tar inn en Rute r og erstatter den med seg selv
    // Opdaterer alle nabopar
    public Aapning(Rute r) {
        super(r.lab, r.radnr, r.kolonnenr);
            nord = r.nord;  
            syd  = r.syd;   
            oest = r.oest; 
            vest = r.vest; 
            if( r.nord != null) {r.nord.syd  = this;}
            if( r.oest != null) {r.oest.vest = this;}
            if( r.syd  != null) {r.syd.nord  = this;}
            if( r.vest != null) {r.vest.oest = this;}
    }


    
    @Override
    public void finn(Rute fra, ArrayList<Tuppel> vei, int maks, int id) {
        funnet = false;     // sørger for at aapning kan bli funnet mer enn én gang. 
                            // men ikke mer en én gang per finnUtveiFra()
        if(lagretId == id) {funnet = true;}
        if(!funnet) {System.out.println("Rute ["+radnr + ", "+kolonnenr + "] er en åpning");}
        lagretId = id;
        
        ArrayList<Tuppel> nySti = new ArrayList<>(vei);
        nySti.add(new Tuppel(radnr, kolonnenr));
        lab.veier.add(nySti);
    }
}


