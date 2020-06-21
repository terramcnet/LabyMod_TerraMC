package net.terramc.addon.modules;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;

public class GameRankModule extends SimpleModule {

    @Override
    public String getDisplayName() {
        return "Game-Rang";
    }

    @Override
    public String getControlName() {
        return "Game-Rang";
    }

    @Override
    public String getDisplayValue() {
        return TerraMCnetServer.getGameRank();
    }

    @Override
    public String getDefaultValue() {
        return "#0";
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.DIAMOND_SWORD);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "Game-Rang";
    }

    @Override
    public String getDescription() {
        return "Zeigt dir deinen aktuellen Rang in einem Spielmodus auf TerraMC.net an";
    }

    @Override
    public int getSortingId() {
        return 2;
    }

    @Override
    public ModuleCategory getCategory() {
        return Main.TERRAMCNET_CATEGORY;
    }

    @Override
    public boolean isShown() {
        return Main.enabled & Main.displayGameRank & TerraMCnetServer.getGameRank() != null;
    }
}
