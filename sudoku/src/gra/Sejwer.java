package gra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static gra.Plansza.planszaPelna;
import static gra.Plansza.planszaStartowa;


public class Sejwer {
    public Sejwer(int[][] Dozapisu){
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(Dozapisu[i], 0, plansza[i], 0, SIZE);
        }
        ZapisDoPliku("zapis");
    }
    private static final int[][] plansza = new int[9][9];
    private static final int EMPTY = 0;
    private static final int SIZE = 9;

    public static void ZapisDoPliku(String nazwa){
        StringBuilder tekst = new StringBuilder();
        String tekst2;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(plansza[i][j] == EMPTY){
                    tekst.append("0,");
                } else if(i != 8 ||
                        j != 8){
                    tekst.append(plansza[i][j]).append(",");
                }
                else{
                    tekst.append(plansza[i][j]);
                }
            }
        }
        tekst.append("!");

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(planszaStartowa[i][j] == EMPTY){
                    tekst.append("0,");
                } else if(i != 8 || j != 8){
                    tekst.append(planszaStartowa[i][j]).append(",");
                }
                else{
                    tekst.append(planszaStartowa[i][j]);
                }
            }
        }

        tekst.append("!");

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                 if(i != 8 || j != 8){
                    tekst.append(planszaPelna[i][j]).append(",");
                }
                else{
                    tekst.append(planszaPelna[i][j]);
                }
            }
        }

        tekst2 = tekst.toString();

        try {
            File myObj = new File(nazwa + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("Utworzono plik: " + myObj.getName());
            } else {
                System.out.println("Plik już istnieje");
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(nazwa + ".txt");
            myWriter.write(tekst2);
            myWriter.close();
            System.out.println("Pomyślnie zapisano do pliku");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd.");
            e.printStackTrace();
        }
    }
}




