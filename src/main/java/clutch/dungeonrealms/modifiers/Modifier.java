package clutch.dungeonrealms.modifiers;

public class Modifier {

    private String name = "";
    private String displayName = "";

    public Modifier(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
