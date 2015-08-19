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

public class vectorFieldGUI extends JFrame {

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
	public vectorFieldGUI(PApplet sketch) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel processingSketchPanel = new JPanel();
		processingSketchPanel.setBounds(46, 11, 347, 183);
		processingSketchPanel.add(sketch);
		sketch.setEnabled(false);
		getContentPane().add(processingSketchPanel);
		
		JButton btnGenerateMosaic = new JButton("Generate Mosaic");
		btnGenerateMosaic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				mosaicGUI mosaic = new mosaicGUI(sketch);
				setVisible(false);
				mosaic.setVisible(true);
			}
		});
		btnGenerateMosaic.setBounds(254, 205, 139, 23);
		contentPane.add(btnGenerateMosaic);
		
		JButton btnBck = new JButton("Back");
		btnBck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				drawGUI draw = new drawGUI(sketch);
				setVisible(false);
				draw.setVisible(true);
			}
		});
		btnBck.setBounds(46, 205, 89, 23);
		contentPane.add(btnBck);
	}
}
