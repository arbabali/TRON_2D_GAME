/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res;

import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.Clip;

/**
 *
 * @author Arbab Ali
 */

public class AudioSource {
    /**
	 * Keep track of clips that have already been drawn so that we don't have to load them every time.
	 */
    private static Map<String, Clip> cache = new HashMap<String, Clip>();

public static void playClip( String filepath)  {
		try {
			Clip clip;
			if (cache.containsKey(filepath))
				clip = cache.get(filepath);
			else {
                               clip= ResourceLoader.loadClip(filepath);
				cache.put(filepath, clip);
			}
			//clip.open(audioInputStream);
                         clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
public static Clip getClip( String filepath)  {
		try {
			Clip clip;
			if (cache.containsKey(filepath))
				clip = cache.get(filepath);
			else {
                               clip= ResourceLoader.loadClip(filepath);
				cache.put(filepath, clip);
			}
			//clip.open(audioInputStream);
                         return clip;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
                return null;
	}
}
