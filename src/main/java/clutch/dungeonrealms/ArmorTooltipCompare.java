package clutch.dungeonrealms;

import clutch.dungeonrealms.reflection.ReflectionPlayerInventory;
import clutch.dungeonrealms.utils.ArmorUtils;
import clutch.dungeonrealms.utils.ItemStackUtils;
import clutch.dungeonrealms.utils.WeaponUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
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
            if (GuiScreen.isCtrlKeyDown()) {
                int plus = ItemStackUtils.getPlus(tooltipStack);
                List<String> newToolTipToShow = new ArrayList<>();
                String hpLore = ItemStackUtils.getLore(tooltipStack, TextFormatting.RED + "HP: +", TextFormatting.RED + "HP: +", true).trim().replaceAll(" ", "");
                int hp = 1;
                if (hpLore != null) {
                    hp = Integer.parseInt(hpLore.trim());
                }
                final int finalHp = hp;
                if (plus < 12) {
                    int index = plus + 1;
                    while (index < 13) {
                        int newHigh = (int) getDamageIncrease(hp, index);
                        newToolTipToShow.add(TextFormatting.RED + "[+" + index + "] Health: +" + newHigh + TextFormatting.GRAY + " (" + TextFormatting.GREEN + "+" + (newHigh - finalHp) + TextFormatting.GRAY + ")");
                        index++;
                    }
                    String displayName = event.getToolTip().get(0);
                    event.getToolTip().clear();
                    event.getToolTip().add(displayName);
                    event.getToolTip().addAll(newToolTipToShow);
                }
                return;
            }

            ItemAttributes tooltipModifiers = ArmorUtils.getModifiers(tooltipStack);
            int value = tooltipStackArmorType.ordinal() - 1;
            if (value == -1) return;
            ItemStack equipped = getItemstackFromArmor(value);
            if (equipped != tooltipStack) {
                addCompareTooltip(event.getToolTip(), tooltipModifiers, equipped);
            }
        } else {
            WeaponUtils.WeaponType weaponType = WeaponUtils.getWeaponType(tooltipStack);
            if (weaponType != WeaponUtils.WeaponType.NONE) {
                if (GuiScreen.isCtrlKeyDown()) {
                    int plus = ItemStackUtils.getPlus(tooltipStack);
                    List<String> newToolTipToShow = new ArrayList<>();
                    String lore = ItemStackUtils.getLore(tooltipStack, TextFormatting.RED + "DMG: ", TextFormatting.RED + "DMG: ", true).trim().replaceAll(" ", "");
                    int lowDamage = 1;
                    int highDamage = 1;
                    if (lore.contains("-")) {
                        lowDamage = Integer.parseInt(lore.split("-")[0].trim());
                        highDamage = Integer.parseInt(lore.split("-")[1].trim());
                    }
                    final int finalLowDamage = lowDamage;
                    final int finalHighDamage = highDamage;
                    if (plus < 12) {
                        int index = plus + 1;
                        while (index < 13) {
                            int newHigh = (int) getDamageIncrease(finalHighDamage, index);
                            int newLow = (int) getDamageIncrease(finalLowDamage, index);
                            newToolTipToShow.add(TextFormatting.RED + "[+" + index + "] Damage: " + newLow + " - " + newHigh + " " + TextFormatting.GRAY + "(" + TextFormatting.GREEN + "+" + (newLow - finalLowDamage) + TextFormatting.GRAY + " - " + TextFormatting.GREEN + "+" + (newHigh - finalHighDamage) + TextFormatting.GRAY + ")");
                            index++;
                        }
                        String displayName = event.getToolTip().get(0);
                        String rarity = event.getToolTip().get(event.getToolTip().size() - 1);
                        event.getToolTip().clear();
                        event.getToolTip().add(displayName);
                        event.getToolTip().addAll(newToolTipToShow);
                        event.getToolTip().add(rarity);
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public double getDamageIncrease(double damage, int plus) {
        double increase;
        switch (plus) {
            case 1:
                increase = 1.05;
                break;
            case 2:
                increase = 1.10;
                break;
            case 3:
                increase = 1.15;
                break;
            case 4:
                increase = 1.20;
                break;
            case 5:
                increase = 1.25;
                break;
            case 6:
                increase = 1.30;
                break;
            case 7:
                increase = 1.35;
                break;
            case 8:
                increase = 1.40;
                break;
            case 9:
                increase = 1.45;
                break;
            case 10:
                increase = 1.50;
                break;
            case 11:
                increase = 1.55;
                break;
            case 12:
                increase = 1.60;
                break;
            default:
                increase = 1;
                break;
        }
        damage = damage * increase;
        return damage;

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
        int modEquippedValue = equippedAttributes.getCompareValue(currentAttribute);
        int modTooltipValue = tooltipAttributes.getCompareValue(currentAttribute);

        if (modEquippedValue != modTooltipValue) {
            if (modEquippedValue > modTooltipValue) {
                tooltips.add(TextFormatting.RED + "-" + (modEquippedValue - modTooltipValue) + " " + currentAttribute);
            } else {
                tooltips.add(TextFormatting.GREEN + "+" + (modTooltipValue - modEquippedValue) + " " + currentAttribute);
            }
        }
    }
}
