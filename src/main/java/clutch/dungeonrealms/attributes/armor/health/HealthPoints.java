package clutch.dungeonrealms.attributes.armor.health;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class HealthPoints extends Attribute {

    private int healthPoints = 0;

    public HealthPoints() {
        super("HP");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.healthPoints = ArmorUtils.getInt(stack, "healthPoints");
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}