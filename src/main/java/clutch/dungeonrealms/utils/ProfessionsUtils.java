package clutch.dungeonrealms.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.text.DecimalFormat;

public class ProfessionsUtils {

    public static String getXPBar(int currentXp, int maxXp) {
        if (currentXp == 0 && maxXp == 0) return "";
        String xpBar =  TextFormatting.GREEN + "";
        if (maxXp != 0) {
            xpBar = "" + TextFormatting.GRAY + currentXp + " " +  xpBar;
        }
        double percentXp = (double) (Math.round(100.0D * Double.parseDouble((new DecimalFormat("##.#").format(((double)currentXp / (double)maxXp))))));
        if (percentXp <= 0 && (double) maxXp > 0) {
            percentXp = 1;
        }
        double percentInterval = (100.0D / 50);
        int barCount = 0;
        while (percentXp > 0 && barCount < 50) {
            percentXp -= percentInterval;
            barCount++;
            xpBar += "|";
        }
        xpBar += TextFormatting.RED;
        while (barCount < 50) {
            xpBar += "|";
            barCount++;
        }
        if (maxXp != 0) {
            xpBar = xpBar + TextFormatting.GRAY + " " + maxXp;
        }
        return xpBar;
    }

    public static boolean isProfessionItem(ItemStack stack) {
        return stack.getItem() == Items.WOODEN_PICKAXE || stack.getItem() == Items.STONE_PICKAXE ||
                stack.getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.DIAMOND_PICKAXE ||
                stack.getItem() == Items.GOLDEN_PICKAXE || stack.getItem() == Items.FISHING_ROD;
    }

    public static int getLevel(ItemStack stack) {
        // Return level
        return stack.getTagCompound().getInteger("level");
    }

    public static int getXP(ItemStack stack) {
        // Return xp
        return stack.getTagCompound().getInteger("XP");
    }

    public static int getMaxXP(ItemStack stack) {
        // Return max xp
        return stack.getTagCompound().getInteger("maxXP");
    }
}
