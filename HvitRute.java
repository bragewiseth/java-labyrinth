import java.util.ArrayList;

public class HvitRute extends Rute {
    
    public HvitRute(Labyrint lab, int rad, int kol) {
        radnr = rad;
        kolonnenr = kol;
        this.lab = lab;
    }



    @Override
    public void finn(Rute fra, ArrayList<Tuppel> vei, int maks, int id) {
                                            
        if(vei.size() > maks) {return;}     //Hindrer algoritmen i å gå i en evig loop, men tillater å gå på samme rute flere ganger
                                            //Kan også bestemme maks lengde på løsninger av labyrinten.
        ArrayList<Tuppel> nySti = new ArrayList<>(vei);
        nySti.add(new Tuppel(radnr, kolonnenr));
        if( nord != fra ) {nord.finn(this, nySti, maks, id);}
        if( oest != fra ) {oest.finn(this, nySti, maks, id);}
        if( syd  != fra ) {syd.finn(this,  nySti, maks, id);}
        if( vest != fra ) {vest.finn(this, nySti, maks, id);}
    }



    public String toString() {
        if(!viseSti) {return "   ";}
        return " * ";
    }
}
