package gra;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Random;

import static gra.Audio.Odtwarzacz;
import static gra.Plansza.planszaStartowa;
import static gra.Plansza.planszaPelna;
import static gra.Plansza.planszaBiezaca;

public class Controller {

    private class Objekt{
        final TextField[][] obj;
        Objekt() {
            this.obj = new TextField[][]{
                    {t11, t12, t13, t14, t15, t16, t17, t18, t19},
                    {t21, t22, t23, t24, t25, t26, t27, t28, t29},
                    {t31, t32, t33, t34, t35, t36, t37, t38, t39},
                    {t41, t42, t43, t44, t45, t46, t47, t48, t49},
                    {t51, t52, t53, t54, t55, t56, t57, t58, t59},
                    {t61, t62, t63, t64, t65, t66, t67, t68, t69},
                    {t71, t72, t73, t74, t75, t76, t77, t78, t79},
                    {t81, t82, t83, t84, t85, t86, t87, t88, t89},
                    {t91, t92, t93, t94, t95, t96, t97, t98, t99},
            };
        }
    }
    @FXML TextField doWczytania;

    @FXML TextField
            t11,t12,t13,t14,t15,t16,t17,t18,t19,
            t21,t22,t23,t24,t25,t26,t27,t28,t29,
            t31,t32,t33,t34,t35,t36,t37,t38,t39,
            t41,t42,t43,t44,t45,t46,t47,t48,t49,
            t51,t52,t53,t54,t55,t56,t57,t58,t59,
            t61,t62,t63,t64,t65,t66,t67,t68,t69,
            t71,t72,t73,t74,t75,t76,t77,t78,t79,
            t81,t82,t83,t84,t85,t86,t87,t88,t89,
            t91,t92,t93,t94,t95,t96,t97,t98,t99;



    @FXML Text tekst; //tekst z informacja o wygranej lub przegranej

    @FXML Text zledane; //tekst z informacja o planszy wlasnej

    @FXML Button sprawdz;

    @FXML MenuItem rozw;

    @FXML MenuItem podpowie;

    @FXML MenuItem zapi;

    @FXML MenuItem rese;

    @FXML Button zatwierdzWlasna;


    private void clear(){
        Objekt t = new Objekt();
        for (int i = 0; i<9;i++) {
            for (int j = 0; j < 9; j++) {
                t.obj[i][j].getStyleClass().clear();
                t.obj[i][j].getStyleClass().addAll("text-field", "text-input");
                t.obj[i][j].setDisable(false);

            }
        }
        rozw.setDisable(true);
        podpowie.setDisable(true);
        zatwierdzWlasna.setVisible(false);
        zledane.setVisible(false);
        tekst.setVisible(false);
    }

    public void nowaLatwa(){
        nowaGra(15);

    }
    public void nowaSrednia(){
        nowaGra(25);

    }
    public void nowaTrudna(){
        nowaGra(40);

    }
    public void nowaEkspert(){
        nowaGra(50);

    }

    public void nowaWlasna(){
        clear();
        Objekt t = new Objekt();
        for (int i = 0; i<9;i++) {
            for (int j = 0; j < 9; j++) {
                t.obj[i][j].setText("");

            }
        }
        sprawdz.setDisable(true);
        zatwierdzWlasna.setVisible(true);
        System.out.println("nowa własna");
    }
    private boolean czyWWierszu(int[][] plansza, int wiersz, int cyfra) {
        for (int i = 0; i < 9; i++)
            if (plansza[wiersz][i] == cyfra)
                return true;
        return false;
    }

    // metoda sprawdzająca obecnosc cyfry w kolumnie
    private boolean czyWKolumnie(int[][] plansza, int kolumna, int cyfra) {
        for (int i = 0; i < 9; i++)
            if (plansza[i][kolumna] == cyfra)
                return true;
        return false;
    }

    // metoda sprawdzająca obecnosc cyfry w kwadracie 3x3
    private boolean czyWKwadracie(int[][] plansza, int wiersz, int kolumna, int cyfra) {
        int r = wiersz - wiersz % 3;
        int c = kolumna - kolumna % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (plansza[i][j] == cyfra)
                    return true;
        return false;
    }

