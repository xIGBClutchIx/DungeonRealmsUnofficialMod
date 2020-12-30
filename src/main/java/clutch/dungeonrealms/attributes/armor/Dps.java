package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

import java.util.List;

public class Dps extends Attribute {

    private int dpsMax = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        List<Integer> dpsInfo = ArmorUtils.getIntListFromList(stack, getCompare());
        if (dpsInfo.size() >= 2) this.dpsMax = dpsInfo.get(1);
    }

    @Override
    public int getCompareValue() {
        return dpsMax;
    }

    @Override
    public String getCompare() {
        return "DPS";
    }

    @Override
    public String getTooltipName() {
        return "DPS";
    }
}
