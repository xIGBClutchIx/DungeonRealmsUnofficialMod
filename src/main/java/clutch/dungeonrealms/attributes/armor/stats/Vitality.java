package clutch.dungeonrealms.attributes.armor.stats;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Vitality extends Attribute {

    private int vitality = 0;

    public Vitality() {
        super("VIT");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.vitality = ArmorUtils.getInt(stack, "vitality");
    }

    public int getVitality() {
        return vitality;
    }
}
