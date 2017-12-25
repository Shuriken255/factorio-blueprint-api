package ua.kiev.shuriken.blueprint.production;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.ModuleRequest;
import ua.kiev.shuriken.blueprint.Signals;

public class ElectricMiningDrill extends Entity {
	
	public static final int READING_MODE_NONE = -1;
	public static final int READING_MODE_THIS_MINING_DRILL = 0;
	public static final int READING_MODE_ENTIRE_ORE_PATCH = 1;
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	
	
	public ElectricMiningDrill(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public ElectricMiningDrill(float x, float y) {
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
	
	
	private int readMode;
	
	public int getReadMode() {
		return readMode;
	}
	
	public void setReadMode(int mode) {
		readMode = mode;
	}
	
	
	@Override
	protected String setupToString() {
		StringBuilder sb = new StringBuilder();
		
		if(hasConnections()) {
			sb.append("\"control_behavior\":{");
			
			if(enableCondition == null) {
				sb.append("\"circuit_enable_disable\":false");
			} else {
				sb.append(enableCondition.toString());
				sb.append(",\"circuit_enable_disable\":true");
			}
			
			if(readMode == READING_MODE_NONE) {
				sb.append(",\"circuit_read_resources\":true,\"circuit_resource_read_mode\":0");
			} else {
				sb.append(",\"circuit_read_resources\":true,\"circuit_resource_read_mode\":");
				sb.append(readMode);
			}
			
			sb.append('}');
		}
		
		if(sb.length() > 0) {
			return sb.toString();
		} else {
			return null;
		}
	}
	
	
	private ModuleRequest modules = new ModuleRequest();
	
	public ModuleRequest getModuleRequests() {
		return modules;
	}
	
	// Created only for burner mining drill
	protected void removeModules() {
		modules = null;
	}
	
	
	@Override
	protected String advancedSetupToString() {
		return modules.toString();
	}
	
}
