package iguanaman.iguanatweaks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class IguanaConfig {

	// hardness
	public static boolean logHardnessChanges;
    public static double hardnessMultiplier;
    public static boolean hardnessBlockListIsWhitelist;
    public static List<Integer> hardnessBlockList = new ArrayList<Integer>();
	
	// stack sizes
	public static boolean logStackSizeChanges;
    public static int blockStackSizeDividerMin;
    public static int blockStackSizeDividerMax;
    public static int itemStackSizeDivider;
	
	// sleeping and respawning
    public static boolean disableSleeping;
    public static boolean destroyBedOnRespawn;
    public static boolean disableSettingSpawn;
    public static int spawnLocationRandomisationMin;
    public static int spawnLocationRandomisationMax;
    public static int respawnLocationRandomisationMin;
    public static int respawnLocationRandomisationMax;
	public static int respawnHealth;
	public static boolean respawnHealthDifficultyScaling;
	
	// restricted drops
    public static List<String> restrictedDrops = new ArrayList<String>();
    
    // encumbrance and slowdown
    public static boolean addEncumbranceDebugText;
    public static boolean addEncumbranceHudText;
    public static boolean detailedEncumbranceHudText;
    public static int maxCarryWeight;
	public static double armorWeight;
	public static int damageSlowdownPotionId;
	public static int damageSlowdownDuration;
	public static boolean damageSlowdownDifficultyScaling;
	public static int terrainSlowdownPercentage;
	public static int terrainSlowdownOnDirt;
	public static int terrainSlowdownOnIce;
	public static int terrainSlowdownOnPlant;
	public static int terrainSlowdownOnSand;
	public static int terrainSlowdownOnSnow;
	public static int terrainSlowdownInSnow;
	public static int terrainSlowdownInPlant;
	public static int rockWeight;
	
	// item entity lifespans
	public static int itemLifespan;
	public static int itemLifespanMobDeath;
	public static int itemLifespanPlayerDeath;
	public static int itemLifespanTossed;
	
	// disable world gen
	public static boolean disableDirtGen;
	public static boolean disableGravelGen;
	public static boolean disableCoalGen;
	public static boolean disableIronGen;
	public static boolean disableGoldGen;
	public static boolean disableRedstoneGen;
	public static boolean disableLapisGen;
	public static boolean disableDiamondGen;
	
	// other
	public static boolean removeVanillaLeveling;
	public static boolean increasedStepHeight;
    public static boolean lessObviousSilverfish;
    public static boolean alterPoison;
    public static int torchesPerCoal;
	public static int miningExhaustionPercentage;
	public static int tickRateEntityUpdate;
    
	public static void Init(File file)
	{
        Configuration config = new Configuration(file);
        config.load();
        
        // hardness
		ConfigCategory hardnessCategory = config.getCategory("hardness");
		hardnessCategory.setComment("Change the hardness of blocks, using either a blacklist or whitelist");
        
        Property logHardnessChangesProperty = config.get("hardness", "logHardnessChanges", false);
        logHardnessChangesProperty.comment = "Writes a line to the console log when the stack size of an item/block gets changed";
        logHardnessChanges = logHardnessChangesProperty.getBoolean(false);
        
        Property hardnessMultiplierProperty = config.get("hardness", "hardnessMultiplier", 2d);
        hardnessMultiplierProperty.comment = "Multiplier applied to the hardness of blocks (set to 1 to disable feature)";
        hardnessMultiplier = Math.max(hardnessMultiplierProperty.getDouble(2d), 0d);
    	hardnessMultiplierProperty.set(hardnessMultiplier);
        
        Property hardnessBlockListIsWhitelistProperty = config.get("hardness", "hardnessBlockListIsWhitelist", false);
        hardnessBlockListIsWhitelistProperty.comment = "Whether hardness multiplier only affects blocks on the list (true) or if all blocks are affect except those on the list (false)";
        hardnessBlockListIsWhitelist = hardnessBlockListIsWhitelistProperty.getBoolean(false);
		
        Property hardnessBlockListProperty = config.get("hardness", "hardnessBlockList", new int[] {});
        hardnessBlockListProperty.comment = "Block ids (each on seperate line) for the hardness whitelist/blacklist";
        for (int i : hardnessBlockListProperty.getIntList()) hardnessBlockList.add(i);
        
        
        // stacksizes
		ConfigCategory stacksizesCategory = config.getCategory("stacksizes");
		stacksizesCategory.setComment("Change the stack sizes of blocks and items, based on material weight");
        
        Property itemStackSizeDividerProperty = config.get("stacksizes", "itemStackSizeDivider", 2);
        itemStackSizeDividerProperty.comment = "Max stack size divider";
        itemStackSizeDivider = Math.max(itemStackSizeDividerProperty.getInt(2), 1);
    	itemStackSizeDividerProperty.set(itemStackSizeDivider);
        
        Property blockStackSizeDividerMaxProperty = config.get("stacksizes", "blockStackSizeDividerMax", 4);
        blockStackSizeDividerMaxProperty.comment = "Max stack size divider";
        blockStackSizeDividerMax = Math.max(blockStackSizeDividerMaxProperty.getInt(4), 1);
    	blockStackSizeDividerMaxProperty.set(blockStackSizeDividerMax);
        
        Property blockStackSizeDividerMinProperty = config.get("stacksizes", "blockStackSizeDividerMin", 2);
        blockStackSizeDividerMinProperty.comment = "Min stack size divider";
        blockStackSizeDividerMin = Math.min(Math.max(blockStackSizeDividerMinProperty.getInt(2), 1), blockStackSizeDividerMax);
    	blockStackSizeDividerMinProperty.set(blockStackSizeDividerMin);
        
        Property logStackSizeChangesProperty = config.get("stacksizes", "logStackSizeChanges", false);
        logStackSizeChangesProperty.comment = "Writes a line to the console log when the stack size of an item/block gets changed";
        logStackSizeChanges = logStackSizeChangesProperty.getBoolean(false);
        
        
        // sleep and respawn
		ConfigCategory respawnCategory = config.getCategory("respawn");
		respawnCategory.setComment("Various settings to change sleeping and respawning mechanics");
        
        Property respawnHealthDifficultyScalingProperty = config.get("respawn", "respawnHealthDifficultyScaling", true);
        respawnHealthDifficultyScalingProperty.comment = "Is the amount of health you respawn with dependant on difficulty?";
        respawnHealthDifficultyScaling = respawnHealthDifficultyScalingProperty.getBoolean(true);
        
        Property respawnHealthProperty = config.get("respawn", "respawnHealth", 10);
        respawnHealthProperty.comment = "Amount of health you respawn with (with 'respawnHealthDifficultyScaling' this will be modified by difficulty)";
        respawnHealth = Math.min(Math.max(respawnHealthProperty.getInt(10), 1), 20);
        respawnHealthProperty.set(respawnHealth);
        
        Property disableSleepingProperty = config.get("respawn", "disableSleeping", true);
        disableSleepingProperty.comment = "Disable sleeping, spawn can still be set with a bed";
        disableSleeping = disableSleepingProperty.getBoolean(true);
        
        Property destroyBedOnRespawnProperty = config.get("respawn", "destroyBedOnRespawn", false);
        destroyBedOnRespawnProperty.comment = "The players bed will be destroyed upon respawning";
        destroyBedOnRespawn = destroyBedOnRespawnProperty.getBoolean(false);
        
        Property disableSettingSpawnProperty = config.get("respawn", "disableSettingSpawn", false);
        disableSettingSpawnProperty.comment = "If active using a bed will not set your spawn point (requires disableSleeping to be true)";
        disableSettingSpawn = disableSettingSpawnProperty.getBoolean(false);
        
        Property respawnLocationRandomisationMinProperty = config.get("respawn", "respawnLocationRandomisationMin", 0);
        respawnLocationRandomisationMinProperty.comment = "Exactly where you respawn (after death) is randomised around the spawn point (either to a bed or original spawn point), at least a minimum of this many blocks away (set to 0 to disable)";
        respawnLocationRandomisationMin = Math.max(respawnLocationRandomisationMinProperty.getInt(0), 0);
        respawnLocationRandomisationMinProperty.set(respawnLocationRandomisationMin);
        
        Property respawnLocationRandomisationMaxProperty = config.get("respawn", "respawnLocationRandomisationMax", 0);
        respawnLocationRandomisationMaxProperty.comment = "Exactly where you respawn (after death) is randomised around the spawn point (either to a bed or original spawn point), upto a maximum of this many blocks away (set to 0 to disable)";
        respawnLocationRandomisationMax = Math.max(respawnLocationRandomisationMaxProperty.getInt(0), respawnLocationRandomisationMin);
        respawnLocationRandomisationMaxProperty.set(respawnLocationRandomisationMax);
        
        Property spawnLocationRandomisationMinProperty = config.get("respawn", "spawnLocationRandomisationMin", 0);
        spawnLocationRandomisationMinProperty.comment = "Exactly where you spawn (upon login) is randomised around the spawn point, at least a minimum of this many blocks away (set to 0 to disable)";
        spawnLocationRandomisationMin = Math.max(spawnLocationRandomisationMinProperty.getInt(0), 0);
        spawnLocationRandomisationMinProperty.set(spawnLocationRandomisationMin);
        
        Property spawnLocationRandomisationMaxProperty = config.get("respawn", "spawnLocationRandomisationMax", 0);
        spawnLocationRandomisationMaxProperty.comment = "Exactly where you spawn (upon login) is randomised around the spawn point, upto a maximum of this many blocks away (set to 0 to disable)";
        spawnLocationRandomisationMax = Math.max(spawnLocationRandomisationMaxProperty.getInt(0), spawnLocationRandomisationMin);
        spawnLocationRandomisationMaxProperty.set(spawnLocationRandomisationMax);
        
        
        // movement restriction
		ConfigCategory movementrestrictionCategory = config.getCategory("movementrestriction");
		movementrestrictionCategory.setComment("Various settings related to restricting movement ");
        
        Property damageSlowdownDifficultyScalingProperty = config.get("movementrestriction", "damageSlowdownDifficultyScaling", true);
        damageSlowdownDifficultyScalingProperty.comment = "Is the duration of the slowdown dependant on difficulty?";
        damageSlowdownDifficultyScaling = damageSlowdownDifficultyScalingProperty.getBoolean(true);
        
        Property addEncumbranceDebugTextProperty = config.get("movementrestriction", "addEncumbranceDebugText", true);
        addEncumbranceDebugTextProperty.comment = "Shows weight text in the debug (F3) details";
        addEncumbranceDebugText = addEncumbranceDebugTextProperty.getBoolean(true);
        
        Property addEncumbranceHudTextProperty = config.get("movementrestriction", "addEncumbranceHudText", true);
        addEncumbranceHudTextProperty.comment = "Shows weight text on the HUD when carrying too much";
        addEncumbranceHudText = addEncumbranceHudTextProperty.getBoolean(true);
        
        Property detailedEncumbranceHudTextProperty = config.get("movementrestriction", "detailedEncumbranceHudText", false);
        detailedEncumbranceHudTextProperty.comment = "Weight text on the HUD will be more detailed, showing numbers";
        detailedEncumbranceHudText = detailedEncumbranceHudTextProperty.getBoolean(false);
        
        Property armorWeightProperty = config.get("movementrestriction", "armorWeight", 0.5d);
        armorWeightProperty.comment = "Percentage of slowdown for each point (half-shield) of armor (0 to disable)";
        armorWeight = Math.max(armorWeightProperty.getDouble(0.5d), 0d);
    	armorWeightProperty.set(armorWeight);
    	
        Property maxCarryWeightProperty = config.get("movementrestriction", "maxCarryWeight", 512);
        maxCarryWeightProperty.comment = "Maximum carry weight (default 512) (Set 0 to disable)";
        maxCarryWeight = Math.max(maxCarryWeightProperty.getInt(512), 0);
    	maxCarryWeightProperty.set(maxCarryWeight);
        
        Property damageSlowdownDurationProperty = config.get("movementrestriction", "damageSlowdownDuration", 5);
        damageSlowdownDurationProperty.comment = "Number of ticks each heart of damage slows you down for (default 5) (Set 0 to disable)";
        damageSlowdownDuration = Math.max(damageSlowdownDurationProperty.getInt(5), 0);
    	damageSlowdownDurationProperty.set(damageSlowdownDuration);
        
        Property terrainSlowdownPercentageProperty = config.get("movementrestriction", "terrainSlowdownPercentage", 100);
        terrainSlowdownPercentageProperty.comment = "Global modifier on the amount that terrain affects movement speed (Set 0 to disable)";
        terrainSlowdownPercentage = Math.max(terrainSlowdownPercentageProperty.getInt(100), 0);
        terrainSlowdownPercentageProperty.set(terrainSlowdownPercentage);
        
        Property terrainSlowdownOnDirtProperty = config.get("movementrestriction", "terrainSlowdownOnDirt", 5);
        terrainSlowdownOnDirtProperty.comment = "Percentage of slowdown when walking on dirt or grass (Set 0 to disable)";
        terrainSlowdownOnDirt = Math.max(terrainSlowdownOnDirtProperty.getInt(5), 0);
        terrainSlowdownOnDirtProperty.set(terrainSlowdownOnDirt);
        
        Property terrainSlowdownOnIceProperty = config.get("movementrestriction", "terrainSlowdownOnIce", 20);
        terrainSlowdownOnIceProperty.comment = "Percentage of slowdown when walking on ice (Set 0 to disable)";
        terrainSlowdownOnIce = Math.max(terrainSlowdownOnIceProperty.getInt(20), 0);
        terrainSlowdownOnIceProperty.set(terrainSlowdownOnIce);
        
        Property terrainSlowdownOnPlantProperty = config.get("movementrestriction", "terrainSlowdownOnPlant", 20);
        terrainSlowdownOnPlantProperty.comment = "Percentage of slowdown when walking on leaves or plants (Set 0 to disable)";
        terrainSlowdownOnPlant = Math.max(terrainSlowdownOnPlantProperty.getInt(20), 0);
        terrainSlowdownOnPlantProperty.set(terrainSlowdownOnPlant);
        
        Property terrainSlowdownOnSandProperty = config.get("movementrestriction", "terrainSlowdownOnSand", 20);
        terrainSlowdownOnSandProperty.comment = "Percentage of slowdown when walking on sand (Set 0 to disable)";
        terrainSlowdownOnSand = Math.max(terrainSlowdownOnSandProperty.getInt(20), 0);
        terrainSlowdownOnSandProperty.set(terrainSlowdownOnSand);
        
        Property terrainSlowdownOnSnowProperty = config.get("movementrestriction", "terrainSlowdownOnSnow", 20);
        terrainSlowdownOnSnowProperty.comment = "Percentage of slowdown when walking on snow (Set 0 to disable)";
        terrainSlowdownOnSnow = Math.max(terrainSlowdownOnSnowProperty.getInt(20), 0);
        terrainSlowdownOnSnowProperty.set(terrainSlowdownOnSnow);
        
        Property terrainSlowdownInSnowProperty = config.get("movementrestriction", "terrainSlowdownInSnow", 20);
        terrainSlowdownInSnowProperty.comment = "Percentage of slowdown when walking through snow (Set 0 to disable)";
        terrainSlowdownInSnow = Math.max(terrainSlowdownInSnowProperty.getInt(20), 0);
        terrainSlowdownInSnowProperty.set(terrainSlowdownInSnow);
        
        Property terrainSlowdownInPlantProperty = config.get("movementrestriction", "terrainSlowdownInPlant", 5);
        terrainSlowdownInPlantProperty.comment = "Percentage of slowdown when walking through plants (Set 0 to disable)";
        terrainSlowdownInPlant = Math.max(terrainSlowdownInPlantProperty.getInt(5), 0);
        terrainSlowdownInPlantProperty.set(terrainSlowdownInPlant);
        
        Property rockWeightProperty = config.get("movementrestriction", "rockWeight", 1);
        rockWeightProperty.comment = "Weight of one rock block, used as a base to calculate weight of other blocks";
        rockWeight = Math.max(rockWeightProperty.getInt(1), 1);
        rockWeightProperty.set(rockWeight);
        

        // item lifespans
		ConfigCategory itemlifespansCategory = config.getCategory("itemlifespans");
		itemlifespansCategory.setComment("Set lifespan of items spawned in various situations (20 ticks = 1 second)");

        Property itemLifespanProperty = config.get("itemlifespans", "itemLifespan", 6000);
        itemLifespanProperty.comment = "Lifespan (in ticks) of items on the ground (default 6000)";
        itemLifespan = Math.max(itemLifespanProperty.getInt(6000), 0);
        itemLifespanProperty.set(itemLifespan);

        Property itemLifespanMobDeathProperty = config.get("itemlifespans", "itemLifespanMobDeath", 6000);
        itemLifespanMobDeathProperty.comment = "Lifespan (in ticks) of items dropped when a mob dies (default 6000)";
        itemLifespanMobDeath = Math.max(itemLifespanMobDeathProperty.getInt(6000), 0);
        itemLifespanMobDeathProperty.set(itemLifespanMobDeath);

        Property itemLifespanPlayerDeathProperty = config.get("itemlifespans", "itemLifespanPlayerDeath", 6000);
        itemLifespanPlayerDeathProperty.comment = "Lifespan (in ticks) of items dropped when a player dies (default 6000)";
        itemLifespanPlayerDeath = Math.max(itemLifespanPlayerDeathProperty.getInt(6000), 0);
        itemLifespanPlayerDeathProperty.set(itemLifespanPlayerDeath);

        Property itemLifespanTossedProperty = config.get("itemlifespans", "itemLifespanTossed", 6000);
        itemLifespanTossedProperty.comment = "Lifespan (in ticks) of items tossed on the grown (default 6000)";
        itemLifespanTossed = Math.max(itemLifespanTossedProperty.getInt(6000), 0);
        itemLifespanTossedProperty.set(itemLifespanTossed);
        
        
        // disable world gen
		ConfigCategory vanillaoregenCategory = config.getCategory("vanillaoregen");
		vanillaoregenCategory.setComment("Allows you disable parts of the vanilla ore generation");

        Property disableDirtGenProperty = config.get("vanillaoregen", "disableDirtGen", false);
        disableDirtGenProperty.comment = "Disables vanilla generation of underground dirt clusters";
        disableDirtGen = disableDirtGenProperty.getBoolean(false);

        Property disableGravelGenProperty = config.get("vanillaoregen", "disableGravelGen", false);
        disableGravelGenProperty.comment = "Disables vanilla generation of underground gravel clusters";
        disableGravelGen = disableGravelGenProperty.getBoolean(false);

        Property disableCoalGenProperty = config.get("vanillaoregen", "disableCoalGen", false);
        disableCoalGenProperty.comment = "Disables vanilla generation of coal ore";
        disableCoalGen = disableCoalGenProperty.getBoolean(false);

        Property disableIronGenProperty = config.get("vanillaoregen", "disableIronGen", false);
        disableIronGenProperty.comment = "Disables vanilla generation of Iron ore";
        disableIronGen = disableIronGenProperty.getBoolean(false);

        Property disableGoldGenProperty = config.get("vanillaoregen", "disableGoldGen", false);
        disableGoldGenProperty.comment = "Disables vanilla generation of Gold ore";
        disableGoldGen = disableGoldGenProperty.getBoolean(false);

        Property disableRedstoneGenProperty = config.get("vanillaoregen", "disableRedstoneGen", false);
        disableRedstoneGenProperty.comment = "Disables vanilla generation of Redstone ore";
        disableRedstoneGen = disableRedstoneGenProperty.getBoolean(false);

        Property disableLapisGenProperty = config.get("vanillaoregen", "disableLapisGen", false);
        disableLapisGenProperty.comment = "Disables vanilla generation of Lapis ore";
        disableLapisGen = disableLapisGenProperty.getBoolean(false);

        Property disableDiamondGenProperty = config.get("vanillaoregen", "disableDiamondGen", false);
        disableDiamondGenProperty.comment = "Disables vanilla generation of Diamond ore";
        disableDiamondGen = disableDiamondGenProperty.getBoolean(false);

        
        // other
		ConfigCategory otherCategory = config.getCategory("other");
		otherCategory.setComment("Collection of misfits");
        
        Property removeVanillaLevelingProperty = config.get("other", "removeVanillaLeveling", false);
        removeVanillaLevelingProperty.comment = "Remove XP bar and XP orbs?";
        removeVanillaLeveling = removeVanillaLevelingProperty.getBoolean(false);
        
        Property increasedStepHeightProperty = config.get("other", "increasedStepHeight", false);
        increasedStepHeightProperty.comment = "Can players step up full blocks without jumping?";
        increasedStepHeight = increasedStepHeightProperty.getBoolean(false);
        
        Property lessObviousSilverfishProperty = config.get("other", "lessObviousSilverfish", true);
        lessObviousSilverfishProperty.comment = "Silverfish blocks are less easy to spot";
        lessObviousSilverfish = lessObviousSilverfishProperty.getBoolean(true);
        
        Property alterPoisonProperty = config.get("other", "alterPoison", true);
        alterPoisonProperty.comment = "Poison causes damage less often but is now deadly (possibly causes crashes, use at own risk)";
        alterPoison = alterPoisonProperty.getBoolean(true);
        
        Property torchesPerCoalProperty = config.get("other", "torchesPerCoal", 1);
        torchesPerCoalProperty.comment = "Torches given from torch recipe";
        torchesPerCoal = Math.max(torchesPerCoalProperty.getInt(1), 1);
    	torchesPerCoalProperty.set(torchesPerCoal); 
        
        Property miningExhaustionPercentageProperty = config.get("other", "miningExhaustionPercentage", 100);
        miningExhaustionPercentageProperty.comment = "Modifier on the exhaustion given when breaking blocks (0 disables feature)";
        miningExhaustionPercentage = Math.max(miningExhaustionPercentageProperty.getInt(100), 0);
        miningExhaustionPercentageProperty.set(miningExhaustionPercentage);
        
        Property damageSlowdownPotionIdProperty = config.get("other", "damageSlowdownPotionId", 29);
        damageSlowdownPotionIdProperty.comment = "Potion ID for the damage slowdown effect (maximum 31)";
        damageSlowdownPotionId = Math.max(damageSlowdownPotionIdProperty.getInt(29), 0);
        damageSlowdownPotionIdProperty.set(damageSlowdownPotionId);
        
        Property tickRateEntityUpdateProperty = config.get("other", "tickRateEntityUpdate", 5);
        tickRateEntityUpdateProperty.comment = "How often the speed of entities are calculated (in ticks).  Higher values reduce client-side CPU load but increase the chance of odd behavior";
        tickRateEntityUpdate = Math.max(tickRateEntityUpdateProperty.getInt(5), 1);
        tickRateEntityUpdateProperty.set(tickRateEntityUpdate);

		
    	
		// restrictions
		ConfigCategory droprestrictionsCategory = config.getCategory("droprestrictions");

        Property restrictedDropsProperty = config.get("droprestrictions", "restrictedDrops", new String[] {});
        restrictedDropsProperty.comment = "List of items/blocks to restrict from mob drops (separated by new line, format id:meta)";
        for (String i : restrictedDropsProperty.getStringList()) restrictedDrops.add(i);
        

        config.save();
	}
}
