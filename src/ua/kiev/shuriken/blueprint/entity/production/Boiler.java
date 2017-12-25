package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class Boiler extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	

	public Boiler(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public Boiler(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.BOILER;
	}

}
