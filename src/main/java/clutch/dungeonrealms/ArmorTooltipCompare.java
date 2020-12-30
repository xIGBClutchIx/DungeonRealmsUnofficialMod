package clutch.dungeonrealms;

import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ArmorTooltipCompare {

    private boolean addedBeginningText = false;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack tooltipStack = event.getItemStack();
        if (!tooltipStack.hasTagCompound()) return;
        ArmorUtils.ArmorType tooltipStackArmorType = ArmorUtils.getArmorType(tooltipStack);
        if (tooltipStackArmorType != ArmorUtils.ArmorType.NONE) {
            ItemAttributes tooltipModifiers = ArmorUtils.getModifiers(tooltipStack);
            int value = tooltipStackArmorType.ordinal() - 1;
            if (value == -1) return;
            ItemStack equipped = Minecraft.getMinecraft().player.inventory.armorInventory.get(value);
            if (equipped != tooltipStack) {
                addCompareTooltip(event.getToolTip(), tooltipModifiers, equipped);
            }
        }
    }

    public void addCompareTooltip(List<String> tooltips, ItemAttributes itemAttributes, ItemStack equippedStacks) {
        // Equipped attributes
        ItemAttributes equippedAttributes = new ItemAttributes();
        if (equippedStacks != null) {
            equippedAttributes = ArmorUtils.getModifiers(equippedStacks);
        }
        addedBeginningText = false;
        for (String compareModifier : equippedAttributes.getAttributes()) {
            createTooltip(compareModifier, tooltips, itemAttributes, equippedAttributes);
        }
    }

    public void createTooltip(String currentAttribute, List<String> tooltips, ItemAttributes tooltipAttributes, ItemAttributes equippedAttributes) {
        if (!addedBeginningText) {
            tooltips.add("");
            tooltips.add(TextFormatting.GOLD + "Changes when equipped");
            addedBeginningText = true;
        }
        String tooltipName = equippedAttributes.getCompareName(currentAttribute);
        int modEquippedValue = equippedAttributes.getCompareValue(currentAttribute);
        int modTooltipValue = tooltipAttributes.getCompareValue(currentAttribute);

        if (modEquippedValue != modTooltipValue) {
            if (modEquippedValue > modTooltipValue) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedValue - modTooltipValue) + " " + tooltipName);
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipValue - modEquippedValue) + " " + tooltipName);
            }
        }
    }
}
