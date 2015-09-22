package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import sun.applet.Main;

public class VectorFieldGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	private Integer tileSize;
	private int iterations;
	private int groutColour;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public VectorFieldGUI(String img) throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set image to what was passed in
		// and resize if necessary
		java.net.URL url = new URL("file://" + img);
		ImageIcon imgIcon = new ImageIcon(url);
		Image fullImg = imgIcon.getImage();
		// ImageIcon imgIcon = new ImageIcon(fullImg);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Integer screenWidth = (int) screenSize.getWidth();
		Integer screenHeight = (int) screenSize.getHeight();
		Image scaledImg = fullImg;

		if (imgIcon.getIconHeight() > screenHeight - 200) {
			scaledImg = fullImg.getScaledInstance(imgIcon.getIconWidth(),
					screenHeight - 200, java.awt.Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(scaledImg);
		} else {
			scaledImg = fullImg;
		}
		if (imgIcon.getIconWidth() > screenWidth) {
			scaledImg = fullImg.getScaledInstance(screenWidth,
					imgIcon.getIconHeight(), java.awt.Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(scaledImg);
		} else {
			scaledImg = fullImg;
		}

		this.setMinimumSize(new Dimension(500, 300));
		this.setSize(imgIcon.getIconWidth(), imgIcon.getIconHeight() + 100);
		this.image = scaledImg;

		this.setLocationRelativeTo(null);

		this.setTitle("Mosaic Mecca");

		// set default tiles, iteratios and grout colour
		tileSize = 20;
		iterations = 15;
		groutColour = 125;

		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel sketchPanel = new JPanel();
		getContentPane().add(sketchPanel, BorderLayout.CENTER);
		Mosaic mosaic = new Mosaic(image, sketchPanel.getWidth(),
				sketchPanel.getHeight(), tileSize, iterations, groutColour);
		sketchPanel.add(mosaic);
		mosaic.setSize(new Dimension(mosaic.getImage().width,
				mosaic.getImage().height));
		mosaic.setLocation(0, 0);
		mosaic.init();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JLabel lblTileSize = new JLabel("Tile Size");
		mnFile.add(lblTileSize);

		JSlider tileSlider = new JSlider();
		tileSlider.setPaintTicks(true);
		tileSlider.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				tileSize = tileSlider.getValue();
				mosaic.setTileSize(tileSize);
			}
		});
		tileSlider.setValue(20);
		tileSlider.setPaintLabels(true);
		tileSlider.setMinimum(5);
		tileSlider.setMaximum(100);
		mnFile.add(tileSlider);

		JLabel lblGroutColour = new JLabel("Grout Colour");
		mnFile.add(lblGroutColour);

		JSlider groutSlider = new JSlider();
		groutSlider.setPaintTicks(true);
		groutSlider.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				groutColour = groutSlider.getValue();
				mosaic.setGroutColour(groutColour);
			}
		});
		groutSlider.setValue(125);
		groutSlider.setMinimum(0);
		groutSlider.setMaximum(255);
		groutSlider.setPaintLabels(true);
		mnFile.add(groutSlider);

		JLabel lblNumberOfIterations = new JLabel("Number of Iterations");
		mnFile.add(lblNumberOfIterations);

		JSlider iterationSlider = new JSlider();
		iterationSlider.setPaintTicks(true);
		iterationSlider.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
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
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);

		JPanel BtnPanel = new JPanel();
		getContentPane().add(BtnPanel, BorderLayout.SOUTH);
		BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton DLBtn = new JButton("Download Mosaic");
		BtnPanel.add(DLBtn);
		DLBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(getContentPane());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null,
							"You chose to save this mosaic to: "
									+ chooser.getSelectedFile()
											.getAbsolutePath(), "Ok",
							JOptionPane.INFORMATION_MESSAGE);
					mosaic.setFileName(chooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});
		DLBtn.setVisible(false);

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartScreenGUI start = new StartScreenGUI();
				start.setVisible(true);
				dispose();
			}
		});
		BtnPanel.add(backBtn);

		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mosaic.clearEdgeCurve();
			}
		});
		BtnPanel.add(clearBtn);

		JButton genMosaicBtn = new JButton("Generate Mosaic");
		BtnPanel.add(genMosaicBtn);

		JButton editBtn = new JButton("Edit Edge Features");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		genMosaicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean mosaicBoo = mosaic.startMosaic();
				if (mosaicBoo == false) {
					JOptionPane.showMessageDialog(null,
							"Please draw at least one edge curve ", "Ok",
							JOptionPane.INFORMATION_MESSAGE);
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
				java.net.URL url = Main.class
						.getResource("/resources/Pencil-icon.png");
				ImageIcon icon = new ImageIcon(url);
				Image img = icon.getImage();
				img = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
				Point point = new Point(0, 0);
				java.awt.Cursor cursor = tk.createCustomCursor(img, point,
						"Erase");
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
