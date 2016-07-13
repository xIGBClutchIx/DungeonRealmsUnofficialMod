package clutch.dungeonrealms.modifiers.armor;

import clutch.dungeonrealms.modifiers.Modifier;

public class EnergyRegen extends Modifier {

    private int energyRegen = 0;

    public EnergyRegen(int energyRegen) {
        super("energyRegen", "ENERGY REGEN");
        this.energyRegen = energyRegen;
    }

    public int getEnergyRegen() {
        return energyRegen;
    }
}
