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
		
		/**
		 * Gets item that will be requested
		 * @return
		 */
		public String getItem() {
			return item;
		}
		
		/**
		 * Sets item for request
		 * @param item item's signal
		 */
		public void setItem(String item) {
			this.item = item;
		}
		
		
		private int count;
		
		/**
		 * Gets request's amount
		 * @return amount
		 */
		public int getAmount() {
			return count;
		}
		
		/**
		 * Sets requests's amount
		 * @param amount amount of items for request
		 */
		public void setAmount(int amount) {
			this.count = amount;
		}
		
		
		public Request(String item, int count) {
			this.item = item;
			this.count = count;
		}
		
	}
	
	private Request[] requests = new Request[12];
	
	/**
	 * Gets request at certain index
	 * @param index index of request
	 * @return Request at certain index or "null if request is not set for current index
	 */
	public Request getRequest(int index) {
		return requests[index];
	}
	
	/**
	 * Sets request at certain index or removes it
	 * @param index index of request
	 * @param request request for current position or "null" if you want to clear it.
	 */
	public void setRequest(int index, Request request) {
		requests[index] = request;
	}
	
	/**
	 * Checks if requester chests contains at least one request
	 * @return "true" if chest contains at least one request and "false" if not.
	 */
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
	
	/**
	 * Checks if requester chest sets requests depends of signals from circuit network
	 * @return
	 */
	public boolean isSettingRequestsFromSignal() {
		return setRequestsFromSignal;
	}
	
	/**
	 * Sets if requester chests should set signals from circuit network or not
	 * @param setRequestsFromSignal "true" to enable "set contents" option and "false" to disable
	 */
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
