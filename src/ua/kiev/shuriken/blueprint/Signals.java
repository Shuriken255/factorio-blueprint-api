package ua.kiev.shuriken.blueprint;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all signals items and fluids that can be used
 * in circuit conditions, recipes, module requests, logistic requests and more.
 */
public class Signals {
	
	private static Map<String, String> types;
	
	public static final String TYPE_ITEM = "item";
	public static final String TYPE_VIRTUAL = "virtual";
	public static final String TYPE_FLUID = "fluid";
	
	/**
	 * Returns type of signal. You should use all constants in this class and inner
	 * classes for this method.
	 * @param signal signal you want to know type of.
	 * @return type of this signal
	 */
	public static String getType(String signal) {
		return types.get(signal);
	}
	
	/**
	 * Contains all items in 0.16 Factorio version.
	 */
	public static class Items {
		
		// All belts
		public static final String TRANSPORT_BELT = "transport-belt";
		public static final String FAST_TRANSPORT_BELT = "fast-transport-belt";
		public static final String EXPRESS_TRANSPORT_BELT = "express-transport-belt";
		public static final String UNDERGROUND_BELT = "underground-belt";
		public static final String FAST_UNDERGROUND_BELT = "fast-underground-belt";
		public static final String EXPRESS_UNDERGROUND_BELT = "express-underground-belt";
		public static final String SPLITTER = "splitter";
		public static final String FAST_SPLITTER = "fast-splitter";
		public static final String EXPRESS_SPLITTER = "express-splitter";
		
		// All inserters
		public static final String BURNER_INSERTER = "burner-inserter";
		public static final String INSERTER = "inserter";
		public static final String LONG_HANDED_INSERTER = "long-handed-inserter";
		public static final String FAST_INSERTER = "fast-inserter";
		public static final String FILTER_INSERTER = "filter-inserter";
		public static final String STACK_INSERTER = "stack-inserter";
		public static final String STACK_FILTER_INSERTER = "stack-filter-inserter";
		
		// All storage chests
		public static final String WOODEN_CHEST = "wooden-chest";
		public static final String IRON_CHEST = "iron-chest";
		public static final String STEEL_CHEST = "steel-chest";
		
		// All electric poles
		public static final String SMALL_ELECTRIC_POLE = "small-electric-pole";
		public static final String MEDIUM_ELECTRIC_POLE = "medium-electric-pole";
		public static final String BIG_ELECTRIC_POLE = "big-electric-pole";
		public static final String SUBSTATION = "substation";
		
		// Train-related stuff
		public static final String RAIL = "rail";
		public static final String TRAIN_STOP = "train-stop";
		public static final String RAIL_SIGNAL = "rail-signal";
		public static final String RAIL_CHAIN_SIGNAL = "rail-chain-signal";
		public static final String LOCOMOTIVE = "locomotive";
		public static final String CARGO_WAGON = "cargo-wagon";
		public static final String FLUID_WAGON = "fluid-wagon";
		public static final String ARTILLERY_WAGON = "artillery-wagon";
		
		// Car and tank
		public static final String CAR = "car";
		public static final String TANK = "tank";
		
		// Liquid related stuff
		public static final String STORAGE_TANK = "storage-tank";
		public static final String PIPE = "pipe";
		public static final String PIPE_TO_GROUND = "pipe-to-ground";
		public static final String PUMP = "pump";
		
		// Circuit network stuff
		public static final String LAMP = "small-lamp";
		public static final String RED_WIRE = "red-wire";
		public static final String GREEN_WIRE = "green-wire";
		public static final String ARITHMETIC_COMBINATOR = "arithmetic-combinator";
		public static final String DECIDER_COMBINATOR = "decider-combinator";
		public static final String CONSTANT_COMBINATOR = "constant-combinator";
		public static final String POWER_SWITCH = "power-switch";
		public static final String PROGRAMMABLE_SPEAKER = "programmable-speaker";
		
