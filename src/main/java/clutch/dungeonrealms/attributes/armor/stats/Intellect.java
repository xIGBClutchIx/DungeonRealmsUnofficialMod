package clutch.dungeonrealms.attributes.armor.stats;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Intellect extends Attribute {

    private int intellect = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.intellect = ArmorUtils.getIntListFromList(stack, getCompare()).get(0);
    }

    @Override
    public int getCompareValue() {
        return intellect;
    }

    @Override
    public String getCompare() {
        return "INTELLECT";
    }

    @Override
    public String getTooltipName() {
        return "INT";
    }
}
