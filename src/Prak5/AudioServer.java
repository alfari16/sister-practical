/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak5;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author alfari
 */
public class AudioServer {

    public static void main(String[] args) {
        try {
            final int PORT = 2000;
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server started");

            Socket client = server.accept();
            System.out.printf("Request came from %s:%s\n", client.getInetAddress(), client.getPort());
//            InputStream audioRaw = new BufferedInputStream(client.getInputStream());
//            System.out.println(audioRaw);
//            AudioStream audio = new AudioStream(audioRaw);
//            System.out.println("Listening...");
//            AudioPlayer.player.start(audio);

            InputStream inputStream = client.getInputStream();
            // DataInputStream din=new DataInputStream(inputStream);
            while (inputStream != null) {
                if (inputStream.available() > 0) {
                    System.out.println(inputStream.available());
                    InputStream bufferedIn = new BufferedInputStream(inputStream);
                    System.out.println("********** Buffred *********" + bufferedIn.available());
                    AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
                }
            }

            client.close();
        } catch (IOException ex) {
            Logger.getLogger(AudioServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
