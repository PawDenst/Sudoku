package gra;

public class Plansza {
    public static int[][] planszaStartowa = new int[9][9];
    public static int[][] planszaPelna = new int[9][9];
    public static final int[][] planszaBiezaca = new int[9][9];

    Plansza(int poziom) {
        Generator nowaPlansza = new Generator(poziom);
        planszaStartowa = nowaPlansza.getPlansza();
        Solwer rozwiazanaPlansza = new Solwer(planszaStartowa);
        planszaPelna = rozwiazanaPlansza.getPlansza();
        for(int i=0; i<planszaPelna.length; i++) {
            for (int j = 0; j < planszaPelna[i].length; j++) {
                System.out.print(planszaPelna[i][j]);
            }
            System.out.print("\n");
        }
    }

    Plansza(int[][] plansza){
        planszaStartowa = plansza;
        Solwer rozwiazanaPlansza = new Solwer(planszaStartowa);
        planszaPelna = rozwiazanaPlansza.getPlansza();
    }
}
