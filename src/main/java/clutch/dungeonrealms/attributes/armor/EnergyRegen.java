package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class EnergyRegen extends Attribute {

    private int energyRegen = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.energyRegen = ArmorUtils.getInt(stack, "energyRegen");
    }

    @Override
    public int getCompareValue() {
        return energyRegen;
    }
}
