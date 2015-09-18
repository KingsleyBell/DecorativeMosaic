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

public class VectorFieldGUI extends JFrame {

	private JPanel contentPane;

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
	public VectorFieldGUI(PApplet sketch) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel processingSketchPanel = new JPanel();
		processingSketchPanel.setBounds(82, 11, 289, 139);
		processingSketchPanel.add(sketch);
		sketch.setEnabled(false);
		getContentPane().add(processingSketchPanel);
		
		JButton btnGenerateMosaic = new JButton("Generate Mosaic");
		btnGenerateMosaic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				MosaicGUI mosaic = new MosaicGUI(sketch);
				setVisible(false);
				mosaic.setVisible(true);
			}
		});
		btnGenerateMosaic.setBounds(254, 205, 139, 23);
		contentPane.add(btnGenerateMosaic);
		
		processingSketchPanel.addMouseListener(new MouseListener() 
		{
		    public void mouseEntered(java.awt.event.MouseEvent evt) 
		    {
		    	java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
				Image img = tk.getImage("C:\\Users\\SuThy\\Desktop\\DecorativeMosaic\\src\\main\\eraserCursor.png");
				Point point = new Point(0,0);
				java.awt.Cursor cursor = tk.createCustomCursor(img, point,"Erase");
				processingSketchPanel.setCursor(cursor);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) 
		    {
		    	processingSketchPanel.setCursor((new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR)));
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
		
		JButton btnBck = new JButton("Back");
		btnBck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				MosaicGUI mosaic = new MosaicGUI(sketch);
				setVisible(false);
				mosaic.setVisible(true);
			}
		});
		btnBck.setBounds(54, 205, 89, 23);
		contentPane.add(btnBck);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		clearButton.setBounds(54, 171, 89, 23);
		contentPane.add(clearButton);
	}
}
