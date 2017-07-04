package clutch.dungeonrealms;

import clutch.dungeonrealms.attributes.Attribute;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Clutch
 * @since 6/24/2017
 */
public class ItemAttributes {

    private Map<String, Attribute> attributes = new HashMap<>();

    public ItemAttributes() {
        // Armor Main
        registerAttribute("ARMOR", new Armor());
        registerAttribute("BLOCK", new Block());
        registerAttribute("DODGE", new Dodge());
        registerAttribute("DPS", new Dps());
        registerAttribute("ENERGY REGEN", new EnergyRegen());
        registerAttribute("GEM FIND", new GemFind());
        registerAttribute("ITEM FIND", new ItemFind());
        registerAttribute("REFLECTION", new Reflection());
        registerAttribute("THORNS", new Thorns());
        // Armor Health
        registerAttribute("HP", new HealthPoints());
        registerAttribute("HP REGEN", new HealthRegen());
        // Armor Resistance
        registerAttribute("FIRE RESISTANCE", new FireResistance());
        registerAttribute("ICE RESISTANCE", new IceResistance());
        registerAttribute("POISON RESISTANCE", new PoisonResistance());
        // Armor Stats
        registerAttribute("DEX", new Dexterity());
        registerAttribute("INT", new Intellect());
        registerAttribute("STR", new Strength());
        registerAttribute("VIT", new Vitality());
    }

    public void updateItemInfo(ItemStack stack) {
        for (Attribute attribute : this.attributes.values()) {
            attribute.updateInfo(stack);
        }
    }

    public int getCompareValue(String name) {
        return this.attributes.get(name).getCompareValue();
    }

    public Set<String> getAttributes() {
        return this.attributes.keySet();
    }

    private void registerAttribute(String name, Attribute attribute) {
        this.attributes.put(name, attribute);
    }
}
