package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class RailChainSignal extends Entity {
	
	public static final int DIRECTION_NORTH = 4;
	public static final int DIRECTION_SOUTH = 0;
	public static final int DIRECTION_EAST = 6;
	public static final int DIRECTION_WEST = 2;
	
	
	public RailChainSignal(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public RailChainSignal(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.RAIL_CHAIN_SIGNAL;
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
	
	
	private String blueSignal = Signals.Virtual.SIGNAL_BLUE;
	
	public String getBlueSignal() {
		return blueSignal;
	}
	
	public void setBlueSignal(String signal) {
		blueSignal = signal;
	}
	
	
	@Override
	protected String setupToString() {
		if(!hasConnections()) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"control_behavior\":{");
		
		boolean firstPartPassed = false;
		if(!redSignal.equals(Signals.Virtual.SIGNAL_RED)) {
			sb.append("\"red_output_signal\":{\"type\":\"");
			sb.append(Signals.getType(redSignal));
			sb.append("\",\"name\":\"");
			sb.append(redSignal);
			sb.append("\"}");
			firstPartPassed = true;
		}
		
		if(!yellowSignal.equals(Signals.Virtual.SIGNAL_YELLOW)) {
			if(firstPartPassed) {
				sb.append(',');
			} else {
				firstPartPassed = true;
			}
			sb.append("\"orange_output_signal\":{\"type\":\"");
			sb.append(Signals.getType(yellowSignal));
			sb.append("\",\"name\":\"");
			sb.append(yellowSignal);
			sb.append("\"}");
		}
		
		if(!greenSignal.equals(Signals.Virtual.SIGNAL_GREEN)) {
			if(firstPartPassed) {
				sb.append(',');
			} else {
				firstPartPassed = true;
			}
			sb.append("\"green_output_signal\":{\"type\":\"");
			sb.append(Signals.getType(greenSignal));
			sb.append("\",\"name\":\"");
			sb.append(greenSignal);
			sb.append("\"}");
		}
		
		if(!blueSignal.equals(Signals.Virtual.SIGNAL_BLUE)) {
			if(firstPartPassed) {
				sb.append(',');
			}
			sb.append("\"blue_output_signal\":{\"type\":\"");
			sb.append(Signals.getType(blueSignal));
			sb.append("\",\"name\":\"");
			sb.append(blueSignal);
			sb.append("\"}");
		}
		
		sb.append('}');
		
		
		if(sb.length() > 25) {
			return sb.toString();
		} else {
			return null;
		}
	}
	
}
