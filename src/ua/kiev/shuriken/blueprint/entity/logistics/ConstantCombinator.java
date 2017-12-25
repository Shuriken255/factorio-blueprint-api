package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class ConstantCombinator extends Entity {

	public ConstantCombinator(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.CONSTANT_COMBINATOR;
	}
	
	
	public static class Signal {
		
		private String signal;
		
		public String getSignal() {
			return signal;
		}
		
		public void setSignal(String signal) {
			this.signal = signal;
		}
		
		
		private int count;
		
		public int getCount() {
			return count;
		}
		
		public void setCount(int count) {
			this.count = count;
		}
		
		
		public Signal(String signal, int count) {
			this.signal = signal;
			this.count = count;
		}
		
	}
	
	
	private Signal[] signals = new Signal[18];
	
	public Signal getSignal(int index) {
		return signals[index];
	}
	
	public void setSignal(int index, Signal signal) {
		signals[index] = signal;
	}
	
	public boolean hasSignals() {
		for(int i = 0; i < 18; i++) {
			if(signals[i] != null) {
				return true;
			}
		}
		
		return false;
	}
	
	
	private boolean on = true;
	
	public boolean isOn() {
		return on;
	}
	
	public void turnOn() {
		on = true;
	}
	
	public void turnOff() {
		on = false;
	}
	
	@Override
	protected String setupToString() {
		if(!hasSignals() && on) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("\"control_behavior\":{");
		
		if(hasSignals()) {
			sb.append("\"filters\":[");
			boolean passedFirst = false;
			for(int i = 0; i < 18; i++) {
				if(signals[i] != null) {
					if(passedFirst) {
						sb.append(',');
					} else {
						passedFirst = true;
					}
					sb.append("{\"signal\":{\"type\":\"");
					sb.append(Signals.getType(signals[i].signal));
					sb.append("\",\"name\":\"");
					sb.append(signals[i].signal);
					sb.append("\"},\"count\":");
					sb.append(signals[i].count);
					sb.append(",\"index\":");
					sb.append(i+1);
					sb.append('}');
				}
			}
			sb.append(']');
			
			if(!on) {
				sb.append(',');
			}
		}
		
		if(!on) {
			sb.append("\"is_on\":false");
		}
		
		sb.append('}');
		return sb.toString();
	}
	
}
