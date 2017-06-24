package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

import java.util.List;

public class Dps extends Attribute {

    private int dpsMin = 0;
    private int dpsMax = 0;

    public Dps() {
        super("DPS");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        List<Integer> dpsInfo = ArmorUtils.getIntListFromList(stack, "dps");
        if (dpsInfo.size() >= 1) this.dpsMin = dpsInfo.get(0);
        if (dpsInfo.size() >= 2) this.dpsMax = dpsInfo.get(1);
    }

    public int getDpsMin() {
        return dpsMin;
    }

    public int getDpsMax() {
        return dpsMax;
    }
}