    private boolean isOk(int[][] plansza, int wiersz, int kolumna, int cyfra) {
        return czyWWierszu(plansza, wiersz, cyfra)  ||  czyWKolumnie(plansza, kolumna, cyfra)  ||  czyWKwadracie(plansza, wiersz, kolumna, cyfra);
    }

    public void zatwierdzWlasna() {
        boolean dobra = true;

        System.out.println("zatwierdź");
        Objekt t = new Objekt();
        int[][] plansza = new int[9][9];
        for (int i = 0; i<9;i++) {
            for (int j = 0; j < 9; j++) {
                try{
                    plansza[i][j] = Integer.parseInt(t.obj[i][j].getText());
                } catch (Exception e){
                    plansza[i][j] = 0;
                }
            }
        }

        for(int i = 0; i<9;i++){
            for (int j = 0; j < 9; j++) {
                int temp = plansza[i][j];
                if(temp==0){
                    temp=11;
                }
                plansza[i][j] = 10;
                if(isOk(plansza,i,j,temp)){
                    dobra = false;
                    //System.out.println("nie ok");
                }
                if(temp==11){
                    temp=0;
                }
                plansza[i][j] = temp;
            }
        }
        int liczba = 0;
        for(int i = 0; i<9;i++){
            for (int j = 0; j < 9; j++) {
                if(plansza[i][j] != 0)
                    liczba++;
            }
        }


        if(dobra && liczba > 29){
            new Plansza(plansza);
            sprawdz.setDisable(false);
            zatwierdzWlasna.setVisible(false);
            podpowie.setDisable(false);
            rozw.setDisable(false);
            zapi.setDisable(false);
            rese.setDisable(false);
            tekst.setVisible(false);
            for (int i = 0; i<9;i++){
                for (int j = 0; j<9;j++) {
                    if(planszaStartowa[i][j] == 0){
                        t.obj[i][j].setText(null);
                    }else{
                        t.obj[i][j].setText(String.valueOf(planszaStartowa[i][j]));
                        t.obj[i][j].getStyleClass().add("full");
                        t.obj[i][j].setDisable(true);
                    }
                }
            }
        }else{
            tekst.setText("Wprowadzono niepoprawną planszę lub za mało pól :(");
            tekst.setVisible(true);
            System.out.println("złe pola");
        }
    }

    private void nowaGra(int poziom) {
        new Plansza(poziom);
        Objekt t = new Objekt();
        sprawdz.setDisable(false);
        clear();
        rozw.setDisable(false);
        podpowie.setDisable(false);
        zapi.setDisable(false);
        rese.setDisable(false);
        for (int i = 0; i<9;i++){
            for (int j = 0; j<9;j++) {
                if(planszaStartowa[i][j] == 0){
                    t.obj[i][j].setText(null);
                }else{
                    t.obj[i][j].setText(String.valueOf(planszaStartowa[i][j]));
                    t.obj[i][j].getStyleClass().add("full");
                    t.obj[i][j].setDisable(true);
                }
            }
        }
    }

