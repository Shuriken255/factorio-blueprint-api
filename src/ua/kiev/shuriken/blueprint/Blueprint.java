package ua.kiev.shuriken.blueprint;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represent blueprint, it contains all necessary information for creating blueprint.
 */
public class Blueprint {
	
	public static final long BLUEPRINT_VERSION = 68719738880l;
	
	/**
	 * Creates empty blueprint
	 */
	public Blueprint() {
		init();
	}
	
	
	private String[] icons;
	
	/**
	 * Returns icon's name on certain index.
	 * Signals class's constants should be used to compare it.
	 * @param index Icon's index. Can only be in range from 0 to 3.
	 * @return icon's name. You should use Signals constants to compare it.
	 */
	public String getIcon(int index) {
		return icons[index];
	}
	
	/**
	 * Sets icon at certain index. Index can be [0..3] range.
	 * Signals class's constants should be used for setting it.
	 * @param index Icon's index. Can only be in range from 0 to 3.
	 * @param icon Icon name. You should use Signals class's constants here.
	 */
	public void setIcon(int index, String icon) {
		icons[index] = icon;
	}
	
	
	private String name;
	
	/**
	 * Returns name that will be used as title for this blueprint.
	 * @return Blueprint's title.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets name that will be used as title for this blueprint.
	 * @param name name that will be used in blueprint's title.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	private void init() {
		icons = new String[4];
		name = "No name";
	}
	
	
	private LinkedList<Entity> entities = new LinkedList<>();
	
	/**
	 * Adds entity into blueprint. Not added entities won't be included!
	 * @param entity Entity you want to add into your blueprint.
	 */
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
	
	/**
	 * Return blueprint string that you can import into Factorio's blueprint library.
	 */
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
		sb.append(BLUEPRINT_VERSION);
		sb.append("}}");
		
		return BlueprintParser.fromJSONToBlueprintString(sb.toString());
	}
	
}
