package clutch.dungeonrealms;

import clutch.dungeonrealms.modifiers.Modifier;
import clutch.dungeonrealms.reflection.ReflectionPlayerInventory;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArmorTooltipCompare {

    private static final List<String> MODIFIERS_TO_COMPARE = Arrays.asList("armor", "healthPoints", "healthRegen", "energyRegen", "dps",
            "strength", "dexterity", "vitality", "intellect", "fireResistance", "iceResistance", "poisonResistance",
            "dodge", "block", "thorns", "reflection", "itemFind");
    private boolean addedBeginningText = false;

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack tooltipStack = event.getItemStack();
        if (!tooltipStack.hasTagCompound()) return;
        ArmorUtils.ArmorType tooltipStackArmorType = ArmorUtils.getArmorType(tooltipStack);
        if (tooltipStackArmorType != ArmorUtils.ArmorType.NONE) {
            List<Modifier> tooltipModifiers = ArmorUtils.getModifiers(tooltipStack);
            int value = tooltipStackArmorType.ordinal() - 1;
            if (value == -1) return;
            ItemStack equipped = getItemstackFromArmor(value);
            if (equipped != tooltipStack) {
                addCompareTooltip(event.getToolTip(), tooltipModifiers, equipped);
            }
        }
    }

    /**
     * Hacky fix for 1.10.2 - 1.11 changes start
     */
    public ItemStack getItemstackFromArmor(int slot) {
        Object armorInventory = ReflectionPlayerInventory.getArmorInventory();
        if (armorInventory instanceof ItemStack[]) {
            return ((ItemStack[]) armorInventory)[slot];
        } else if (armorInventory instanceof List) {
            return ((List<ItemStack>) armorInventory).get(slot);
        }
        return new ItemStack(Blocks.AIR);
    }
    /**
     * Hacky fix for 1.10.2 - 1.11 changes end
     */

    public void addCompareTooltip(List<String> tooltips, List<Modifier> tooltipModifiers, ItemStack equippedStacks) {
        if (tooltipModifiers.isEmpty()) return;
        // equipped modifiers
        List<Modifier> equippedModifiers = new ArrayList<>();
        if (equippedStacks != null) {
            equippedModifiers = ArmorUtils.getModifiers(equippedStacks);
        }
        addedBeginningText = false;
        for (String compareModifier : MODIFIERS_TO_COMPARE) {
            createTooltip(compareModifier, tooltips, tooltipModifiers, equippedModifiers);
        }
        // TODO GEM FIND
    }

    public void createTooltip(String currentModifier, List<String> tooltips, List<Modifier> tooltipModifiers, List<Modifier> equippedModifiers) {
        if (!addedBeginningText) {
            // Comparison tooltip text
            tooltips.add("");
            tooltips.add(TextFormatting.GOLD + "Changes when equipped");
            addedBeginningText = true;
        }
        Modifier modEquipped = ArmorUtils.checkModifier(currentModifier, getModifier(currentModifier, equippedModifiers));
        Modifier modTooltip = ArmorUtils.checkModifier(currentModifier, getModifier(currentModifier, tooltipModifiers));
        int modEquippedValue = ArmorUtils.getValueFromModifier(modEquipped);
        int modTooltipValue = ArmorUtils.getValueFromModifier(modTooltip);
        if (modEquippedValue != modTooltipValue) {
            if (modEquippedValue > modTooltipValue) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedValue - modTooltipValue) + " " + modTooltip.getDisplayName());
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipValue - modEquippedValue) + " " + modTooltip.getDisplayName());
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
