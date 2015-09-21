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
import java.awt.FlowLayout;

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
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.setTitle("Mosaic Mecca");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		image = "test.jpg";
		
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton uploadBtn = new JButton("Upload Image");
		uploadBtn.addActionListener(new ActionListener() {
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
		panel.add(uploadBtn);
		
			
			JButton nextBtn = new JButton("Next");
			panel.add(nextBtn);
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
		
		
		
	}
}
