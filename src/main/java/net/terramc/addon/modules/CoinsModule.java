package net.terramc.addon.modules;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;

public class CoinsModule extends SimpleModule {

    @Override
    public String getDisplayName() {
        return "GCoins";
    }

    @Override
    public String getControlName() {
        return "GCoins";
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(TerraMCnetServer.getCoins());
    }

    @Override
    public String getDefaultValue() {
        return "0";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.GOLD_INGOT);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "GCoins";
    }

    @Override
    public String getDescription() {
        return "Zeigt deine aktuellen GCoins auf TerraMC.net an";
    }

    @Override
    public int getSortingId() {
        return 1;
    }

    @Override
    public ModuleCategory getCategory() {
    return Main.USER_CATEGORY;
    }

    @Override
    public boolean isShown() {
        return Main.enabled & Main.displayCoins;
    }
}
