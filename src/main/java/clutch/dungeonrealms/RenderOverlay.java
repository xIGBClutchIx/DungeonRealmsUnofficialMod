package clutch.dungeonrealms;

import clutch.dungeonrealms.reflection.ReflectionGuiIngame;
import clutch.dungeonrealms.utils.GuiIngameUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderOverlay {

    private static RenderOverlay instance = new RenderOverlay();

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        // Render on text
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            int remainingHighlightTicks = ReflectionGuiIngame.remainingHighlightTicks();
            ItemStack highlightingItemStack = ReflectionGuiIngame.highlightingItemStack();
            // Checks
            if (remainingHighlightTicks > 0 && highlightingItemStack != null && highlightingItemStack.getItem() != null) {
                // Opacity
                int opacity = (int)((float)remainingHighlightTicks * 256.0F / 10.0F);
                if (opacity > 255) opacity = 255;
                if (opacity > 0) {
                    // Pickaxe/Fishing Rod exp level
                    renderProfessionLevel(-20, highlightingItemStack, opacity);
                    // Pickaxe/Fishing Rod exp bar
                    renderProfessionExp(-10, highlightingItemStack, opacity);
                    // Realm portal rune tier
                    renderRealmTier(-10, highlightingItemStack, opacity);
                }
            }
        }
    }

    public void renderProfessionExp(int yOffset, ItemStack stack, int opacity) {
        // Bar info
        String startingBar = TextFormatting.GRAY + "EXP: ";
        String containsBar = "";
        boolean removeStartingBar = true;
        // Exp info
        String startingEXP = TextFormatting.GRAY.toString();
        String containsEXP = TextFormatting.GRAY + " / " + TextFormatting.GRAY;
        boolean removeStartingEXP = false;
        // Only render on pickaxe
        if (stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemFishingRod) {
            // Start getting lore
            String expBarText = GuiIngameUtils.getLore(stack, startingBar, containsBar, removeStartingBar);
            String expText = GuiIngameUtils.getLore(stack, startingEXP, containsEXP, removeStartingEXP);
            // Check
            if (expBarText.isEmpty() || expText.isEmpty()) return;
            // Split exp text into two halves
            String[] expTextSplit = expText.split(containsEXP);
            if (expTextSplit.length != 2) return;
            // Split exp text
            String currentExpText = expText.split(containsEXP)[0] + " ";
            String endExpText = TextFormatting.GRAY + " " + expText.split(containsEXP)[1];
            // Check
            if (currentExpText.isEmpty() || endExpText.isEmpty()) return;
            // Render
            GuiIngameUtils.renderText(currentExpText + expBarText + endExpText, yOffset, opacity);
        }
    }

    public void renderProfessionLevel(int yOffset, ItemStack stack, int opacity) {
        if (stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemFishingRod) {
            renderDefault(TextFormatting.GRAY + "Level: ", "", false, yOffset, stack, opacity);
        }
    }

    public void renderRealmTier(int yOffset, ItemStack stack, int opacity) {
        if (stack.getItem() == Items.NETHER_STAR) {
            renderDefault(TextFormatting.GRAY + "Tier: ", "", false, yOffset, stack, opacity);
        }
    }

    public void renderDefault(String starting, String contains, boolean removeStarting, int yOffset, ItemStack stack, int opacity) {
        String lore = GuiIngameUtils.getLore(stack, starting, contains, removeStarting);
        if (!lore.isEmpty()) GuiIngameUtils.renderText(lore, yOffset, opacity);
    }

    public static RenderOverlay instance() {
        return instance;
    }
}
