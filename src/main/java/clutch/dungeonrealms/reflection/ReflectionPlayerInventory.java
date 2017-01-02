package clutch.dungeonrealms.reflection;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ReflectionPlayerInventory {

    private static final String[] ARMOR_INVENTORY = {"armorInventory", "field_70460_b", "field_70458_d"};

    public static Object getArmorInventory() {
        return getObjectFromField(ARMOR_INVENTORY);
    }

    private static Object getObjectFromField(String[] list) {
        return ReflectionHelper.getPrivateValue(InventoryPlayer.class, Minecraft.getMinecraft().thePlayer.inventory, list);
    }
}
