package ua.kiev.shuriken.blueprint.entity.production;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class Accumulator extends Entity {

	public Accumulator(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.ACCUMULATOR;
	}
	
	
	private String outputSignal = Signals.Virtual.SIGNAL_A;
	
	public String getOutputSignal() {
		return outputSignal;
	}
	
	public void setOutputSignal(String signal) {
		outputSignal = signal;
	}
	
	
	@Override
	protected String setupToString() {
		if(hasConnections() && outputSignal != null) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("\"control_behavior\":{\"output_signal\":{\"type\":\"");
			sb.append(Signals.getType(outputSignal));
			sb.append("\",\"name\":\"");
			sb.append(outputSignal);
			sb.append("\"}}");
			
			return sb.toString();
		} else {
			return null;
		}
	}

}
