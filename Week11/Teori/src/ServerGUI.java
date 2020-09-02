import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.swing.*;

public class ServerGUI implements ActionListener{
	// Main object
	Server server;
	
	private int serverPort;
	private static final int DEFAULT_SERVER_PORT = 2558;
	
	// GUI objects
	private JScrollPane jspServerActivity;
	private JScrollPane jspUsers;
	
	private JButton jbKickUser;
	private JButton jbSend;
	private JButton jbStartService;
	
	private JFrame mainFrame;
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	private JLabel jl4;
	private JLabel jlUser;
	private List users;
	
	private JPanel mainPanel;

	private JTextArea jtaServerActivity;
	
	private JTextField jtfServerPort;
	private JTextField jtfMessage;
	
	public ServerGUI() {
		initComponents();
	}

	private void initComponents(){
		mainFrame = new JFrame("  Server side chat application  ");
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		// for the server activity
		jl1 = new JLabel();
		jl1.setFont(new java.awt.Font("Tahoma", 0, 10));
		jl1.setText("Server Activity :");
		jl1.setBounds(10, 10, 74, 13);
		mainPanel.add(jl1);
		
		jtaServerActivity = new JTextArea();
		jtaServerActivity.setColumns(20);
		jtaServerActivity.setFont(new java.awt.Font("Tahoma", 0, 10));
		jtaServerActivity.setRows(5);
		
		jspServerActivity = new JScrollPane();
		jspServerActivity.setBounds(10, 30, 280, 218);
		jspServerActivity.setViewportView(jtaServerActivity);
		mainPanel.add(jspServerActivity);
		
		// for setup server configuration
		jl4 = new JLabel();
		jl4.setFont(new java.awt.Font("Tahoma", 0, 10));
		jl4.setText("Setup your sever configuration");
		jl4.setBounds(300, 10, 150, 14);
		mainPanel.add(jl4);
		
		jl2 = new JLabel();
		jl2.setFont(new java.awt.Font("Tahoma", 0, 10));
		jl2.setText("Server port    :");
		jl2.setBounds(300, 30, 68, 13);
		mainPanel.add(jl2);
		
		jtfServerPort = new JTextField();
		jtfServerPort.setFont(new java.awt.Font("Tahoma", 0, 10));
		jtfServerPort.setText("" + DEFAULT_SERVER_PORT);
        jtfServerPort.setBounds(380, 30, 59, 18);
        mainPanel.add(jtfServerPort);
		
		jbStartService = new JButton();
		jbStartService.setFont(new java.awt.Font("Tahoma", 0, 10));
		jbStartService.setText("Start Service");
		jbStartService.setBounds(300, 50, 140, 21);
		jbStartService.addActionListener(this);
		mainPanel.add(jbStartService);
		
		// for online user
		jlUser = new JLabel();
		jlUser.setFont(new java.awt.Font("Tahoma", 0, 10));
		jlUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jlUser.setText("jlUser");
		jlUser.setBounds(310, 80, 100, 13);
		mainPanel.add(jlUser);
		
		users = new List();
		users.setFont(new java.awt.Font("Tahoma", 0, 10));
		users.setMultipleSelections(false);
		users.setVisible(true);
		users.setBounds(300, 100, 140, 120);
		mainPanel.add(users);
		
		jbKickUser = new JButton();
		jbKickUser.setFont(new java.awt.Font("Tahoma", 0, 10));
		jbKickUser.setText("Kick user");
		jbKickUser.setBounds(300, 230, 140, 21);
		jbKickUser.addActionListener(this);
		mainPanel.add(jbKickUser);
		
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
		
		disable();
		setTotalUser(0);
    }
    
    private void setServerPort(){
    	serverPort = Integer.parseInt(jtfServerPort.getText().trim());
    }
    
