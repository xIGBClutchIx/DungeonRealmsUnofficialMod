package clutch.dungeonrealms.attributes.armor.health;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class HealthPoints extends Attribute {

    private int healthPoints = 0;

    @Override
    public void updateInfo(ItemStack stack) {
        this.healthPoints = ArmorUtils.getIntListFromList(stack, getCompare()).get(0);
    }

    @Override
    public int getCompareValue() {
        return healthPoints;
    }

    @Override
    public String getCompare() {
        return "HEALTH_POINTS";
    }

    @Override
    public String getTooltipName() {
        return "HP";
    }
}