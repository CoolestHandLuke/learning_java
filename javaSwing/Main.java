package javaSwing;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;

public class Main {

    public static void main(String[] args) {

        // JFrame = a GUI window to add components to
        // JLabel = a GUI display are for a string of text, an image, or both
        
        ImageIcon image = new ImageIcon("learning_java/javaSwing/dude.png");
        Border border = BorderFactory.createLineBorder(Color.green, 3); // Create a new border object for your labels

        JLabel label = new JLabel(); // Create a label
        label.setText("Bro, do you even code?"); // Fills the label with a string. 
        label.setIcon(image); // Set the image of the label
        label.setHorizontalTextPosition(JLabel.CENTER); // Set text left, center, or right of image/icon
        label.setVerticalTextPosition(JLabel.TOP); // Set text top, center, or bottom of image icon
        label.setForeground(new Color(123,123,123)); // Set the font color
        label.setFont(new Font("Sans Serif", Font.PLAIN, 90)); // Set font of the text
        label.setIconTextGap(100); // Change the gap distance between the imgae and the text. Negative numbers move it closer
        label.setBackground(Color.black); // Tries to change the background color but will only work with the opaque setting enabled
        label.setOpaque(true); // making the background solid
        label.setBorder(border); // Apply the border to the label
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER); // These two lines will force the label to stay in the center of the window, no matter what
        //label.setBounds(100, 100, 250, 250); // First two numbers are where the top left corner of the label will go
                                            // Next two numbers are the dimensions


        MyFrame frame = new MyFrame();
        frame.setSize(500, 500);
        //frame.setLayout(null); // This will prevent the label from taking up the entire window
        frame.getContentPane().setBackground(new Color(255,255,255));
        frame.setResizable(true);
        frame.add(label);
        frame.pack(); // SIZE OF THE FRAME WILL AUTOMATICALLY RESIZE THE FRAME TO FIT EVERYTHING INSIDE IT

    }

}