package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Thorns extends Attribute {

    private int thorns = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.thorns = ArmorUtils.getInt(stack, "thorns");
    }

    @Override
    public int getCompareValue() {
        return thorns;
    }
}
