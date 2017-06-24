package clutch.dungeonrealms.attributes.armor.stats;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Intellect extends Attribute {

    private int intellect = 0;

    public Intellect() {
        super("INT");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.intellect = ArmorUtils.getInt(stack, "intellect");
    }

    public int getIntellect() {
        return intellect;
    }
}
