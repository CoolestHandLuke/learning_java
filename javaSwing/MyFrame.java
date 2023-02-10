package javaSwing;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.ImageIcon;

public class MyFrame extends JFrame{

    MyFrame() {

        this.setTitle("JFrame title goes here"); // Sets title of the instance
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits out of application rather than hide
        this.setResizable(true); // Able to change window size
        this.setSize(420, 420); // Sets the x dimension and y dismension
        this.setVisible(true);  // Make frame visible

        ImageIcon image = new ImageIcon("learning_java/javaSwing/money.jpg");
        this.setIconImage(image.getImage()); // Change icon of this
        this.getContentPane().setBackground(new Color(0x123456)); // Change color of background
    }
    
}
