package clutch.dungeonrealms.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderUtils {

    public static void renderText(String text, int yOffset, int opacity) {
        if (text.isEmpty()) return;
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontrenderer = minecraft.fontRendererObj;
        PlayerControllerMP playerController = minecraft.playerController;
        ScaledResolution res = new ScaledResolution(minecraft);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ZERO);
        int x = (res.getScaledWidth() - fontrenderer.getStringWidth(text)) / 2;
        int y = res.getScaledHeight() - 59 + yOffset;
        if (playerController != null && !playerController.shouldDrawHUD()) y += 14;
        fontrenderer.drawStringWithShadow(text, x, y, 0xFFFFFF | (opacity << 24));
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}
