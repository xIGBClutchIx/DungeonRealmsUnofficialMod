package clutch.dungeonrealms.attributes.armor.resistance;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class PoisonResistance extends Attribute {

    private int poisonResistance = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.poisonResistance = ArmorUtils.getInt(stack, "poisonResistance");
    }

    @Override
    public int getCompareValue() {
        return poisonResistance;
    }
}
