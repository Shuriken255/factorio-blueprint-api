package ua.kiev.shuriken.connection;

import java.io.UnsupportedEncodingException;

public class MessageReader {
	
	private byte[] message;
	private int cursor;
	
	public MessageReader(byte[] message) {
		this.message = message;
		cursor = 0;
	}
	
	public int getSize() {
		return message.length;
	}
	
	public boolean hasNext() {
		return cursor < message.length;
	}
	
	public int getBytesLeft() {
		return message.length - cursor;
	}
	
	
	public int readByte() {
		int nextByte = message[cursor++];
		if(nextByte < 0) {
			nextByte += 256;
		}
		return nextByte;
	}
	
	public boolean readBoolean() {
		return message[cursor++] == 1;
	}
	
	public short readShort() {
		short nextShort = (short)(message[cursor++] + message[cursor++]*0x100);
		
		return nextShort;
	}
	
	public int readInt() {
		int nextInt = 0;
		int multiplier = 1;
		for(int i = 0; i < 4; i++) {
			if(message[cursor] < 0) {
				nextInt += (message[cursor++] + 256) * multiplier;
			} else {
				nextInt += message[cursor++] * multiplier;
			}
			multiplier *= 0x100;
		}
		return nextInt;
	}
	
	public long readLong() {
		long nextLong = 0;
		long multiplier = 1;
		for(int i = 0; i < 8; i++) {
			if(message[cursor] < 0) {
				nextLong += (message[cursor++] + 256) * multiplier;
			} else {
				nextLong += message[cursor++] * multiplier;
			}
			multiplier *= 0x100;
		}
		return nextLong;
	}
	
	public float readFloat() {
		int bits = 0;
		int multiplier = 1;
		for(int i = 0; i < 4; i++) {
			if(message[cursor] < 0) {
				bits += (message[cursor++] + 256) * multiplier;
			} else {
				bits += message[cursor++] * multiplier;
			}
			multiplier *= 0x100;
		}
		return Float.intBitsToFloat(bits);
	}
	
	public double readDouble() {
		long bits = 0;
		long multiplier = 1;
		for(int i = 0; i < 8; i++) {
			if(message[cursor] < 0) {
				bits += (message[cursor++] + 256) * multiplier;
			} else {
				bits += message[cursor++] * multiplier;
			}
			multiplier *= 0x100;
		}
		return Double.longBitsToDouble(bits);
	}
	
	public byte[] readByteArray(int length) {
		byte[] array = new byte[length];
		for(int i = 0; i < array.length; i++) {
			array[i] = message[cursor++];
		}
		return array;
	}
	
	public byte[] readRestBytes() {
		byte[] array = new byte[message.length-cursor];
		for(int i = 0; i < array.length; i++) {
			array[i] = message[cursor++];
		}
		return array;
	}
	
	public char readChar() {
		return (char)(message[cursor++] + message[cursor++]*0x100);
	}
	
	public String readString() {
		short length = readShort();
		try {
			return new String(readByteArray(length), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
