package ua.kiev.shuriken.blueprint.production;

import ua.kiev.shuriken.blueprint.Signals;

public class SteamTurbine extends SteamEngine {
	
	public SteamTurbine(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public SteamTurbine(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.STEAM_TURBINE;
	}
	
}
