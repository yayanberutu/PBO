import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.swing.*;

public class PublicRoomChat implements ActionListener{
	// main variables and declare objects
	private static final String DEFAULT_SERVER_IP = "localhost";
	private static final int DEFAULT_SERVER_PORT = 2558;
	
	ServerConnection server;
	
	private String id;
	private String name;
	
	private String serverIP;
	private int serverPort;
	
	private Hashtable PrivateRoomChatTable = new Hashtable();
	private PrivateRoomChat PriRoCha;
	
	// GUI objects
	private JScrollPane jspMessages;
	private JScrollPane jspUsers;
	
	private JButton jbChat;
	private JButton jbSend;
	private JButton jbConnect;
	
	private JFrame mainFrame;
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	private JLabel jl4;
	private JLabel jl5;
	private JLabel jlUser;
	private List users;
	
	private JPanel mainPanel;

	private JTextArea jtaMessages;
	
	private JTextField jtfServerIP;
	private JTextField jtfServerPort;
	private JTextField jtfMessage;
	
	private DefaultListModel dlm;
	
	public PublicRoomChat() {
		getUserName();
		initComponents();
	}

	private void initComponents(){
		mainFrame = new JFrame("  Public Room Chat [user : " + name + "]");
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		// for the server activity
		jl1 = new JLabel();
		jl1.setFont(new java.awt.Font("Tahoma", 0, 10));
		jl1.setText("All Messages :");
		jl1.setBounds(10, 10, 74, 13);
		mainPanel.add(jl1);
		
		jtaMessages = new JTextArea();
		jtaMessages.setColumns(20);
		jtaMessages.setFont(new java.awt.Font("Tahoma", 0, 10));
		
		jspMessages = new JScrollPane();
		jspMessages.setBounds(10, 30, 280, 218);
		jspMessages.setViewportView(jtaMessages);
		mainPanel.add(jspMessages);
		
		// for setup server configuration
		jl4 = new JLabel();
		jl4.setFont(new java.awt.Font("Tahoma", 0, 10));
		jl4.setText("Configure your connection");
		jl4.setBounds(300, 10, 150, 14);
		mainPanel.add(jl4);
		
		jl5 = new JLabel();
		jl5.setFont(new java.awt.Font("Tahoma", 0, 10));
		jl5.setText("Server IP    :");
		jl5.setBounds(300, 30, 65, 13);
		mainPanel.add(jl5);
		
		jtfServerIP = new JTextField();
		jtfServerIP.setFont(new java.awt.Font("Tahoma", 0, 10));
		jtfServerIP.setText("localhost");
		jtfServerIP.setDragEnabled(false);
        jtfServerIP.setBounds(360, 30, 80, 18);
        mainPanel.add(jtfServerIP);
        
        jl2 = new JLabel();
		jl2.setFont(new java.awt.Font("Tahoma", 0, 10));
		jl2.setText("Server port :");
		jl2.setBounds(300, 50, 68, 13);
		mainPanel.add(jl2);
		
		jtfServerPort = new JTextField();
		jtfServerPort.setFont(new java.awt.Font("Tahoma", 0, 10));
		jtfServerPort.setText("" + DEFAULT_SERVER_PORT);
		jtfServerPort.setDragEnabled(false);
        jtfServerPort.setBounds(360, 50, 50, 18);
        mainPanel.add(jtfServerPort);
		
		jbConnect = new JButton();
		jbConnect.setFont(new java.awt.Font("Tahoma", 0, 10));
		jbConnect.setText("Connect");
		jbConnect.setBounds(300, 70, 140, 21);
		jbConnect.addActionListener(this);
		mainPanel.add(jbConnect);
		
		// for online user
		jlUser = new JLabel();
		jlUser.setFont(new java.awt.Font("Tahoma", 0, 10));
		jlUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		setTotalUser(0);
		jlUser.setBounds(300, 94, 140, 13);
		mainPanel.add(jlUser);
		
		users = new List();
		users.setFont(new java.awt.Font("Tahoma", 0, 10));
		users.setMultipleSelections(false);
		users.setVisible(true);
		users.setBounds(300, 110, 140, 120);
		mainPanel.add(users);
		
		jbChat = new JButton();
		jbChat.setFont(new java.awt.Font("Tahoma", 0, 10));
		jbChat.setText("Chat with...");
		jbChat.setBounds(300, 230, 140, 21);
		jbChat.addActionListener(this);
		mainPanel.add(jbChat);
		
		// for sending message
		jtfMessage = new JTextField();
		jtfMessage.setFont(new java.awt.Font("Tahoma", 0, 10));
		jtfMessage.setBounds(10, 270, 350, 20);
		mainPanel.add(jtfMessage);
		
		jbSend = new JButton();
		jbSend.setFont(new java.awt.Font("Tahoma", 0, 10));
		jbSend.setText("Send");
		jbSend.setBounds(370, 270, 70, 21);
		jbSend.addActionListener(this);
		mainPanel.add(jbSend);
        
		jl3 = new JLabel();
		jl3.setFont(new java.awt.Font("Tahoma", 0, 10));
		jl3.setText("------------------------------------------------------------------------------------------------------------");
		jl3.setBounds(10, 250, 450, 13);
		mainPanel.add(jl3);
		
		mainFrame.add(mainPanel);
		mainFrame.setBounds(200, 200, 460, 330);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dlm = new DefaultListModel();
		disable();
    }

