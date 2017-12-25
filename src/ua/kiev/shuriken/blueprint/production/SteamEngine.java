package ua.kiev.shuriken.blueprint.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class SteamEngine extends Entity {
	
	public static final int DIRECTION_VERTICAL = 0;
	public static final int DIRECTION_HORISONTAL = 2;
	

	public SteamEngine(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public SteamEngine(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.STEAM_ENGINE;
	}

}
