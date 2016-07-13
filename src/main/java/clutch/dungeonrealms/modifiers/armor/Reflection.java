package clutch.dungeonrealms.modifiers.armor;

import clutch.dungeonrealms.modifiers.Modifier;

public class Reflection extends Modifier {

    private int reflection = 0;

    public Reflection(int reflection) {
        super("reflection", "REFLECTION");
        this.reflection = reflection;
    }

    public int getReflection() {
        return reflection;
    }
}
