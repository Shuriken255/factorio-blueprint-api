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
	
	/**
	 * Class that is used to describe signal and it's value.
	 */
	public static class Signal {
		
		private String signal;
		
		/**
		 * Returns signal's constant. Use Signals class's constants to compare.
		 * @return Signal's constant.
		 */
		public String getSignal() {
			return signal;
		}
		
		/**
		 * Allows you to change signal.
		 * @param signal signal you want to set. Use Signals class's constants for this variable.
		 */
		public void setSignal(String signal) {
			this.signal = signal;
		}
		
		
		private int value;
		
		/**
		 * Returns signal's value that constant combinator will output for this signal.
		 * @return Signal's value.
		 */
		public int getValue() {
			return value;
		}
		
		/**
		 * Allows you to set signal's value that constant combinator will output for this signal.
		 * @param count value.
		 */
		public void setValue(int value) {
			this.value = value;
		}
		
		
		public Signal(String signal, int value) {
			this.signal = signal;
			this.value = value;
		}
		
	}
	
	
	private Signal[] signals = new Signal[18];
	
	/**
	 * Returns signal in certain position.
	 * @param index index of signal, may be [0..17]
	 * @return Signal in certain position.
	 */
	public Signal getSignal(int index) {
		return signals[index];
	}
	
	/**
	 * Allows you to fully change signal in certain position or remove it, using "null" for signal.
	 * @param index index you want to make replacement in.
	 * @param signal signal object or "null", if you want to delete it.
	 */
	public void setSignal(int index, Signal signal) {
		signals[index] = signal;
	}
	
	/**
	 * Returns "true" if combinator contains at least one signal and "false" if not.
	 * @return "True" if combinator contains at least one signal and "false" if not.
	 */
	public boolean hasSignals() {
		for(int i = 0; i < 18; i++) {
			if(signals[i] != null) {
				return true;
			}
		}
		
		return false;
	}
	
	
	private boolean on = true;
	
	/**
	 * Returns "true" if combinator is turned on and "false" if not.
	 * @return "true" if combinator is turned on and "false" if not.
	 */
	public boolean isOn() {
		return on;
	}
	
	/**
	 * Sets constant combinator's state to "On"
	 */
	public void turnOn() {
		on = true;
	}
	
	/**
	 * Sets constant combinator's state to "Off"
	 */
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
					sb.append(signals[i].value);
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