		// Drones and logistic stuff
		public static final String LOGISTIC_ROBOT = "logistic-robot";
		public static final String CONSTRUCTION_ROBOT = "construction-robot";
		public static final String LOGISTIC_CHEST_ACTIVE_PROVIDER = "logistic-chest-active-provider";
		public static final String LOGISTIC_CHEST_PASSIVE_PROVIDER = "logistic-chest-passive-provider";
		public static final String LOGISTIC_CHEST_STORAGE = "logistic-chest-storage";
		public static final String LOGISTIC_CHEST_BUFFER = "logistic-chest-buffer";
		public static final String LOGISTIC_CHEST_REQUESTER = "logistic-chest-requester";
		public static final String ROBOPORT = "roboport";
		
		// Surfaces
		public static final String STONE_BRICK = "stone-brick";
		public static final String CONCRETE = "concrete";
		public static final String HAZARD_CONCRETE = "hazard-concrete";
		public static final String LANDFILL = "landfill";
		public static final String CLIFF_EXPLOSIVES = "cliff-explosives";
		
		// Energy
		public static final String BOILER = "boiler";
		public static final String STEAM_ENGINE = "steam-engine";
		public static final String STEAM_TURBINE = "steam-turbine";
		public static final String SOLAR_PANEL = "solar-panel";
		public static final String ACCUMULATOR = "accumulator";
		public static final String NUCLEAR_REACTOR = "nuclear-reactor";
		public static final String HEAT_EXCHANGER = "heat-exchanger";
		public static final String HEAT_PIPE = "heat-pipe";
		
		// Tools
		public static final String IRON_AXE = "iron-axe";
		public static final String STEEL_AXE = "steel-axe";
		public static final String REPAIR_PACK = "repair-pack";
		public static final String BLUEPRINT = "blueprint";
		public static final String DECONSTRUCTION_PLANNER = "deconstruction-planner";
		public static final String BLUEPRINT_BOOK = "blueprint-book";
		
		// Furnaces
		public static final String STONE_FURNACE = "stone-furnace";
		public static final String STEEL_FURNACE = "steel-furnace";
		public static final String ELECTRIC_FURNACE = "electric-furnace";
		
		// Mining
		public static final String BURNER_MINING_DRILL = "burner-mining-drill";
		public static final String ELECTRIC_MINING_DRILL = "electric-mining-drill";
		public static final String OFFSHORE_PUMP = "offshore-pump";
		public static final String PUMPJACK = "pumpjack";
		
		// Modules
		public static final String BEACON = "beacon";
		public static final String SPEED_MODULE_1 = "speed-module";
		public static final String SPEED_MODULE_2 = "speed-module-2";
		public static final String SPEED_MODULE_3 = "speed-module-3";
		public static final String EFFECTIVITY_MODULE_1 = "effectivity-module";
		public static final String EFFECTIVITY_MODULE_2 = "effectivity-module-2";
		public static final String EFFECTIVITY_MODULE_3 = "effectivity-module-3";
		public static final String PRODUCTIVITY_MODULE_1 = "productivity-module";
		public static final String PRODUCTIVITY_MODULE_2 = "productivity-module-2";
		public static final String PRODUCTIVITY_MODULE_3 = "productivity-module-3";
		
		// Craft
		public static final String ASSEMBLING_MACHINE_1 = "assembling-machine-1";
		public static final String ASSEMBLING_MACHINE_2 = "assembling-machine-2";
		public static final String ASSEMBLING_MACHINE_3 = "assembling-machine-3";
		public static final String OIL_REFINERY = "oil-refinery";
		public static final String CHEMICAL_PLANT = "chemical-plant";
		public static final String CENTRIFUGE = "centrifuge";
		public static final String LAB = "lab";
		
		// Basic resources
		public static final String RAW_WOOD = "raw-wood";
		public static final String COAL = "coal";
		public static final String STONE = "stone";
		public static final String IRON_ORE = "iron-ore";
		public static final String COPPER_ORE = "copper-ore";
		public static final String URANIUM_ORE = "uranium-ore";
		public static final String RAW_FISH = "raw-fish";
		