	// to enable and disable GUI
    private void enableMessages(){
		jtaMessages.setEditable(true);
	}
		
	private void disableMessages(){
		jtaMessages.setEditable(false);
	}
	
	private void enableUsers(){
		users.setEnabled(true);
		jbChat.setEnabled(true);
	}
	
	private void disableUsers(){
		users.setEnabled(false);
		jbChat.setEnabled(false);
	}
	
	private void enableMessage(){
		jtfMessage.setEnabled(true);
		jbSend.setEnabled(true);
	}
	
	private void disableMessage(){
		jtfMessage.setEnabled(false);
		jbSend.setEnabled(false);
	}
	
	private void enableConfiguring(){
		jtfServerIP.setEnabled(true);
		jtfServerPort.setEnabled(true);
		jbConnect.setEnabled(true);
		jbConnect.setText("Connect");
	}
	
	private void disableConfiguring(){
		jtfServerIP.setEnabled(false);
		jtfServerPort.setEnabled(false);
		jbConnect.setText("Disconnect");
	}
	
	public void enable(){
		enableMessages();
		enableUsers();
		enableMessage();
		disableConfiguring();
	}
	
	public void disable(){
		disableMessages();
		disableUsers();
		disableMessage();
		enableConfiguring();
	}
    
    // bellow are setters
    public void setID(String _id){
		id = _id;
	}
	
    private void setServerIP(){
    	serverIP = jtfServerIP.getText().trim();
    }
    
    private void setServerPort(){
    	serverPort = Integer.parseInt(jtfServerPort.getText());
    }
    
    // set users label with current online user
	private void setTotalUser(int _totalUser){
		jlUser.setText("(" + _totalUser + ") Online user(s) :");
	}
    
