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
	private int numTiles;
	private int iterations;
	private int groutColour;
	
	
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
		
		this.setTitle("Mosaic Mecca");
		
		//set default tiles, iteratios and grout colour
		  numTiles = 80;
		  iterations = 15;
		  groutColour = 125;
		
		  //set image to what was passed in
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
		
		JPanel sketchPanel = new JPanel();
		getContentPane().add(sketchPanel, BorderLayout.CENTER);
		sketchPanel.setBackground(new Color(255,0,0));
		MosaicWORKING pSketch = new MosaicWORKING(image, sketchPanel.getWidth(), sketchPanel.getHeight(), numTiles, iterations, groutColour);
		sketchPanel.add(pSketch);
		pSketch.setSize(new Dimension(pSketch.getImage().width,pSketch.getImage().height));
		pSketch.setLocation(0,0);
		pSketch.init();
		
		sketchPanel.addMouseListener(new MouseListener() 
		{
		    public void mouseEntered(java.awt.event.MouseEvent evt) 
		    {
		    	java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
				Image img = tk.getImage("pencil-icon.png");
				Point point = new Point(0,0);
				java.awt.Cursor cursor = tk.createCustomCursor(img, point,"Erase");
				pSketch.setCursor(cursor); //Set the cursor in the panel to a paintbrush
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) 
		    {
		    	//set the cursor back to the default cursor for the sketch panel - not needed
		    	//sketchPanel.setCursor((new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR)));
		    }

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		
		genMosaicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				
				//Mosaic mosaic = new Mosaic(pSketch.getImage(), pSketch.getEdgeCurve(),numTiles, iterations,groutColour, sketchPanel.getHeight(), sketchPanel.getWidth());
				//mosaic.init();
				
				//MosaicGUI mGUI = new MosaicGUI(mosaic);
				//mGUI.setVisible(true);
			}
		});
		BtnPanel.add(genMosaicBtn);
	}
}
