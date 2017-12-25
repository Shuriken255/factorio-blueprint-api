package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class Splitter extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	
	
	public Splitter(float x, float y) {
		super(x, y);
	}
	
	public Splitter(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	@Override
	public String getName() {
		return Signals.Items.SPLITTER;
	}

}