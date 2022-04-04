package gra;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    public static void Odtwarzacz(int rodzaj) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        Clip clip;
        AudioInputStream muzyka;
        String filePath ="";
        if(rodzaj == 1){
            filePath ="wow.wav";
        }else if(rodzaj == 2) {
            filePath = "e_kurator.wav";
        }else if(rodzaj == 3){
            filePath ="przerywnik.wav";
        }else if(rodzaj == 4){
            filePath ="reset.wav";
        }



        muzyka = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(muzyka);
        clip.start();
    }



}
