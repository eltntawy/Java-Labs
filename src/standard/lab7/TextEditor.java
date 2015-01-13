package standard.lab7;

/**
 * 		@author eltntawy
 * 
 * 		Copyright (C) 2014-2015 Mohamed Refaat, Cairo - Egypt
 *
 *	 	This program is free software: you can redistribute it and/or modify
 * 		it under the terms of the GNU General Public License as published by
 *		the Free Software Foundation, either version 3 of the License, or
 *		(at your option) any later version.
 *
 *		This program is distributed in the hope that it will be useful,
 *		but WITHOUT ANY WARRANTY; without even the implied warranty of
 *		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *		GNU General Public License for more details.
 *
 *		You should have received a copy of the GNU General Public License
 *		along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * **/

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TextEditor extends JFrame implements ActionListener, KeyListener {

	private JTextArea txtArea;
	private JScrollPane mainPanel;
	private JMenuBar menuBar;

	private JMenu menuFile;
	private JMenu menuEdit;
	private JMenu menuAbout;

	private JMenuItem menuItemNew;
	private JMenuItem menuItemOpen;
	private JMenuItem menuItemSave;
	private JMenuItem menuItemExit;

	private JMenuItem menuItemFormat;
	private JMenuItem menuItemCopy;
	private JMenuItem menuItemPast;
	private JMenuItem menuItemDelete;
	private JMenuItem menuItemSelectAll;

	private JMenuItem menuItemUndo;
	private JMenuItem menuItemRedo;
	private JMenuItem menuItemAbout;

	private int index = 1;

	private JMenu menuLookAndFeel;

	Vector<String> historyVector = new Vector<String>();

	private JFileChooser fileChooser;

	public TextEditor() {

		initSplashScreen();
		init();
		dynInit();
	}

	static void renderSplashFrame(Graphics2D g, int frame) {
		final String[] comps = { "", ".", "..", "..." };
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(0, 0, 400, 300);
		g.setPaintMode();
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		g.drawString("Loading" + comps[(frame / 5) % 4] + "", 10, 280);
	}

	public void initSplashScreen() {

		SplashScreen splashScreen = SplashScreen.getSplashScreen();

		if (splashScreen == null) {
			System.err
					.println("Error: TextEditor.initSplashScreen() cannot initialize SplashScreen");
			return;
		}

		Graphics2D graphics = splashScreen.createGraphics();

		for (int i = 0; i < 25; i++) {

			renderSplashFrame(graphics, i);
			splashScreen.update();
			try {
				Thread.sleep(90);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		splashScreen.close();
		toFront();

	}

	public void init() {

		// initializing component
		txtArea = new JTextArea();
		mainPanel = new JScrollPane();
		menuBar = new JMenuBar();

		fileChooser = new JFileChooser();

		menuLookAndFeel = new JMenu("Themes");

		menuFile = new JMenu("File");
		menuFile.setMnemonic('f');

		menuEdit = new JMenu("Edit");
		menuEdit.setMnemonic('e');

		menuAbout = new JMenu("About");
		menuAbout.setMnemonic('u');

		menuItemNew = new JMenuItem("New");
		menuItemNew.setMnemonic('n');
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_MASK));

		menuItemOpen = new JMenuItem("Open");
		menuItemOpen.setMnemonic('o');
		menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				KeyEvent.CTRL_MASK));

		menuItemSave = new JMenuItem("Save");
		menuItemSave.setMnemonic('s');
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_MASK));

		menuItemExit = new JMenuItem("Exit");
		menuItemExit.setMnemonic('x');
		menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				KeyEvent.CTRL_MASK));

		menuItemCopy = new JMenuItem("Copy");
		menuItemCopy.setMnemonic('C');
		menuItemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				KeyEvent.CTRL_MASK));

		menuItemFormat = new JMenuItem("Format");
		menuItemFormat.setMnemonic('f');
		menuItemFormat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				KeyEvent.CTRL_MASK));

		menuItemPast = new JMenuItem("Past");
		menuItemPast.setMnemonic('p');
		menuItemPast.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				KeyEvent.CTRL_MASK));

		menuItemDelete = new JMenuItem("Delete");
		menuItemDelete.setMnemonic('d');
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				KeyEvent.CTRL_MASK));

		menuItemSelectAll = new JMenuItem("Select All");
		menuItemSelectAll.setMnemonic('a');
		menuItemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				KeyEvent.CTRL_MASK));

		menuItemAbout = new JMenuItem("About...");
		menuItemAbout.setMnemonic('u');
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				KeyEvent.CTRL_MASK));

		menuItemUndo = new JMenuItem("Undo");
		menuItemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				KeyEvent.CTRL_MASK));

		menuItemRedo = new JMenuItem("Redo");
		menuItemRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				KeyEvent.CTRL_MASK));

		// adding action listener for component
		menuItemNew.addActionListener(this);
		menuItemOpen.addActionListener(this);
		menuItemSave.addActionListener(this);
		menuItemExit.addActionListener(this);
		menuItemCopy.addActionListener(this);
		menuItemFormat.addActionListener(this);
		menuItemPast.addActionListener(this);
		menuItemDelete.addActionListener(this);
		menuItemSelectAll.addActionListener(this);
		menuItemAbout.addActionListener(this);

		menuItemUndo.addActionListener(this);
		menuItemRedo.addActionListener(this);

		txtArea.addKeyListener(this);
		historyVector.add("");

	}

	public void dynInit() {

		// init frame
		this.setTitle("Text Editor version 0.1");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		this.setVisible(true);

		// adding component
		menuFile.add(menuItemNew);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemSave);
		menuFile.add(menuItemExit);

		menuEdit.add(menuItemFormat);
		menuEdit.add(menuItemCopy);
		menuEdit.add(menuItemPast);
		menuEdit.add(menuItemDelete);
		menuEdit.add(menuItemSelectAll);
		menuEdit.add(menuItemUndo);
		menuEdit.add(menuItemRedo);

		menuAbout.add(menuItemAbout);

		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuLookAndFeel);
		menuBar.add(menuAbout);

		// get system available lookAndFeel
		LookAndFeelInfo[] lookAndFeelArray = UIManager
				.getInstalledLookAndFeels();

		for (LookAndFeelInfo l : lookAndFeelArray) {

			JMenuItem item = new JMenuItem();
			item.setText(l.getName());
			item.setToolTipText(l.getClassName());

			item.addActionListener(this);
			menuLookAndFeel.add(item);

		}

		// add textArea to MainPanel
		mainPanel.setViewportView(txtArea);

		// adding component to frame
		this.setJMenuBar(menuBar);
		this.add(mainPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == menuItemNew) {
			if (JOptionPane.showConfirmDialog(this,
					"Are you sure to open new Text Editor ?") == JOptionPane.OK_OPTION) {
				txtArea.setText("");
			}

		} else if (e.getSource() == menuItemOpen) {

			if (JOptionPane.showConfirmDialog(this,
					"Are you sure to open new file ?") == JOptionPane.OK_OPTION) {
				if (JFileChooser.APPROVE_OPTION == fileChooser
						.showOpenDialog(this)) {

					File file = fileChooser.getSelectedFile();

					try {
						FileInputStream fos = new FileInputStream(file);
						StringBuffer strBuffer = new StringBuffer();
						txtArea.setText("");
						int b;
						while ((b = fos.read()) != -1) {
							strBuffer.append(Character.toString((char) b));
						}
						txtArea.setText(strBuffer.toString());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this, e1.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this, e1.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}

		} else if (e.getSource() == menuItemSave) {

			if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this)) {
				File file = fileChooser.getSelectedFile();

				try {
					FileOutputStream fos = new FileOutputStream(file);

					fos.write(txtArea.getText().getBytes());

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}

		} else if (e.getSource() == menuItemExit) {

			if (JOptionPane.showConfirmDialog(this, "Are you sure to exit ?") == JOptionPane.OK_OPTION)
				dispose();

		} else if (e.getSource() == menuItemCopy) {

			txtArea.copy();

		} else if (e.getSource() == menuItemPast) {

			txtArea.paste();

		} else if (e.getSource() == menuItemDelete) {

			int start = txtArea.getSelectionStart();
			int end = txtArea.getSelectionEnd();
			String temp = txtArea.getText();

			String strStart = "";
			String strEnd = "";

			strStart = temp.substring(0, start);
			strEnd = temp.substring(end);

			temp = strStart + strEnd;

			txtArea.setText(temp);

		} else if (e.getSource() == menuItemSelectAll) {

			txtArea.selectAll();

		} else if (e.getSource() == menuItemAbout) {

			new AboutDialog(this);

		} else if (e.getSource() == menuItemUndo) {

			if (0 <= index && index <= historyVector.size()) {
				index = index == 0 ? 0 : index - 1;
				txtArea.setText(historyVector.get(index));
			}
			System.out.println(index + " - " + historyVector.get(index));

		} else if (e.getSource() == menuItemRedo) {

			if (0 <= index && index <= historyVector.size()) {
				index = index + 1 == historyVector.size() ? index : index + 1;

				txtArea.setText(historyVector.get(index));
			}

			System.out.println(index + " - " + historyVector.get(index));
		} else if (e.getSource() == menuItemFormat) {

			FontDialog fontDialog = new FontDialog(this, txtArea.getFont(),
					txtArea.getForeground());
			fontDialog.setVisible(true);

		} else if (e.getSource() instanceof JMenuItem) {

			JMenuItem item = (JMenuItem) e.getSource();

			try {
				if (item != null && item.getToolTipText() != null) {
					UIManager.setLookAndFeel(item.getToolTipText());
					SwingUtilities.updateComponentTreeUI(this);
				}
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if ((!e.isControlDown())
				|| (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V)) {
			updateHistoryVectory();
		}
	}

	private void updateHistoryVectory() {
		historyVector.add(txtArea.getText());
		index++;

		/*
		 * if(!historyVector.isEmpty()) for(int i = index+1 ; i <
		 * historyVector.size() ; i++) { historyVector.remove(i); }
		 */
	}

	class FontDialog extends JDialog implements ActionListener, ChangeListener,
			ListSelectionListener {

		private JList<String> fontJlist;
		private JComboBox<String> colorJComboBox;
		private JSlider sizeJSlider;
		private JPanel toolPanel;
		private JPanel samplePanel;
		private JLabel sampleLabel;
		private JPanel buttonPanel;
		private JButton applyButton;
		private JButton cancelButton;

		private Font font;
		private Color color;

		public FontDialog(JFrame f, Font font, Color color) {

			super(f, true);
			setTitle("Font Chooser");
			setSize(500, 300);
			setLayout(new BorderLayout());

			this.font = font;
			this.color = color;

			init();
			dynInit();

			sampleLabel.setForeground(color);
			sampleLabel.setFont(font);
		}

		public void init() {
			fontJlist = new JList<String>();
			colorJComboBox = new JComboBox<String>();
			sizeJSlider = new JSlider(JSlider.VERTICAL);
			toolPanel = new JPanel();

			applyButton = new JButton("Apply");
			cancelButton = new JButton("cancel");

			samplePanel = new JPanel();
			sampleLabel = new JLabel("Sample");
			buttonPanel = new JPanel();

			// font = sampleLabel.getFont();
			add(toolPanel, BorderLayout.CENTER);
			add(samplePanel, BorderLayout.SOUTH);

		}

		public void dynInit() {

			// font
			DefaultListModel<String> fontListModel = new DefaultListModel<String>();
			GraphicsEnvironment genv = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			for (String fontName : genv.getAvailableFontFamilyNames()) {
				fontListModel.addElement(fontName);
			}
			fontJlist.setModel(fontListModel);
			fontJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			fontJlist.addListSelectionListener(this);

			// color
			DefaultComboBoxModel<String> colorListModel = new DefaultComboBoxModel<String>();
			colorListModel.addElement("BLACK");
			colorListModel.addElement("RED");
			colorListModel.addElement("GREEN");
			colorListModel.addElement("BLUE");
			colorJComboBox.setModel(colorListModel);
			colorJComboBox.addActionListener(this);
			colorJComboBox.setRenderer(new ListCellRenderer<String>() {

				@Override
				public Component getListCellRendererComponent(
						JList<? extends String> list, String value, int index,
						boolean isSelected, boolean cellHasFocus) {

					JLabel l = new JLabel(value);
					l.setOpaque(true);
					Color selectedColor = null;

					if (isSelected) {
						if ("RED" == value) {
							selectedColor = Color.RED;
						} else if ("GREEN" == value) {
							selectedColor = Color.GREEN;
						} else if ("BLUE" == value) {
							selectedColor = Color.BLUE;
						} else if ("BLACK" == value) {
							selectedColor = Color.BLACK;
						}
						l.setBackground(selectedColor);
						l.setForeground(Color.WHITE);

					} else {
						l.setBackground(Color.WHITE	);
					}
					return l;

				}
			});

			String selectedColor = "";
			if (Color.RED == color) {
				selectedColor = "RED";
			} else if (Color.GREEN == color) {
				selectedColor = "GREEN";
			} else if (Color.BLUE == color) {
				selectedColor = "BLUE";
			} else if (Color.BLACK == color) {
				selectedColor = "BLACK";
			}

			colorJComboBox.setSelectedItem(selectedColor);
			// font size

			sizeJSlider.setMaximum(60);
			sizeJSlider.setMinimum(10);
			sizeJSlider.setMajorTickSpacing(10);
			sizeJSlider.setMinorTickSpacing(5);
			sizeJSlider.setPaintTicks(true);
			sizeJSlider.setPaintLabels(true);
			sizeJSlider.setPaintTrack(true);
			sizeJSlider.setValue(font.getSize());

			sizeJSlider.addChangeListener(this);

			// button
			applyButton.addActionListener(this);
			cancelButton.addActionListener(this);

			// adding to panel
			samplePanel.setLayout(new BorderLayout());

			buttonPanel.add(applyButton);
			buttonPanel.add(cancelButton);

			samplePanel.add(sampleLabel, BorderLayout.WEST);
			samplePanel.add(buttonPanel, BorderLayout.EAST);

			toolPanel.setLayout(new FlowLayout());
			JScrollPane fontScrollPane = new JScrollPane();
			fontScrollPane.setSize(50, 100);
			fontScrollPane.setViewportView(fontJlist);
			toolPanel.add(fontScrollPane);
			toolPanel.add(colorJComboBox);
			toolPanel.add(sizeJSlider);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (e.getSource() == applyButton) {

				txtArea.setFont(font);
				txtArea.setForeground(color);
				dispose();

			} else if (e.getSource() == cancelButton) {
				dispose();
			} else if (e.getSource() == colorJComboBox) {
				String selectedColor = colorJComboBox.getSelectedItem()
						.toString();
				if ("RED".equals(selectedColor)) {
					sampleLabel.setForeground(Color.RED);
					color = Color.RED;
				} else if ("GREEN".equals(selectedColor)) {
					sampleLabel.setForeground(Color.GREEN);
					color = Color.GREEN;
				} else if ("BLUE".equals(selectedColor)) {
					sampleLabel.setForeground(Color.BLUE);
					color = Color.BLUE;
				} else if ("BLACK".equals(selectedColor)) {
					sampleLabel.setForeground(Color.BLACK);
					color = Color.BLACK;
				}

			}

		}

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			int fontSize = 0;

			fontSize = ((JSlider) e.getSource()).getValue();

			font = new Font(font.getName(), font.getStyle(), fontSize);
			sampleLabel.setFont(font);

		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			String fontName = ((JList<String>) e.getSource())
					.getSelectedValue().toString();

			font = new Font(fontName, font.getStyle(), font.getSize());
			sampleLabel.setFont(font);

		}

		public void setFont(Font font) {
			this.font = font;
		}

		public Font getFont() {
			return font;
		}

		public void setColor(Color color) {
			sampleLabel.setForeground(color);
		}

		public Color getColor() {
			return sampleLabel.getForeground();
		}

	}

	class AboutDialog extends JDialog {

		private JLabel dialogText;
		private JLabel dialogImg;

		public AboutDialog(TextEditor t) {
			// TODO Auto-generated constructor stub
			super(t);

			dialogImg = new JLabel(new ImageIcon("Info.png"));
			dialogText = new JLabel(
					"<html><b>Text Editor version 0.1</b><br><br>Open Source Software<br>"
							+ "Developed by : Mohamed Refaat<br />"
							+ "Email        : Eltntawy@gmail.com</html>");
			setTitle("About");

			setLayout(new FlowLayout());

			add(dialogImg);
			add(dialogText);

			setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			setLocation(100, 100);
			setSize(new Dimension(500, 125));
			setResizable(false);
			setVisible(true);
		}

	}

	public static void main(String[] args) {

		new TextEditor();
	}

}
