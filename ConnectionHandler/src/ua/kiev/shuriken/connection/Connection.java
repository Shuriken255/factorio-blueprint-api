package ua.kiev.shuriken.connection;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

// Поддержка создания и анализа "ознакомительных" сообщений
// Тщательная проверка каждого аспекта данной библиотеки
// Избавление от printStackTrace() в конце работы

public class Connection {
	public static final byte MESSAGE = 0;
	public static final byte DISCONNECT = 1;
	public static final byte FULL_SERVER = 2;
	public static final byte ACCEPT = 3;
	public static final byte DENIED = 4;
	public static final byte INTRODUCING_MESSAGE = 5;
	
	public static final int MIN_SEND_MESSAGE_DELAY = 10;
	
	private final Socket socket;
	private final String stringIP;
	private final byte[] byteIP;
	
	private boolean connected = true;
	
	private final InputStream input;
	private final Thread inputThread;
	
	private final OutputStream output;
	private final Thread outputThread;
	
	private long lastMessageSent = System.currentTimeMillis();
	
	private Queue<byte[]> messages;
	
	public Connection(final Socket socket) throws IOException {
		this.socket = socket;
		
		// Determining IP address in byte and string
		byteIP = socket.getInetAddress().getAddress();
		StringBuilder ipBuilder = new StringBuilder();
		for(int i = 0; i < 4; i++) {
			if(i != 0) {
				ipBuilder.append('.');
			}
			if(byteIP[i] < 0) {
				ipBuilder.append((int)byteIP[i]+256);
			} else {
				ipBuilder.append(byteIP[i]);
			}
		}
		stringIP = ipBuilder.toString();
		
		// If connection class's object is created, connection won't be denied.
		socket.getOutputStream().write(ACCEPT);
		
		int result = socket.getInputStream().read();
		switch(result) {
		case ACCEPT:
			break;
			
		case FULL_SERVER:
			socket.close();
			onFullServer();
			throw new IOException();
			
		case DENIED:
			socket.close();
			onDecline();
			throw new IOException();
		}
		
		// Setting up input stream
		input = socket.getInputStream();
		inputThread = new Thread() {
			@Override
			public void run() {
				while(connected) {
					try {
						int sign = input.read();
						if(sign != -1) {
							switch(sign) {
							case MESSAGE:
							case INTRODUCING_MESSAGE:
								int length = 0;
								int multiplier = 1;
								for(int i = 0; i < 4; i++) {
									int nextByte = input.read();
									if(nextByte == -1) {
									} else {
										if(nextByte < 0) {
											length += (nextByte + 256) * multiplier;
										} else {
											length += nextByte;
										}
										multiplier *= 0x100;
									}
								}
								byte[] message = new byte[length];
								for(int i = 0; i < length; i++) {
									int nextByte = input.read();
									if(nextByte != -1) {
										message[i] = (byte)nextByte;
									} else {
										throw new IOException("Couldn't read the message");
									}
								}
								
								if(sign == MESSAGE) {
									processMessage(message);
								} else {
									processIntroducingMessage(message);
								}
								if(System.currentTimeMillis() < lastMessageSent + MIN_SEND_MESSAGE_DELAY) {
									try {
										Thread.sleep(lastMessageSent + MIN_SEND_MESSAGE_DELAY
												- System.currentTimeMillis());
									} catch (InterruptedException e) {
										
									}
								}
								if(messages.isEmpty()) {
									byte[] continuousMessage = continuousMessage();
									if(continuousMessage != null) {
										send(continuousMessage);
									}
								}
								lastMessageSent = System.currentTimeMillis();
								break;
								
							case DISCONNECT:
								connected = false;
								output.write(DISCONNECT);
								inputThread.interrupt();
								outputThread.interrupt();
								socket.close();
								onOtherSideDisconnect();
								break;
							}
						}
					} catch(Exception e) {
						if(!connected) {
							return;
						}
						try {
							connected = false;
							inputThread.interrupt();
							outputThread.interrupt();
							socket.close();
							onConnectionLost();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		};
		inputThread.start();
		
		// Setting up output stream
		messages = new LinkedList<byte[]>();
		
		output = socket.getOutputStream();
		outputThread = new Thread() {
			@Override
			public void run() {
				try {
					pushMessageToOutputStream(INTRODUCING_MESSAGE, introducingMessage());
					while(connected) {
						if(!messages.isEmpty()) {
							pushMessageToOutputStream(MESSAGE, messages.remove());
						}
						Thread.sleep(MIN_SEND_MESSAGE_DELAY);
					}
					inputThread.interrupt();
					interrupt();
				} catch(Exception e) {
					if(!connected) {
						return;
					}
					try {
						connected = false;
						inputThread.interrupt();
						outputThread.interrupt();
						socket.close();
						onConnectionLost();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
		outputThread.start();
	}
	
	public int getPort() {
		return socket.getPort();
	}
	
	public String getStringIP() {
		return stringIP;
	}
	
	public byte[] getByteIP() {
		return byteIP;
	}
	
	public void disconnect() {
		try {
			connected = false;
			output.write(DISCONNECT);
			try {
				input.read();
			} catch (IOException e) {}
			socket.close();
			onDisconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(byte[] message) {
		messages.add(message);
	}
	
	private void pushMessageToOutputStream(byte sign, byte[] message) throws IOException {
		if(message == null) {
			return;
		}
		output.write(sign);
		int length = message.length;
		int divider = 1;
		for(int i = 0; i < 4; i++) {
			output.write((length/divider) & 0xFF);
			divider *= 0x100;
		}
		for(int i = 0; i < length; i++) {
			output.write(message[i]);
		}
	}
	
	public void processMessage(byte[] message) {
		// Should be overridden
	}
	
	public void processIntroducingMessage(byte[] message) {
		// Should be overridden
	}
	
	public byte[] continuousMessage() {
		// Should be overridden
		return null;
	}
	
	public byte[] introducingMessage() {
		// Should be overridden
		return null;
	}
	
	public void onConnectionLost() {
		// Should be overridden
	}
	
	public void onDisconnect() {
		// Should be overridden
	}
	
	public void onOtherSideDisconnect() {
		// Should be overridden
	}
	
	public void onFullServer() {
		// Should be overridden
	}
	
	public void onSuccessfulConnect() {
		// Should be overridden
	}
	
	public void onDecline() {
		// Should be overridden
	}
	
}
