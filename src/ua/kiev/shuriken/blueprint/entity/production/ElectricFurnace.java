package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.ModuleRequest;
import ua.kiev.shuriken.blueprint.Signals;

public class ElectricFurnace extends SteelFurnace {

	public ElectricFurnace(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.ELECTRIC_FURNACE;
	}
	
	
	private ModuleRequest modules = new ModuleRequest();
	
	/**
	 * Gets module request of this machine
	 * @return module request
	 */
	public ModuleRequest getModuleRequests() {
		return modules;
	}
	
	
	@Override
	protected String advancedSetupToString() {
		return modules.toString();
	}
	
}
