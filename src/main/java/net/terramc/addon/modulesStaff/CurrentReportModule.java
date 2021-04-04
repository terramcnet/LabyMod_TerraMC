package net.terramc.addon.modulesStaff;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleTextModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;
import net.terramc.addon.utils.CurrentReportData;
import net.terramc.addon.utils.StaffSettings;

public class CurrentReportModule extends SimpleTextModule {

    @Override
    public String[] getValues() {
        if(TerraMCnetServer.getCurrentReportData() != null) {
            CurrentReportData reportData = TerraMCnetServer.getCurrentReportData();
            return new String[] {reportData.getPlayer(), reportData.getReportedBy(), reportData.getReason(), String.valueOf(reportData.getId()), ModColor.RED + "/rpf"};
        }
        return new String[0];
    }

    @Override
    public String[] getDefaultValues() {
        return new String[0];
    }

    @Override
    public String[] getKeys() {
        if(TerraMCnetServer.getCurrentReportData() != null) {
            return new String[] {"Reportet", "Von", "Grund", "ID", "Abschließen?"};
        }
        return new String[0];
    }

    @Override
    public String[] getDefaultKeys() {
        return new String[0];
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.REDSTONE);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getControlName() {
        return "Aktueller Report";
    }

    @Override
    public String getSettingName() {
        return "Aktueller Report";
    }

    @Override
    public String getDescription() {
        return "Zeigt Infos zum Report, den du aktuell bearbeitest.";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public ModuleCategory getCategory() {
        return Main.TERRAMCNET_STAFF_CATEGORY;
    }

    @Override
    public boolean isShown() {
        return Main.enabled & Main.isStaff() & StaffSettings.showCurrentReportData & TerraMCnetServer.getCurrentReportData() != null;
    }
}
