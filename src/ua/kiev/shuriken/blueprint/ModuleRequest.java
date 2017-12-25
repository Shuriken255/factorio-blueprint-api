package ua.kiev.shuriken.blueprint;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ModuleRequest {
	
	private Map<String, Integer> modules = new HashMap<>();
	
	public void addModule(String module, int amount) {
		if(modules.containsKey(module)) {
			modules.put(module, modules.get(module)+amount);
		} else {
			modules.put(module, amount);
		}
	}
	
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
	
	public void clear() {
		modules.clear();
	}
	
}
