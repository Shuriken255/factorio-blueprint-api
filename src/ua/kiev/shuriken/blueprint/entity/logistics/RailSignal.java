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
	
	public String getRedSignal() {
		return redSignal;
	}
	
	public void setRedSignal(String signal) {
		redSignal = signal;
	}
	
	
	private String yellowSignal = Signals.Virtual.SIGNAL_YELLOW;
	
	public String getYellowSignal() {
		return yellowSignal;
	}
	
	public void setYellowSignal(String signal) {
		yellowSignal = signal;
	}
	
	
	private String greenSignal = Signals.Virtual.SIGNAL_GREEN;
	
	public String getGreenSignal() {
		return greenSignal;
	}
	
	public void setGreenSignal(String signal) {
		greenSignal = signal;
	}
	
	
	private boolean readSignal = true;
	
	public boolean isReadingSignal() {
		return readSignal;
	}
	
	public void setReadingSignal(boolean read) {
		readSignal = read;
	}
	
	
	private Condition closeCondition;
	
	public Condition getCloseCondition() {
		return closeCondition;
	}
	
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
