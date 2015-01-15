package standard.lab9;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultCaret;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient extends JDialog implements ActionListener {
    private JButton btnSend;
    private JScrollPane scrollPane;
    private JTextArea txtChatHistory;
    private JTextArea txtChat;
    private JPanel panel;

    private String address;
    private int port;

    private DataInputStream dis;
    private DataOutputStream dos;

    public ChatClient(String address, int port) {

	this.address = address;
	this.port = port;

	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(new Dimension(600, 400));
	setPreferredSize(new Dimension(600, 400));
	setTitle("Client Chat Application");
	init();
	dynInit();
	try {
	    initSocketConnection();
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    JOptionPane.showMessageDialog(null, e.getMessage());
	    e.printStackTrace();

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    JOptionPane.showMessageDialog(null, e.getMessage());
	    e.printStackTrace();
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
	DefaultCaret caret = (DefaultCaret)txtChatHistory.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public void dynInit() {

	btnSend.addActionListener(this);
	txtChat.addKeyListener(new KeyListener() {

	    @Override
	    public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
		    sendMessage();
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	    }
	});
	scrollPane.setViewportView(txtChatHistory);
	getContentPane().add(panel, BorderLayout.SOUTH);
	panel.add(txtChat, BorderLayout.WEST);
	panel.add(btnSend, BorderLayout.EAST);
	getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void initSocketConnection() throws IOException {

	try {
	    Socket s = new Socket(address, port);
	    dis = new DataInputStream(s.getInputStream());
	    dos = new DataOutputStream(s.getOutputStream());

	    new ChatHandlerClientReadThread(dis, txtChatHistory).start();

	} catch (UnknownHostException e) {
	    JOptionPane.showMessageDialog(null, e.getMessage());
	    dispose();
	    e.printStackTrace();
	}

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

	if (e.getSource() == btnSend) {
	    sendMessage();
	}
    }

    void sendMessage() {
	new ChatHandlerClientWriteThread(dos, txtChat.getText()).start();
	txtChat.setText("");
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

	String address = JOptionPane.showInputDialog(null, "Please enter server address :", "127.0.0.1");
	if(address == null) {
		return;
	    }
	int port = 0;

	try {

	    String port1 = JOptionPane.showInputDialog(null, "please enter your server port", "8888");
	    if(port1 == null) {
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

	} catch (IOException | NullPointerException e) {
	    // TODO Auto-generated catch block
	    JOptionPane.showMessageDialog(null, "No Connection");
	    e.printStackTrace();
	}

    }

}

class ChatHandlerClientReadThread extends Thread {

    DataInputStream dis;

    JTextArea txtAreaHistory;

    public ChatHandlerClientReadThread(DataInputStream dis, JTextArea txtAreaHistory) {

	this.dis = dis;

	this.txtAreaHistory = txtAreaHistory;

    }

    @Override
    public void run() {
	try {

	    while (true) {
		String s = dis.readUTF();
		txtAreaHistory.append(s + "");
		
	    }

	} catch (IOException | NullPointerException e) {
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
