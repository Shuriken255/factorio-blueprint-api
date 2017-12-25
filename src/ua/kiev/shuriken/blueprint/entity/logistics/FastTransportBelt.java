package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class FastTransportBelt extends TransportBelt {
	
	public FastTransportBelt(float x, float y, int direction) {
		super(x, y, direction);
	}
	
	public FastTransportBelt(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.FAST_TRANSPORT_BELT;
	}
	
}
