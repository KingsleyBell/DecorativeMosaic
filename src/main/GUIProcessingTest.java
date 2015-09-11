package main;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import processing.core.PApplet;

public class GUIProcessingTest extends JFrame {
	public GUIProcessingTest () {
		super("Easy Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pSketch = new JPanel();  //Create a region to host Processing sketch
        pSketch.setLayout(new FlowLayout());
        pSketch.setBounds(10, 10, 300, 300); //set location of panel
        PApplet sketch = new newVectorFieldTest();  //Create a Processing sketch to be placed in panel
//        pSketch.add(sketch);  //Add sketch to panel
        ImageIcon I = new ImageIcon("img.jpg");
        JButton imgLabel = new JButton(I);
        JButton b = new JButton("ASDASD");
        pSketch.add(b);
        sketch.init();  //start the sketch
//        this.add(pSketch);  //add panel to JFrame
        pSketch.add(imgLabel);
        this.add(pSketch);
        this.setVisible(true);  //Set frame to be visible
	}
	
	public static void main(String[] args) {
		new GUIProcessingTest().setVisible(true);
	}
}
