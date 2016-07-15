package clutch.dungeonrealms;

import clutch.dungeonrealms.modifiers.Modifier;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;

public class ArmorTooltipCompare {

    private static final List<String> MODIFIERS_TO_COMPARE = Arrays.asList(new String[]{"armor", "healthPoints", "healthRegen", "energyRegen", "dps",
            "strength", "dexterity", "vitality", "intellect", "fireResistance", "iceResistance", "poisonResistance",
            "dodge", "block", "thorns", "reflection", "itemFind"});
    private boolean addedBeginningText = false;

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
