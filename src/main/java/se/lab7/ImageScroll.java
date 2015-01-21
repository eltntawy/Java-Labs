package main.java.se.lab7;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;

public class ImageScroll extends JFrame {
	JScrollPane scrollPane = new JScrollPane();
	Image img;
	JLabel lblImage;
	ImageIcon imgIcon;

	public ImageScroll() {

		setSize(400, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JButton btn = new JButton("..");
		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyFileChooser fileChooser = new MyFileChooser();
				fileChooser
						.setCurrentDirectory(new File(
								"/home/eltntawy/ITI/Java/Java-Labs/src/main.java.se./lab7/SplashScreen.png"));
				fileChooser.setFileFilter(new myFileFilter());

				if (JFileChooser.APPROVE_OPTION == fileChooser
						.showOpenDialog(ImageScroll.this)) {
					img = Toolkit.getDefaultToolkit().getImage(
							fileChooser.getSelectedFile().getPath());
					imgIcon = new ImageIcon(img);
					lblImage = new JLabel(imgIcon);
					scrollPane.setViewportView(lblImage);

					ImageScroll.this.repaint();
					ImageScroll.this.validate();
				}

			}
		});

		img = Toolkit
				.getDefaultToolkit()
				.getImage(
						"/home/eltntawy/ITI/Java/Java-Labs/src/main.java.se./lab7/SplashScreen.png");
		imgIcon = new ImageIcon(img);
		lblImage = new JLabel(imgIcon);
		scrollPane.setViewportView(lblImage);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.add(btn, ScrollPaneConstants.LOWER_RIGHT_CORNER);

		add(scrollPane);
		setVisible(true);

	}

	class MyFileChooser extends JFileChooser {

		MyFileChooser() {
			super();
			setAccessory(new ImagePreview(this));
		}
	}

	class ImagePreview extends JPanel implements PropertyChangeListener {

		Image img;
		ImageIcon imgIcon;
		JLabel lblImage;

		public ImagePreview(JFileChooser fc) {
			fc.addPropertyChangeListener(this);
			Image img = null;
			ImageIcon imgIcon = new ImageIcon();
			lblImage = new JLabel();
			add(lblImage);

		}

		public void propertyChange(PropertyChangeEvent e) {

			File file = null;

			if(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY .equals(e.getPropertyName()))
				file = (File) e.getNewValue();

			if (file != null && file.isFile()) {
				Image img = Toolkit.getDefaultToolkit().getImage(file.getPath()).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon imgIcon = new ImageIcon(img);

				lblImage.setIcon(imgIcon);
				lblImage.repaint();
				repaint();
			}

		}
	}

	class myFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub

			if (f.getName().endsWith(".png") || f.isDirectory()) {
				return true;
			}
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "Image (*.png)";
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ImageScroll();
	}

}
