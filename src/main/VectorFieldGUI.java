package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import processing.core.PApplet;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class VectorFieldGUI extends JFrame {
	private String image;
	private Integer tileSize;
	private int iterations;
	private int groutColour;
	private Mosaic mosaic = new Mosaic();

	/**
	 * Create the frame.
	 */
	public VectorFieldGUI(String img) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);

		this.setTitle("Mosaic Mecca");

		// set default tiles, iteratios and grout colour
		tileSize = 20;
		iterations = 15;
		groutColour = 125;

		// set image to what was passed in
		this.image = img;

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JLabel lblTileSize = new JLabel("Tile Size");
		mnFile.add(lblTileSize);
		
		JSlider tileSlider = new JSlider();
		tileSlider.setPaintTicks(true);
		tileSlider.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) 
			{
				tileSize = tileSlider.getValue();
				mosaic.setTileSize(tileSize);
			}
		});
		tileSlider.setValue(30);
		tileSlider.setPaintLabels(true);
		tileSlider.setMinimum(10);
		mnFile.add(tileSlider);
		
		JLabel lblGroutColour = new JLabel("Grout Colour");
		mnFile.add(lblGroutColour);
		
		JSlider groutSlider = new JSlider();
		groutSlider.setPaintTicks(true);
		groutSlider.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt)
			{
				groutColour = groutSlider.getValue();
				mosaic.setGroutColour(groutColour);
			}
		});
		groutSlider.setValue(125);
		groutSlider.setMaximum(255);
		groutSlider.setPaintLabels(true);
		mnFile.add(groutSlider);
		
		JLabel lblNumberOfIterations = new JLabel("Number of Iterations");
		mnFile.add(lblNumberOfIterations);
		
		JSlider iterationSlider = new JSlider();
		iterationSlider.setPaintTicks(true);
		iterationSlider.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) 
			{
				iterations = iterationSlider.getValue();
				mosaic.setIterations(iterations);
			}
		});
		iterationSlider.setValue(15);
		iterationSlider.setMaximum(50);
		iterationSlider.setMinimum(1);
		iterationSlider.setPaintLabels(true);
		mnFile.add(iterationSlider);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
		
		getContentPane().setLayout(new BorderLayout(0, 0));

		
		
		JPanel sketchPanel = new JPanel();
		getContentPane().add(sketchPanel, BorderLayout.CENTER);
		mosaic = new Mosaic(image, sketchPanel.getWidth(), sketchPanel.getHeight(), tileSize, iterations,
				groutColour);
		sketchPanel.add(mosaic);
		mosaic.setSize(new Dimension(mosaic.getImage().width, mosaic.getImage().height));
		mosaic.setLocation(0, 0);
		mosaic.init();

		JPanel BtnPanel = new JPanel();
		getContentPane().add(BtnPanel, BorderLayout.SOUTH);
		BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton DLBtn = new JButton("Download Mosaic");
		BtnPanel.add(DLBtn);
		DLBtn.setVisible(false);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				StartScreenGUI start = new StartScreenGUI();
				start.setVisible(true);
				dispose();
			}
		});
		BtnPanel.add(backBtn);

		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mosaic.clearEdgeCurve();
			}
		});
		BtnPanel.add(clearBtn);

		JButton genMosaicBtn = new JButton("Generate Mosaic");
		BtnPanel.add(genMosaicBtn);

		JButton editBtn = new JButton("Edit Edge Features");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mosaic.editEdgeCurve();
				backBtn.setVisible(true);
				clearBtn.setVisible(true);
				genMosaicBtn.setVisible(true);
				DLBtn.setVisible(false);
				editBtn.setVisible(false);
			}
		});
		BtnPanel.add(editBtn);
		editBtn.setVisible(false);

		DLBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser chooser = new JFileChooser();				
				int returnVal = chooser.showSaveDialog(getContentPane());							    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {			    
			    	JOptionPane.showMessageDialog(null, "You chose to save this mosaic to: " +
				            chooser.getSelectedFile().getAbsolutePath(),
							"Ok", JOptionPane.INFORMATION_MESSAGE);
			       mosaic.setFileName(chooser.getSelectedFile().getAbsolutePath());
			    }
			}
		});

		genMosaicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean mosaicBoo = mosaic.startMosaic();
				if (mosaicBoo == false) {					
					JOptionPane.showMessageDialog(null, "Please draw at least one edge curve ",
							"Ok", JOptionPane.INFORMATION_MESSAGE);
				} else {
					backBtn.setVisible(false);
					clearBtn.setVisible(false);
					genMosaicBtn.setVisible(false);
					DLBtn.setVisible(true);
					editBtn.setVisible(true);
				}
			}
		});

		sketchPanel.addMouseListener(new MouseListener() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
				Image img = tk.getImage("pencil-icon.png");
				Point point = new Point(0, 0);
				java.awt.Cursor cursor = tk.createCustomCursor(img, point, "Erase");
				mosaic.setCursor(cursor); // Set the cursor in the panel to a
											// paintbrush
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				// set the cursor back to the default cursor for the sketch
				// panel - not needed
				// sketchPanel.setCursor((new
				// java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR)));
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
	}
}
