package ua.kiev.shuriken.blueprint;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class represents modules that can be requested by machine, they may be different kinds.
 */
public class ModuleRequest {
	
	private Map<String, Integer> modules = new HashMap<>();
	
	/**
	 * This method allows you to add modules to module request. If module request already has
	 * certain amount of modules of same type you want to add, their amount will append.
	 * @param module Module you want to add to request.
	 * @param amount Amount of modules you want to add to request.
	 */
	public void addModule(String module, int amount) {
		if(modules.containsKey(module)) {
			modules.put(module, modules.get(module)+amount);
		} else {
			modules.put(module, amount);
		}
	}
	
	/**
	 * Returns JSON representation of module request.
	 * @return JSON representation of module request.
	 */
	@Override
	public String toString() {
		if(modules.isEmpty()) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"items\":{");
		Iterator<Map.Entry<String, Integer>> iterator = modules.entrySet().iterator();
		boolean isFirst = true;
		while(iterator.hasNext()) {
			if(isFirst) {
				isFirst = false;
			} else {
				sb.append(',');
			}
			Map.Entry<String, Integer> entry = iterator.next();
			sb.append('"');
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append(entry.getValue());
		}
		sb.append('}');
		
		return sb.toString();
	}
	
	/**
	 * Clears all modules from requests.
	 */
	public void clear() {
		modules.clear();
	}
	
}
