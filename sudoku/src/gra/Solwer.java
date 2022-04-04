package gra;

public class Solwer {
    private final int[][] plansza = new int[SIZE][SIZE];
    private static final int EMPTY = 0;
    private static final int SIZE = 9;

    public Solwer(int[][] board) {
        for(int i=0; i<board.length; i++)
            System.arraycopy(board[i], 0, plansza[i], 0, board[i].length);
        solve();
    }

    public int[][] getPlansza() {
        return plansza;
    }

    // metoda sprawdzająca obecnosc cyfry w wierszu
    private boolean czyWWierszu(int wiersz, int cyfra) {
        for (int i = 0; i < SIZE; i++)
            if (plansza[wiersz][i] == cyfra)
                return true;
        return false;
    }

    // metoda sprawdzająca obecnosc cyfry w kolumnie
    private boolean czyWKolumnie(int kolumna, int cyfra) {
        for (int i = 0; i < SIZE; i++)
            if (plansza[i][kolumna] == cyfra)
                return true;
        return false;
    }

    // metoda sprawdzająca obecnosc cyfry w kwadracie 3x3
    private boolean czyWKwadracie(int wiersz, int kolumna, int cyfra) {
        int r = wiersz - wiersz % 3;
        int c = kolumna - kolumna % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (plansza[i][j] == cyfra)
                    return true;
        return false;
    }

    private boolean isOk(int wiersz, int kolumna, int cyfra) {
        return !czyWWierszu(wiersz, cyfra)  &&  !czyWKolumnie(kolumna, cyfra)  &&  !czyWKwadracie(wiersz, kolumna, cyfra);
    }

    /**
     *Glowna metoda rozwiazujaca plansze
     */
    private boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (plansza[row][col] == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
                        if (isOk(row, col, number)) {
                            plansza[row][col] = number;

                            if (solve()) {
                                return true;
                            } else {
                                plansza[row][col] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Metoda pozwalająca na wypisanie aktualnego stanu zmiennrj @param board
     * przechowującej planszę przed lub po jej rozwiązaniu
     */
    public void display() {
        StringBuilder tekst = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tekst.append(" ").append(plansza[i][j]);
            }
            tekst.append("\n");
        }
        tekst.append("\n");
        System.out.println(tekst);
    }
}