		// Processed resources
		public static final String WOOD = "wood";
		public static final String IRON_PLATE = "iron-plate";
		public static final String COPPER_PLATE = "copper-plate";
		public static final String SOLID_PLATE = "solid-fuel";
		public static final String STEEL_PLATE = "steel-plate";
		public static final String PLASTIC_BAR = "plastic-bar";
		public static final String SULFUR = "sulfur";
		public static final String BATTERY = "battery";
		public static final String EXPLOSIVES = "explosives";
		
		// Basic components
		public static final String COPPER_CABLE = "copper-cable";
		public static final String IRON_STICK = "iron-stick";
		public static final String IRON_GEAR_WHEEL = "iron-gear-wheel";
		public static final String EMPTY_BARREL = "empty-barrel";
		public static final String ELECTRONIC_CIRCUIT = "electronic-circuit";
		public static final String ADVANCED_CIRCUIT = "advanced-circuit";
		public static final String PROCESSING_UNIT = "processing-unit";
		public static final String ENGINE_UNIT = "engine-unit";
		public static final String ELECTRIC_ENGINE_UNIT = "electric-engine-unit";
		public static final String FLYING_ROBOT_FRAME = "flying-robot-frame";
		
		// Barrels
		public static final String CRUDE_OIL_BARREL = "crude-oil-barrel";
		public static final String HEAVY_OIL_BARREL = "heavy-oil-barrel";
		public static final String LIGHT_OIL_BARREL = "light-oil-barrel";
		public static final String LUBRICANT_BARREL = "lubricant-barrel";
		public static final String PETROLEUM_GAS_BARREL = "petroleum-gas-barrel";
		public static final String SULFURIC_ACID_BARREL = "sulfuric-acid-barrel";
		public static final String WATER_BARREL = "water-barrel";
		
		// Science packs
		public static final String SCIENCE_PACK_1 = "science-pack-1";
		public static final String SCIENCE_PACK_2 = "science-pack-2";
		public static final String SCIENCE_PACK_3 = "science-pack-3";
		public static final String MILITARY_SCIENCE_PACK = "military-science-pack";
		public static final String PRODUCTION_SCIENCE_PACK = "production-science-pack";
		public static final String HIGH_TECH_SCIENCE_PACK = "high-tech-science-pack";
		public static final String SPACE_SCIENCE_PACK = "space-science-pack";
		
		// Science packs
		public static final String SATELLITE = "satellite";
		public static final String ROCKET_CONTROL_UNIT = "rocket-control-unit";
		public static final String LOW_DENSITY_STRUCTURE = "low-density-structure";
		public static final String ROCKET_FUEL = "rocket-fuel";
		public static final String NUCLEAR_FUEL = "nuclear-fuel";
		public static final String URANIUM_235 = "uranium-235";
		public static final String URANIUM_238 = "uranium-238";
		public static final String URANIUM_FUEL_CELL = "uranium-fuel-cell";
		public static final String USED_UP_URANIUM_FUEL_CELL = "used-up-uranium-fuel-cell";
		
		// Ammo
		public static final String FIREARM_MAGAZINE = "firearm-magazine";
		public static final String PIERCING_ROUNDS_MAGAZINE = "piercing-rounds-magazine";
		public static final String URANIUM_ROUNDS_MAGAZINE = "uranium-rounds-magazine";
		public static final String SHOTGUN_SHELL = "shotgun-shell";
		public static final String PIERCING_SHOTGUN_SHELL = "piercing-shotgun-shell";
		public static final String CANNON_SHELL = "cannon-shell";
		public static final String EXPLOSIVE_CANNON_SHELL = "explosive-cannon-shell";
		public static final String URNAIUM_CANNON_SHELL = "uranium-cannon-shell";
		public static final String EXPLOSIVE_URNAIUM_CANNON_SHELL = "explosive-uranium-cannon-shell";
		public static final String ARTILLERY_SHELL = "artillery-shell";
		
