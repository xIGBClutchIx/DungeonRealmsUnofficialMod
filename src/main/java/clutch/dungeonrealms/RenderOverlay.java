package clutch.dungeonrealms;

import clutch.dungeonrealms.reflection.ReflectionGuiIngame;
import clutch.dungeonrealms.utils.ItemStackUtils;
import clutch.dungeonrealms.utils.ProfessionsUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOverlay {

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        // Render on text
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            int remainingHighlight = ReflectionGuiIngame.getRemainingHighlightTicks();
            ItemStack stack = ReflectionGuiIngame.getHighlightingItemStack();
            // Checks
            if (remainingHighlight > 0 && stack != null) {
                // Opacity
                int opacity = (int)((float) remainingHighlight * 256.0 / 10.0);
                if (opacity > 255) opacity = 255;
                if (opacity > 0) {
                    // Pickaxe/Fishing Rod exp level and bar
                    if (ProfessionsUtils.isProfessionItem(stack)) {
                        // If does not have nbt tags then return
                        if (!stack.hasTagCompound()) return;
                        // Exp info
                        String xpBar = ProfessionsUtils.getXPBar(stack);
                        // Render exp and bar if able to
                        if (!xpBar.isEmpty()) {
                            renderText(xpBar, 10, opacity);
                        }
                        // Render level
                        int level = ProfessionsUtils.getLevel(stack);
                        if (level != 0) {
                            renderText(TextFormatting.GRAY + "Level: " + ItemStackUtils.getColor(stack) + level, -10, opacity);
                        }
                    }
                    // Realm portal rune tier
                    if (stack.getItem() == Items.NETHER_STAR) {
                        // If does not have nbt tags then return
                        if (!stack.hasTagCompound()) return;
                        String realmTier = ItemStackUtils.getLore(stack, TextFormatting.GRAY + "Tier: ", "", false);
                        if (!realmTier.trim().isEmpty()) {
                            renderText(realmTier, 10, opacity);
                        }
                    }
                }
            }
        }
    }

    public void renderText(String text, int yOffset, int opacity) {
        if (text.isEmpty()) return;
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontrenderer = minecraft.fontRenderer;
        PlayerControllerMP playerController = minecraft.playerController;
        ScaledResolution res = new ScaledResolution(minecraft);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        int x = (res.getScaledWidth() - fontrenderer.getStringWidth(text)) / 2;
        int y = res.getScaledHeight() - 59 + yOffset;
        if (playerController != null && !playerController.shouldDrawHUD()) y += 14;
        fontrenderer.drawStringWithShadow(text, x, y, 0xFFFFFF | (opacity << 24));
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}
