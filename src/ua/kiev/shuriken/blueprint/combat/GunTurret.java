package ua.kiev.shuriken.blueprint.combat;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class GunTurret extends Entity {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	

	public GunTurret(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public GunTurret(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.GUN_TURRET;
	}
	
}
