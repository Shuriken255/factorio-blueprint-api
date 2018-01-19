package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class RailSignal extends Entity {
	
	public static final int DIRECTION_NORTH = 4;
	public static final int DIRECTION_SOUTH = 0;
	public static final int DIRECTION_EAST = 6;
	public static final int DIRECTION_WEST = 2;
	

	public RailSignal(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public RailSignal(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.RAIL_SIGNAL;
	}
	
	
	private String redSignal = Signals.Virtual.SIGNAL_RED;
	
	/**
	 * Returns signal that will be used as output when signal is red.
	 * Signals class's constants should be used to compare.
	 * @return Signal that will be used as output when signal is red
	 */
	public String getRedSignal() {
		return redSignal;
	}
	
	/**
	 * Sets signal that will be used as output when signal is red.
	 * Signals class's constants should be used.
	 * @param signal
	 */
	public void setRedSignal(String signal) {
		redSignal = signal;
	}
	
	
	private String yellowSignal = Signals.Virtual.SIGNAL_YELLOW;
	
	/**
	 * Returns signal that will be used as output when signal is yellow.
	 * Signals class's constants should be used to compare.
	 * @return Signal that will be used as output when signal is yellow.
	 */
	public String getYellowSignal() {
		return yellowSignal;
	}
	
	public void setYellowSignal(String signal) {
		yellowSignal = signal;
	}
	
	
	private String greenSignal = Signals.Virtual.SIGNAL_GREEN;
	
	/**
	 * Returns signal that will be used as output when signal is green.
	 * Signals class's constants should be used to compare.
	 * @return Signal that will be used as output when signal is green.
	 */
	public String getGreenSignal() {
		return greenSignal;
	}
	
	public void setGreenSignal(String signal) {
		greenSignal = signal;
	}
	
	
	private boolean readSignal = true;
	
	/**
	 * Returns if rail signal will output signals depends of it's color
	 * @return "true" if rail signal outputs signal and "false" if not
	 */
	public boolean isReadingSignal() {
		return readSignal;
	}
	
	/**
	 * Sets rail signal's read mode
	 * @param read "true" if rail signal should output signals into circuit network and "false" if not
	 */
	public void setReadingSignal(boolean read) {
		readSignal = read;
	}
	
	
	private Condition closeCondition;
	
	/**
	 * Returns close condition of rail signal or "null" if it is disabled.
	 * @param close condition or "null" if it is disabled
	 */
	public Condition getCloseCondition() {
		return closeCondition;
	}
	
	/**
	 * Sets rail signal's close condition or disables it.
	 * @param condition condition that sets rail signal to red or "null" to disable it.
	 */
	public void setCloseCondition(Condition condition) {
		closeCondition = condition;
	}
	
	
	@Override
	protected String setupToString() {
		if(!hasConnections()) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("\"control_behavior\":{");
		
		sb.append("\"circuit_close_signal\":");
		sb.append(closeCondition != null);
		
		sb.append(",\"circuit_read_signal\":");
		sb.append(readSignal);
		
		if(readSignal) {
			if(!redSignal.equals(Signals.Virtual.SIGNAL_RED)) {
				sb.append(",\"red_output_signal\":{\"type\":\"");
				sb.append(Signals.getType(redSignal));
				sb.append("\",\"name\":\"");
				sb.append(redSignal);
				sb.append("\"}");
			}
			if(!yellowSignal.equals(Signals.Virtual.SIGNAL_YELLOW)) {
				sb.append(",\"orange_output_signal\":{\"type\":\"");
				sb.append(Signals.getType(yellowSignal));
				sb.append("\",\"name\":\"");
				sb.append(yellowSignal);
				sb.append("\"}");
			}
			if(!greenSignal.equals(Signals.Virtual.SIGNAL_GREEN)) {
				sb.append(",\"green_output_signal\":{\"type\":\"");
				sb.append(Signals.getType(greenSignal));
				sb.append("\",\"name\":\"");
				sb.append(greenSignal);
				sb.append("\"}");
			}
		}
		
		if(closeCondition != null) {
			sb.append(',');
			sb.append(closeCondition.toString());
		}
		
		sb.append('}');
		return sb.toString();
	}

}
