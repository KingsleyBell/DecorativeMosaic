package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import processing.core.PApplet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MosaicGUI extends JFrame {

	private JPanel contentPane;
	private int numTiles;

	/**
	 * Launch the application.
	 */
// OLD INIT METHOD
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					mosaicGUI frame = new mosaicGUI();
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
	public MosaicGUI(PApplet sketch) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel processingSketchPanel = new JPanel();
		processingSketchPanel.setBounds(46, 11, 328, 184);
		processingSketchPanel.add(sketch);
		getContentPane().add(processingSketchPanel);
		
		JButton btnEditEdgeFeatures = new JButton("Edit Edge Features");
		btnEditEdgeFeatures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				sketch.setEnabled(true);
			}
		});
		btnEditEdgeFeatures.setBounds(41, 213, 145, 23);
		contentPane.add(btnEditEdgeFeatures);
		
		JButton btnNewButton = new JButton("Download Mosaic");
		btnNewButton.setBounds(254, 213, 135, 23);
		contentPane.add(btnNewButton);
	}

}
