package clutch.dungeonrealms.attributes.armor.resistance;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class PoisonResistance extends Attribute {

    private int poisonResistance = 0;

    public PoisonResistance() {
        super("POISON RESISTANCE");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.poisonResistance = ArmorUtils.getInt(stack, "poisonResistance");
    }

    public int getPoisonResistance() {
        return poisonResistance;
    }
}
