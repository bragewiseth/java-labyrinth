import java.util.Scanner;


public class Oblig6 {
    


    public static void main(String[] args) {
        Labyrint labyrint = new Labyrint("labyrinter/3.in");
        Scanner sc = new Scanner(System.in);
        String input = "";
        while( true ) {
            try {

                System.out.println("Skriv inn koordinater <rad> <kolonne>       skriv 'e' for å avslutte");
                input = sc.nextLine(); if(input.equals("e")) {break;}
                // Lagrer koordinatene i variabler
                String dimensjoner[] = input.strip().split(" ");
                int rad = Integer.parseInt(dimensjoner[0]);
                int kol = Integer.parseInt(dimensjoner[1]);

                System.out.println("Skriv inn maks lengde på løsning       skriv 'e' for å avslutte");
                System.out.println("(Obs! Sykliske labyrinter kan fort få veldig mange løsninger ved store makslengder, det er tillat å gå i ring)");
                input = sc.nextLine(); if(input.equals("e")) {break;}
                // Finner løsninger og printer ut
                System.out.println("****************************");
                labyrint.finnUtveiFra(rad, kol, Integer.parseInt(input));
                System.out.println("****************************");
                System.out.println("fant " + labyrint.veier.size() + " veier mindre enn " +input+ " ruter i lengde");
                // Sorterer løsninger fra kortest til lengst
                labyrint.sorterVeier();

                while( labyrint.veier.size() > 0 ) {
                    System.out.println("velg en vei: <0 - " + (labyrint.veier.size()-1) + "> fra korteste vei til lengste       skriv 't' for å gå tilbake" );
                    input = sc.nextLine(); if(input.equals("t")) {break;}
                    try{    
                        System.out.println("\nHer er veien du gikk:\n" + labyrint.toString(labyrint.veier.get(Integer.parseInt(input))));
                    } catch ( NumberFormatException e) {System.out.println("skriv et heltall -_-");
                    } catch ( IndexOutOfBoundsException e) {System.out.println("finner ikke vei :(");}
                }
            } catch (NumberFormatException e) {System.out.println("skriv heltall -_-");;
            } catch ( IndexOutOfBoundsException e ) {System.out.println("finner ikke rute :(");}
        }
        sc.close();
    }
}