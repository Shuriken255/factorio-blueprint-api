package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class PipeToGround extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	

	public PipeToGround(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public PipeToGround(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.PIPE_TO_GROUND;
	}

}