    // get username from user. (validating included)
    private void getUserName(){
    	String _message = new String("");

		while(true){
			try{
				name = new String(JOptionPane.showInputDialog(null, _message + " Insert your name : ", " Register", JOptionPane.QUESTION_MESSAGE));
			}catch(Exception e){
				name = new String("anonymous");
			}
			name = name.trim();
			System.out.println(name.lastIndexOf(" "));
			if(name.length() > 0){
				if(name.lastIndexOf(" ") == -1){
					break;
				}
				_message = "You have entered an invalid name!\nWhite space is disallowed.\n";
			}else{
				_message = "You have entered an invalid name!\nWhite space is disallowed.\n";
			}
		}
    }
/*
// bellow are methods to managing user in the list.
    private void rearrangeUsers(){
		int size = jlUsers.getModel().getSize();
		
		if(size < 1){
			disable();
		}else{
			String[] allUsers = new String[size];
			String tmpUser = new String();
			
			for(int ctr = 0; ctr < size; ctr++) {
				allUsers[ctr] = (String) jlUsers.getModel().getElementAt(ctr);
			}
			
			for(int i = 0; i < (allUsers.length - 1); ++i){ // do sort
				for(int j = i; j < allUsers.length; ++j){
					if(allUsers[i].compareTo(allUsers[j]) == 1){
						tmpUser = allUsers[i];
						allUsers[i] = allUsers[j];
						allUsers[j] = tmpUser;
						
						--i;
						break;
					}
				}
			}
			
			jlUsers.setListData(allUsers);
			
			enable();
		}
		
		setTotalUser(size); // including me.
	}
	
	// checking if there is an existed user in the list
	private boolean isUserExist(String _id, String _name){
		int size = jlUsers.getModel().getSize();
    	String information = _id + " " + _name;
    	int ctr = 0;
    	boolean isExist = false;
    	
    	if(size > 0){
	    	for(ctr = 0; ctr < size; ctr++) {
	    		if(information.equalsIgnoreCase((String) jlUsers.getModel().getElementAt(ctr))){
	    			isExist = true;
	    			break;
	    		}
			}
    	}
		
		return (isExist);
	}
	
	// to add user in the list, before adding it will be checked first.
	public boolean addUser(String _id, String _name){
		int size = jlUsers.getModel().getSize();
		String[] allUsers;
    	allUsers = new String[size + 1];
    	int ctr = 0;
		boolean isExist = isUserExist(_id, _name);
    	
    	if(!isExist){ // the username does not exist
			for(ctr = 0; ctr < size; ctr++) {
				allUsers[ctr] = (String) jlUsers.getModel().getElementAt(ctr);
			}
		
			allUsers[ctr] = _id + " " + _name;
			
			jlUsers.setListData(allUsers);
		
			rearrangeUsers();
		}

		return (isExist);
	}
	
	// to remove user in the list, before removing it will be checked first.
	public void removeUser(String _id, String _name){
		int size = jlUsers.getModel().getSize();
		jUsers.getModel().
		String[] allUsers;
    	allUsers = new String[size + 1];
    	String aUser;
    	int ctr = 0;
		boolean isExist = isUserExist(_id, _name);
    	
		if(isExist){ // remove user if the user is exist.
			for(ctr = 0; ctr < size; ctr++) {
				aUser = (String) jlUsers.getModel().getElementAt(ctr);
				if(!aUser.equals(_id + " " + _name)){
					allUsers[ctr] = aUser;
				}
			}
			
			rearrangeUsers();
		}
	}
	
	public void clearUsers(){
		jlUsers = null;
		jlUsers = new JList();
		jlUsers.repaint();
		jspUsers.repaint();
	}
*/
	private void rearrangeUsers(){
		String[] allUsers = users.getItems();
		String tmpUser = new String();
		
		for(int i = 0; i < (allUsers.length - 1); ++i){
			for(int j = i; j < allUsers.length; ++j){
				if(allUsers[i].compareTo(allUsers[j]) == 1){
					tmpUser = allUsers[i];
					allUsers[i] = allUsers[j];
					allUsers[j] = tmpUser;
					
					--i;
					break;
				}
			}
		}
		
		users.removeAll();
		
		for(int i = 0; i < allUsers.length; ++i){
			users.add(allUsers[i]);
		}
		
		if(users.getItemCount() < 1){
			disableUsers();
		}else{
			enableUsers();
		}
		
		setTotalUser(users.getItemCount() + 1); // including me.
	}
	
	public boolean addUser(String _id, String _name){
		String s = new String(_id + " " + _name);
		String[] allUsers = users.getItems();
		boolean isExist = false;
		int i = 0;
		
		for(i = 0; i < users.getItemCount(); ++i){
			System.out.println("sasas");
			System.out.println(allUsers[i]);
			if(s.equals(allUsers[i])){
				isExist = true;
			}
		}
		
		if(isExist == false){
			users.add(s);
			rearrangeUsers();
			addMessage("  ***  [" + s + "] joined the room  ***");
		}
		
		return (isExist);
	}
	
