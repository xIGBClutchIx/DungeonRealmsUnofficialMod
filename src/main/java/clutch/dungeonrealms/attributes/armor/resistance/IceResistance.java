package clutch.dungeonrealms.attributes.armor.resistance;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class IceResistance extends Attribute {

    private int iceResistance = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.iceResistance = ArmorUtils.getIntListFromList(stack, getCompare()).get(0);
    }

    @Override
    public int getCompareValue() {
        return iceResistance;
    }

    @Override
    public String getCompare() {
        return "ICE_RESISTANCE";
    }

    @Override
    public String getTooltipName() {
        return "ICE RESISTANCE";
    }
}