    public void reset() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Objekt t = new Objekt();
        clear();
        sprawdz.setDisable(false);
        rozw.setDisable(false);
        zapi.setDisable(false);
        podpowie.setDisable(false);
        for (int i = 0; i<9;i++){
            for (int j = 0; j<9;j++) {
                if(planszaStartowa[i][j] == 0){
                    t.obj[i][j].setText(null);
                }else{
                    t.obj[i][j].setText(String.valueOf(planszaStartowa[i][j]));
                    t.obj[i][j].getStyleClass().add("full");
                    t.obj[i][j].setDisable(true);
                }
            }
        }
        Odtwarzacz(4);
    }

    public void podpowiedz() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        int[][] plansza = new int[9][9];
        Objekt t = new Objekt();
        for (int i = 0; i<9;i++) {
            for (int j = 0; j < 9; j++) {
                try{
                    plansza[i][j] = Integer.parseInt(t.obj[i][j].getText());
                } catch (Exception e){
                    plansza[i][j] = 0;
                }
            }
        }

        ArrayList<Integer> mozliwew = new ArrayList<>();
        ArrayList<Integer> mozliwek = new ArrayList<>();
        Random gen = new Random();

        for (int i = 0; i<9;i++){
            for (int j = 0; j<9;j++) {
                if(plansza[i][j] == 0 || plansza[i][j] != planszaPelna[i][j]){
                    mozliwew.add(i);
                    mozliwek.add(j);
                }
            }
        }
        if(mozliwew.size()>0){
            int a = gen.nextInt(mozliwew.size());
            t.obj[mozliwew.get(a)][mozliwek.get(a)].setText(String.valueOf(planszaPelna[mozliwew.get(a)][mozliwek.get(a)]));
            t.obj[mozliwew.get(a)][mozliwek.get(a)].getStyleClass().add("hint");
            t.obj[mozliwew.get(a)][mozliwek.get(a)].setDisable(true);
            mozliwek.remove(a);
            mozliwew.remove(a);
            Odtwarzacz(3);
        }
    }

    public void rozwiaz(){
        Objekt t = new Objekt();
        for (int i = 0; i<9;i++){
            for (int j = 0; j<9;j++) {
                t.obj[i][j].setText(String.valueOf(planszaPelna[i][j]));
            }
        }
    }

    public void sprawdz() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Objekt t = new Objekt();
        boolean flaga = true;
        sprawdz.setDisable(true);
        rozw.setDisable(true);
        podpowie.setDisable(true);

        int[][] board = new int[9][9];
        for (int i = 0; i<9;i++) {
            for (int j = 0; j < 9; j++) {
                try{
                    board[i][j] = Integer.parseInt(t.obj[i][j].getText());
                } catch (Exception e){
                    board[i][j] = 0;
                }
            }
        }

        for(int i=0; i<board.length; i++)
            System.arraycopy(board[i], 0, planszaBiezaca[i], 0, board[i].length);

        for (int i = 0; i<9;i++){
            for (int j = 0; j<9;j++) {
                if(planszaBiezaca[i][j] == planszaPelna[i][j]){
                    t.obj[i][j].getStyleClass().add("ok");
                    t.obj[i][j].setDisable(true);
                }else{
                    t.obj[i][j].getStyleClass().add("notOk");
                    t.obj[i][j].setDisable(true);
                    flaga = false;
                }
            }
        }
        if(flaga){
            tekst.setText("Wygrałeś!!!");
            tekst.setVisible(true);
            Odtwarzacz(1);
        }else{
            tekst.setText("Spróbuj jeszcze raz :(");
            tekst.setVisible(true);
            Odtwarzacz(2);
        }
    }

    public void zapiszDoDruku(){
        DoDruku.save("Sudoku do druku");
    }

    public void zapisz(){
        int[][] PlanszaDoZapisu = new int[9][9];
        Objekt t = new Objekt();
        for (int i = 0; i<9;i++) {
            for (int j = 0; j < 9; j++) {
                try{
                    PlanszaDoZapisu[i][j] = Integer.parseInt(t.obj[i][j].getText());
                } catch (Exception e){
                    PlanszaDoZapisu[i][j] = 0;
                }
            }
        }
        new Sejwer(PlanszaDoZapisu);
    }

    public void wczytaj() {
        Objekt t = new Objekt();
        sprawdz.setDisable(false);
        clear();
        rozw.setDisable(false);
        podpowie.setDisable(false);
        zapi.setDisable(false);
        rese.setDisable(false);
        Wczytywacz.WczytajPlik("zapis.txt");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (planszaStartowa[i][j] == 0) {
                    t.obj[i][j].setText(null);
                } else {
                    t.obj[i][j].setText(String.valueOf(planszaStartowa[i][j]));
                    t.obj[i][j].getStyleClass().add("full");
                    t.obj[i][j].setDisable(true);
                }
                if (planszaBiezaca[i][j] != 0)
                    t.obj[i][j].setText(String.valueOf(planszaBiezaca[i][j]));
            }
        }
    }
}