		// Weapons
		public static final String PISTOL = "pistol";
		public static final String SUBMACHINE_GUN = "submachine-gun";
		public static final String SHOTGUN = "shotgun";
		public static final String COMBAT_SHOTGUN = "combat-shotgun";
		public static final String ROCKET_LAUNCHER = "rocket-launcher";
		public static final String FLAMETHROWER = "flamethrower";
		public static final String LAND_MINE = "land-mine";
		
		// Throwable
		public static final String GRENADE = "grenade";
		public static final String CLUSTER_GRENADE = "cluster-grenade";
		public static final String POISON_CAPSULE = "poison-capsule";
		public static final String SLOWDOWN_CAPSULE = "slowdown-capsule";
		public static final String DEFENDER_CAPSULE = "defender-capsule";
		public static final String DISTRACTOR_CAPSULE = "distractor-capsule";
		public static final String DESTROYER_CAPSULE = "destroyer-capsule";
		public static final String DISCHARGE_DEFENSE_REMOTE = "discharge-defense-remote";
		public static final String ARTILLERY_TARGETING_REMOTE = "artillery-targeting-remote";
		
		// Rockets and flamethrower ammo
		public static final String ROCKET = "rocket";
		public static final String EXPLOSIVE_ROCKET = "explosive-rocket";
		public static final String ATOMIC_BOMB = "atomic-bomb";
		public static final String FLAMETHROWER_AMMO = "flamethrower-ammo";
		
		// Items for modular armor
		public static final String SOLAR_PANEL_EQUIPMENT = "solar-panel-equipment";
		public static final String RUSION_REACTOR_EQUIPMENT = "fusion-reactor-equipment";
		public static final String ENERGY_SHIELD_EQUIPMENT = "energy-shield-equipment";
		public static final String ENERGY_SHIELD_MK2_EQUIPMENT = "energy-shield-mk2-equipment";
		public static final String BATTERY_EQUIPMENT = "battery-equipment";
		public static final String BATTERY_MK2_EQUIPMENT = "battery-mk2-equipment";
		public static final String PERSONAL_LASER_DEFENSE_EQUIPMENT = "personal-laser-defense-equipment";
		public static final String DISCHARGE_DEFENSE_EQUIPMENT = "discharge-defense-equipment";
		public static final String EXOSKELETON_EQUIPMENT = "exoskeleton-equipment";
		public static final String PERSONAL_ROBOT_EQUIPMENT = "personal-roboport-equipment";
		public static final String PERSON_ROBOPORT_MK2_EQUIPMENT = "personal-roboport-mk2-equipment";
		public static final String NIGHT_VISION_EQUIPMENT = "night-vision-equipment";
		
		// Armor
		public static final String LIGHT_ARMOR = "light-armor";
		public static final String HEAVY_ARMOR = "heavy-armor";
		public static final String MODULAR_ARMOR = "modular-armor";
		public static final String POWER_ARMOR = "power-armor";
		public static final String POWER_ARMOR_MK2 = "power-armor-mk2";
		
		// Turrets and walls
		public static final String STONE_WALL = "stone-wall";
		public static final String GATE = "gate";
		public static final String GUN_TURRET = "gun-turret";
		public static final String LASER_TURRET = "laser-turret";
		public static final String FLAMETHROWER_TURRET = "flamethrower-turret";
		public static final String ARTILLERY_TURRET = "artillery-turret";
		public static final String RADAR = "radar";
		public static final String ROCKET_SILO = "rocket-silo";
		
	}
	
	/**
	 * Contains all virtual signals in 0.16 Factorio version.
	 */
	public static class Virtual {
		
