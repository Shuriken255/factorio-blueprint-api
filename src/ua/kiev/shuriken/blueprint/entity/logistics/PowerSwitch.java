package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class PowerSwitch extends Entity {

	public PowerSwitch(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.POWER_SWITCH;
	}
	
	
	private Condition enableCondition;
	
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	public void setEnableCondition(Condition condition) {
		enableCondition = condition;
	}
	
	
	@Override
	protected String setupToString() {
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
