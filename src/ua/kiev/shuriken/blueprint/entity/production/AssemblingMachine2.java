package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.ModuleRequest;
import ua.kiev.shuriken.blueprint.Signals;

public class AssemblingMachine2 extends AssemblingMachine1 {
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 2;
	public static final int DIRECTION_SOUTH = 4;
	public static final int DIRECTION_WEST = 6;
	
	
	public AssemblingMachine2(float x, float y, int direction) {
		super(x, y);
		setDirection(direction);
	}
	
	public AssemblingMachine2(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.ASSEMBLING_MACHINE_2;
	}
	
	
	private ModuleRequest modules = new ModuleRequest();
	
	/**
	 * Gets module request for this machine
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