		// Numbers
		public static final String SIGNAL_0 = "signal-0";
		public static final String SIGNAL_1 = "signal-1";
		public static final String SIGNAL_2 = "signal-2";
		public static final String SIGNAL_3 = "signal-3";
		public static final String SIGNAL_4 = "signal-4";
		public static final String SIGNAL_5 = "signal-5";
		public static final String SIGNAL_6 = "signal-6";
		public static final String SIGNAL_7 = "signal-7";
		public static final String SIGNAL_8 = "signal-8";
		public static final String SIGNAL_9 = "signal-9";
		
		// Letters
		public static final String SIGNAL_A = "signal-A";
		public static final String SIGNAL_B = "signal-B";
		public static final String SIGNAL_C = "signal-C";
		public static final String SIGNAL_D = "signal-D";
		public static final String SIGNAL_E = "signal-E";
		public static final String SIGNAL_F = "signal-F";
		public static final String SIGNAL_G = "signal-G";
		public static final String SIGNAL_H = "signal-H";
		public static final String SIGNAL_I = "signal-I";
		public static final String SIGNAL_J = "signal-J";
		public static final String SIGNAL_K = "signal-K";
		public static final String SIGNAL_L = "signal-L";
		public static final String SIGNAL_M = "signal-M";
		public static final String SIGNAL_N = "signal-N";
		public static final String SIGNAL_O = "signal-O";
		public static final String SIGNAL_P = "signal-P";
		public static final String SIGNAL_Q = "signal-Q";
		public static final String SIGNAL_R = "signal-R";
		public static final String SIGNAL_S = "signal-S";
		public static final String SIGNAL_T = "signal-T";
		public static final String SIGNAL_U = "signal-U";
		public static final String SIGNAL_V = "signal-V";
		public static final String SIGNAL_W = "signal-W";
		public static final String SIGNAL_X = "signal-X";
		public static final String SIGNAL_Y = "signal-Y";
		public static final String SIGNAL_Z = "signal-Z";
		
		// Colors
		public static final String SIGNAL_RED = "signal-red";
		public static final String SIGNAL_GREEN = "signal-green";
		public static final String SIGNAL_BLUE = "signal-blue";
		public static final String SIGNAL_YELLOW = "signal-yellow";
		public static final String SIGNAL_PINK = "signal-pink";
		public static final String SIGNAL_CYAN = "signal-cyan";
		public static final String SIGNAL_WHITE = "signal-white";
		public static final String SIGNAL_GREY = "signal-grey";
		public static final String SIGNAL_BLACK = "signal-black";
		
		// General
		public static final String SIGNAL_ANYTHING = "signal-anything";
		public static final String SIGNAL_EVERYTHING = "signal-everything";
		public static final String SIGNAL_EACH = "signal-each";
		
	}
	
	/**
	 * Contains all fluids in 0.16 Factorio version.
	 */
	public static class Fluid {
		
		public static final String WATER = "water";
		public static final String CRUDE_OIL = "crude-oil";
		public static final String STEAM = "fluid";
		public static final String HEAVY_OIL = "heavy-oil";
		public static final String LIGHT_OIL = "light-oil";
		public static final String PETROLEUM = "petroleum-gas";
		public static final String SULFURIC_ACID = "sulfuric-acid";
		public static final String LUBRICANT = "lubricant";
		
	}
	
	static {
		
		types = new HashMap<>();
		
		Class<Signals.Items> itemsClass = Signals.Items.class;
		for(Field f:itemsClass.getFields()) {
			if(f.getType() == String.class) {
				try {
					types.put((String) f.get(String.class), TYPE_ITEM);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Class<Signals.Virtual> virtualsClass = Signals.Virtual.class;
		for(Field f:virtualsClass.getFields()) {
			if(f.getType() == String.class) {
				try {
					types.put((String) f.get(String.class), TYPE_VIRTUAL);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Class<Signals.Fluid> fluidClass = Signals.Fluid.class;
		for(Field f:fluidClass.getFields()) {
			if(f.getType() == String.class) {
				try {
					types.put((String) f.get(String.class), TYPE_FLUID);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
