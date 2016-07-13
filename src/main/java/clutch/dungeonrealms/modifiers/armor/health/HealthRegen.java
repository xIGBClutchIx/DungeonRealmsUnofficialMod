package clutch.dungeonrealms.modifiers.armor.health;

import clutch.dungeonrealms.modifiers.Modifier;

public class HealthRegen extends Modifier {

    private int healthRegen = 0;

    public HealthRegen(int healthRegen) {
        super("healthRegen", "HP REGEN");
        this.healthRegen = healthRegen;
    }

    public int getHealthRegen() {
        return healthRegen;
    }
}
