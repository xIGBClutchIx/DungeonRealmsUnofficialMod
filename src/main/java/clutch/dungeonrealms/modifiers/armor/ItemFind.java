package clutch.dungeonrealms.modifiers.armor;

import clutch.dungeonrealms.modifiers.Modifier;

public class ItemFind extends Modifier {

    private int itemFind = 0;

    public ItemFind(int itemFind) {
        super("itemFind", "ITEM FIND");
        this.itemFind = itemFind;
    }

    public int getItemFind() {
        return itemFind;
    }
}
