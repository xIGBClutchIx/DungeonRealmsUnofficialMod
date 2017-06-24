package clutch.dungeonrealms.attributes.armor.stats;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Dexterity extends Attribute {

    private int dexterity = 0;

    public Dexterity() {
        super("DEX");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.dexterity = ArmorUtils.getInt(stack, "dexterity");
    }

    public int getDexterity() {
        return dexterity;
    }
}
