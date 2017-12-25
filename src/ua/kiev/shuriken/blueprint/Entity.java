package ua.kiev.shuriken.blueprint;

import java.util.HashSet;
import java.util.Set;

public abstract class Entity {
	
	public Entity(float x, float y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	abstract public String getName();
	
	private int direction;
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
	// Planned stuff
	/*abstract public float getWidth();
	abstract public float getHeight();*/
	
	
	private float x, y;
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
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
				inputGreenConnections.add(new Connection(entity, typeTo));
			} else {
				inputRedConnections.add(new Connection(entity, typeTo));
			}
		}
	}
	
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
