import java.io.*;
import java.net.*;
import java.util.Hashtable;
import java.util.StringTokenizer;

class ServerConnection implements Runnable{
	private String serverIP;
	private int serverPort;
	private Socket socket;
	
	private String id;
	private String name;
	
	private BufferedReader in;
	private PrintWriter out;
	
	private PublicRoomChat PRC;
	private Hashtable users = new Hashtable();
	
	private boolean isConnected;

// constructor contains : a PublicRoomChat, the username, the server's IP, and the server's port (where the server listens).
	public ServerConnection(PublicRoomChat _PRC, String _name, String _serverIP, int _serverPort){
		PRC = _PRC;
		name = _name;
		
		serverIP = _serverIP;
		serverPort = _serverPort;
		
		System.out.println(" Trying to connect with : " + serverIP + " " + serverPort);
		
		if(setConnection()){
			System.out.println("Connection was established successfully.");
			PRC.connectionEstablished();
			new Thread(this).start();
			isConnected = true;
		}else{
			System.out.println("Connection was refused.");
			PRC.connectionRefused();
			isConnected = false;
		}		
	}
	
// bellow are fundamental methods.
	private boolean setConnection(){
		try{
			socket = new Socket(serverIP, serverPort);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			registerName();
			return (true);
		}catch(ConnectException ce){
			System.out.println("\n A ConnectException occured! (" + ce + ")");
			return (false);
		}catch(IOException ioe){
			System.out.println("\n An IOException occured! (" + ioe + ")");
			return (false);
		}catch(Exception e){
			System.out.println("\n An Exception occured! (" + e + ")");
			return (false);
		}
	}
	
	public void registerName(){
		write("REGISTER " + name);
	}
	
	public void closingConnection(){
		try{
			if(isConnected){
				write("REMOVE " + id + " " + name);
				socket.close();
				isConnected = false;
			}
			
		}catch(Exception e){}
	}
	
// bellow are setters
	
	private void setID(String _id){
		id = _id;
		PRC.setID(_id);
	}
	
// bellow are getters

	public String getID(){
		return (id);
	}
		
	public String getName(){
		return (name);
	}
	
// bellow are needed methods in communicating to other online users.
	
	private String read(){
		try{
			return(in.readLine());
		}catch(IOException ioe){
			System.out.println("\n An IOException occured! (" + ioe + ")");
			if(isConnected){
				PRC.connectionRefused();
			}
		}catch(Exception e){
			System.out.println("\n An Exception occured! (" + e + ")");
			if(isConnected){
				PRC.connectionRefused();
			}
		}
		return (null);
	}
	
	public void write(String _message){
		try{
			out.println(_message);
			System.out.println("write:" + _message);
		}catch(Exception e){
			System.out.println("\n An Exception occured! (" + e + ")");
		}
	}
	
	private void tellOtherUser(){
		write("ADD " + id + " " + name);
	}
	
	private void tellUser(String _id){
		write("TELL " + _id + " ADD " + id + " " + name);
	}
	
	private boolean addUser(String _id, String _name){
		users.remove(_id);
		users.put(_id, _name);
		
//		return (true);
		return(PRC.addUser(_id, _name));
	}
	
	private void removeUser(String _id, String _name){
		PRC.removeUser(_id, _name);
	}
	
	public void publicChat(String _message){
		write("CHAT PUBLIC " + id + " " + name + ">>" + _message);
		PRC.addMessage(id + " " + name + ">>" + _message);
	}
	
	public void privateChat(String _otherID, String _otherName, String _message){
		write("CHAT PRIVATE " + _otherID + " " + id + " " + name + ">>" + _message);
		PRC.routeMessage(_otherID + " " + _otherName, id + " " + name + ">>" + _message);
	}
	
// bellow are needed attributes and methods while this object is running.
	
	private static final int ID = 0;
	private static final int ADD = 1;
	private static final int REMOVE = 2;
	private static final int CHAT = 3;
	private static final int PUBLIC = 4;
	private static final int DISCONNECT = 5;
	private static final int KICKED = 6;
	private static Hashtable keys = new Hashtable();
	private static String keystring[] = {"ID", "ADD", "REMOVE", "CHAT", "PUBLIC", "DISCONNECT", "KICKED"};
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
	
	public void run(){
		String s = new String();
		String keyword = new String("");
		StringTokenizer st;
		
		while((s = read()) != null){
			System.out.println("SC:" + s);
			st = new StringTokenizer(s);
			keyword = st.nextToken();
			switch(lookup(keyword)){
				case ID:{
					setID(st.nextToken());
					PRC.addMessage("  ***  Welcome to the Public Room Chat  ***\n");
					tellOtherUser();
					break;
				}
				case ADD:{
					String _id = st.nextToken();
					String _name = st.nextToken();
					while(st.hasMoreTokens()){
						_name = _name + " " + st.nextToken();
					}
					
					if(!addUser(_id, _name)){
						tellUser(_id);
					}
					break;
				}
				case REMOVE:{
					String _id = st.nextToken();
					String _name = st.nextToken();
					while(st.hasMoreTokens()){
						_name = _name + " " + st.nextToken();
					}
					removeUser(_id, _name);
					break;
				}
				case CHAT:{
					keyword = st.nextToken();
					if(lookup(keyword) == PUBLIC){
						String _id = st.nextToken();
						String _message = _id.toString();
						while(st.hasMoreElements()){
							_message = _message + " " + st.nextToken();
						}
						PRC.addMessage(_message);
					}else{
						String _information = st.nextToken() + " " + st.nextToken();
						String _message = st.nextToken();
						while(st.hasMoreElements()){
							_message = _message + " " + st.nextToken();
						}
						PRC.routeMessage(_information, _message);
						System.out.println("here:" + _information + ":" + _message);
					}
					break;
				}
				case DISCONNECT:{
					PRC.connectionRefused();
					break;
				}
				case KICKED:{
					PRC.kicked();
					break;
				}
				default:{
					System.out.println(s);
				}
			}
		}
	}
}