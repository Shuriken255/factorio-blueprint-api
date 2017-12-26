package ua.kiev.shuriken.blueprint;

/**
 * This class is responsible to hold all information about connection.
 */
public class Connection {
	
	/**
	 * This type of connection is used for most connection cases.
	 */
	public static final int TYPE_GENERAL = 0;
	
	/**
	 * This type of connection is used only for ArithmeticCombinator and DeciderCombinator.
	 * Use it when you need to make connection from/into input side of those combinators.
	 */
	public static final int TYPE_INPUT = 1;
	
	/**
	 * This type of connection is used only for ArithmeticCombinator and DeciderCombinator.
	 * Use it when you need to make connection from/into output side of those combinators.
	 */
	public static final int TYPE_OUTPUT = 2;
	
	public static final int COLOR_RED = 0;
	public static final int COLOR_GREEN = 1;
	
	/**
	 * Creates new connection. In all cases, Entity class creates connection class objects
	 * himself. You don't have to call this constructor at all.
	 * @param entity Entity, you are connection your entity to.
	 * @param type Type of connection. Use "TYPE_" constants.
	 */
	public Connection(Entity entity, int type) {
		this.entity = entity;
		this.type = type;
	}
	
	
	private int type;
	/**
	 * Returns type of current connection. "TYPE_" constants should be used for comparing.
	 * @return Type of current connection.
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Sets type of connection, "TYPE_" constants should be used.
	 * @param type type of connection.
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	
	private Entity entity;
	
	/**
	 * Returns entity that current connection is dedicated to.
	 * @return Entity that current connection is dedicated to.
	 */
	public Entity getEntity() {
		return entity;
	}
	
	/**
	 * Allows you to change Entity, this connection is dedicated to.
	 * @param entity Entity you want to connect to.
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Returns JSON representation of this connection.
	 * @return JSON representation of this connection. 
	 */
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
