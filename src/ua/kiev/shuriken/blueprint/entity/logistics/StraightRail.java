package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;

public class StraightRail extends Entity {
	
	public static final int DIRECTION_VERTICAL = 0;
	public static final int DIRECTION_HORISONTAL = 2;
	
	public static final int DIRECTION_DIAGONAL_TOP_LEFT_PART = 3;
	public static final int DIRECTION_DIAGONAL_DOWN_RIGHT_PART = 7;
	public static final int DIRECTION_DIAGONAL_DOWN_LEFT_PART = 1;
	public static final int DIRECTION_DIAGONAL_TOP_RIGHT_PART = 5;
	

	public StraightRail(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public StraightRail(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return "straight-rail";
	}
	
}
