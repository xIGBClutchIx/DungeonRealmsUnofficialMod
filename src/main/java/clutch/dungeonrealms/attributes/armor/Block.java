package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class Block extends Attribute {

    private int block = 0;

    public Block() {
        super("BLOCK");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.block = ArmorUtils.getInt(stack, "block");
    }

    public int getBlock() {
        return block;
    }
}
