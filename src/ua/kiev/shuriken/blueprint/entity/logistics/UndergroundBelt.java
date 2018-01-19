package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class UndergroundBelt extends Entity {
	
	public static final int INPUT = 0;
	public static final int OUTPUT = 1;
	
	
	public UndergroundBelt(float x, float y, int type) {
		super(x, y);
		this.type = type;
	}

	public UndergroundBelt(float x, float y, int direction, int type) {
		super(x, y, direction);
		this.type = type;
	}
	
	@Override
	public String getName() {
		return Signals.Items.UNDERGROUND_BELT;
	}
	
	
	private int type;
	
	/**
	 * Gets type of this belt. Use "INPUT" and "OUTPUT" constants to compare
	 * @return type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Sets type of this belt. Use "INPUT" and "OUTPUT" constants to compare
	 * @param type. Use "INPUT" and "OUTPUT" constants for this argument.
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	
	@Override
	protected String setupToString() {
		if(type == INPUT) {
			return "\"type\":\"input\"";
		} else {
			return "\"type\":\"output\"";
		}
	}

}
