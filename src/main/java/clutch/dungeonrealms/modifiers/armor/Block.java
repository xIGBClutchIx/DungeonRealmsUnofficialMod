package clutch.dungeonrealms.modifiers.armor;

import clutch.dungeonrealms.modifiers.Modifier;

public class Block extends Modifier {

    private int block = 0;

    public Block(int block) {
        super("block", "BLOCK");
        this.block = block;
    }

    public int getBlock() {
        return block;
    }
}
