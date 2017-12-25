package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Signals;

public class AssemblingMachine3 extends AssemblingMachine2 {
	
	public AssemblingMachine3(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public AssemblingMachine3(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.ASSEMBLING_MACHINE_3;
	}
	
}
