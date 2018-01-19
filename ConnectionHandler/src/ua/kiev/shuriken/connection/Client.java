package ua.kiev.shuriken.connection;

import java.io.IOException;
import java.net.Socket;

public abstract class Client {
	
	private static final int IP = 0, PORT = 1;
	public static final byte SERVER_NOT_FOUND = 0, FULL_SERVER = 1, DENIED = 2, INNER_PROBLEM = 3;
	
	private boolean connected = false;
	
	public boolean isConnected() {
		return connected;
	}
	
	private Connection connection;
	private Socket socket;
	
	public void connect(String address) {
		if(connected) {
			disconnect();
		}
		String[] ipAndPort = address.split(":");
		try {
			socket = new Socket(ipAndPort[IP], Integer.parseInt(ipAndPort[PORT]));
		} catch (IOException e) {
			onConnectionFailure(SERVER_NOT_FOUND);
			return;
		}
		try {
			connection = new Connection(socket) {
				
				@Override
				public void onConnectionLost() {
					Client.this.connected = false;
					Client.this.onConnectionLost();
				}
				
				@Override
				public void onDisconnect() {
					Client.this.onDisconnect();
				}
				
				@Override
				public void processMessage(byte[] message) {
					Client.this.processMessage(message);
				}
				
				@Override
				public void processIntroducingMessage(byte[] message) {
					Client.this.processIntroducingMessage(message);
				}
				
				@Override
				public void onDecline() {
					onConnectionFailure(DENIED);
				}
				
				@Override
				public void onFullServer() {
					onConnectionFailure(FULL_SERVER);
				}
				
				@Override
				public byte[] introducingMessage() {
					return Client.this.introducingMessage();
				}
				
				@Override
				public byte[] continuousMessage() {
					return Client.this.continuousMessage();
				}
				
				@Override
				public void onOtherSideDisconnect() {
					Client.this.onOtherSideDisconnect();
				}
				
			};
		} catch (IOException e) {
			e.printStackTrace();
			onConnectionFailure(INNER_PROBLEM);
			return;
		}
		connected = true;
		onConnect();
	}
	
	public void disconnect() {
		connected = false;
		connection.disconnect();
	}
	
	abstract public void onConnect();
	
	abstract public void onConnectionFailure(byte reason);
	
	abstract public void onDisconnect();
	
	abstract public void onConnectionLost();
	
	abstract public void processMessage(byte[] message);
	
	abstract public void processIntroducingMessage(byte[] message);
	
	abstract public byte[] introducingMessage();
	
	abstract public byte[] continuousMessage();
	
	abstract public void onOtherSideDisconnect();
	
	public void send(byte[] message) {
		connection.send(message);
	}
	
}
