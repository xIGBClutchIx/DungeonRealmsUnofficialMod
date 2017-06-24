package clutch.dungeonrealms.utils;

import clutch.dungeonrealms.ItemAttributes;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;

public class ArmorUtils {

    public static ItemAttributes getModifiers(ItemStack stack) {
        ItemAttributes itemAttributes = new ItemAttributes();
        itemAttributes.updateItemInfo(stack);
        return itemAttributes;
    }

    public static int getValueFromAttribute(Attribute attribute) {
        if (attribute == null) return 0;
        switch (attribute.getDisplayName()) {
            case "HP":
                return ((HealthPoints) attribute).getHealthPoints();
            case "HP REGEN":
                return ((HealthRegen) attribute).getHealthRegen();
            case "FIRE RESISTANCE":
                return ((FireResistance) attribute).getFireResistance();
            case "ICE RESISTANCE":
                return ((IceResistance) attribute).getIceResistance();
            case "POISON RESISTANCE":
                return ((PoisonResistance) attribute).getPoisonResistance();
            case "DEX":
                return ((Dexterity) attribute).getDexterity();
            case "INT":
                return ((Intellect) attribute).getIntellect();
            case "STR":
                return ((Strength) attribute).getStrength();
            case "VIT":
                return ((Vitality) attribute).getVitality();
            case "ARMOR":
                return ((Armor) attribute).getArmorMax();
            case "BLOCK":
                return ((Block) attribute).getBlock();
            case "DODGE":
                return ((Dodge) attribute).getDodge();
            case "DPS":
                return ((Dps) attribute).getDpsMax();
            case "ENERGY REGEN":
                return ((EnergyRegen) attribute).getEnergyRegen();
            case "ITEM FIND":
                return ((ItemFind) attribute).getItemFind();
            case "REFLECTION":
                return ((Reflection) attribute).getReflection();
            case "THORNS":
                return ((Thorns) attribute).getThorns();
            default:
                return 0;
        }
    }

    public static Attribute getAttribute(String attributeString, ItemAttributes attribute) {
        switch (attributeString) {
            case "HP":
                return attribute.healthPoints;
            case "HP REGEN":
                return attribute.healthRegen;
            case "FIRE RESISTANCE":
                return attribute.fireResistance;
            case "ICE RESISTANCE":
                return attribute.iceResistance;
            case "POISON RESISTANCE":
                return attribute.poisonResistance;
            case "DEX":
                return attribute.dexterity;
            case "INT":
                return attribute.intellect;
            case "STR":
                return attribute.strength;
            case "VIT":
                return attribute.vitality;
            case "ARMOR":
                return attribute.armor;
            case "BLOCK":
                return attribute.block;
            case "DODGE":
                return attribute.dodge;
            case "DPS":
                return attribute.dps;
            case "ENERGY REGEN":
                return attribute.energyRegen;
            case "ITEM FIND":
                return attribute.itemFind;
            case "REFLECTION":
                return attribute.reflection;
            case "THORNS":
                return attribute.thorns;
            default:
                return null;
        }
    }

    public static List<Integer> getIntListFromList(ItemStack stack, String intName) {
        // If does not have nbt tags then return
        if (stack.getTagCompound() == null) return new ArrayList<>();
        // If does not have nbt tag attributes then return
        if (!stack.getTagCompound().hasKey("itemAttributes", 10)) return new ArrayList<>();
        NBTTagCompound itemAttributes = stack.getTagCompound().getCompoundTag("itemAttributes");
        // If attributes has not tags then return
        if (itemAttributes.hasNoTags()) System.out.println(intName + ": No tags!");
        // If attributes has not tags then return
        if (!itemAttributes.hasKey(intName)) return new ArrayList<>();
        // Return
        NBTTagList tagList = itemAttributes.getTagList(intName, 3);
        List<Integer> integerList = new ArrayList<>();
        for (int tagIndex = 0; tagIndex < tagList.tagCount(); ++tagIndex) {
            integerList.add(tagList.getIntAt(tagIndex));
        }
        return integerList;
    }

    public static int getInt(ItemStack stack, String intName) {
        // If does not have nbt tags then return
        if (stack.getTagCompound() == null) return 0;
        // If does not have nbt tag attributes then return
        if (!stack.getTagCompound().hasKey("itemAttributes", 10)) return 0;
        NBTTagCompound itemAttributes = stack.getTagCompound().getCompoundTag("itemAttributes");
        // If attributes has not tags then return
        if (itemAttributes.hasNoTags()) return 0;
        // If attributes has not tags then return
        if (!itemAttributes.hasKey(intName)) return 0;
        // Return
        return itemAttributes.getInteger(intName);
    }

    public static ArmorType getArmorType(ItemStack stack) {
        // Helmets
        if (stack.getItem() == Items.LEATHER_HELMET || stack.getItem() == Items.CHAINMAIL_HELMET ||
                stack.getItem() == Items.IRON_HELMET || stack.getItem() == Items.DIAMOND_HELMET ||
                stack.getItem() == Items.GOLDEN_HELMET) {
            return ArmorType.HELMET;
        }
        // Chestplate
        if (stack.getItem() == Items.LEATHER_CHESTPLATE || stack.getItem() == Items.CHAINMAIL_CHESTPLATE ||
                stack.getItem() == Items.IRON_CHESTPLATE || stack.getItem() == Items.DIAMOND_CHESTPLATE ||
                stack.getItem() == Items.GOLDEN_CHESTPLATE) {
            return ArmorType.CHESTPLATE;
        }
        // Leggings
        if (stack.getItem() == Items.LEATHER_LEGGINGS || stack.getItem() == Items.CHAINMAIL_LEGGINGS ||
                stack.getItem() == Items.IRON_LEGGINGS || stack.getItem() == Items.DIAMOND_LEGGINGS ||
                stack.getItem() == Items.GOLDEN_LEGGINGS ) {
            return ArmorType.LEGGINGS;
        }
        // Boots
        if (stack.getItem() == Items.LEATHER_BOOTS || stack.getItem() == Items.CHAINMAIL_BOOTS ||
                stack.getItem() == Items.IRON_BOOTS || stack.getItem() == Items.DIAMOND_BOOTS ||
                stack.getItem() == Items.GOLDEN_BOOTS ) {
            return ArmorType.BOOTS;
        }
        return ArmorType.NONE;
    }

    public enum ArmorType {
        NONE,
        BOOTS,
        LEGGINGS,
        CHESTPLATE,
        HELMET
    }
}
