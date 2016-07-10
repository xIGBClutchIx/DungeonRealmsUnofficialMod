package clutch.dungeonrealms.reflection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;

public class ReflectionGuiIngame {

    public static int remainingHighlightTicks() {
        int remainingHighlightTicks = 0;
        try {
            Field field = GuiIngame.class.getDeclaredField(ReflectionUtils.getFieldName("remainingHighlightTicks", "field_92017_k"));
            field.setAccessible(true);
            remainingHighlightTicks = (Integer) field.get(Minecraft.getMinecraft().ingameGUI);
        } catch (IllegalAccessException ignored) {
        } catch (NoSuchFieldException ignored) {}
        return remainingHighlightTicks;
    }


    public static ItemStack highlightingItemStack() {
        ItemStack highlightingItemStack = null;
        try {
            Field field = GuiIngame.class.getDeclaredField(ReflectionUtils.getFieldName("highlightingItemStack", "field_92016_l"));
            field.setAccessible(true);
            highlightingItemStack = (ItemStack) field.get(Minecraft.getMinecraft().ingameGUI);
        } catch (IllegalAccessException ignored) {
        } catch (NoSuchFieldException ignored) {}
        return highlightingItemStack;
    }
}
