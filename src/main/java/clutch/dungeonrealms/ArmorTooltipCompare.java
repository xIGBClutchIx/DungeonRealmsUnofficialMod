package clutch.dungeonrealms;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.reflection.ReflectionPlayerInventory;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class ArmorTooltipCompare {

    private static final List<String> MODIFIERS_TO_COMPARE = Arrays.asList("ARMOR", "HP", "HP REGEN", "ENERGY REGEN", "DPS",
            "STR", "DEX", "VIT", "INT", "FIRE RESISTANCE", "ICE RESISTANCE", "POISON RESISTANCE",
            "DODGE", "BLOCK", "THORNS", "REFLECTION", "ITEM FIND");
    private boolean addedBeginningText = false;

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack tooltipStack = event.getItemStack();
        if (!tooltipStack.hasTagCompound()) return;
        ArmorUtils.ArmorType tooltipStackArmorType = ArmorUtils.getArmorType(tooltipStack);
        if (tooltipStackArmorType != ArmorUtils.ArmorType.NONE) {
            ItemAttributes tooltipModifiers = ArmorUtils.getModifiers(tooltipStack);
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

    public void addCompareTooltip(List<String> tooltips, ItemAttributes itemAttributes, ItemStack equippedStacks) {
        // Equipped attributes
        ItemAttributes equippedAttributes = new ItemAttributes();
        if (equippedStacks != null) {
            equippedAttributes = ArmorUtils.getModifiers(equippedStacks);
        }
        addedBeginningText = false;
        for (String compareModifier : MODIFIERS_TO_COMPARE) {
            createTooltip(compareModifier, tooltips, itemAttributes, equippedAttributes);
        }
        // TODO GEM FIND
    }

    public void createTooltip(String currentAttribute, List<String> tooltips, ItemAttributes tooltipAttributes, ItemAttributes equippedAttributes) {
        if (!addedBeginningText) {
            // Comparison tooltip text
            tooltips.add("");
            tooltips.add(TextFormatting.GOLD + "Changes when equipped");
            addedBeginningText = true;
        }
        Attribute modEquipped = ArmorUtils.getAttribute(currentAttribute, equippedAttributes);
        Attribute modTooltip = ArmorUtils.getAttribute(currentAttribute, tooltipAttributes);
        int modEquippedValue = ArmorUtils.getValueFromAttribute(modEquipped);
        int modTooltipValue = ArmorUtils.getValueFromAttribute(modTooltip);

        if (modEquippedValue != modTooltipValue) {
            if (modEquippedValue > modTooltipValue) {
                if (modTooltip != null) {
                    tooltips.add(TextFormatting.RED + "-" + (modEquippedValue - modTooltipValue) + " " + modTooltip.getDisplayName());
                }
            } else {
                if (modTooltip != null) {
                    tooltips.add(TextFormatting.GREEN + "+" + (modTooltipValue - modEquippedValue) + " " + modTooltip.getDisplayName());
                }
            }
        }
    }
}
