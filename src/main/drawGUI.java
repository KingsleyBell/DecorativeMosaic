package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import processing.core.PApplet;

import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;

public class drawGUI extends JFrame {

	/**
	 * Launch the application.
	 */
	
	private PApplet sketch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
//OLD INIT METHOD
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					drawGUI frame = new drawGUI(null);
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
	public drawGUI(PApplet sketchy) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				vectorFieldGUI vfGUI = new vectorFieldGUI(sketch);
				setVisible(false);
				vfGUI.setVisible(true);
			}
		});
		nextButton.setBounds(205, 202, 144, 23);
		getContentPane().add(nextButton);
		
		JPanel processingSketchPanel = new JPanel();
		processingSketchPanel.setBounds(46, 11, 304, 184);
		sketch = sketchy;
		processingSketchPanel.add(sketch);
		
		sketch.setEnabled(false);
		getContentPane().add(processingSketchPanel);
		
		JRadioButton drawRadioButton = new JRadioButton("Draw");
		buttonGroup.add(drawRadioButton);
		drawRadioButton.setBounds(46, 202, 109, 23);
		getContentPane().add(drawRadioButton);
		
		
		JRadioButton eraseRadioButton = new JRadioButton("Erase");
		buttonGroup.add(eraseRadioButton);
		eraseRadioButton.setBounds(46, 228, 109, 23);
		getContentPane().add(eraseRadioButton);
		this.setVisible(true);
		
		
		drawRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(drawRadioButton.isSelected())
				{
					sketch.setEnabled(true);
					//draw mode
				}
			}
		});
		
		eraseRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(eraseRadioButton.isSelected())
				{
					sketch.setEnabled(true);
					//erase mode
				}
			}
		});
	}
	
}
