package ua.kiev.shuriken.blueprint.production;

import ua.kiev.shuriken.blueprint.Signals;

public class BurnerMiningDrill extends ElectricMiningDrill {

	public BurnerMiningDrill(float x, float y, int direction) {
		super(x, y, direction);
		removeModules();
	}

	public BurnerMiningDrill(float x, float y) {
		super(x, y);
		removeModules();
	}
	
	@Override
	public String getName() {
		return Signals.Items.BURNER_MINING_DRILL;
	}

}
