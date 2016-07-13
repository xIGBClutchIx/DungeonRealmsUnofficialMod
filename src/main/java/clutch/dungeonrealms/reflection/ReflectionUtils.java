package clutch.dungeonrealms.reflection;

import net.minecraft.client.gui.GuiIngame;

public class ReflectionUtils {

    public static boolean srgNames = false;

    public static void init() {
        try  {
            GuiIngame.class.getDeclaredField("remainingHighlightTicks");
        } catch (SecurityException ignored) {
        } catch (NoSuchFieldException ex) {
            srgNames = true;
        }
    }

    public static String getFieldName(String fieldName, String srgFieldName) {
        return (srgNames ? srgFieldName : fieldName);
    }
}
