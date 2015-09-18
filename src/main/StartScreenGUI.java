package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartScreenGUI extends JFrame {

	private JPanel contentPane;
	private String image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartScreenGUI frame = new StartScreenGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartScreenGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton uploadImgBtn = new JButton("Upload Image");
		uploadImgBtn.setBounds(152, 154, 112, 23);
		contentPane.add(uploadImgBtn);
		
		JButton nextBtn = new JButton("Next");
		nextBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				VectorFieldGUI vfGUI = new VectorFieldGUI(image);
			}
		});
		nextBtn.setBounds(335, 228, 89, 23);
		contentPane.add(nextBtn);
	}
}
