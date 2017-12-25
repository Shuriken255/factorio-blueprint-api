package ua.kiev.shuriken.blueprint;

import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class BlueprintParser {
	
	private static int bufferSize = 1024*256;
	
	public static int getBufferSize() {
		return bufferSize;
	}
	
	public static void setBufferSize(int bufferSize) {
		BlueprintParser.bufferSize = bufferSize;
	}
	
	public static String fromBlueprintStringToJSON(String blueprint) {
		try {
			blueprint = blueprint.substring(1, blueprint.length());
			
			Decoder decoder = Base64.getDecoder();
			byte[] decodedByteArray = decoder.decode(blueprint);
			
			Inflater zDecoder = new Inflater();
			byte[] encodedByteArray = new byte[bufferSize];
			
			zDecoder.setInput(decodedByteArray, 0, decodedByteArray.length);
			int resultLength = zDecoder.inflate(encodedByteArray);
			zDecoder.end();
			
			return new String(encodedByteArray, 0, resultLength, "ASCII");
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String fromJSONToBlueprintString(String json) {
		try {
			Deflater zEncoder = new Deflater();
			byte[] stringBytes = json.getBytes("ASCII");
			
			zEncoder.setInput(stringBytes);
			zEncoder.finish();
			byte[] encodedBytes = new byte[bufferSize];
			int size = zEncoder.deflate(encodedBytes, 0, bufferSize);
			zEncoder.end();
			
			Encoder encoder = Base64.getEncoder();
			
			return "0" + encoder.encodeToString(Arrays.copyOfRange(encodedBytes, 0, size));
		} catch (Exception e) {
			return null;
		}
	}
	
}
