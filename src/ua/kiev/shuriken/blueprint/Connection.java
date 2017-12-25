package ua.kiev.shuriken.blueprint;

public class Connection {
	
	public static final int TYPE_GENERAL = 0;
	public static final int TYPE_INPUT = 1;
	public static final int TYPE_OUTPUT = 2;
	
	public static final int COLOR_RED = 0;
	public static final int COLOR_GREEN = 1;
	
	
	public Connection(Entity entity, int type) {
		this.entity = entity;
		this.type = type;
	}
	
	
	private int type;
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	
	private Entity entity;
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"entity_id\":");
		sb.append(entity.getEntityNumber());
		
		if(type != TYPE_GENERAL) {
			sb.append(",\"circuit_id\":");
			sb.append(type);
		}
		
		sb.append('}');
		
		return sb.toString();
	}
	
}
