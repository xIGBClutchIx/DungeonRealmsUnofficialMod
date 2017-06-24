package clutch.dungeonrealms;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "dungeonrealmsunofficialmod", version = "1.0.1", clientSideOnly = true, acceptedMinecraftVersions="[1.9.4,)")
public class DungeonRealmsMod {

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new RenderOverlay());
        MinecraftForge.EVENT_BUS.register(new ArmorTooltipCompare());
    }
}
