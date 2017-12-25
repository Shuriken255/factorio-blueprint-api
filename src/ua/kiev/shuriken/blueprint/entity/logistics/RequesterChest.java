package ua.kiev.shuriken.blueprint.entity.logistics;

import ua.kiev.shuriken.blueprint.Entity;
import ua.kiev.shuriken.blueprint.Signals;

public class RequesterChest extends Entity {
	
	public RequesterChest(float x, float y) {
		super(x, y);
	}
	
	@Override
	public String getName() {
		return Signals.Items.LOGISTIC_CHEST_REQUESTER;
	}
	
	public static class Request {
		
		private String item;
		
		public String getItem() {
			return item;
		}
		
		public void setItem(String item) {
			this.item = item;
		}
		
		
		private int count;
		
		public int getCount() {
			return count;
		}
		
		public void setCount(int count) {
			this.count = count;
		}
		
		
		public Request(String item, int count) {
			this.item = item;
			this.count = count;
		}
		
	}
	
	private Request[] requests = new Request[12];
	
	public Request getRequest(int index) {
		return requests[index];
	}
	
	public void setRequest(int index, Request request) {
		requests[index] = request;
	}
	
	public boolean isContainingRequests() {
		for(int i = 0; i < 12; i++) {
			if(requests[i] != null) {
				return true;
			}
		}
		
		return false;
	}
	
	protected String requestsToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"request_filters\":[");
		
		boolean passedFirst = false;
		for(int i = 0; i < 12; i++) {
			if(requests[i] != null) {
				if(passedFirst) {
					sb.append(',');
				} else {
					passedFirst = true;
				}
				sb.append("{\"index\":");
				sb.append(i+1);
				sb.append(",\"name\":\"");
				sb.append(requests[i].item);
				sb.append("\",\"count\":");
				sb.append(requests[i].count);
				sb.append('}');
			}
		}
		
		sb.append(']');
		
		return sb.toString();
	}
	
	private boolean setRequestsFromSignal;
	
	public boolean isSettingRequestsFromSignal() {
		return setRequestsFromSignal;
	}
	
	public void setSettingRequestsFromSignal(boolean setRequestsFromSignal) {
		this.setRequestsFromSignal = setRequestsFromSignal;
	}
	
	@Override
	protected String setupToString() {
		if(hasConnections() && setRequestsFromSignal) {
			return "\"control_behavior\":{\"circuit_mode_of_operation\":1}";
		} else {
			return null;
		}
	}
	
	@Override
	public String advancedSetupToString() {
		if((hasConnections() && setRequestsFromSignal) || !isContainingRequests()) {
			return null;
		} else {
			return requestsToString();
		}
	}
	
}
