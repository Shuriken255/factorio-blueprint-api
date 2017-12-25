package ua.kiev.shuriken.blueprint.test;

import java.util.Base64;


import java.util.Base64.Decoder;
import java.util.zip.Inflater;

import ua.kiev.shuriken.blueprint.Blueprint;
import ua.kiev.shuriken.blueprint.Signals;
import ua.kiev.shuriken.blueprint.production.AssemblingMachine3;
import ua.kiev.shuriken.blueprint.production.OilRefinery;

public class Test {
	
	public static final int BUFFER_SIZE = 1024*256;
	
	public static void main(String[] args) {
		Blueprint b = new Blueprint();
		b.setName("Test blueprint");
		b.setIcon(0, Signals.Virtual.SIGNAL_F);
		b.setIcon(1, Signals.Virtual.SIGNAL_U);
		b.setIcon(2, Signals.Virtual.SIGNAL_C);
		b.setIcon(3, Signals.Virtual.SIGNAL_K);
		
		b.add(new OilRefinery(0, -3, OilRefinery.DIRECTION_WEST));
		
		AssemblingMachine3 machine = new AssemblingMachine3(0, 1);
		machine.getModuleRequests().addModule(Signals.Items.EFFECTIVITY_MODULE_2, 2);
		machine.setRecipe(Signals.Items.ATOMIC_BOMB);
		
		b.add(machine);
		
		String blueprint = b.toString();
		// String blueprintForTest = "0eNqV0N0KwjAMBeB3OdcduPm7voqIdDVoYM1G24lj9N1dJ4IgCF4mJB85mdC0A/WeJUJPYNtJgD5OCHwV0+ZeHHuCBkdyUBDjcmVCINe0LNfCGXtjoaJEUmC50AO6TCcFksiR6eUtxXiWwTXk54HfkkLfhXm5k3zBDBaVwgi9Skl9WdWf1ptS8GR5CWesHdzQmth55NOXsPrjNwp38mExdod9Wdfr7aauUnoCMohq+A==";
		// String blueprintForTest = "0eNp9kN1OBCEMhd+l15g4/g+vYgwpa50ly08DzCqZ8O6WMbvRG29IzmnPV9oNrF+Js4sV9AbukGIB/bpBcUtEP7zamECDqxRAQcQwFOe0ZAwBraebwoQnytAVuPhOX6Cn/qaAYnXV0Q9vF83ENVjp1NP/JAWcioRTHD8Q4K2CJq9MYJRuqpTLKLHHZvFwMufk18ET8NVbfLLovQQ/0BdSICJ9Gk6+8THFi99HgXI1f9HlKL174ZrfrRRNQAZd80qXZKBScBnrQB+r78fSv26r4CzYfZ+nl+dpnu8fH+a73r8BqqqG0Q==";
		System.out.println(blueprint);
		
		System.out.println(convertBlueprintStringToJSON(blueprint));
		// System.out.println(convertBlueprintStringToJSON(blueprintForTest));
	}
	
	public static String convertBlueprintStringToJSON(String blueprint) {
		blueprint = blueprint.substring(1, blueprint.length());
		
		try {
			Decoder decoder = Base64.getDecoder();
			byte[] decodedByteArray = decoder.decode(blueprint);
			
			Inflater zDecoder = new Inflater();
			byte[] encodedByteArray = new byte[BUFFER_SIZE];
			
			zDecoder.setInput(decodedByteArray, 0, decodedByteArray.length);
			int resultLength = zDecoder.inflate(encodedByteArray);
			zDecoder.end();
			
			String deflatedBlueprint = new String(encodedByteArray, 0, resultLength, "ASCII");
			StringBuilder sb = new StringBuilder();
			int currentTab = 0;
			for(int i = 0; i < deflatedBlueprint.length(); i++) {
				char c = deflatedBlueprint.charAt(i);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
