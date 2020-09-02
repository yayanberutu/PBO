import java.net.*;
import java.io.*;
import java.util.*;

class Server implements Runnable{
	private int serverPort;
	private ServerSocket serverSocket;
	private Socket socket = new Socket();
	private Hashtable connectionTable = new Hashtable();
	private ServerGUI serverGUI;
	private int id;
	private InetAddress ia;
	
	private boolean running;
	
	public Server(ServerGUI _serverGUI, int _serverPort){
		serverGUI = _serverGUI;
		serverPort = _serverPort;
		new Thread(this).start();
	}

// bellow are all fundamental methods
	
	synchronized void addConnection(){
		ClientConnection CC = new ClientConnection(this, socket, id);
		socket = null;
		++id;
	}

// bellow are methods in managing online users

	synchronized void setConnectionTable(String _id, ClientConnection _CC){
		connectionTable.remove(_id);
		connectionTable.put(_id, _CC);
	}
	
	synchronized void remove(ClientConnection _CC){
		broadcast(_CC.getID(), "REMOVE " + _CC.getID() + " " + _CC.getName());
	}
	
	synchronized void kickUser(String _information){
		StringTokenizer st = new StringTokenizer(_information);
		String _id = st.nextToken();
		ClientConnection CC = (ClientConnection) connectionTable.get(_id);
		CC.write("KICKED");
//		remove(CC);
	}
	
	synchronized void refuseAllUsers(){
		Enumeration e = connectionTable.keys();
		String _id = new String();
		
		while(e.hasMoreElements()){
			_id = (String) e.nextElement(); // read the keys one by one
			ClientConnection CC = (ClientConnection) connectionTable.get(_id);
			serverGUI.removeUser(CC.getID(), CC.getName(), CC.getInetAddress());
			send(_id, "DISCONNECT");
		}
	}
	
	synchronized void register(ClientConnection _CC){
		serverGUI.addUser(_CC.getID(), _CC.getName(), _CC.getInetAddress());
	}
	
	synchronized void kill(ClientConnection _CC){
		serverGUI.removeUser(_CC.getID(), _CC.getName(), _CC.getInetAddress());
		remove(_CC);
		connectionTable.remove(_CC.getID());
	}
	
	synchronized void stopRunning(){
		try{
			running = false;
			serverSocket.close();
			socket.close();
			System.out.println("closing");
		}catch (Exception e){}
	}
	
// bellow are methods in messanging over online user.
	// 'broadcast' method will send the message to all online users (the sender is excepted).
	synchronized void broadcast(String _id, String _message){
		watchUserActivity(_id, _message);
		Enumeration e = connectionTable.keys();
		String other = new String();
		
		while(e.hasMoreElements()){
			other = (String) e.nextElement(); // read the keys one by one
			ClientConnection CC = (ClientConnection) connectionTable.get(other);
			
			if(!other.equals(_id)){
				CC.write(_message);
			}
		}
	}
	
	// 'send' method will send the message to specified user.
	synchronized void send(String _id, String _message){
		watchUserActivity(_id, _message);
		System.out.println(_id + ":" + _message);
		ClientConnection CC = (ClientConnection) connectionTable.get(_id);
		CC.write(_message);
		System.out.println("_id:" + _id + " | _message" + _message);
	}
	
	private static final int CHAT = 0;
	private static final int PUBLIC = 1;
	private static final int PRIVATE = 2;
	private static Hashtable keys = new Hashtable();
	private static String keystring[] = {"CHAT", "PUBLIC", "PRIVATE"};
	static {
		for(int i = 0; i < keystring.length; ++i){
			keys.put(keystring[i], new Integer(i));
		}
	}
	
	private int lookup(String _s){
		Integer i = (Integer) keys.get(_s);
		return (i == null ? -1 : i.intValue());
	}
	
// bellow are overiden methods
	
	synchronized void watchUserActivity(String id, String _content){
		System.out.println(id + "||" + _content);
		String s = new String();
		String keyword = new String("");
		StringTokenizer st;
		
		st = new StringTokenizer(_content);
		keyword = st.nextToken();
		switch(lookup(keyword)){
			case CHAT:{
				keyword = st.nextToken();
				if(lookup(keyword) == PUBLIC){
					String _id = st.nextToken();
					String _message = _id.toString();
					while(st.hasMoreElements()){
						_message = _message + " " + st.nextToken();
					}
					addPublicActivity(_id, _message);
				}else{
					String _id = st.nextToken();
					st.nextToken();
					String _message = st.nextToken();
					while(st.hasMoreElements()){
						_message = _message + " " + st.nextToken();
					}
					addPrivateActivity(id, _message);
				}
				break;
			}
		}
	}
	
	synchronized void addPrivateActivity(String _id, String _message){
		
		ClientConnection CC = (ClientConnection) connectionTable.get(_id);
		serverGUI.addActivity("[Private to : " + CC.getID() + " " + CC.getName() + "] " + _message);
	}
	
	synchronized void addPublicActivity(String _id, String _message){
		ClientConnection CC = (ClientConnection) connectionTable.get(_id);
		serverGUI.addActivity("[Public] " + _message);
	}
	
// bellow are overiden methods
	
	public void run(){
		try{
			serverSocket = new ServerSocket(serverPort);
			System.out.println(" This server is listening on : " + serverPort);
			serverGUI.serviceStartedSuccessfully();
			running = true;
			while(running){
				socket = serverSocket.accept();
				ia = socket.getInetAddress();
				addConnection();
			}
		}catch(IOException ioe){
			if(running){
				serverGUI.startServiceFailed();
				System.out.println("\n An IOException occured! (" + ioe + ")"); // didisable
			}
		}catch(Exception e){
			if(running){
				serverGUI.startServiceFailed();
				System.out.println("\n An Exception occured! (" + e + ")");
			}
		}
	}
}