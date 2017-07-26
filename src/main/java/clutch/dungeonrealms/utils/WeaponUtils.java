package clutch.dungeonrealms.utils;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WeaponUtils {

    public static WeaponType getWeaponType(ItemStack stack) {
        Item item = stack.getItem();
        if ((item == Items.DIAMOND_SWORD) || (item == Items.IRON_SWORD) || (item == Items.STONE_SWORD) || (item == Items.GOLDEN_SWORD) || (item == Items.WOODEN_SWORD)) {
            return WeaponType.SWORD;
        }
        if ((item == Items.DIAMOND_AXE) || (item == Items.IRON_AXE) || (item == Items.STONE_AXE) || (item == Items.GOLDEN_AXE) || (item == Items.WOODEN_AXE)) {
            return WeaponType.AXE;
        }
        if ((item == Items.DIAMOND_SHOVEL) || (item == Items.IRON_SHOVEL) || (item == Items.STONE_SHOVEL) || (item == Items.GOLDEN_SHOVEL) || (item == Items.WOODEN_SHOVEL)) {
            return WeaponType.POLEARM;
        }
        if ((item == Items.DIAMOND_HOE) || (item == Items.IRON_HOE) || (item == Items.STONE_HOE) || (item == Items.GOLDEN_HOE) || (item == Items.WOODEN_HOE)) {
            return WeaponType.STAFF;
        }
        if (item == Items.BOW) {
            return WeaponType.BOW;
        } else {
            return WeaponType.NONE;
        }
    }

    public enum WeaponType {
        NONE,
        SWORD,
        AXE,
        POLEARM,
        BOW,
        STAFF
    }
}
