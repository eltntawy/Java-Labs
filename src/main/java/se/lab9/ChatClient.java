package main.java.se.lab9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class ChatClient extends JFrame implements ActionListener {
    private JButton btnSend;
    private JScrollPane scrollPane;
    private JTextArea txtChatHistory;
    private JTextArea txtChat;
    private JPanel panel;

    private String address;
    private int port;

    private DataInputStream dis;
    private DataOutputStream dos;
    private JScrollPane onlineClientsPanel;
    private final JList onlineList = new JList();

    public ChatClient(String address, int port) {

	this.address = address;
	this.port = port;

	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(new Dimension(600, 400));
	setPreferredSize(new Dimension(600, 400));
	setTitle("Client Chat Application");
	init();
	dynInit();
	addWindowListener(new WindowListener() {

	    
	    public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	    }

	    
	    public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		sendMessage("EOF");
	    }

	    
	    public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	    }

	    
	    public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	    }

	    
	    public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	    }

	    
	    public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	    }

	    
	    public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	    }

	});

	try {
	    initSocketConnection();
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    JOptionPane.showMessageDialog(null, e.getMessage());
	    e.printStackTrace();
	    dispose();
	    System.exit(1);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    JOptionPane.showMessageDialog(null, e.getMessage());
	    e.printStackTrace();
	    dispose();
	    System.exit(1);
	}

    }

    public void init() {
	panel = new JPanel();
	panel.setLayout(new BorderLayout(0, 0));
	txtChat = new JTextArea();
	txtChat.setFont(new Font(txtChat.getFont().getName(), Font.BOLD, 13));
	txtChat.setPreferredSize(new Dimension(449, 100));
	btnSend = new JButton("Send");
	btnSend.setPreferredSize(new Dimension(149, 100));
	scrollPane = new JScrollPane();
	scrollPane.setSize(new Dimension(600, 500));
	txtChatHistory = new JTextArea();
	txtChatHistory.setFont(new Font(txtChat.getFont().getName(), Font.BOLD, 20));
	txtChatHistory.setAutoscrolls(true);
	txtChatHistory.setEditable(false);
	DefaultCaret caret = (DefaultCaret) txtChatHistory.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	btnSend.setEnabled(false);
    }

    public void dynInit() {

	btnSend.addActionListener(this);
	txtChat.addKeyListener(new KeyListener() {

	    
	    public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	    }

	    
	    public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	    }

	    
	    public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		e.consume();
		if(e.getKeyCode() == KeyEvent.VK_ENTER && !txtChat.getText().equals("")) {
		    sendMessage();
		    txtChat.setText("");
		}
		
		if(txtChat.getText().equals("") )
		    btnSend.setEnabled(false);
		else 
		    btnSend.setEnabled(true);
	    }
	});
	scrollPane.setViewportView(txtChatHistory);
	getContentPane().add(panel, BorderLayout.SOUTH);
	panel.add(txtChat, BorderLayout.WEST);
	panel.add(btnSend, BorderLayout.EAST);
	getContentPane().add(scrollPane, BorderLayout.CENTER);
	
	onlineClientsPanel = new JScrollPane();
	onlineClientsPanel.setPreferredSize(new Dimension(150,400));
	onlineClientsPanel.setViewportView(onlineList);
	getContentPane().add(onlineClientsPanel,BorderLayout.EAST);
	
    }

    public void initSocketConnection() throws IOException {

	Socket s = new Socket(address, port);
	dis = new DataInputStream(s.getInputStream());
	dos = new DataOutputStream(s.getOutputStream());

	new ChatHandlerClientReadThread(dis, txtChatHistory, onlineList).start();

    }

    
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

	if (e.getSource() == btnSend && !txtChat.getText().equals("")) {
	    sendMessage();
	}
    }

    void sendMessage() {
	new ChatHandlerClientWriteThread(dos, txtChat.getText()).start();
	txtChat.setText("");
	btnSend.setEnabled(false);
    }

    void sendMessage(String string) {
	// TODO Auto-generated method stub
	new ChatHandlerClientWriteThread(dos, string).start();
	txtChat.setText("");
	btnSend.setEnabled(false);
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

	String address = JOptionPane.showInputDialog(null, "Please enter server address :", "127.0.0.1");
	if (address == null) {
	    return;
	}
	int port = 0;

	try {

	    String port1 = JOptionPane.showInputDialog(null, "please enter your server port", "8888");
	    if (port1 == null) {
		return;
	    }
	    port = Integer.parseInt(port1);
	    new ChatClient(address, port).setVisible(true);

	} catch (NumberFormatException e) {
	    JOptionPane.showMessageDialog(null, "Please enter valid data");

	    e.printStackTrace();
	}

    }
}

class ChatHandlerClientWriteThread extends Thread {

    DataOutputStream dos;

    String msg;

    public ChatHandlerClientWriteThread(DataOutputStream dos, String msg) {
	this.dos = dos;
	this.msg = msg;
    }

    
    @Override
    public void run() {
	try {

	    dos.writeUTF(msg);

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    JOptionPane.showMessageDialog(null, "No Connection");
	    e.printStackTrace();
	}

    }

}

class ChatHandlerClientReadThread extends Thread {

    DataInputStream dis;

    JTextArea txtAreaHistory;
    JList<String> onlineList;

    public ChatHandlerClientReadThread(DataInputStream dis, JTextArea txtAreaHistory, JList<String> onLineList) {

	this.dis = dis;
	this.onlineList = onLineList;
	this.txtAreaHistory = txtAreaHistory;

    }

    
    @Override
    public void run() {
	try {

	    while (true) {
		String s = dis.readUTF();
		String online = dis.readUTF();

		DefaultListModel<String> model = new DefaultListModel<String>();
		StringTokenizer strTkn = new StringTokenizer(online, "#");
		while (strTkn.hasMoreElements()) {
		    model.addElement(strTkn.nextToken());
		}
		onlineList.setModel(model);
		onlineList.repaint();
		txtAreaHistory.append(s + "");

	    }

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    JOptionPane.showMessageDialog(null, "Connection Loss");
	    e.printStackTrace();
	    try {
		dis.close();
	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	}

    }

}
