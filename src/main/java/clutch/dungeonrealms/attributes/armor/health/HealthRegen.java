package clutch.dungeonrealms.attributes.armor.health;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class HealthRegen extends Attribute {

    private int healthRegen = 0;

    public HealthRegen() {
        super("HP REGEN");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.healthRegen = ArmorUtils.getInt(stack, "healthRegen");
    }

    public int getHealthRegen() {
        return healthRegen;
    }
}
