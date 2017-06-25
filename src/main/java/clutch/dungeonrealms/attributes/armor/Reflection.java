package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Reflection extends Attribute {

    private int reflection = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.reflection = ArmorUtils.getInt(stack, "reflection");
    }

    @Override
    public int getCompareValue() {
        return reflection;
    }
}
