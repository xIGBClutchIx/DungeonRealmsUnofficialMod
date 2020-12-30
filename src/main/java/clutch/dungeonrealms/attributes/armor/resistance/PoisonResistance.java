package clutch.dungeonrealms.attributes.armor.resistance;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class PoisonResistance extends Attribute {

    private int poisonResistance = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.poisonResistance = ArmorUtils.getIntListFromList(stack, getCompare()).get(0);
    }

    @Override
    public int getCompareValue() {
        return poisonResistance;
    }

    @Override
    public String getCompare() {
        return "POISON_RESISTANCE";
    }

    @Override
    public String getTooltipName() {
        return "POISON RESISTANCE";
    }
}
