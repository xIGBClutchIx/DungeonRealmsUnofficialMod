package clutch.dungeonrealms.modifiers.armor;

import clutch.dungeonrealms.modifiers.Modifier;

public class Armor extends Modifier {

    private int armorMin = 0;
    private int armorMax = 0;

    public Armor(int armorMin, int armorMax) {
        super("armor", "ARMOR");
        this.armorMin = armorMin;
        this.armorMax = armorMax;
    }

    public int getArmorMin() {
        return armorMin;
    }

    public int getArmorMax() {
        return armorMax;
    }
}
