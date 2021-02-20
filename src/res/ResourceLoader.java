/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res;

/**
 *
 * @author Arbab Ali
 */


import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ResourceLoader {
    public static InputStream loadResource(String resName){
        return ResourceLoader.class.getClassLoader().getResourceAsStream(resName);
    }
    
    public static Image loadImage(String resName) throws IOException{
        URL url = ResourceLoader.class.getClassLoader().getResource(resName);
        return ImageIO.read(url);
    }
    public static Clip loadClip(String resName) throws IOException, UnsupportedAudioFileException, LineUnavailableException
            {
               // String soundName = "yourSound.wav";    
                AudioInputStream  audioInputStream = AudioSystem.getAudioInputStream(new File(resName).getAbsoluteFile());
                    
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                return clip;
            }
}
