package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.javafx.tk.Toolkit;
import com.sun.org.apache.xml.internal.security.encryption.Reference;

import javafx.scene.Cursor;
import processing.core.PApplet;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;

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
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class drawGUI extends JFrame {

	/**
	 * Launch the application.
	 */
	
	private PApplet sketch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel processingSketchPanel;
	
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
		this.sketch=sketchy;
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				vectorFieldGUI vfGUI = new vectorFieldGUI(sketch);
				dispose();
				vfGUI.setVisible(true);
			}
		});
		nextButton.setBounds(205, 202, 144, 23);
		getContentPane().add(nextButton);
		processingSketchPanel = new JPanel();
		processingSketchPanel.setBounds(46, 11, 304, 184);
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
					processingSketchPanel.setCursor((new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR)));
				}
			}
		});
	
		
		eraseRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(eraseRadioButton.isSelected())
				{
					sketch.setEnabled(true);
					//erase mode - custom cursor
					java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
					Image img = tk.getImage("C:\\Users\\SuThy\\Desktop\\DecorativeMosaic\\src\\main\\eraserCursor.png");
					Point point = new Point(0,0);
					java.awt.Cursor cursor = tk.createCustomCursor(img, point,"Erase");
					processingSketchPanel.setCursor(cursor);

				}
			}
		});
	}
}
