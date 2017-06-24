package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Reflection extends Attribute {

    private int reflection = 0;

    public Reflection() {
        super("REFLECTION");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.reflection = ArmorUtils.getInt(stack, "reflection");
    }

    public int getReflection() {
        return reflection;
    }
}
