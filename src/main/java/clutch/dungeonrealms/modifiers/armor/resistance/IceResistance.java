package clutch.dungeonrealms.modifiers.armor.resistance;

import clutch.dungeonrealms.modifiers.Modifier;

public class IceResistance extends Modifier {

    private int iceResistance = 0;

    public IceResistance(int iceResistance) {
        super("iceResistance", "ICE RESISTANCE");
        this.iceResistance = iceResistance;
    }

    public int getIceResistance() {
        return iceResistance;
    }
}
