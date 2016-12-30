package clutch.dungeonrealms.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.text.DecimalFormat;

public class ProfessionsUtils {

    public static String getXPBar(ItemStack stack) {
        int maxXP = getMaxXP(stack);
        if (maxXP == 0) return "";
        int xp = getXP(stack);
        String startingBar = TextFormatting.GRAY + "EXP: ";
        String containsBar = "";
        String expBarText = ItemStackUtils.getLore(stack, startingBar, containsBar, true);
        return TextFormatting.GRAY + "" + xp + " " + expBarText  + TextFormatting.GRAY + " " + maxXP;
    }

    public static boolean isProfessionItem(ItemStack stack) {
        return stack.getItem() == Items.WOODEN_PICKAXE || stack.getItem() == Items.STONE_PICKAXE ||
                stack.getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.DIAMOND_PICKAXE ||
                stack.getItem() == Items.GOLDEN_PICKAXE || stack.getItem() == Items.FISHING_ROD;
    }

    public static int getLevel(ItemStack stack) {
        if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("level")) return 0;
        return stack.getTagCompound().getInteger("level");
    }

    public static int getXP(ItemStack stack) {
        if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("XP")) return 0;
        return stack.getTagCompound().getInteger("XP");
    }

    public static int getMaxXP(ItemStack stack) {
        if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("maxXP")) return 0;
        return stack.getTagCompound().getInteger("maxXP");
    }
}
