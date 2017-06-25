package clutch.dungeonrealms.attributes.armor.stats;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Strength extends Attribute {

    private int strength = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.strength = ArmorUtils.getInt(stack, "strength");
    }

    @Override
    public int getCompareValue() {
        return strength;
    }
}
