package clutch.dungeonrealms.attributes.armor;

import clutch.dungeonrealms.attributes.Attribute;
import clutch.dungeonrealms.utils.ArmorUtils;
import net.minecraft.item.ItemStack;

public class ItemFind extends Attribute {

    private int itemFind = 0;

    public ItemFind() {
        super("ITEM FIND");
    }

    @Override
    public void updateInfo(ItemStack stack) {
        this.itemFind = ArmorUtils.getInt(stack, "itemFind");
    }

    public int getItemFind() {
        return itemFind;
    }
}
