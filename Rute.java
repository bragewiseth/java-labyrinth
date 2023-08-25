import java.util.ArrayList;

abstract class Rute {
    Labyrint lab;
    int radnr;
    int kolonnenr;
    Rute nord;
    Rute syd;
    Rute oest;
    Rute vest;
    boolean viseSti = false;        //Brukes til å bestemme hva som skal printes i toString
                                    //kan flagges av andre metoder i Labyrint klassen.


    public void finn(Rute fra, ArrayList<Tuppel> vei, int maks, int id) {}
}