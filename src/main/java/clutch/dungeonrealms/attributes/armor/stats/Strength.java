package clutch.dungeonrealms.attributes.armor.stats;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Strength extends Attribute {

    private int strength = 0;

    public Strength() {
        super("STR");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.strength = ArmorUtils.getInt(stack, "strength");
    }

    public int getStrength() {
        return strength;
    }
}
