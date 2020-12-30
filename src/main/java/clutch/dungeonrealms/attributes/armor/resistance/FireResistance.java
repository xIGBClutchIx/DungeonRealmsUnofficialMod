package clutch.dungeonrealms.attributes.armor.resistance;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class FireResistance extends Attribute {

    private int fireResistance = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.fireResistance = ArmorUtils.getIntListFromList(stack, getCompare()).get(0);
    }

    @Override
    public int getCompareValue() {
        return fireResistance;
    }

    @Override
    public String getCompare() {
        return "FIRE_RESISTANCE";
    }

    @Override
    public String getTooltipName() {
        return "FIRE RESISTANCE";
    }
}
