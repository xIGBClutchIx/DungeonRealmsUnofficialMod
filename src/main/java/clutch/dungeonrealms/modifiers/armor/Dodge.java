package clutch.dungeonrealms.modifiers.armor;

import clutch.dungeonrealms.modifiers.Modifier;

public class Dodge extends Modifier {

    private int dodge = 0;

    public Dodge(int dodge) {
        super("dodge", "DODGE");
        this.dodge = dodge;
    }

    public int getDodge() {
        return dodge;
    }
}
