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
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author alfari
 */
public class Image {

    public static void execute() {
        try {
            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getDirectory() + dialog.getFile();

            InputStream imgRaw = new BufferedInputStream(new FileInputStream(file));
            BufferedImage img = ImageIO.read(imgRaw);
            
            
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(img));
            frame.getContentPane().add(label, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);

            Files.copy(new File(file).toPath(), new File("/home/alfari/NetBeansProjects/MyApplication/src/assets/img.jpg").toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Berhasil Copy");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
