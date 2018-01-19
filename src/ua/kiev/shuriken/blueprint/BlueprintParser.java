package ua.kiev.shuriken.blueprint;

import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * This library uses this class to parse blueprint strings into blueprint JSON representations and back.
 */
public class BlueprintParser {
	
	private static int bufferSize = 1024*256;
	
	/**
	 * Returns buffer's size in bytes that is used for converting 
	 * as blueprint strings into JSON, as JSON into blueprint strings.
	 * @return Buffer's size in bytes.
	*/
	public static int getBufferSize() {
		return bufferSize;
	}
	
	/**
	 * Changes buffer's size that is used for conversion between blueprint strings and JSON.
	 * @param bufferSize Buffer size in bytes
	 */
	public static void setBufferSize(int bufferSize) {
		BlueprintParser.bufferSize = bufferSize;
	}
	
	/**
	 * Converts blueprint string to representation of blueprint in JSON.
	 * @param blueprint Factorio's blueprint string.
	 * @return JSON representation of blueprint.
	 */
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
	
	/**
	 * Converts representation of blueprint in JSON to blueprint string.
	 * @param json blueprint's representation in JSON.
	 * @return Blueprint string.
	 */
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
	
	public static String oneLineJSONToNormalJSON(String blueprint) {
		StringBuilder sb = new StringBuilder();
		int currentTab = 0;
		for(int i = 0; i < blueprint.length(); i++) {
			char c = blueprint.charAt(i);
			if (c == '{') {
				currentTab++;
				sb.append("{\n");
				for(int j = 0; j < currentTab*4; j++) {
					sb.append(' ');
				}
			} else if (c == '}') {
				currentTab--;
				sb.append("\n");
				for(int j = 0; j < currentTab*4; j++) {
					sb.append(' ');
				}
				sb.append('}');
			} else if (c == ',') {
				sb.append(",\n");
				for(int j = 0; j < currentTab*4; j++) {
					sb.append(' ');
				}
			} else {
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
	
}
