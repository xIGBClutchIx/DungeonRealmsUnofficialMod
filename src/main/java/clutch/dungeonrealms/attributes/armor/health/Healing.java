package clutch.dungeonrealms.attributes.armor.health;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Healing extends Attribute {

    private int healing = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.healing = ArmorUtils.getIntListFromList(stack, getCompare()).get(0);
    }

    @Override
    public int getCompareValue() {
        return healing;
    }

    @Override
    public String getCompare() {
        return "HEALING";
    }

    @Override
    public String getTooltipName() {
        return "HEALING";
    }
}
