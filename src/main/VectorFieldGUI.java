package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import processing.core.PApplet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class VectorFieldGUI extends JFrame {
	private String image;
	/**
	 * Launch the application.
	 */
// THIS IS THE OLD INITIALISATION
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					vectorFieldGUI frame = new vectorFieldGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VectorFieldGUI(String img) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		this.image = img;
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNumberOfTiles = new JMenuItem("Number of Tiles");
		mnFile.add(mntmNumberOfTiles);
		
		JMenuItem mntmGroutColour = new JMenuItem("Grout Colour");
		mnFile.add(mntmGroutColour);
		
		JMenuItem mntmNumberOfIterations = new JMenuItem("Number of Iterations");
		mnFile.add(mntmNumberOfIterations);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel BtnPanel = new JPanel();
		getContentPane().add(BtnPanel, BorderLayout.SOUTH);
		BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton backBtn = new JButton("Back");
		BtnPanel.add(backBtn);
		
		JButton clearBtn = new JButton("Clear");
		BtnPanel.add(clearBtn);
		
		JButton genMosaicBtn = new JButton("Generate Mosaic");
		BtnPanel.add(genMosaicBtn);
		
		JPanel sketchPanel = new JPanel();
		sketchPanel.setSize(new Dimension(200,200));
		getContentPane().add(sketchPanel);
		getContentPane().add(sketchPanel, BorderLayout.CENTER);
		DrawEdgeMap pSketch = new DrawEdgeMap(image, sketchPanel.getWidth(), sketchPanel.getHeight());
		System.out.println("THIS: " + sketchPanel.getWidth());
		sketchPanel.add(pSketch);
		pSketch.init();
	}
}