	public void removeUser(String _id, String _name){
		users.remove(_id + " " + _name);
		removePrivateRoomChat(_id + " " + _name);
		rearrangeUsers();
		addMessage("  ***  [" + _id + " " + _name + "] left the room  ***");
	}
	
// bellow are mothods in messangging (is the message is public message or the private one)
	public void routeMessage(String _information, String _message){
		if(!PrivateRoomChatTable.containsKey(_information)){
			addPrivateRoomChat(_information);
		}
		PrivateRoomChat PriRoCha = (PrivateRoomChat) PrivateRoomChatTable.get(_information);
		PriRoCha.addMessage(_message);
		System.out.println("routing:" + _information + ":" + _message);
	}

	// to set a new PrivateRoomChat which will handle a private messaging.
	private void setPrivateRoomChatTable(String _information, PrivateRoomChat _PriRoCha){
		PrivateRoomChatTable.put(_information, _PriRoCha);
	}
	
	private void addPrivateRoomChat(String _information){
		PriRoCha = new PrivateRoomChat(server, id, name, _information); // server, targetname.
		setPrivateRoomChatTable(_information, PriRoCha);
	}
	
	// to remove a PrivateRoomChat, what it means? it means the private messaging with a specified users is ended.
	private void removePrivateRoomChat(String _information){
		if(PrivateRoomChatTable.containsKey(_information)){
			PrivateRoomChat PriRoCha = (PrivateRoomChat) PrivateRoomChatTable.get(_information); // server, targetname.
			JOptionPane.showMessageDialog(null, _information + " left the room.\n This session is going to be expired", " Expired session", JOptionPane.INFORMATION_MESSAGE);
			PriRoCha.endSession();
			PrivateRoomChatTable.remove(_information);
		}
	}
	
	// to remove all sessions (private messagings).
	private void refuseAllSessions(){
		Enumeration e = PrivateRoomChatTable.keys();
		String _information;
		
		if(e.hasMoreElements()){
			JOptionPane.showMessageDialog(null, " All sessions is going to be expired.", " Expired sessions", JOptionPane.ERROR_MESSAGE);
		}
		while(e.hasMoreElements()){
			_information = (String) e.nextElement();
			PrivateRoomChat PriRoCha = (PrivateRoomChat) PrivateRoomChatTable.get(_information);
			PriRoCha.endSession();
			PrivateRoomChatTable.remove(_information);
		}
	}

// bellow are methods to start PublicRoomChat and configuring connection.
    // to get the server IP and server Port to connect. (validating included)
	private boolean configurationValidator(){
		boolean isValid = true;
		boolean _IP = true;
		boolean _Port = true;
		String _serverIP;
		String _serverPort;
		String _message = new String("");
		
		if(jtfServerIP.getText() == null){
			_IP = false;
		}else if(jtfServerIP.getText().length() == 0){
			_IP = false;
		}else{
			_serverIP = jtfServerIP.getText();
			_serverIP = _serverIP.trim();
			
			if(_serverIP.equalsIgnoreCase("localhost")){
				_IP = true;
			}else if(_serverIP.length() < 7){
				_IP = false;
			}else{
				String _dot = new String(".");
				StringTokenizer _st = new StringTokenizer(_serverIP, _dot);
				int _value = 0;
				int index = 0;
				int dotOccurences = 0;

				if(_st.countTokens() != 4){
					_IP = false;
				}else{
					while(_st.hasMoreTokens()){
						try{
							_value = Integer.parseInt(_st.nextToken());
							if(_value < 0){
								_value = 0;
								_IP = false;
								break;
							}
						}catch(NumberFormatException nfe){
							_value = 0;
							_IP = false;
							break;
						}catch(Exception e){
							_value = 0;
							_IP = false;
							break;
						}
					}
				}
				
				index = _serverIP.indexOf(_dot);
				while(index >= 0){
					++dotOccurences;
					index += _dot.length();
					index = _serverIP.indexOf(_dot, index);
				}
				
				if(dotOccurences != 3){
					_IP = false;
				}
			}
		}
		
		if(_IP == false){
			_message = "Invalid server IP format.";
		}
		
		if(jtfServerPort.getText() == null){
			_Port = false;
		}else if(jtfServerPort.getText().length() == 0){
			_Port = false;
		}else{
			int _value = 0;
			_serverPort = jtfServerPort.getText();
			_serverPort = _serverPort.trim();
			
			try{
				_value = Integer.parseInt(_serverPort);
				if((_value < 0) || (_value > 6500)){
					_value = 0;
					_Port = false;
				}
			}catch(NumberFormatException nfe){
				_value = 0;
				_Port = false;
			}catch(Exception e){
				_value = 0;
				_Port = false;
			}
		}
		
		if(_Port == false){
			_message = _message + "\nInvalid server Port format.";
		}
		
		if(_message.length() > 0){
			JOptionPane.showMessageDialog(null, _message, " Error Configuration", JOptionPane.ERROR_MESSAGE);
		}
		
		return (_IP && _Port);
	}
	
