package gra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static gra.Plansza.planszaStartowa;

public class DoDruku {
    private static final int EMPTY = 0;
    private static final int SIZE = 9;

    public static void save(String nazwa){
        StringBuilder tekst = new StringBuilder();
        String tekst2;
        tekst.append("#####################################").append("\r\n");
        for (int i = 0; i < SIZE; i++) {
            tekst.append("#");
            for (int j = 0; j < SIZE; j++) {
                tekst.append(" ");
                if(planszaStartowa[i][j] == EMPTY){
                    tekst.append(" ");
                } else{
                    tekst.append(planszaStartowa[i][j]);
                }
                tekst.append(" ");
                if(j < 8 && j%3 != 2){
                    tekst.append("|");
                }else {
                    tekst.append("#");
                }
            }
            tekst.append("\r\n");
            if(i < 8 && i%3 != 2){
                tekst.append("#-----------#-----------#-----------#").append("\r\n");
            }else if(i < 8){
                tekst.append("############*###########*############").append("\r\n");
            }
        }
        tekst.append("#####################################");
        tekst.append("\r\n");
        tekst2 = tekst.toString();
        System.out.println(tekst2);

        try {
            File myObj = new File(nazwa + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("Stworzono plik " + myObj.getName());
            } else {
                System.out.println("Plik już istnieje");
            }
        } catch (IOException e) {
            System.out.println("Wystąpił błąd");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(nazwa + ".txt", true);
            myWriter.write(tekst2);
            myWriter.close();
            System.out.println("Pomyślnie zapisano do pliku");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd");
            e.printStackTrace();
        }
    }
}
