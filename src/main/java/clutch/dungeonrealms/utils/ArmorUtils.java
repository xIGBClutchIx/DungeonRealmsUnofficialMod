package clutch.dungeonrealms.utils;

import clutch.dungeonrealms.modifiers.Modifier;
import clutch.dungeonrealms.modifiers.armor.*;
import clutch.dungeonrealms.modifiers.armor.health.HealthPoints;
import clutch.dungeonrealms.modifiers.armor.health.HealthRegen;
import clutch.dungeonrealms.modifiers.armor.resistance.FireResistance;
import clutch.dungeonrealms.modifiers.armor.resistance.PoisonResistance;
import clutch.dungeonrealms.modifiers.armor.stats.Dexterity;
import clutch.dungeonrealms.modifiers.armor.stats.Intellect;
import clutch.dungeonrealms.modifiers.armor.stats.Strength;
import clutch.dungeonrealms.modifiers.armor.stats.Vitality;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;

public class ArmorUtils {

    public static List<Modifier> getModifiers(ItemStack stack) {
        List<Modifier> modifiers = new ArrayList<>();
        for (String modifierString : ArmorUtils.detectModifiers(stack)) {
            Modifier modifier = ArmorUtils.getModifier(modifierString, stack.getTagCompound());
            if (modifier != null) {
                modifiers.add(modifier);
            }
        }
        return modifiers;
    }

    public static Modifier getModifier(String modifierString, NBTTagCompound tagCompound) {
        switch (modifierString) {
            case "healthPoints":
                return new HealthPoints(tagCompound.getInteger(modifierString));
            case "healthRegen":
                return new HealthRegen(tagCompound.getInteger(modifierString));
            case "fireResistance":
                return new FireResistance(tagCompound.getInteger(modifierString));
            case "iceResistance":
                return new PoisonResistance(tagCompound.getInteger(modifierString));
            case "poisonResistance":
                return new PoisonResistance(tagCompound.getInteger(modifierString));
            case "dexterity":
                return new Dexterity(tagCompound.getInteger(modifierString));
            case "intellect":
                return new Intellect(tagCompound.getInteger(modifierString));
            case "strength":
                return new Strength(tagCompound.getInteger(modifierString));
            case "vitality":
                return new Vitality(tagCompound.getInteger(modifierString));
            case "armor":
                return new Armor(tagCompound.getInteger("armorMin"), tagCompound.getInteger("armorMax"));
            case "block":
                return new Block(tagCompound.getInteger(modifierString));
            case "dodge":
                return new Dodge(tagCompound.getInteger(modifierString));
            case "dps":
                return new Dps(tagCompound.getInteger("dpsMin"), tagCompound.getInteger("dpsMax"));
            case "energyRegen":
                return new EnergyRegen(tagCompound.getInteger(modifierString));
            case "itemFind":
                return new ItemFind(tagCompound.getInteger(modifierString));
            case "reflection":
                return new Reflection(tagCompound.getInteger(modifierString));
            case "thorns":
                return new Thorns(tagCompound.getInteger(modifierString));
            default:
                return null;
        }
    }

    public static List<String> detectModifiers(ItemStack stack) {
        // If does not have nbt tags then return
        if (!stack.hasTagCompound()) return new ArrayList<>();
        // If does not have nbt modifiers then return
        if (!stack.getTagCompound().hasKey("modifiers", 9)) return new ArrayList<>();
        NBTTagList modifiers = stack.getTagCompound().getTagList("modifiers", 8);
        // If modifiers has not tags then return
        if (modifiers.hasNoTags()) return new ArrayList<>();
        // Loop through modifiers
        List<String> modifiersList = new ArrayList<>();
        for (int tagIndex = 0; tagIndex < modifiers.tagCount(); ++tagIndex) {
            String modifierLine = modifiers.getStringTagAt(tagIndex);
            modifiersList.add(modifierLine);
        }
        // Return modifier list
        return modifiersList;
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
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        NONE
    }
}
