package clutch.dungeonrealms.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class GuiIngameUtils {

    public static String getLore(ItemStack stack, String starting, String contains, boolean removeStarting) {
        if (starting == null || starting.isEmpty()) return "";
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("display", 10)) {
                NBTTagCompound display = stack.getTagCompound().getCompoundTag("display");
                if (display.getTagId("Lore") == 9) {
                    NBTTagList lore = display.getTagList("Lore", 8);
                    if (!lore.hasNoTags()) {
                        for (int l1 = 0; l1 < lore.tagCount(); ++l1) {
                            String loreLine = lore.getStringTagAt(l1);
                            if (loreLine.startsWith(starting)) {
                                if (contains == null || contains.isEmpty()) {
                                    if (removeStarting) {
                                        loreLine = loreLine.replaceFirst(starting, "");
                                    }
                                    return loreLine;
                                } else {
                                    if (loreLine.contains(contains)) {
                                        if (removeStarting) {
                                            loreLine = loreLine.replaceFirst(starting, "");
                                        }
                                        return loreLine;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public static void renderText(String text, int yOffset, int opacity) {
        if (text.isEmpty()) return;
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontrenderer = minecraft.fontRendererObj;
        PlayerControllerMP playerController = minecraft.playerController;
        ScaledResolution res = new ScaledResolution(minecraft);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        int x = (res.getScaledWidth() - fontrenderer.getStringWidth(text)) / 2;
        int y = res.getScaledHeight() - 59 + yOffset;
        if (!playerController.shouldDrawHUD()) y += 14;
        fontrenderer.drawStringWithShadow(text, x, y, 0xFFFFFF | (opacity << 24));
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}
