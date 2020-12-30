package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Dodge extends Attribute {

    private int dodge = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.dodge = ArmorUtils.getIntListFromList(stack, getCompare()).get(0);
    }

    @Override
    public int getCompareValue() {
        return dodge;
    }

    @Override
    public String getCompare() {
        return "DODGE";
    }

    @Override
    public String getTooltipName() {
        return "DODGE";
    }
}
