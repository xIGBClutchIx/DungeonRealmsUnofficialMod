package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

import java.util.List;

public class Armor extends Attribute {

    private int armorMin = 0;
    private int armorMax = 0;

    public Armor() {
        super("ARMOR");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        List<Integer> armorInfo = ArmorUtils.getIntListFromList(stack, "armor");
        if (armorInfo.size() >= 1) this.armorMin = armorInfo.get(0);
        if (armorInfo.size() >= 2) this.armorMax = armorInfo.get(1);
    }

    public int getArmorMin() {
        return armorMin;
    }

    public int getArmorMax() {
        return armorMax;
    }
}
