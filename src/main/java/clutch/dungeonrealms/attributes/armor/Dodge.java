package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Dodge extends Attribute {

    private int dodge = 0;

    public Dodge() {
        super("DODGE");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.dodge = ArmorUtils.getInt(stack, "dodge");
    }

    public int getDodge() {
        return dodge;
    }
}
