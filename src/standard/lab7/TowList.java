package standard.lab7;

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

		setSize(500, 300);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		init();
		setVisible(true);
	}

	void init() {

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
		centerPanel.setLayout(new FlowLayout());

		centerPanel.add(toolPanel);

		list1 = new JList<String>();
		list2 = new JList<String>();
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

		list1Pane = new JScrollPane();
		list2Pane = new JScrollPane();

		list1Pane.setSize(100, 200);
		list2Pane.setSize(100, 200);

		list1Pane.add(list1);
		list2Pane.add(list2);

		toolPanel.add(btnAddAll);
		toolPanel.add(btnAdd);
		toolPanel.add(btnRemove);
		toolPanel.add(btnRemoveAll);

		add(centerPanel, BorderLayout.CENTER);
		add(list1, BorderLayout.WEST);
		add(list2, BorderLayout.EAST);

	}

	public void moveElementFromTo(JList<String> listFrom, JList<String> listTo) {

		String temp = listFrom.getSelectedValue();
		((MyListModel<String>) (listFrom.getModel())).removeElement(listFrom.getSelectedIndex());

		((MyListModel<String>) listTo.getModel()).addElement(temp);

	}

	public void moveAllElementFromTo(JList<String> listFrom,
			JList<String> listTo) {

		MyListModel<String> tempModel = new MyListModel<String>();
		for (String s : ((MyListModel<String>) listFrom.getModel()).dataVector) {
			tempModel.addElement(s);
		}

		listTo.setModel(tempModel);
		((MyListModel<String>) listFrom.getModel()).removeAll();

	}

	@Override
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

		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return dataVector.size();
		}

		public void addElement(E element) {

			dataVector.add(element);
		}

		@Override
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

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			
			String ret = "" ;
			for(E s : dataVector ) {
				ret+=s.toString()+"\n";
			}
			return ret;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TowList();
	}

}
