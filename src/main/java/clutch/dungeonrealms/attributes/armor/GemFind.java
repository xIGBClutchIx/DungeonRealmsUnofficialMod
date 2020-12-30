package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class GemFind extends Attribute {

    private int gemFind = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.gemFind = ArmorUtils.getIntListFromList(stack, getCompare()).get(0);
    }

    @Override
    public int getCompareValue() {
        return gemFind;
    }

    @Override
    public String getCompare() {
        return "GEM_FIND";
    }

    @Override
    public String getTooltipName() {
        return "GEM FIND";
    }
}
