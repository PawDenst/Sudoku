package gra;

import java.util.Random;


public class Generator {
    private int[][] plansza = new int[SIZE][SIZE];
    private static final int SIZE = 9;


    Generator(int poziom) {
        Random gen = new Random();
        int a = gen.nextInt(3);
        int b = gen.nextInt(3);
        plansza[0][0] = gen.nextInt(4) + 6;
        plansza[1][1] = gen.nextInt(3) + 3;
        plansza[2][2] = gen.nextInt(2) + 1;
        plansza[a+3][b+3] = gen.nextInt(9) + 1;
        plansza[a+3][b+3] = gen.nextInt(9) + 1;

        Solwer solw = new Solwer(plansza);
        plansza = solw.getPlansza();

        //zmianaKolumn();


        for (int i = 0; i < poziom; i++) {
            int c = gen.nextInt(9);
            int d = gen.nextInt(9);
                while(plansza[c][d] == 0){
                    c = gen.nextInt(9);
                    d = gen.nextInt(9) ;
                }
                plansza[c][d] = 0;
        }
    }

    public int[][] getPlansza(){
        int[][] temp = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(plansza[i], 0, temp[i], 0, 9);
        }
        return temp;
    }
}