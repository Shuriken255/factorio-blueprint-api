package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class ExpressTransportBelt extends FastTransportBelt {

	public ExpressTransportBelt(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public ExpressTransportBelt(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.EXPRESS_TRANSPORT_BELT;
	}
	
}
