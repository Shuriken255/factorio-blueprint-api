package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Condition;
import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class TransportBelt extends Entity {
	
	public static final int READ_MODE_NONE = -1;
	public static final int READ_MODE_PULSE = 0;
	public static final int READ_MODE_HOLD = 1;
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	
	
	public TransportBelt(float x, float y) {
		super(x, y);
	}

	public TransportBelt(float x, float y, int direction) {
		super(x, y, direction);
	}

	@Override
	public String getName() {
		return Signals.Items.TRANSPORT_BELT;
	}
	
	
	private Condition enableCondition;
	
	public Condition getEnableCondition() {
		return enableCondition;
	}
	
	public void setEnableCondition(Condition condition) {
		this.enableCondition = condition;
	}
	
	
	private int readMode = READ_MODE_NONE;
	
	public int getReadMode() {
		return readMode;
	}
	
	public void setReadMode(int readMode) {
		this.readMode = readMode;
	}

	@Override
	protected String setupToString() {
		if(hasConnections()) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("\"control_behavior\":{");
			if(enableCondition != null) {
				sb.append(enableCondition.toString());
				sb.append(',');
			}
			sb.append("\"circuit_enable_disable\":");
			sb.append(enableCondition != null);
			sb.append(",\"circuit_read_hand_contents\":");
			sb.append(readMode != READ_MODE_NONE);
			sb.append(",\"circuit_contents_read_mode\":");
			if(readMode == READ_MODE_NONE) {
				sb.append('0');
			} else {
				sb.append(readMode);
			}
			sb.append('}');
			
			return sb.toString();
		} else {
			return null;
		}
	}

}
