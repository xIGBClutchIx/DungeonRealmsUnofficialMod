package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class EnergyRegen extends Attribute {

    private int energyRegen = 0;

    public EnergyRegen() {
        super("ENERGY REGEN");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.energyRegen = ArmorUtils.getInt(stack, "energyRegen");
    }

    public int getEnergyRegen() {
        return energyRegen;
    }
}
