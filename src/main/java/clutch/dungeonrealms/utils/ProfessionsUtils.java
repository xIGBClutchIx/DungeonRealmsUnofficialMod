package clutch.dungeonrealms.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class ProfessionsUtils {

    public static String getXPBar(ItemStack stack) {
        String startingBar = TextFormatting.GRAY + "XP: ";
        String containsBar = "";
        String expBarText = ItemStackUtils.getLore(stack, startingBar, containsBar, true);
        return TextFormatting.GRAY + "" + getXP(stack) + " " + expBarText  + TextFormatting.GRAY + " " + getMaxXP(stack);
    }

    public static String getMaxXP(ItemStack stack) {
        String xp = ItemStackUtils.getLore(stack, TextFormatting.GRAY.toString(), " / ", true);
        if (xp.contains(" / ")) {
            return xp.split(" / ")[1];
        }
        return "?";
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
        if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("xp")) return 0;
        return stack.getTagCompound().getInteger("xp");
    }
}