	private void serviceStarted(){
		jtfServerPort.setEnabled(false);
		jbConnect.setText("Stop Service");
	}
	
	private void serviceStoped(){
		jtfServerPort.setEnabled(true);
		jbConnect.setText("Start Service");
		disable();
	}
	
	public void addMessage(String _message){
		jtaMessages.append(_message + "\n");
	}
    
    // main method
    public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PublicRoomChat();
			}
		});
	}
	
	// information messages
	public void connectionEstablished(){
		jtaMessages.setText("");
		enableMessage();
		setTotalUser(1); // only me.
		disableConfiguring();
		JOptionPane.showMessageDialog(null, " Connection was established successfully!\n You are connected with :\n Server IP     :  " + serverIP + "\n Server port :  " + serverPort, " Connection was established", JOptionPane.INFORMATION_MESSAGE);
	}
	
    public void connectionRefused(){
		disable();
		enableConfiguring();
		setTotalUser(0);
		refuseAllSessions();
		users.removeAll();
		JOptionPane.showMessageDialog(null, " Server service is unavailable or connection has been refused!", " Error connection", JOptionPane.ERROR_MESSAGE);
//		clearUsers();
	}
	
	public void disconnected(){
		disable();
		setTotalUser(0);
		refuseAllSessions();
		server.closingConnection();
		users.removeAll();
		addMessage("  ***  Disconnected with server.  ***");
		JOptionPane.showMessageDialog(null, " Disconnected with server.", " Disconnected", JOptionPane.INFORMATION_MESSAGE);
//		clearUsers();
	}

	public void kicked(){
		JOptionPane.showMessageDialog(null, " You have been kicked by the server!", "Kicked", JOptionPane.ERROR_MESSAGE);
		addMessage("  ***  Kicked by the server!  ***");
		disable();
		enableConfiguring();
		setTotalUser(0);
		refuseAllSessions();
		server.closingConnection();
		users.removeAll();
//		clearUsers();
	}
		
    // overriden method
    public void actionPerformed(ActionEvent e){
    	if("Connect".equalsIgnoreCase(e.getActionCommand())){
    		if(configurationValidator()){
				setServerIP();
				setServerPort();
				server = new ServerConnection(this, name, serverIP, serverPort);
				System.out.println(" " + serverIP + " " + serverPort);
    		}
		}else if("Disconnect".equalsIgnoreCase(e.getActionCommand())){
			disconnected();
		}else if("Send".equalsIgnoreCase(e.getActionCommand())){
			server.publicChat(jtfMessage.getText());
			jtfMessage.setText("");
		}else if("Chat with...".equalsIgnoreCase(e.getActionCommand())){
			addPrivateRoomChat(users.getSelectedItem()); // a harm line of code
		}
    }
}
