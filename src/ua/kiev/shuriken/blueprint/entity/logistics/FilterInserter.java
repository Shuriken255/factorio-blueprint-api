package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Signals;

public class FilterInserter extends Inserter {
	
	public FilterInserter(float x, float y, int direction) {
		super(x, y, direction);
	}

	public FilterInserter(float x, float y) {
		super(x, y);
	}

	@Override
	public String getName() {
		return Signals.Items.FILTER_INSERTER;
	}
	
	
	private boolean setFiltersFromSignals;
	
	public boolean isSettingFiltersFromSignals() {
		return setFiltersFromSignals;
	}
	
	public void setFiltersFromSignals(boolean setFiltersFromSignals) {
		this.setFiltersFromSignals = setFiltersFromSignals;
	}
	
	
	@Override
	protected String setupToString() {
		if(hasConnections()) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("\"control_behavior\":{");
			if(setFiltersFromSignals) {
				sb.append("\"circuit_mode_of_operation\":1");
			} else if(getEnableCondition() != null) {
				sb.append(getEnableCondition().toString());
			} else {
				sb.append("\"circuit_mode_of_operation\":3");
			}
			
			if(getReadMode() != READ_MODE_NONE) {
				sb.append(",\"circuit_read_hand_contents\":true,\"circuit_hand_read_mode\":");
				sb.append(getReadMode());
			}
			
			if(getStackSizeSignal() != null) {
				sb.append(",\"circuit_set_stack_size\":true,\"stack_control_input_signal\":{\"type\":\"");
				sb.append(Signals.getType(getStackSizeSignal()));
				sb.append("\",\"name\":\"");
				sb.append(getStackSizeSignal());
				sb.append("\"}");
			}
			
			sb.append('}');
			
			return sb.toString();
		} else {
			return null;
		}
	}
	
	
	private String[] filters = new String[5];
	
	public String getFilter(int index) {
		return filters[index];
	}
	
	public void setFilter(int index, String signal) {
		filters[index] = signal;
	}
	
	public boolean areFiltersEmpty() {
		for(int i = 0; i < 5; i++) {
			if(filters[i] != null) {
				return false;
			}
		}
		
		return true;
	}
	
	private String filtersToString() {
		if(areFiltersEmpty()) {
			return null;
		} else {
			StringBuilder sb = new StringBuilder();
			
			boolean isFirst = true;
			sb.append("\"filters\":[");
			for(int i = 0; i < 5; i++) {
				if(filters[i] != null) {
					if(isFirst) {
						isFirst = false;
					} else {
						sb.append(',');
					}
					sb.append("{\"index\":");
					sb.append(i+1);
					sb.append(",\"name\":\"");
					sb.append(filters[i]);
					sb.append("\"}");
				}
			}
			sb.append(']');
			
			return sb.toString();
		}
	}
	
	
	@Override
	protected String advancedSetupToString() {
		StringBuilder sb = new StringBuilder();
		if(!areFiltersEmpty()) {
			sb.append(filtersToString());
		}
		
		if(getOverrideStackSize() > 0 && getStackSizeSignal() == null) {
			if(!areFiltersEmpty()) {
				sb.append(',');
			}
			sb.append("\"override_stack_size\":");
			sb.append(getOverrideStackSize());
		}
		
		if(sb.length() == 0) {
			return null;
		} else {
			return sb.toString();
		}
	}
	
}
