/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prak5;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author alfari
 */
public class Audio {
     public static AudioInputStream authenticateFile(String filename) {
        AudioInputStream din = null;
        try {
            File file = new File(filename);
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            System.out.println("Before :: " + in.available());

            AudioFormat baseFormat = in.getFormat();
            AudioFormat decodedFormat =
                    new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, baseFormat.getSampleRate(),
                            8, baseFormat.getChannels(), baseFormat.getChannels(),
                            baseFormat.getSampleRate(), false);
            din = AudioSystem.getAudioInputStream(decodedFormat, in);
            System.out.println("After :: " + din.available());
        } catch (Exception e) {
            // Handle exception.
            e.printStackTrace();
        }
        return din;
    }

    public static void main(String[] args) {
        try {
            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getDirectory() + dialog.getFile();
            
            Socket client = new Socket("localhost", 2000);

            InputStream audioRaw = new FileInputStream(file);
//            AudioStream audio = new AudioStream(audioRaw);
//            System.out.println("Listening...");
//            AudioPlayer.player.start(audio);
            
//            OutputStream out = client.getOutputStream();
//            byte buffer[] = new byte[2048];
//            int count = audioRaw.read(buffer);
//            do {
//                System.out.println(count);
//                out.write(buffer, 0, count);
//                count = audioRaw.read(buffer);
//            }while (count != -1);

            if (client.isBound()) {
//                client = serverSocker.accept();
                OutputStream out = client.getOutputStream();
                while (true) {
                    AudioInputStream ais = authenticateFile(file);
                    if (ais != null) {
                        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, out);
                    }
                }
            }
            
            System.out.println("done");
                
//            out.flush();
//            out.close();
            
            client.close();
            
            System.exit(0);

//            Files.copy(new File(file).toPath(), new File("/home/alfari/NetBeansProjects/MyApplication/src/assets/audio.wav").toPath(), StandardCopyOption.REPLACE_EXISTING);
//            System.out.println("Berhasil Copy");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
