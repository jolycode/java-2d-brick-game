package javaprojectgame;

import java.net.URL;

import javax.sound.sampled.*;

public class music {
	    private Clip clip;
	    public static music sound1 = new music("/play.wav");
	    public static music sound2 = new music("/win.wav");
	    public static music sound3 = new music("/lose.wav");
	    public music (String fileName) {
	        try {
	            AudioInputStream ais = AudioSystem.getAudioInputStream(music.class.getResource(fileName));
	            clip = AudioSystem.getClip();
	            clip.open(ais);
	        } catch (Exception e) {
	            e.printStackTrace();}}
	    private static music getAudioInputStream(URL resource) {
			return null;}
		public void play() { // to run music
			try {
	            if (clip != null) {
	                new Thread() {
	                    public void run() {
	                        synchronized (clip) {
	                            clip.stop();
	                            clip.setFramePosition(0);
	                            clip.start();}}
	                }.start();}} 
	        catch (Exception e) {
	        	e.printStackTrace();}}
	    public void stop(){ // to stop the music
	        if(clip == null) return;
	        clip.stop();}
	
}