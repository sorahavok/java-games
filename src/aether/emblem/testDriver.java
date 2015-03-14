package aether.emblem;

import javax.swing.*;
import java.awt.*;

public class testDriver extends JPanel
{
    private static final long serialVersionUID = 1L;
	public static void main (String[] args)
    {
        JFrame fieldFrame = new JFrame ("Field");
        Container box = Box.createVerticalBox();
        box.add (Generator.Field);
        box.add (Generator.InfoScreen);
        fieldFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        fieldFrame.getContentPane().add (box,BorderLayout.CENTER);
        fieldFrame.setVisible(true);
        fieldFrame.pack();
    }
}
