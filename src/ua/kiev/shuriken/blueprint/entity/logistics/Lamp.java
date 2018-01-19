package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class Lamp extends Entity {

	public Lamp(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.LAMP;
	}
	
	private Condition enableCondition;
	
	/**
	 * Returns enable condition. If enable condition is disabled, returns "null".
	 * @return Enable condition or "null" if it is disabled.
	 */
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	/**
	 * Sets or disables enable condition.
	 * @param condition enable condition or "null" to disable.
	 */
	public void setEnableCondition(Condition condition) {
		enableCondition = condition;
	}
	
	
	private boolean usingColors;
	
	/**
	 * Returns "using colors" flag.
	 * @return "True" if "using colors" flag enabled or "false" if not.
	 */
	public boolean isUsingColors() {
		return usingColors;
	}
	
	/**
	 * Sets "using colors" flag.
	 * @param usingColors "true" to enable flag and "false" to disable.
	 */
	public void setUsingColors(boolean usingColors) {
		this.usingColors = usingColors;
	}
	
	
	@Override
	protected String setupToString() {
		if(!hasConnections()) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("\"control_behavior\":{");
		
		if(enableCondition != null) {
			sb.append(enableCondition.toString());
			if(usingColors) {
				sb.append(',');
			}
		}
		
		if(usingColors) {
			sb.append("\"use_colors\":true");
		}
		
		sb.append('}');
		if(sb.length() > 25) {
			return sb.toString();
		} else {
			return null;
		}
	}

}
