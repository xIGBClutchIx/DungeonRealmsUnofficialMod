package clutch.dungeonrealms.reflection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ReflectionGuiIngame {

    private static final String[] HIGHLIGHT_TICKS = {"remainingHighlightTicks", "field_92017_k"};
    private static final String[] HIGHLIGHT_ITEMSTACK = {"highlightingItemStack", "field_92016_l"};

    public static int getRemainingHighlightTicks() {
        return (Integer) getObjectFromField(HIGHLIGHT_TICKS);
    }

    public static ItemStack getHighlightingItemStack() {
        return (ItemStack) getObjectFromField(HIGHLIGHT_ITEMSTACK);
    }

    private static Object getObjectFromField(String[] list) {
        return ReflectionHelper.getPrivateValue(GuiIngame.class, Minecraft.getMinecraft().ingameGUI, list);
    }
}
