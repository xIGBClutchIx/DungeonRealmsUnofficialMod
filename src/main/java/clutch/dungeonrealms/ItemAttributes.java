package clutch.dungeonrealms;

import clutch.dungeonrealms.attributes.armor.*;
import clutch.dungeonrealms.attributes.armor.health.HealthPoints;
import clutch.dungeonrealms.attributes.armor.health.HealthRegen;
import clutch.dungeonrealms.attributes.armor.resistance.FireResistance;
import clutch.dungeonrealms.attributes.armor.resistance.IceResistance;
import clutch.dungeonrealms.attributes.armor.resistance.PoisonResistance;
import clutch.dungeonrealms.attributes.armor.stats.Dexterity;
import clutch.dungeonrealms.attributes.armor.stats.Intellect;
import clutch.dungeonrealms.attributes.armor.stats.Strength;
import clutch.dungeonrealms.attributes.armor.stats.Vitality;
import net.minecraft.item.ItemStack;

/**
 * @author Clutch
 * @since 6/24/2017
 */
public class ItemAttributes {

    // Armor Main
    public Armor armor = new Armor();
    public Block block = new Block();
    public Dodge dodge = new Dodge();
    public Dps dps = new Dps();
    public EnergyRegen energyRegen = new EnergyRegen();
    public ItemFind itemFind = new ItemFind();
    public Reflection reflection = new Reflection();
    public Thorns thorns = new Thorns();

    // Armor Health
    public HealthPoints healthPoints = new HealthPoints();
    public HealthRegen healthRegen = new HealthRegen();

    // Armor Resistance
    public FireResistance fireResistance = new FireResistance();
    public IceResistance iceResistance = new IceResistance();
    public PoisonResistance poisonResistance = new PoisonResistance();

    // Armor Stats
    public Dexterity dexterity = new Dexterity();
    public Intellect intellect = new Intellect();
    public Strength strength = new Strength();
    public Vitality vitality = new Vitality();

    public void updateItemInfo(ItemStack stack) {
        // Armor Main
        armor.updateInfo(stack);
        block.updateInfo(stack);
        dodge.updateInfo(stack);
        dps.updateInfo(stack);
        energyRegen.updateInfo(stack);
        itemFind.updateInfo(stack);
        reflection.updateInfo(stack);
        thorns.updateInfo(stack);
        // Armor Health
        healthPoints.updateInfo(stack);
        healthRegen.updateInfo(stack);
        // Armor Resistance
        fireResistance.updateInfo(stack);
        iceResistance.updateInfo(stack);
        poisonResistance.updateInfo(stack);
        // Armor Stats
        dexterity.updateInfo(stack);
        intellect.updateInfo(stack);
        strength.updateInfo(stack);
        vitality.updateInfo(stack);
    }
}
