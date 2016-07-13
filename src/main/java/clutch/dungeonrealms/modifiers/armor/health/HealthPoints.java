package clutch.dungeonrealms.modifiers.armor.health;

import clutch.dungeonrealms.modifiers.Modifier;

public class HealthPoints extends Modifier {

    private int healthPoints = 0;

    public HealthPoints(int healthPoints) {
        super("healthPoints", "HP");
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}