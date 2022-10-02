package ua.kiev.shuriken.blueprint;

import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class for all entities you add into blueprint.
 */
public abstract class Entity {
	
	/**
	 * Creates new entity with certain position and direction.
	 * Use child class's "DIRECTION_" constants for it.
	 * @param x position of Entity by "x" coordinate
	 * @param y position of Entity by "y" coordinate
	 * @param direction direction of object. Use child class's "DIRECTION_" constants
	 */
	public Entity(float x, float y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	/**
	 * Creates new entity with certain position.
	 * @param x position of Entity by "x" coordinate
	 * @param y position of Entity by "y" coordinate
	 */
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns entity's name. Signals class's constants should be used to compare.
	 * @return must return item's name used as "name" variable in blueprint's JSON
	 */
	abstract public String getName();
	
	
	private int direction;
	
	/**
	 * Returns entity's direction.
	 * @return Entity's direction.
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Sets entity's direction.
	 * @param direction direction, should use child class's constants;
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
	// Planned stuff
	/*abstract public float getWidth();
	abstract public float getHeight();*/
	
	
	private float x, y;
	
	/**
	 * Returns entity's position by "x" coordinate
	 * @return Entity's position by "x" coordinate
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Returns entity's position by "y" coordinate
	 * @return Entity's position by "y" coordinate
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Sets entity's position by "x" coordinate
	 * @param x desired "x" position
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Sets entity's position by "y" coordinate
	 * @param y desired "y" position
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Sets entity's position by both coordinate
	 * @param x desired "x" position
	 * @param y desired "y" position
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	private int entityNumber = -1;
	
	protected void setEntityNumber(int number) {
		entityNumber = number;
	}
	
	protected int getEntityNumber() {
		return entityNumber;
	}
	
	
	protected String setupToString() {
		return null;
	}
	
	protected String advancedSetupToString() {
		return null;
	}
	
	
	private Set<Connection> inputGreenConnections = new HashSet<>();
	private Set<Connection> inputRedConnections = new HashSet<>();
	private Set<Connection> outputGreenConnections = new HashSet<>();
	private Set<Connection> outputRedConnections = new HashSet<>();
	
	/**
	 * This method creates connection between entity you call this method from and entity specified in parameters.
	 * If you are familiar with JSON representations of Factorio's blueprints, you probably know, that connections
	 * should be created on both entities. Don't worry, once you call this method on one entity, it creates
	 * connection on another one.
	 * 
	 * This method should be used for simple connections, since it uses general connection type on both ends.
	 * If you need to make connection from/with arithmetical or decider combinator, you need to use
	 * "connectTo(Entity, int, int, int)" method.
	 * @param entity entity you want to connect to.
	 * @param color color of wire you want to use for this connection.
	 */
	public void connectTo(Entity entity, int color) {
		int typeFrom = Connection.TYPE_GENERAL;
		int typeTo = Connection.TYPE_GENERAL;
		if(typeFrom == Connection.TYPE_INPUT || typeFrom == Connection.TYPE_GENERAL) {
			if(color == Connection.COLOR_GREEN) {
				inputGreenConnections.add(new Connection(entity, typeTo));
				entity.addConnectionWithoutReflection(this, typeTo, typeFrom, color);
			} else {
				inputRedConnections.add(new Connection(entity, typeTo));
				entity.addConnectionWithoutReflection(this, typeTo, typeFrom, color);
			}
		} else {
			if(color == Connection.COLOR_GREEN) {
				outputGreenConnections.add(new Connection(entity, typeTo));
				entity.addConnectionWithoutReflection(this, typeTo, typeFrom, color);
			} else {
				outputRedConnections.add(new Connection(entity, typeTo));
				entity.addConnectionWithoutReflection(this, typeTo, typeFrom, color);
			}
		}
	}
	
	/**
	 * Same as method with 2 arguments, but in this one you can choose connection type, that will be
	 * used for both ends. You must use it if your connection has arithmetical or decider combinator
	 * at least on one end.
	 * @param entity entity you want to connect to.
	 * @param typeFrom connection type you want to use for entity you are calling this method from.
	 * @param typeTo connection type you want to use for entity you specified in parameters.
	 * @param color color of wire you want to use for this connection.
	 */
	public void connectTo(Entity entity, int typeFrom, int typeTo, int color) {
		if(typeFrom == Connection.TYPE_INPUT) {
			if(color == Connection.COLOR_GREEN) {
				inputGreenConnections.add(new Connection(entity, typeTo));
				entity.addConnectionWithoutReflection(this, typeTo, typeFrom, color);
			} else {
				inputRedConnections.add(new Connection(entity, typeTo));
				entity.addConnectionWithoutReflection(this, typeTo, typeFrom, color);
			}
		} else {
			if(color == Connection.COLOR_GREEN) {
				outputGreenConnections.add(new Connection(entity, typeTo));
				entity.addConnectionWithoutReflection(this, typeTo, typeFrom, color);
			} else {
				outputRedConnections.add(new Connection(entity, typeTo));
				entity.addConnectionWithoutReflection(this, typeTo, typeFrom, color);
			}
		}
	}
	
	private void addConnectionWithoutReflection(Entity entity, int typeFrom, int typeTo, int color) {
		if(entity == this) {
			return;
		}
		if(typeFrom == Connection.TYPE_INPUT) {
			if(color == Connection.COLOR_GREEN) {
				inputGreenConnections.add(new Connection(entity, typeTo));
			} else {
				inputRedConnections.add(new Connection(entity, typeTo));
			}
		} else {
			if(color == Connection.COLOR_GREEN) {
				outputGreenConnections.add(new Connection(entity, typeTo));
			} else {
				outputRedConnections.add(new Connection(entity, typeTo));
			}
		}
	}
	
	/**
	 * Returns "true" if entity has at least one connection.
	 * @return "true" if entity has at least one connection.
	 */
	public boolean hasConnections() {
		return !inputGreenConnections.isEmpty()
				|| !inputRedConnections.isEmpty()
				|| !outputGreenConnections.isEmpty()
				|| !outputRedConnections.isEmpty();
	}
	
	private String connectionsToString() {
		if(hasConnections()) {
			StringBuilder sb = new StringBuilder();
			sb.append("\"connections\":{");
			boolean inputHasConnections = !inputGreenConnections.isEmpty() || !inputRedConnections.isEmpty();
			if(inputHasConnections) {
				sb.append("\"1\":{");
				if(!inputRedConnections.isEmpty()) {
					sb.append("\"red\":[");
					boolean isFirst = true;
					for(Connection c:inputRedConnections) {
						if(isFirst) {
							isFirst = false;
						} else {
							sb.append(',');
						}
						sb.append(c.toString());
					}
					sb.append("]");
					if(!inputGreenConnections.isEmpty()) {
						sb.append(',');
					}
				}
				
				if(!inputGreenConnections.isEmpty()) {
					sb.append("\"green\":[");
					boolean isFirst = true;
					for(Connection c:inputGreenConnections) {
						if(isFirst) {
							isFirst = false;
						} else {
							sb.append(',');
						}
						sb.append(c.toString());
					}
					sb.append("]");
				}
				sb.append('}');
			}
			
			
			if(!outputGreenConnections.isEmpty() || !outputRedConnections.isEmpty()) {
				if(inputHasConnections) {
					sb.append(',');
				}
				sb.append("\"2\":{");
				if(!outputRedConnections.isEmpty()) {
					sb.append("\"red\":[");
					boolean isFirst = true;
					for(Connection c:outputRedConnections) {
						if(isFirst) {
							isFirst = false;
						} else {
							sb.append(',');
						}
						sb.append(c.toString());
					}
					sb.append("]");
					if(!outputGreenConnections.isEmpty()) {
						sb.append(',');
					}
				}
				
				if(!outputGreenConnections.isEmpty()) {
					sb.append("\"green\":[");
					boolean isFirst = true;
					for(Connection c:outputGreenConnections) {
						if(isFirst) {
							isFirst = false;
						} else {
							sb.append(',');
						}
						sb.append(c.toString());
					}
					sb.append("]");
				}
				sb.append('}');
			}
			sb.append('}');
			
			return sb.toString();
		}
		
		return null;
	}
	
	/**
	 * Returns JSON representation of current entity.
	 * @return JSON representation of current entity.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// Basic information
		sb.append("{\"entity_number\":");
		sb.append(entityNumber);
		sb.append(",\"name\":\"");
		sb.append(getName());
		sb.append("\",\"position\":{\"x\":");
		if(x%1 == 0) {
			sb.append((int)x);
		} else {
			sb.append(x);
		}
		sb.append(",\"y\":");
		if(y%1 == 0) {
			sb.append((int)y);
		} else {
			sb.append(y);
		}
		sb.append('}');
		
		// Direction
		if(direction != 0) {
			sb.append(",\"direction\":");
			sb.append(direction);
		}
		
		// Setup
		String setup = setupToString();
		if(setup != null) {
			sb.append(',');
			sb.append(setup);
		}
		
		// Connections
		if(hasConnections()) {
			sb.append(',');
			sb.append(connectionsToString());
		}
		
		String advancedSetup = advancedSetupToString();
		if(advancedSetup != null) {
			sb.append(',');
			sb.append(advancedSetupToString());
		}
		
		sb.append('}');
		return sb.toString();
	}
	
}
