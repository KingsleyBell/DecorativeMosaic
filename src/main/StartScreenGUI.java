package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

public class StartScreenGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * @throws IOException 
	 */
	public StartScreenGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize ( 800, 300 );
		this.setLocationRelativeTo ( null );
		
		this.setTitle("Mosaic Mecca");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout(0, 0));
		
		File bannerFile = new File("main/resources/banner.jpg");
		try {
			BufferedImage bannerImage = ImageIO.read(bannerFile);
			JLabel northPanel = new JLabel(new ImageIcon(bannerImage));
			contentPane.add(northPanel, BorderLayout.NORTH);
			northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		catch (IOException e) {			
			e.printStackTrace();
		}				
		
		JLabel fileLabel = new JLabel("Choose a File");
		fileLabel.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(fileLabel,BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
			       fileLabel.setText("File chosen: " + image);
			    }
			}
		});
		southPanel.add(uploadBtn);
		
			
			JButton nextBtn = new JButton("Next");
			southPanel.add(nextBtn);
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
						VectorFieldGUI vfGUI = null;
						try {
							vfGUI = new VectorFieldGUI(image);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    dispose();
						vfGUI.setVisible(true);
					}
				}
			});
			nextBtn.setBounds(335, 228, 89, 23);
		
		
		
	}
}
