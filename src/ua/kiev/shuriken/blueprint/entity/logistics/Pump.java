package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class Pump extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	
	
	public Pump(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public Pump(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.PUMP;
	}
	
	
	private Condition enableCondition;
	
	/**
	 * Returns enable condition used by this pump.
	 * @return Enable condition or "null" if enable condition wasn't set yet.
	 */
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	/**
	 * Sets enable condition used by this pump.
	 * @param condition enable condition you want to use.
	 */
	public void setEnableCondition(Condition condition) {
		enableCondition = condition;
	}
	
	
	@Override
	public String setupToString() {
		if(hasConnections() && enableCondition != null) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("\"control_behavior\":{");
			sb.append(enableCondition.toString());
			sb.append('}');
			
			return sb.toString();
		} else {
			return null;
		}
	}

}
