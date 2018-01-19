package ua.kiev.shuriken.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public abstract class Server {
	private int maxConnections;
	
	private Set<Connection> connections;
	private ServerSocket serverSocket;
	
	private final int port;
	
	public int getPort() {
		return port;
	}
	
	private Thread connectionAcceptingThread;
	
	public int getConnectionsValue() {
		return connections.size();
	}
	
	public Server(int port, int maxConnectionsValue) throws IOException {
		maxConnections = maxConnectionsValue;
		this.port = port;
		
		connections = new HashSet<Connection>();
		
		serverSocket = new ServerSocket(port);
		
		connectionAcceptingThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Socket newSocket = serverSocket.accept();
						if(!mayConnectToServer(newSocket.getInetAddress())) {
							newSocket.getOutputStream().write(Connection.DENIED);
							try {
								newSocket.getInputStream().read();
								Thread.sleep(200);
							} catch(IOException | InterruptedException e) {}
							newSocket.close();
						} else if(maxConnections > 0 && connections.size() >= maxConnections) {
							newSocket.getOutputStream().write(Connection.FULL_SERVER);
							newSocket.close();
						} else {
							Connection newConnection = new Connection(newSocket) {
								
								@Override
								public void processMessage(byte[] message) {
									Server.this.processMessage(this, message);
								}
								
								@Override
								public void processIntroducingMessage(byte[] message) {
									Server.this.processIntroducingMessage(this, message);
								}
								
								@Override
								public void onDisconnect() {
									connections.remove(this);
									Server.this.onDisconnect(this);
								}
								
								@Override
								public void onConnectionLost() {
									connections.remove(this);
									Server.this.onConnectionLost(this);
								}
								
								@Override
								public byte[] introducingMessage() {
									return Server.this.introducingMessage(this);
								}
								
								@Override
								public byte[] continuousMessage() {
									return Server.this.continuousMessage(this);
								}
								
							}; 
							connections.add(newConnection); 
							onNewConnection(newConnection);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		};
		connectionAcceptingThread.start();
		
		onSuccessfullServerStart();
	}
	
	public void sendTo(Connection connection, byte[] message) {
		connection.send(message);
	}
	
	public void sendToEveryone(byte[] message) {
		for(Connection connection:connections) {
			connection.send(message);
		}
	}
	
	public void sendToEveryoneBut(Connection connection, byte[] message) {
		for(Connection c:connections) {
			if(c != connection) {
				c.send(message);
			}
		}
	}
	
	abstract public void onNewConnection(Connection connection);
	
	abstract public void processMessage(Connection connection, byte[] message);
	
	abstract public void processIntroducingMessage(Connection connection, byte[] message);
	
	abstract public byte[] continuousMessage(Connection connection);
	
	abstract public byte[] introducingMessage(Connection connection);
	
	abstract public void onConnectionLost(Connection connection);
	
	abstract public void onDisconnect(Connection connection);
	
	abstract public boolean mayConnectToServer(InetAddress adress);
	
	abstract public void onSuccessfullServerStart();
	
}
