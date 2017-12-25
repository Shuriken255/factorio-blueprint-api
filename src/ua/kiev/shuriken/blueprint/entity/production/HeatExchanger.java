package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Signals;

public class HeatExchanger extends Boiler {

	public HeatExchanger(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public HeatExchanger(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.HEAT_EXCHANGER;
	}

}
