package clutch.dungeonrealms.attributes.armor.resistance;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class FireResistance extends Attribute {

    private int fireResistance = 0;

    public FireResistance() {
        super("FIRE RESISTANCE");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.fireResistance = ArmorUtils.getInt(stack, "fireResistance");
    }

    public int getFireResistance() {
        return fireResistance;
    }
}
