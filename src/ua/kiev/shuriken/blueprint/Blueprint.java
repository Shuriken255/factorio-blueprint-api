package ua.kiev.shuriken.blueprint;

import java.util.Iterator;
import java.util.LinkedList;

public class Blueprint {
	
	public static final long VERSION = 68719738880l;
	
	
	public Blueprint() {
		init();
	}
	
	public Blueprint(String blueprintString) {
		init();
		blueprintString = BlueprintParser.fromBlueprintStringToJSON(blueprintString);
	}
	
	
	private String[] icons;
	
	public String getIcon(int index) {
		return icons[index];
	}
	
	public void setIcon(int index, String icon) {
		icons[index] = icon;
	}
	
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	private void init() {
		icons = new String[4];
		name = "No name";
	}
	
	
	private LinkedList<Entity> entities = new LinkedList<>();
	
	public void add(Entity entity) {
		entities.addLast(entity);
	}
	
	private void setEntityNumbers() {
		Iterator<Entity> iterator = entities.iterator();
		int entityNumber = 0;
		while(iterator.hasNext()) {
			iterator.next().setEntityNumber(++entityNumber);
		}
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// Initialization
		sb.append("{\"blueprint\":{");
		
		// Icons
		sb.append("\"icons\":[");
		boolean firstIconAdded = false;
		for(int i = 0; i < 4; i++) {
			if(icons[i] != null) {
				if(firstIconAdded) {
					sb.append(',');
				}
				sb.append("{\"signal\":{\"type\":\"");
				sb.append(Signals.getType(icons[i]));
				sb.append("\",\"name\":\"");
				sb.append(icons[i]);
				sb.append("\"},\"index\":");
				sb.append(i+1);
				sb.append('}');
				firstIconAdded = true;
			}
		}
		if(!firstIconAdded) {
			throw new BlueprintException("Blueprint has to have at least one icon.");
		}
		sb.append("],");
		
		// Entities
		sb.append("\"entities\":[");
		boolean isFirstEntity = true;
		setEntityNumbers();
		Iterator<Entity> entityIterator = entities.iterator();
		while(entityIterator.hasNext()) {
			if(isFirstEntity) {
				isFirstEntity = false;
			} else {
				sb.append(',');
			}
			sb.append(entityIterator.next().toString());
		}
		sb.append("],");
		
		// Ending
		sb.append("\"item\":\"blueprint\"");
		if(name != null) {
			sb.append(",\"label\":\"");
			sb.append(name);
		}
		sb.append("\",\"version\":");
		sb.append(VERSION);
		sb.append("}}");
		
		return BlueprintParser.fromJSONToBlueprintString(sb.toString());
	}
	
}
