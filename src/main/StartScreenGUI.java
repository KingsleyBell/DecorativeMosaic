package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

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
		
		this.setTitle("Mosaic Mecca");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		image = "test.jpg";
		
		JButton uploadImgBtn = new JButton("Upload Image");
		uploadImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG & GIF Images", "jpg", "gif");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	JOptionPane.showInternalMessageDialog(contentPane, "You chose to open this file: " +
				            chooser.getSelectedFile().getAbsolutePath(),
							"Ok", JOptionPane.INFORMATION_MESSAGE);
			       image = chooser.getSelectedFile().getAbsolutePath();
			    }
			}
		});
		uploadImgBtn.setBounds(151, 228, 112, 23);
		contentPane.add(uploadImgBtn);
		
		JButton nextBtn = new JButton("Next");
		nextBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(image == null)
				{
					JOptionPane.showInternalMessageDialog(contentPane, "Please upload an image before proceeding",
							"Ok", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else
				{
					VectorFieldGUI vfGUI = new VectorFieldGUI(image);
					vfGUI.setVisible(true);
				}
			}
		});
		nextBtn.setBounds(335, 228, 89, 23);
		contentPane.add(nextBtn);
	}
}
