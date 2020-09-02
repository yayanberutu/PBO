import java.io.*;
import java.net.*;
import java.util.Hashtable;
import java.util.StringTokenizer;

class ClientConnection implements Runnable{
	private Server server;
	private Socket socket;
	
	private String id;
	private String name;
	private InetAddress ia;
	
	private BufferedReader in;
	private PrintWriter out;
	
// constructor contains : a Server, the connection in an Socket object, the connection's id.
	public ClientConnection (Server _server, Socket _socket, int _id){
		try{
			server = _server;
			socket = _socket;
			id = "" + _id;
			ia = socket.getInetAddress();
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
			write("ID " + id);
			new Thread(this).start();
		}catch(IOException ioe){
			System.out.println("\n An IOException occured! (" + ioe + ")");
		}catch(Exception e){
			System.out.println("\n An Exception occured! (" + e + ")");
		}
	}
	
// bellow are setters
	
	private void setName(String _name){
		name = _name;
		server.register(this);
	}

// bellow are getters and toString
	public String toString(){
		return (id + " " + name + " " + socket.toString());
	}
	
	public String getID(){
		return (id);
	}
	
	public String getName(){
		return (name);
	}
	
	public String getInetAddress(){
		return(ia.toString());
	}
	
// bellow are needed methods in communicating to other online users.

	private String read(){
		try{
			return(in.readLine());
		}catch(SocketException se){
			System.out.println("\n An SocketException occured! (" + se + ")");
			server.kill(this);
			return (null);
		}catch(Exception e){
			System.out.println("\n An Exception occured! (" + e + ")");
			server.kill(this);
			return (null);
		}
	}
	
	public void write(String _message){
		try{
			out.println("" + _message);
		}catch(Exception e){
			System.out.println("\n An Exception occured! (" + e + ")");
		}
	}

// bellow are methods in maintain connection.
	
	public void close(){
		server.kill(this);
		try{
			socket.close();
		}catch(IOException ioe){
			System.out.println("\n An IOException occured! (" + ioe + ")");
		}
	}
	
// bellow are needed attributes and methods while this object is running.

	private final int ADD = 0;
	private final int REMOVE = 1;
	private final int CHAT = 2;
	private final int PUBLIC = 3;
	private final int TELL = 4;
	private final int REGISTER = 5;
	private Hashtable keys = new Hashtable();
	private String keystring[] = {"ADD", "REMOVE", "CHAT", "PUBLIC", "TELL", "REGISTER"};
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
		server.setConnectionTable(id, this);
		
		String s;
		StringTokenizer st;
		String keyword;
		
		while((s = read()) != null){
			System.out.println(id + "CC:" + s);
			st = new StringTokenizer(s);
			keyword = st.nextToken();
			switch(lookup(keyword)){
				case ADD:{
					server.broadcast(id, s);
					break;
				}
				case REMOVE:{
//					server.broadcast(id, s);
//					server.remove(this);
					server.kill(this);
					break;
				}
				case CHAT:{
					keyword = st.nextToken();
					if(lookup(keyword) == PUBLIC){
						String _message = st.nextToken();
						while(st.hasMoreTokens()){
							_message = _message + " " + st.nextToken();
						}
						server.broadcast(id, "CHAT PUBLIC " + _message);
					}else{
						String _otherID = st.nextToken();
						String _message = st.nextToken();
						while(st.hasMoreTokens()){
							_message = _message + " " + st.nextToken();
						}
						server.send(_otherID, "CHAT PRIVATE " + id + " " + name + " " + _message);
					}
					break;
				}
				case TELL:{
					String _id = st.nextToken();
					String _message = st.nextToken();
					while(st.hasMoreTokens()){
						_message = _message + " " + st.nextToken();
					}
					server.send(_id, _message);
					break;
				}
				case REGISTER:{
					String _name = st.nextToken();
					while(st.hasMoreTokens()){
						_name = _name + " " + st.nextToken();
					}
					setName(_name);
					break;
				}
				default:{
					System.out.println(s);
				}
			}
		}
	}
}