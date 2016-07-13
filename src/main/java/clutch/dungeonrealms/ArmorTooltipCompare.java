package clutch.dungeonrealms;

import clutch.dungeonrealms.modifiers.Modifier;
import clutch.dungeonrealms.modifiers.armor.*;
import clutch.dungeonrealms.modifiers.armor.health.HealthPoints;
import clutch.dungeonrealms.modifiers.armor.health.HealthRegen;
import clutch.dungeonrealms.modifiers.armor.resistance.FireResistance;
import clutch.dungeonrealms.modifiers.armor.resistance.IceResistance;
import clutch.dungeonrealms.modifiers.armor.resistance.PoisonResistance;
import clutch.dungeonrealms.modifiers.armor.stats.Dexterity;
import clutch.dungeonrealms.modifiers.armor.stats.Intellect;
import clutch.dungeonrealms.modifiers.armor.stats.Strength;
import clutch.dungeonrealms.modifiers.armor.stats.Vitality;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ArmorTooltipCompare {

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack tooltipStack = event.getItemStack();
        if (!tooltipStack.hasTagCompound()) return;
        ArmorUtils.ArmorType tooltipStackArmorType = ArmorUtils.getArmorType(tooltipStack);
        if (tooltipStackArmorType != ArmorUtils.ArmorType.NONE) {
            List<Modifier> tooltipModifiers = ArmorUtils.getModifiers(tooltipStack);
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            switch (tooltipStackArmorType) {
                case HELMET:
                    ItemStack equippedHelmet = player.inventory.armorInventory[3];
                    if (equippedHelmet != tooltipStack) {
                        addCompareTooltip(event.getToolTip(), tooltipModifiers, equippedHelmet);
                    }
                    break;
                case CHESTPLATE:
                    ItemStack equippedChestplate = player.inventory.armorInventory[2];
                    if (equippedChestplate != tooltipStack) {
                        addCompareTooltip(event.getToolTip(), tooltipModifiers, equippedChestplate);
                    }
                    break;
                case LEGGINGS:
                    ItemStack equippedLeggings = player.inventory.armorInventory[1];
                    if (equippedLeggings != tooltipStack) {
                        addCompareTooltip(event.getToolTip(), tooltipModifiers, equippedLeggings);
                    }
                    break;
                case BOOTS:
                    ItemStack equippedBoots = player.inventory.armorInventory[0];
                    if (equippedBoots != tooltipStack) {
                        addCompareTooltip(event.getToolTip(), tooltipModifiers, equippedBoots);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void addCompareTooltip(List<String> tooltips, List<Modifier> tooltipModifiers, ItemStack equippedStacks) {
        if (tooltipModifiers.isEmpty()) return;
        // equipped modifiers
        List<Modifier> equippedModifiers = new ArrayList<>();
        if (equippedStacks != null) {
            equippedModifiers = ArmorUtils.getModifiers(equippedStacks);
        }
        boolean addedBeginningText = false;
        // ARMOR
        String currentCompareString = "armor";
        Armor modTooltipAR = (Armor) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipAR == null) {
            modTooltipAR = new Armor(0, 0);
        }
        Armor modEquippedAR = (Armor) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedAR == null) {
            modEquippedAR = new Armor(0, 0);
        }
        if (modEquippedAR.getArmorMax() != modTooltipAR.getArmorMax()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedAR.getArmorMax() > modTooltipAR.getArmorMax()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedAR.getArmorMax() - modTooltipAR.getArmorMax()) + " " + modTooltipAR.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipAR.getArmorMax() - modEquippedAR.getArmorMax()) + " " + modTooltipAR.getDisplayName());
            }
        }
        // HEALTH POINTS
        currentCompareString = "healthPoints";
        HealthPoints modTooltipHP = (HealthPoints) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipHP == null) {
            modTooltipHP = new HealthPoints(0);
        }
        HealthPoints modEquippedHP = (HealthPoints) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedHP == null) {
            modEquippedHP = new HealthPoints(0);
        }
        if (modEquippedHP.getHealthPoints() != modTooltipHP.getHealthPoints()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedHP.getHealthPoints() > modTooltipHP.getHealthPoints()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedHP.getHealthPoints() - modTooltipHP.getHealthPoints()) + " " + modTooltipHP.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipHP.getHealthPoints() - modEquippedHP.getHealthPoints()) + " " + modTooltipHP.getDisplayName());
            }
        }
        // HEALTH REGEN
        currentCompareString = "healthRegen";
        HealthRegen modTooltipHR = (HealthRegen) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipHR == null) {
            modTooltipHR = new HealthRegen(0);
        }
        HealthRegen modEquippedHR = (HealthRegen) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedHR == null) {
            modEquippedHR = new HealthRegen(0);
        }
        if (modEquippedHR.getHealthRegen() != modTooltipHR.getHealthRegen()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedHR.getHealthRegen() > modTooltipHR.getHealthRegen()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedHR.getHealthRegen() - modTooltipHR.getHealthRegen()) + " " + modTooltipHR.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipHR.getHealthRegen() - modEquippedHR.getHealthRegen()) + " " + modTooltipHR.getDisplayName());
            }
        }
        // ENERGY REGEN
        currentCompareString = "energyRegen";
        EnergyRegen modTooltipER = (EnergyRegen) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipER == null) {
            modTooltipER = new EnergyRegen(0);
        }
        EnergyRegen modEquippedER = (EnergyRegen) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedER == null) {
            modEquippedER = new EnergyRegen(0);
        }
        if (modEquippedER.getEnergyRegen() != modTooltipER.getEnergyRegen()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedER.getEnergyRegen() > modTooltipER.getEnergyRegen()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedER.getEnergyRegen() - modTooltipER.getEnergyRegen()) + " " + modTooltipER.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipER.getEnergyRegen() - modEquippedER.getEnergyRegen()) + " " + modTooltipER.getDisplayName());
            }
        }
        // DPS
        currentCompareString = "dps";
        Dps modTooltipDPS = (Dps) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipDPS == null) {
            modTooltipDPS = new Dps(0, 0);
        }
        Dps modEquippedDPS = (Dps) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedDPS == null) {
            modEquippedDPS = new Dps(0, 0);
        }
        if (modEquippedDPS.getDpsMax() != modTooltipDPS.getDpsMax()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedDPS.getDpsMax() > modTooltipDPS.getDpsMax()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedDPS.getDpsMax() - modTooltipDPS.getDpsMax()) + " " + modTooltipDPS.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipDPS.getDpsMax() - modEquippedDPS.getDpsMax()) + " " + modTooltipDPS.getDisplayName());
            }
        }
        // STRENGTH
        currentCompareString = "strength";
        Strength modTooltipSTR = (Strength) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipSTR == null) {
            modTooltipSTR = new Strength(0);
        }
        Strength modEquippedSTR = (Strength) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedSTR == null) {
            modEquippedSTR = new Strength(0);
        }
        if (modEquippedSTR.getStrength() != modTooltipSTR.getStrength()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedSTR.getStrength() > modTooltipSTR.getStrength()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedSTR.getStrength() - modTooltipSTR.getStrength()) + " " + modTooltipSTR.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipSTR.getStrength() - modEquippedSTR.getStrength()) + " " + modTooltipSTR.getDisplayName());
            }
        }
        // DEXTERITY
        currentCompareString = "dexterity";
        Dexterity modTooltipDEX = (Dexterity) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipDEX == null) {
            modTooltipDEX = new Dexterity(0);
        }
        Dexterity modEquippedDEX = (Dexterity) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedDEX == null) {
            modEquippedDEX = new Dexterity(0);
        }
        if (modEquippedDEX.getDexterity() != modTooltipDEX.getDexterity()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedDEX.getDexterity() > modTooltipDEX.getDexterity()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedDEX.getDexterity() - modTooltipDEX.getDexterity()) + " " + modTooltipDEX.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipDEX.getDexterity() - modEquippedDEX.getDexterity()) + " " + modTooltipDEX.getDisplayName());
            }
        }
        // VITALITY
        currentCompareString = "vitality";
        Vitality modTooltipVIT = (Vitality) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipVIT == null) {
            modTooltipVIT = new Vitality(0);
        }
        Vitality modEquippedVIT = (Vitality) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedVIT == null) {
            modEquippedVIT = new Vitality(0);
        }
        if (modEquippedVIT.getVitality() != modTooltipVIT.getVitality()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedVIT.getVitality() > modTooltipVIT.getVitality()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedVIT.getVitality() - modTooltipVIT.getVitality()) + " " + modTooltipVIT.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipVIT.getVitality() - modEquippedVIT.getVitality()) + " " + modTooltipVIT.getDisplayName());
            }
        }
        // INTELLECT
        currentCompareString = "intellect";
        Intellect modTooltipINT = (Intellect) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipINT == null) {
            modTooltipINT = new Intellect(0);
        }
        Intellect modEquippedINT = (Intellect) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedINT == null) {
            modEquippedINT = new Intellect(0);
        }
        if (modEquippedINT.getIntellect() != modTooltipINT.getIntellect()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedINT.getIntellect() > modTooltipINT.getIntellect()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedINT.getIntellect() - modTooltipINT.getIntellect()) + " " + modTooltipINT.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipINT.getIntellect() - modEquippedINT.getIntellect()) + " " + modTooltipINT.getDisplayName());
            }
        }
        // FIRE RESISTANCE
        currentCompareString = "fireResistance";
        FireResistance modTooltipFR = (FireResistance) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipFR == null) {
            modTooltipFR = new FireResistance(0);
        }
        FireResistance modEquippedFR = (FireResistance) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedFR == null) {
            modEquippedFR = new FireResistance(0);
        }
        if (modEquippedFR.getFireResistance() != modTooltipFR.getFireResistance()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedFR.getFireResistance() > modTooltipFR.getFireResistance()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedFR.getFireResistance() - modTooltipFR.getFireResistance()) + " " + modTooltipFR.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipFR.getFireResistance() - modEquippedFR.getFireResistance()) + " " + modTooltipFR.getDisplayName());
            }
        }
        // ICE RESISTANCE
        currentCompareString = "iceResistance";
        IceResistance modTooltipIR = (IceResistance) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipIR == null) {
            modTooltipIR = new IceResistance(0);
        }
        IceResistance modEquippedIR = (IceResistance) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedIR == null) {
            modEquippedIR = new IceResistance(0);
        }
        if (modEquippedIR.getIceResistance() != modTooltipIR.getIceResistance()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedIR.getIceResistance() > modTooltipIR.getIceResistance()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedIR.getIceResistance() - modTooltipIR.getIceResistance()) + " " + modTooltipIR.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipIR.getIceResistance() - modEquippedIR.getIceResistance()) + " " + modTooltipIR.getDisplayName());
            }
        }
        // POISON RESISTANCE
        currentCompareString = "poisonResistance";
        PoisonResistance modTooltipPR = (PoisonResistance) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipPR == null) {
            modTooltipPR = new PoisonResistance(0);
        }
        PoisonResistance modEquippedPR = (PoisonResistance) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedPR == null) {
            modEquippedPR = new PoisonResistance(0);
        }
        if (modEquippedPR.getPoisonResistance() != modTooltipPR.getPoisonResistance()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedPR.getPoisonResistance() > modTooltipPR.getPoisonResistance()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedPR.getPoisonResistance() - modTooltipPR.getPoisonResistance()) + " " + modTooltipPR.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipPR.getPoisonResistance() - modEquippedPR.getPoisonResistance()) + " " + modTooltipPR.getDisplayName());
            }
        }
        // DODGE
        currentCompareString = "dodge";
        Dodge modTooltipDG = (Dodge) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipDG == null) {
            modTooltipDG = new Dodge(0);
        }
        Dodge modEquippedDG = (Dodge) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedDG == null) {
            modEquippedDG = new Dodge(0);
        }
        if (modEquippedDG.getDodge() != modTooltipDG.getDodge()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedDG.getDodge() > modTooltipDG.getDodge()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedDG.getDodge() - modTooltipDG.getDodge()) + " " + modTooltipDG.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipDG.getDodge() - modEquippedDG.getDodge()) + " " + modTooltipDG.getDisplayName());
            }
        }
        // BLOCK
        currentCompareString = "block";
        Block modTooltipBLK = (Block) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipBLK == null) {
            modTooltipBLK = new Block(0);
        }
        Block modEquippedBLK = (Block) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedBLK == null) {
            modEquippedBLK = new Block(0);
        }
        if (modEquippedBLK.getBlock() != modTooltipBLK.getBlock()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedBLK.getBlock() > modTooltipBLK.getBlock()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedBLK.getBlock() - modTooltipBLK.getBlock()) + " " + modTooltipBLK.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipBLK.getBlock() - modEquippedBLK.getBlock()) + " " + modTooltipBLK.getDisplayName());
            }
        }
        // THORNS
        currentCompareString = "thorns";
        Thorns modTooltipTRN = (Thorns) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipTRN == null) {
            modTooltipTRN = new Thorns(0);
        }
        Thorns modEquippedTRN = (Thorns) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedTRN == null) {
            modEquippedTRN = new Thorns(0);
        }
        if (modEquippedTRN.getThorns() != modTooltipTRN.getThorns()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedTRN.getThorns() > modTooltipTRN.getThorns()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedTRN.getThorns() - modTooltipTRN.getThorns()) + " " + modTooltipTRN.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipTRN.getThorns() - modEquippedTRN.getThorns()) + " " + modTooltipTRN.getDisplayName());
            }
        }
        // REFLECTION
        currentCompareString = "reflection";
        Reflection modTooltipRF = (Reflection) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipRF == null) {
            modTooltipRF = new Reflection(0);
        }
        Reflection modEquippedRF = (Reflection) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedRF == null) {
            modEquippedRF = new Reflection(0);
        }
        if (modEquippedRF.getReflection() != modTooltipRF.getReflection()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedRF.getReflection() > modTooltipRF.getReflection()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedRF.getReflection() - modTooltipRF.getReflection()) + " " + modTooltipRF.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipRF.getReflection() - modEquippedRF.getReflection()) + " " + modTooltipRF.getDisplayName());
            }
        }
        // TODO GEM FIND
        // ITEM FIND
        currentCompareString = "itemFind";
        ItemFind modTooltipIF = (ItemFind) getModifier(currentCompareString, tooltipModifiers);
        if (modTooltipIF == null) {
            modTooltipIF = new ItemFind(0);
        }
        ItemFind modEquippedIF = (ItemFind) getModifier(currentCompareString, equippedModifiers);
        if (modEquippedIF == null) {
            modEquippedIF = new ItemFind(0);
        }
        if (modEquippedIF.getItemFind() != modTooltipIF.getItemFind()) {
            if (!addedBeginningText) {
                // Comparison tooltip text
                tooltips.add("");
                tooltips.add(TextFormatting.GOLD + "Changes when equipped");
                addedBeginningText = true;
            }
            if (modEquippedIF.getItemFind() > modTooltipIF.getItemFind()) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedIF.getItemFind() - modTooltipIF.getItemFind()) + " " + modTooltipIF.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipIF.getItemFind() - modEquippedIF.getItemFind()) + " " + modTooltipIF.getDisplayName());
            }
        }
    }

    public Modifier getModifier(String modifierString, List<Modifier> modifiers) {
        for (Modifier modifier : modifiers) {
            if (modifier.getName().equals(modifierString)) {
                return modifier;
            }
        }
        return null;
    }
}
