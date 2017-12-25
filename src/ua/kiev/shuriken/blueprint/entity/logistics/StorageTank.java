package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class StorageTank extends Entity {
	
	public static final int NORTH_WEST_AND_SOUTH_EAST = 0;
	public static final int NORTH_EAST_AND_SOUTH_WEST = 2;
	
	
	public StorageTank(float x, float y) {
		super(x, y);
	}
	
	public StorageTank(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	@Override
	public String getName() {
		return Signals.Items.STORAGE_TANK;
	}
	
}