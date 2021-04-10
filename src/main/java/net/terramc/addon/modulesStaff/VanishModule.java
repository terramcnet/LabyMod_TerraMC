package net.terramc.addon.modulesStaff;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleTextModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;
import net.terramc.addon.utils.StaffSettings;

public class VanishModule extends SimpleTextModule {

    @Override
    public String[] getValues() {
        if(Main.isStaff()) {
            return new String[] {TerraMCnetServer.isVanish() ? "§aAktiv" : "§cInaktiv", TerraMCnetServer.isAutoVanish() ? "§aAktiv" : "§cInaktiv"};
        }
        return new String[0];
    }

    @Override
    public String[] getDefaultValues() {
        if(Main.isStaff()) {
            return new String[] {"§4X", "§4X"};
        }
        return new String[0];
    }

    @Override
    public String[] getKeys() {
        if(Main.isStaff()) {
            return new String[] {"Vanish", "Auto. Vanish"};
        }
        return new String[0];
    }

    @Override
    public String[] getDefaultKeys() {
        if(Main.isStaff()) {
            return new String[] {"Vanish", "Auto. Vanish"};
        }
        return new String[0];
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.QUARTZ);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getControlName() {
        return "Vanish-Status";
    }

    @Override
    public String getSettingName() {
        return "Vanish-Status";
    }

    @Override
    public String getDescription() {
        return "Zeigt den aktuellen Vanish-Status";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public ModuleCategory getCategory() {
        return Main.STAFF_CATEGORY;
    }

    @Override
    public boolean isShown() {
        return Main.enabled & Main.isStaff() & StaffSettings.showVanishStatus;
    }
}
