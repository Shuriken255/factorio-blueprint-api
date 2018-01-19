package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class Inserter extends Entity {
	
	public static final int DIRECTION_FROM_SOUTH_TO_NORTH = 4;
	public static final int DIRECTION_FROM_WEST_TO_EAST = 6;
	public static final int DIRECTION_FROM_NORTH_TO_SOUTH = 0;
	public static final int DIRECTION_FROM_EAST_TO_WEST = 2;
	
	public static final int READ_MODE_NONE = -1;
	public static final int READ_MODE_PULSE = 0;
	public static final int READ_MODE_HOLD = 1;
	
	
	public Inserter(float x, float y, int direction) {
		super(x, y, direction);
	}

	public Inserter(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.INSERTER;
	}
	
	
	private Condition enableCondition;
	
	/**
	 * Returns enable condition used for this inserter. May return "null" if enable/disable function is off.
	 * @return Enable condition or "null" if it doesn't exist. 
	 */
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	/**
	 * Sets enable condition for inserter. If condition is null, enable/disable function
	 * will be off. In case if condition is not null, enable/disable function will be on.
	 * @param condition condition on which inserter should work.
	 */
	public void setEnableCondition(Condition condition) {
		enableCondition = condition;
	}
	
	
	private int readMode = READ_MODE_NONE;
	
	/**
	 * Returns inserter's content read mode. Inserter's constants should be used to compare.
	 * @return Inserter's content read mode.
	 */
	public int getReadMode() {
		return readMode;
	}
	
	/**
	 * Sets inserter's content read mode.
	 * @param readMode read mode. Constants from Inserter's class should be used for this parameter.
	 */
	public void setReadMode(int readMode) {
		this.readMode = readMode;
	}
	
	
	private String stackSizeSignal;
	
	/**
	 * Returns signal, that inserter will use to override it's stack size. May return "null" if function is disabled.
	 * @return Signal, used for overriding or "null" if override function is disabled.
	 */
	public String getStackSizeSignal() {
		return stackSizeSignal;
	}
	
	/**
	 * Sets signal that will be used for setting stack size of inserter.
	 * Setting it to null will disable setting stack size depends of signal.
	 * @param signal signal you want to set stack size from
	 */
	public void setStackSizeSignal(String signal) {
		stackSizeSignal = signal;
	}
	

	@Override
	protected String setupToString() {
		if(hasConnections()) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("\"control_behavior\":{");
			if(enableCondition == null) {
				sb.append("\"circuit_mode_of_operation\":3");
			} else {
				sb.append(enableCondition.toString());
			}
			
			if(readMode != READ_MODE_NONE) {
				sb.append(",\"circuit_read_hand_contents\":true,\"circuit_hand_read_mode\":");
				sb.append(readMode);
			}
			
			if(stackSizeSignal != null) {
				sb.append(",\"circuit_set_stack_size\":true,\"stack_control_input_signal\":{\"type\":\"");
				sb.append(Signals.getType(stackSizeSignal));
				sb.append("\",\"name\":\"");
				sb.append(stackSizeSignal);
				sb.append("\"}");
			}
			
			sb.append('}');
			
			return sb.toString();
		} else {
			return null;
		}
	}
	
	
	private int overrideStackSize = 0;
	
	/**
	 * Returns inserter's stack size. May return "0" if stack size override is disabled.
	 * @return Inserter's overridden stack size or "0" if this function is disabled.
	 */
	public int getOverrideStackSize() {
		return overrideStackSize;
	}
	
	/**
	 * Sets override stack size. Setting it on "0" disables it.
	 * @param size stack size.
	 */
	public void setOverrideStackSize(int size) {
		overrideStackSize = size;
	}
	
	
	@Override
	protected String advancedSetupToString() {
		if(overrideStackSize > 0 && stackSizeSignal == null) {
			return "\"override_stack_size\":" + overrideStackSize;
		} else {
			return null;
		}
	}

}
