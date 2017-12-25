package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class Roboport extends Entity {
	
	public static final int MODE_READ_LOGISTICS_CONTENTS = 0;
	public static final int MODE_READ_ROBOT_STATISTICS = 1;
	
	
	public Roboport(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.ROBOPORT;
	}
	
	
	private int mode = MODE_READ_LOGISTICS_CONTENTS;
	
	public int getMode() {
		return mode;
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	
	private String availableLogisticOutputSignal = Signals.Virtual.SIGNAL_X;
	
	public String getAvailableLogisticOutputSignal() {
		return availableLogisticOutputSignal;
	}
	
	public void setAvialableLogisticOutputSignal(String signal) {
		availableLogisticOutputSignal = signal;
	}
	
	
	private String totalLogisticOutputSignal = Signals.Virtual.SIGNAL_Y;
	
	public String getTotalLogisticOutputSignal() {
		return totalLogisticOutputSignal;
	}
	
	public void setTotalLogisticOutputSignal(String signal) {
		totalLogisticOutputSignal = signal;
	}
	
	
	private String availableConstructionOutputSignal = Signals.Virtual.SIGNAL_Z;
	
	public String getAvailableConstructionOutputSignal() {
		return availableConstructionOutputSignal;
	}
	
	public void setAvialableConstructionOutputSignal(String signal) {
		availableConstructionOutputSignal = signal;
	}
	
	
	private String totalConstructionOutputSignal = Signals.Virtual.SIGNAL_T;
	
	public String getTotalConstructionOutputSignal() {
		return totalConstructionOutputSignal;
	}
	
	public void setTotalConstructionOutputSignal(String signal) {
		totalConstructionOutputSignal = signal;
	}
	
	
	@Override
	protected String setupToString() {
		if(!hasConnections()) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("\"control_behavior\":{");
		
		sb.append("\"circuit_mode_of_operation\":");
		sb.append(mode);
		
		if(mode == MODE_READ_ROBOT_STATISTICS) {
			if(availableLogisticOutputSignal != Signals.Virtual.SIGNAL_X) {
				sb.append(",\"available_logistic_output_signal\":{\"type\":\"");
				sb.append(Signals.getType(availableLogisticOutputSignal));
				sb.append("\",\"name\":\"");
				sb.append(availableLogisticOutputSignal);
				sb.append("\"}");
			}
			
			if(totalLogisticOutputSignal != Signals.Virtual.SIGNAL_Y) {
				sb.append(",\"total_logistic_output_signal\":{\"type\":\"");
				sb.append(Signals.getType(totalLogisticOutputSignal));
				sb.append("\",\"name\":\"");
				sb.append(totalLogisticOutputSignal);
				sb.append("\"}");
			}
			
			if(availableConstructionOutputSignal != Signals.Virtual.SIGNAL_Z) {
				sb.append(",\"available_construction_output_signal\":{\"type\":\"");
				sb.append(Signals.getType(availableConstructionOutputSignal));
				sb.append("\",\"name\":\"");
				sb.append(availableConstructionOutputSignal);
				sb.append("\"}");
			}
			
			if(totalConstructionOutputSignal != Signals.Virtual.SIGNAL_T) {
				sb.append(",\"total_construction_output_signal\":{\"type\":\"");
				sb.append(Signals.getType(totalConstructionOutputSignal));
				sb.append("\",\"name\":\"");
				sb.append(totalConstructionOutputSignal);
				sb.append("\"}");
			}
		}
		
		sb.append('}');
		
		return sb.toString();
	}

}
