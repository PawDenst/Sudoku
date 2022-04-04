package gra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static gra.Plansza.planszaStartowa;
import static gra.Plansza.planszaPelna;
import static gra.Plansza.planszaBiezaca;

public class Wczytywacz {

    public static void WczytajPlik(String filePath) {
        try {
            BufferedReader fileReader;
            fileReader = new BufferedReader(new FileReader(filePath));
            String Dane = fileReader.readLine();
            String[] Plansze = Dane.split("!");
            String[] PolaBiezace = Plansze[0].split(",");
            String[] PolaStartowe = Plansze[1].split(",");
            String[] PolaPelne = Plansze[2].split(",");

            int k = -1;
            for (int i = 0; i<9;i++) {
                for (int j = 0; j < 9; j++) {
                    k=k+1;
                    planszaBiezaca[i][j] = Integer.parseInt(PolaBiezace[k]) ;
                    planszaStartowa[i][j] = Integer.parseInt(PolaStartowe[k]) ;
                    planszaPelna[i][j] = Integer.parseInt(PolaPelne[k]) ;
                }
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(planszaStartowa[i][j]);
                    System.out.print(",");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}