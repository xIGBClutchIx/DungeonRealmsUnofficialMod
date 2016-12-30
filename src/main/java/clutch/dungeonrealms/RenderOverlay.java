package clutch.dungeonrealms;

import clutch.dungeonrealms.reflection.ReflectionGuiIngame;
import clutch.dungeonrealms.utils.ItemStackUtils;
import clutch.dungeonrealms.utils.ProfessionsUtils;
import clutch.dungeonrealms.utils.RealmRuneUtils;
import clutch.dungeonrealms.utils.RenderUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
                int opacity = (int)((float)remainingHighlight * 256.0F / 10.0F);
                if (opacity > 255) opacity = 255;
                if (opacity > 0) {
                    // Pickaxe/Fishing Rod exp level and bar
                    if (ProfessionsUtils.isProfessionItem(stack)) {
                        // If does not have nbt tags then return
                        if (!stack.hasTagCompound()) return;
                        // Exp info
                        String xpBar = ProfessionsUtils.getXPBar(stack);
                        int expOffset = -10;
                        // Render exp and bar if able to and offset
                        if (!xpBar.isEmpty()) {
                            RenderUtils.renderText(xpBar, expOffset, opacity);
                            expOffset = expOffset - 10;
                        }
                        // Render Level
                        int level = ProfessionsUtils.getLevel(stack);
                        if (level != 0) {
                            RenderUtils.renderText(TextFormatting.GRAY + "Level: " + ItemStackUtils.getColor(stack) + level, expOffset, opacity);
                        }
                    }
                    // Realm portal rune tier
                    if (RealmRuneUtils.isRealmRune(stack)) {
                        // If does not have nbt tags then return
                        if (!stack.hasTagCompound()) return;
                        String realmTier = ItemStackUtils.getLore(stack, TextFormatting.GRAY + "Tier: ", "", false);
                        if (!realmTier.trim().isEmpty()) {
                            RenderUtils.renderText(realmTier, -10, opacity);
                        }
                    }
                }
            }
        }
    }
}
