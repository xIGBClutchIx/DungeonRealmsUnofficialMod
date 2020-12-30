package clutch.dungeonrealms.attributes.armor.resistance;


import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class ElementalResistance extends Attribute {

    private int elementalResistance = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.elementalResistance = ArmorUtils.getIntListFromList(stack, getCompare()).get(0);
    }

    @Override
    public int getCompareValue() {
        return elementalResistance;
    }

    @Override
    public String getCompare() {
        return "ELEMENTAL_RESISTANCE";
    }

    @Override
    public String getTooltipName() {
        return "ELEMENTAL RESISTANCE";
    }
}
