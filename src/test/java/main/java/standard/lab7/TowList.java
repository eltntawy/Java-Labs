package main.java.standard.lab7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class TowList extends JFrame implements ActionListener {

	JList<String> list1;
	JList<String> list2;

	private JScrollPane list1Pane;
	private JScrollPane list2Pane;
	private JPanel toolPanel;
	private JPanel centerPanel;

	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnAddAll;
	private JButton btnRemoveAll;

	public TowList() {
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
		list2 = new JList<String>();
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MyListModel<String> list1Model = new MyListModel<String>();

		list1Model.addElement("Element 1");
		list1Model.addElement("Element 2");
		list1Model.addElement("Element 3");
		list1Model.addElement("Element 4");
		list1Model.addElement("Element 5");
		list1Model.addElement("Element 6");

		MyListModel<String> list2Model = new MyListModel<String>();
		list2Model.addElement("Element 1");
		list2Model.addElement("Element 2");
		list2Model.addElement("Element 3");
		list2Model.addElement("Element 4");
		list2Model.addElement("Element 5");
		list2Model.addElement("Element 6");

		list1.setModel(list1Model);
		list2.setModel(list2Model);
		
		//list1.setPrototypeCellValue("12345678901234567890"); 
		//list2.setPrototypeCellValue("12345678901234567890"); 

		list1Pane = new JScrollPane();
		list1Pane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		list2Pane = new JScrollPane();
		list2Pane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		list1Pane.setPreferredSize(new Dimension(200,300));
		list2Pane.setPreferredSize(new Dimension(200,300));
		
		

		list1Pane.setViewportView(list1);
		list2Pane.setViewportView(list2);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(list1Pane);
		
				btnAdd = new JButton(">");
				btnAdd.setMinimumSize(new Dimension(50, 50));
				btnRemove = new JButton("<");
				btnRemove.setMinimumSize(new Dimension(50, 50));
				btnAddAll = new JButton(">>");
				btnAddAll.setMinimumSize(new Dimension(50, 50));
				btnRemoveAll = new JButton("<<");
				btnRemoveAll.setMinimumSize(new Dimension(50, 50));
				
						btnAdd.addActionListener(this);
						btnRemove.addActionListener(this);
						btnAddAll.addActionListener(this);
						btnRemoveAll.addActionListener(this);
						
								toolPanel = new JPanel();
								toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));
								
										centerPanel = new JPanel();
										centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
										
												centerPanel.add(toolPanel);
												
														toolPanel.add(btnAddAll);
														toolPanel.add(btnAdd);
														toolPanel.add(btnRemove);
														toolPanel.add(btnRemoveAll);
														
																getContentPane().add(centerPanel);
		getContentPane().add(list2Pane);

	}

	public void moveElementFromTo(JList<String> listFrom, JList<String> listTo) {

		if (0 <= listFrom.getSelectedIndex()  && listFrom.getSelectedIndex() < listFrom.getModel().getSize()) {
			String temp = listFrom.getSelectedValue();
			((MyListModel<String>) (listFrom.getModel()))
					.removeElement(listFrom.getSelectedIndex());

			((MyListModel<String>) listTo.getModel()).addElement(temp);

			MyListModel<String> tempModel = new MyListModel<String>();
			for (String s : ((MyListModel<String>) listTo.getModel()).dataVector) {
				tempModel.addElement(s);
			}

			listTo.setModel(tempModel);

		}
	}

	public void moveAllElementFromTo(JList<String> listFrom,
			JList<String> listTo) {

		 
		MyListModel<String> tempModel = new MyListModel<String>();
		for (String s : ((MyListModel<String>) listFrom.getModel()).dataVector) {
			((MyListModel<String> ) listTo.getModel()).addElement(s);
		}
		
		for (String s : ((MyListModel<String>) listTo.getModel()).dataVector) {
			
			tempModel.addElement(s);
			
		}
		
		((MyListModel<String>) listFrom.getModel()).removeAll();
		listTo.setModel(tempModel);

	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btnAdd) {
			moveElementFromTo(list1, list2);
		} else if (e.getSource() == btnRemove) {
			moveElementFromTo(list2, list1);
		} else if (e.getSource() == btnAddAll) {
			moveAllElementFromTo(list1, list2);
		} else if (e.getSource() == btnRemoveAll) {
			moveAllElementFromTo(list2, list1);
		}

		list1.repaint();
		list2.repaint();

	}

	class MyListModel<E> extends AbstractListModel<E> {

		Vector<E> dataVector = new Vector<E>();

		
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TowList();
	}
}
