package ua.kiev.shuriken.blueprint;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.zip.DataFormatException;
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
	@Deprecated
	public static int getBufferSize() {
		return bufferSize;
	}
	
	/**
	 * Changes buffer's size that is used for conversion between blueprint strings and JSON.
	 * @param bufferSize Buffer size in bytes
	 */
	@Deprecated
	public static void setBufferSize(int bufferSize) {
		BlueprintParser.bufferSize = bufferSize;
	}

	private static byte[] inflate(byte[] data) throws DataFormatException {
		//Output size estimation
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		byte[] block = new byte[bufferSize];
		int fullBlocks = -1;
		int lastBlock;
		do {
			fullBlocks++;
			lastBlock = inflater.inflate(block);
		} while (lastBlock == bufferSize);
		inflater.end();
		//Actual output
		byte[] completeData = new byte[bufferSize * fullBlocks + lastBlock];
		inflater = new Inflater();
		inflater.setInput(data);
		inflater.inflate(completeData);
		inflater.end();
		return completeData;
	}
	
	/**
	 * Converts blueprint string to representation of blueprint in JSON.
	 * @param blueprint Factorio's blueprint string.
	 * @return JSON representation of blueprint.
	 */
	public static String fromBlueprintStringToJSON(String blueprint) {
		try {
			blueprint = blueprint.substring(1);
			
			Decoder decoder = Base64.getDecoder();
			byte[] decodedByteArray = decoder.decode(blueprint);
			byte[] encodedByteArray = inflate(decodedByteArray);
			
			return new String(encodedByteArray, StandardCharsets.UTF_8);
		} catch (Exception e) {
			return null;
		}
	}

	private static byte[] deflate(byte[] data) {
		//Output size estimation
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		byte[] block = new byte[bufferSize];
		int fullBlocks = -1;
		int lastBlock;
		do {
			fullBlocks++;
			lastBlock = deflater.deflate(block);
		} while (lastBlock == bufferSize);
		deflater.end();
		//Actual output
		byte[] completeData = new byte[bufferSize * fullBlocks + lastBlock];
		deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		deflater.deflate(completeData);
		deflater.end();
		return completeData;
	}
	
	/**
	 * Converts representation of blueprint in JSON to blueprint string.
	 * @param json blueprint's representation in JSON.
	 * @return Blueprint string.
	 */
	public static String fromJSONToBlueprintString(String json) {
		try {
			byte[] stringBytes = json.getBytes(StandardCharsets.UTF_8);
			byte[] encodedBytes = deflate(stringBytes);
			
			Encoder encoder = Base64.getEncoder();
			
			return "0" + encoder.encodeToString(encodedBytes);
		} catch (Exception e) {
			return null;
		}
	}
	
}
