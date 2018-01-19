package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;

public class CurvedRail extends Entity {
	
	public static final int DIRECTION_NORTH_NORTH_EAST = 3;
	public static final int DIRECTION_EAST_NORTH_EAST = 0;
	public static final int DIRECTION_EAST_SOUTH_EAST = 5;
	public static final int DIRECTION_SOUTH_SOUTH_EAST = 2;
	public static final int DIRECTION_SOUTH_SOUTH_WEST = 7;
	public static final int DIRECTION_WEST_SOUTH_WEST = 4;
	public static final int DIRECTION_WEST_NORTH_WEST = 1;
	public static final int DIRECTION_NORTH_NORTH_WEST = 6;
	
	
	public CurvedRail(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public CurvedRail(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return "curved-rail";
	}
	
}
