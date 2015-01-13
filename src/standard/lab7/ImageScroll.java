package standard.lab7;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

public class ImageScroll extends JFrame {
	JScrollPane scrollPane = new JScrollPane();
	Image img;
	JLabel lblImage;
	ImageIcon imgIcon;

	public ImageScroll() {
		// TODO Auto-generated constructor stub
		setSize(400, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JButton btn = new JButton("...");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("/home/eltntawy/ITI/Java/Labs/src/standard/lab7/SplashScreen.png"));
				fileChooser.setFileFilter(new myFileFilter());
				
				if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(ImageScroll.this)) {
					img = Toolkit.getDefaultToolkit().getImage(fileChooser.getSelectedFile().getPath());
					imgIcon = new ImageIcon(img);
					lblImage = new JLabel(imgIcon);
					scrollPane.setViewportView(lblImage);
					
					ImageScroll.this.repaint();
					ImageScroll.this.validate();
				}

			}
		});

		img = Toolkit.getDefaultToolkit().getImage("/home/eltntawy/ITI/Java/Labs/src/standard/lab7/SplashScreen.png");
		imgIcon = new ImageIcon(img);
		lblImage = new JLabel(imgIcon);
		scrollPane.setViewportView(lblImage);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.add(btn, JScrollPane.LOWER_RIGHT_CORNER);

		add(scrollPane);
		setVisible(true);

	}

	class MyFileChooser extends JFileChooser {
		
		MyFileChooser () {
			
		}
	}
	
	class myFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			
			if (f.getName().endsWith(".png")) {
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