    // set users label with current online user
	private void setTotalUser(int _totalUser){
		jlUser.setText("(" + _totalUser + ") Online user(s) :");
	}
    
//    private void test(){
//    	String[] arrUser;
//    	arrUser = new String[jlUsers.getModel().getSize()];
//    	for(int i = 0; i < jlUsers.getModel().getSize(); i++) {
//    		arrUser[i] = (String) jlUsers.getModel().getElementAt(i);
//			JOptionPane.showMessageDialog(null, i + " " + arrUser[i], "", JOptionPane.INFORMATION_MESSAGE);
//		}
//		rearrangeUsers();
//    }

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ServerGUI();
			}
		});
	}

	private void enableServerActivity(){
		jtaServerActivity.setEditable(true);
	}
		
	private void disableServerActivity(){
		jtaServerActivity.setEditable(false);
	}
	
	private void enableUsers(){
		users.setEnabled(true);
		jbKickUser.setEnabled(true);
	}
	
	private void disableUsers(){
		users.setEnabled(false);
		jbKickUser.setEnabled(false);
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
		jtfServerPort.setEnabled(true);
		jbStartService.setText("Start Service");
	}
	
	private void disableConfiguring(){
		jtfServerPort.setEnabled(false);
		jbStartService.setText("Stop Service");
	}
	
	public void enable(){
		enableServerActivity();
		enableUsers();
		enableMessage();
	}
	
	public void disable(){
		disableServerActivity();
		disableUsers();
		disableMessage();
	}
	
	private void serviceStarted(){
		disableConfiguring();
	}
	
	private void serviceStoped(){
		enableConfiguring();
		disable();
	}
	
	private boolean configurationValidator(){
		boolean _Port = true;
		String _serverPort;
		String _message = new String("");
		
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
				if((_value < 0) || (_value > 65000)){
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
		
		return (_Port);
	}
		
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
			enableMessage();
		}
		
		setTotalUser(users.getItemCount());
	}
	
	public void addUser(String _id, String _name, String _ia){
		String _information = new String(_id + " " + _name + " (" + _ia + ")");
		String[] allUsers = users.getItems();
		boolean isExist = false;
		int i = 0;
		
		for(i = 0; i < users.getItemCount(); ++i){
			System.out.println("sasas");
			System.out.println(allUsers[i]);
			if(_information.equals(allUsers[i])){
				isExist = true;
			}
		}
		
		if(isExist == false){
			users.add(_information);
			rearrangeUsers();
			addActivity("  ***  [" + _information + "] joined the room  ***");
		}
		
	}
	
	public void removeUser(String _id, String _name, String _ia){
		String _information = new String(_id + " " + _name + " (" + _ia + ")");
		users.remove(_information);
		rearrangeUsers();
		addActivity("  ***  [" + _information + "] left the room  ***");
	}
	
	public void addActivity(String _activity){
		jtaServerActivity.append(_activity + "\n");
	}
	
	public void serviceStartedSuccessfully(){
		serviceStarted();
		JOptionPane.showMessageDialog(null, " Service is run successfully on : " + serverPort + " !\n", " Service Started", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void startServiceFailed(){
		serviceStoped();
		server.refuseAllUsers();
		JOptionPane.showMessageDialog(null, " Unable to run server service on port : " + serverPort + " !", " Service Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void sendMessage(){
		server.broadcast("-1", "CHAT PUBLIC SERVER>>" + jtfMessage.getText());
		jtfMessage.setText("");
	}
	
	private void kickUser(){
		String _information = users.getSelectedItem();
		server.kickUser(_information);
	}
	
	
	public void actionPerformed(ActionEvent e){
		String actionCommand = e.getActionCommand();

		if("Start Service".equalsIgnoreCase(actionCommand)){
			if(configurationValidator()){
				setServerPort();
				server = new Server(this, serverPort);
			}
		}else if("Stop Service".equalsIgnoreCase(actionCommand)){
			serviceStoped();
			server.stopRunning();
			JOptionPane.showMessageDialog(null, " Service is stoped successfully on : " + serverPort + " !\n", " Service Stoped", JOptionPane.INFORMATION_MESSAGE);
		}else if("Send".equalsIgnoreCase(e.getActionCommand())){
			sendMessage();
		}else if("Kick user".equalsIgnoreCase(e.getActionCommand())){
			kickUser();
		}
	}
}
