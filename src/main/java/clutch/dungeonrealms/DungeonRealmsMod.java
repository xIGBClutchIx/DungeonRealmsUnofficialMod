package clutch.dungeonrealms;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = "dungeonrealmsunofficialmod", version = "1.1.2", clientSideOnly = true, acceptedMinecraftVersions="[1.12.2,)")
public class DungeonRealmsMod {

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new RenderOverlay());
        MinecraftForge.EVENT_BUS.register(new ArmorTooltipCompare());
    }
}
