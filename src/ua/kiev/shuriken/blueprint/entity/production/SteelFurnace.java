package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Signals;

public class SteelFurnace extends StoneFurnace {

	public SteelFurnace(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.STEEL_FURNACE;
	}

}
