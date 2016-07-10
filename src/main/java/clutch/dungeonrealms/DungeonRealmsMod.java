package clutch.dungeonrealms;

import clutch.dungeonrealms.reflection.ReflectionUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "DungeonRealmsUnofficialMod", version = "Beta-1.0", clientSideOnly = true, acceptedMinecraftVersions="[1.9.4,1.10.2]")
public class DungeonRealmsMod {

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ReflectionUtils.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(RenderOverlay.instance());
    }
}
