package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.ModuleRequest;
import ua.kiev.shuriken.blueprint.Signals;

public class Pumpjack extends Entity {
	
	public static final int READING_MODE_NONE = -1;
	public static final int READING_MODE_THIS_MINING_DRILL = 0;
	public static final int READING_MODE_ENTIRE_ORE_PATCH = 1;
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	
	
	public Pumpjack(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public Pumpjack(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.ELECTRIC_MINING_DRILL;
	}
	
	
	private Condition enableCondition;
	
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	public void setEnableCondition(Condition condition) {
		this.enableCondition = condition;
	}
	
	
	private boolean readResources;
	
	public boolean isReadingResources() {
		return readResources;
	}
	
	public void setReadingResources(boolean read) {
		readResources = read;
	}
	
	
	@Override
	protected String setupToString() {
		if(hasConnections()) {
			StringBuilder sb = new StringBuilder();
			sb.append("\"control_behavior\":{");
			
			if(enableCondition == null) {
				sb.append("\"circuit_enable_disable\":false");
			} else {
				sb.append(enableCondition.toString());
				sb.append("\"circuit_enable_disable\":true");
			}
			
			sb.append(",\"circuit_read_resources\":");
			sb.append(readResources);
			sb.append(",\"circuit_resource_read_mode\":0");
			
			sb.append('}');
			return sb.toString();
		}
		
		return null;
	}
	
	
	private ModuleRequest modules = new ModuleRequest();
	
	public ModuleRequest getModuleRequests() {
		return modules;
	}
	
	
	@Override
	protected String advancedSetupToString() {
		return modules.toString();
	}
	
}
