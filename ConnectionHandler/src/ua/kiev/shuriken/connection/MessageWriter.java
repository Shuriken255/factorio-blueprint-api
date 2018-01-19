package ua.kiev.shuriken.connection;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;

public class MessageWriter {
	
	LinkedList<Byte> bytes = new LinkedList<Byte>();
	
	public byte[] getByteArray() {
		byte[] array = new byte[bytes.size()];
		Iterator<Byte> iterator = bytes.iterator();
		for(int i = 0; i < array.length; i++) {
			array[i] = iterator.next();
		}
		return array;
	}
	
	public void appendByte(byte b) {
		bytes.add(b);
	}
	
	public void appendBoolean(boolean b) {
		if(b) {
			bytes.add((byte)1);
		} else {
			bytes.add((byte)0);
		}
	}
	
	public void appendShort(short s) {
		bytes.add((byte)s);
		bytes.add((byte)(s/256));
	}
	
	public void appendInt(int i) {
		for(int j = 0; j < 4; j++) {
			bytes.add((byte)((i >> j*8)));
		}
	}
	
	public void appendLong(long l) {
		for(int j = 0; j < 8; j++) {
			bytes.add((byte)(l >> j*8));
		}
	}
	
	public void appendFloat(float f) {
		int bits = Float.floatToIntBits(f);
		for(int i = 0; i < 4; i++) {
			bytes.add((byte)(bits >> i*8));
		}
	}
	
	public void appendDouble(double d) {
		long bits = Double.doubleToLongBits(d);
		for(int i = 0; i < 8; i++) {
			bytes.add((byte)(bits >> i*8));
		}
	}
	
	public void appendByteArray(byte[] array) {
		for(int i = 0; i < array.length; i++) {
			bytes.add(array[i]);
		}
	}
	
	public void appendChar(char c) {
		bytes.add((byte)c);
		bytes.add((byte)(c/0x100));
	}
	
	public void appendString(String string) {
		try {
			byte[] array = string.getBytes("UTF-8");
			appendShort((short)array.length);
			for(int i = 0; i < array.length; i++) {
				bytes.add(array[i]);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
}
