package main.java.standard.lab7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

public class ShardModel extends JFrame implements ActionListener {

	JList<String> list1;
	JComboBox<String> list2;

	private JScrollPane list1Pane;
	private JScrollPane list2Pane;
	private JPanel toolPanel;
	private JPanel centerPanel;

	private JButton btnAdd;
	private JButton btnRemove;

	public ShardModel() {
		// TODO Auto-generated method stub
		super("Tow List");

		setSize(600, 350);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		init();
		setVisible(true);
	}

	void init() {

		list1 = new JList<String>();
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.setEnabled(false);
		list2 = new JComboBox<String>();

		MyListModel<String> list1Model = new MyListModel<String>();

		list1Model.addElement("Element 1");
		list1Model.addElement("Element 2");
		list1Model.addElement("Element 3");
		list1Model.addElement("Element 4");
		list1Model.addElement("Element 5");
		list1Model.addElement("Element 6");

		list1.setModel(list1Model);
		list2.setModel(list1Model);

		// list1.setPrototypeCellValue("12345678901234567890");
		// list2.setPrototypeCellValue("12345678901234567890");

		list1Pane = new JScrollPane();
		list1Pane
				.setViewportBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		list2Pane = new JScrollPane();
		list2Pane
				.setViewportBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		list1Pane.setPreferredSize(new Dimension(200, 300));
		 list2Pane.setPreferredSize(new Dimension(200,45));

		list1Pane.setViewportView(list1);
		list2Pane.setViewportView(list2);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(list1Pane);

		btnAdd = new JButton("add");
		btnAdd.setMinimumSize(new Dimension(50, 50));
		btnRemove = new JButton("remove");
		btnRemove.setMinimumSize(new Dimension(50, 50));

		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);

		toolPanel = new JPanel();
		toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

		centerPanel.add(toolPanel);

		toolPanel.add(btnAdd);
		toolPanel.add(btnRemove);

		getContentPane().add(centerPanel);
		getContentPane().add(list2);

	}

	public void removeElement(int index) {

		
		((MyListModel<String >)list2.getModel()).removeElement(index);
		
		MyListModel<String> tempModel = new MyListModel<String>();
		
		for (String s : ((MyListModel<String>) list2.getModel()).dataVector) {
			
			tempModel.addElement(s);
			
		}
		
		list1.setBackground(Color.yellow);
		list1.setModel(tempModel);
	}
	
	
	public void addElement(String element) {
		((MyListModel<String >)list2.getModel()).addElement(element);
		
		MyListModel<String> tempModel = new MyListModel<String>();
		
		for (String s : ((MyListModel<String>) list2.getModel()).dataVector) {
			
			tempModel.addElement(s);
			
		}
		
		list1.setBackground(Color.green);
		list1.setModel(tempModel);
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnAdd) {
			String s = JOptionPane.showInputDialog("Enter the element");
			if(s != null)
				addElement(s);
			
		} else if (e.getSource() == btnRemove) {
			
			if(0 <= list2.getSelectedIndex() )
				removeElement(list2.getSelectedIndex());
			
		}

		list1.repaint();
		list2.repaint();

	}

	class MyListModel<E> extends AbstractListModel<E> implements
			ComboBoxModel<E> {

		Vector<E> dataVector = new Vector<E>();
		int selectedIndex = 0;

		
		public int getSize() {
			// TODO Auto-generated method stub
			return dataVector.size();
		}

		public void addElement(E element) {

			dataVector.add(element);
		}

		
		public E getElementAt(int index) {
			// TODO Auto-generated method stub
			return dataVector.get(index);

		}

		public void removeElement(int index) {
			dataVector.remove(index);
		}

		public void removeAll() {
			dataVector.removeAllElements();
		}

		
		public String toString() {
			// TODO Auto-generated method stub

			String ret = "";
			for (E s : dataVector) {
				ret += s.toString() + "\n";
			}
			return ret;
		}

		
		public void setSelectedItem(Object anItem) {
			// TODO Auto-generated method stub
			for (int i = 0; i < dataVector.size(); i++) {
				if (dataVector.get(i).equals(anItem)) {
					selectedIndex = i;
					return;
				}
			}
			selectedIndex = 0;
		}

		
		public Object getSelectedItem() {
			// TODO Auto-generated method stub
			if ( 0 < dataVector.size() && selectedIndex < dataVector.size() )
				return dataVector.get(selectedIndex);

			return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ShardModel();
	}
}
