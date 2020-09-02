import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;

public class PrivateRoomChat implements ActionListener{
	// main variables and declare objects
	ServerConnection server;
	private String id;
	private String name;
	private String otherName;
	private String otherID;
	
	// GUI objects
	private JScrollPane jspMessages;
	
	private JButton jbSend;
	
	private JFrame mainFrame;
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	private JLabel jl4;
	private JLabel jl5;
	private JLabel jlUser;
	private JList jlUsers;
	
	private JPanel mainPanel;

	private JTextArea jtaMessages;
	
	private JTextField jtfMessage;
	
	public PrivateRoomChat(ServerConnection _server, String _id, String _name, String _information){
		server = _server;
		setID(_id);
		setName(_name);
				
		StringTokenizer st = new StringTokenizer(_information);
		setOtherID(st.nextToken());
		String _otherName = st.nextToken();
		while(st.hasMoreTokens()){
			_otherName = _otherName + " " + st.nextToken();
		}
		
		setOtherName(_otherName);
		
		initComponents();
	}

	private void initComponents(){
		mainFrame = new JFrame("  Public Room Chat [user : " + name + "] with [" + otherName + "]");
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
		jspMessages.setBounds(10, 30, 330, 218);
		jspMessages.setViewportView(jtaMessages);
		mainPanel.add(jspMessages);
				
		// for sending message
		jtfMessage = new JTextField();
		jtfMessage.setFont(new java.awt.Font("Tahoma", 0, 10));
		jtfMessage.setBounds(10, 270, 250, 20);
		mainPanel.add(jtfMessage);
		
		jbSend = new JButton();
		jbSend.setFont(new java.awt.Font("Tahoma", 0, 10));
		jbSend.setText("Send");
		jbSend.setBounds(267, 270, 73, 21);
		jbSend.addActionListener(this);
		mainPanel.add(jbSend);
        
		jl3 = new JLabel();
		jl3.setFont(new java.awt.Font("Tahoma", 0, 10));
		jl3.setText("----------------------------------------------------------------------------------");
		jl3.setBounds(10, 250, 356, 13);
		mainPanel.add(jl3);
		
		mainFrame.add(mainPanel);
		mainFrame.setBounds(200, 200, 355, 330);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		disableMessages();
    }
    
    private void disableMessages(){
    	jtaMessages.setEditable(false);
    	jtaMessages.setFocusable(false);
	}
	
	private void disableMessage(){
		jtfMessage.setEnabled(false);
		jbSend.setEnabled(false);
	}
	
		private void setID(String _id){
		id = _id;
	}

// bellow are setters
	private void setName(String _name){
		name = _name;
	}
	
	private void setOtherID(String _otherID){
		otherID = _otherID;
	}
	
	private void setOtherName(String _otherName){
		otherName = _otherName;
	}
	
// bellow are getters
	public String getOtherID(){
		return (otherID);
	}
	
	public String getOtherName(){
		return (otherName);
	}

    // bellow are methods in massaging
	public void addMessage(String _message){
		if(!mainFrame.isVisible()){
			mainFrame.setVisible(true);
		}
		jtaMessages.append(_message + "\n");
	}	

// bellow are methods in online messaging
	
	private void expired(){
		jtaMessages.append("   This session with : " + otherName + " was expired   ");
		disableMessage();
	}
	
	public void endSession(){
		expired();
		disableMessage();
		server = null;
	}
	
	public void run(){
//		blank action is enough.
	}
	
	public void actionPerformed(ActionEvent e){
		if("send".equalsIgnoreCase(e.getActionCommand())){
			server.privateChat(otherID, otherName, jtfMessage.getText());
			jtfMessage.setText("");
		}
	}
}