package clutch.dungeonrealms.reflection;

import net.minecraft.client.gui.GuiIngame;

public class ReflectionUtils {

    public static boolean seargeNames = false;

    public static void init() {
        try  {
            GuiIngame.class.getDeclaredField("remainingHighlightTicks");
        } catch (SecurityException ignored) {
        } catch (NoSuchFieldException ex) {
            seargeNames = true;
        }
    }

    public static String getFieldName(String fieldName, String seargeFieldName) {
        return (seargeNames ? seargeFieldName : fieldName);
    }
}
