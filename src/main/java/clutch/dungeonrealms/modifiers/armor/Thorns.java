package clutch.dungeonrealms.modifiers.armor;

import clutch.dungeonrealms.modifiers.Modifier;

public class Thorns extends Modifier {

    private int thorns = 0;

    public Thorns(int thorns) {
        super("thorns", "THORNS");
        this.thorns = thorns;
    }

    public int getThorns() {
        return thorns;
    }
}
