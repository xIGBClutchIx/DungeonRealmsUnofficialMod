package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

/**
 * @author Clutch
 * @since 7/4/2017
 */
public class GemFind extends Attribute {

    private int gemFind = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.gemFind = ArmorUtils.getInt(stack, "gemFind");
    }

    @Override
    public int getCompareValue() {
        return gemFind;
    }
}
