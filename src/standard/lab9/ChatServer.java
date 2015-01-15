package standard.lab9;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

public class ChatServer {

    
    public ChatServer() throws IOException{
	
	ServerSocket serverSocket = new ServerSocket(8888);
	while(true) {
	    Socket s = serverSocket.accept();
	    System.out.println(new Date() + " : client "+s.getInetAddress().getHostAddress()+" connected");
	    new ChatHandlerThread(s);
	}
	
    }
    

    
    
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	System.out.println(new Date() + " : server started");
	System.out.println(new Date() + " : waiting for clients");
	try {
	    new ChatServer();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}


class ChatHandlerThread extends Thread {
	
	static Vector <ChatHandlerThread> clientVector = new Vector<ChatHandlerThread>();
	
	Socket clientSocket ;
	
	DataInputStream dis;
	DataOutputStream dos;
	
	public ChatHandlerThread (Socket socket) throws IOException {
	
	    clientSocket = socket;
	    dis = new DataInputStream(socket.getInputStream());
	    dos = new DataOutputStream(socket.getOutputStream());
	    
	    clientVector.add(this);
	    start();
	}
	
	
	@Override
	public void run() {
	// TODO Auto-generated method stub
	    while (true) {
		try {
		    
		    String s = dis.readUTF();
		    if("EOF".equals(s)) {
			clientVector.remove(this);
			break;
		    } else {
			brodcastMessage(s);
		    }
		    
		    
		} catch (IOException e) {
		    e.printStackTrace();
		    clientVector.remove(this);
		    break;
		}
	    }
	}
	
	void brodcastMessage(String s) throws IOException {
	    
	    System.out.print(new Date() + " : "+clientSocket.getInetAddress().getHostAddress()+" - "+s+"\n");
	    for(ChatHandlerThread chat : clientVector) {
		chat.dos.writeUTF(clientSocket.getInetAddress().getHostAddress() + " : "+s+"\n");
		chat.dos.writeUTF(getClientsAsString());
		
		
	    }
	}
	
	String getClientsAsString() {
	    String ret = "";
	    for(ChatHandlerThread client : clientVector) {
		ret+=client.clientSocket.getInetAddress().getHostAddress()+"#";
	    }
	    return ret;
	}
}






