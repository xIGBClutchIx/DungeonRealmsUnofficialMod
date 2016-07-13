package clutch.dungeonrealms.reflection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;

public class ReflectionGuiIngame {

    private static final String HIGHLIGHT_TICKS_NAME = "remainingHighlightTicks";
    private static final String HIGHLIGHT_TICKS_SRG = "field_92017_k";

    private static final String HIGHLIGHT_ITEMSTACK_NAME = "highlightingItemStack";
    private static final String HIGHLIGHT_ITEMSTACK_SRG = "field_92016_l";

    public static int getRemainingHighlightTicks() {
        return (Integer) getObjectFromField(HIGHLIGHT_TICKS_NAME, HIGHLIGHT_TICKS_SRG);
    }

    public static ItemStack getHighlightingItemStack() {
        return (ItemStack) getObjectFromField(HIGHLIGHT_ITEMSTACK_NAME, HIGHLIGHT_ITEMSTACK_SRG);
    }

    private static Object getObjectFromField(String name, String srg) {
        Object object = null;
        try {
            Field field = GuiIngame.class.getDeclaredField(ReflectionUtils.getFieldName(name, srg));
            field.setAccessible(true);
            object = field.get(Minecraft.getMinecraft().ingameGUI);
        } catch (IllegalAccessException ignored) {
        } catch (NoSuchFieldException ignored) {}
        return object;
    }
}
