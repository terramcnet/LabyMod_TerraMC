package net.terramc.addon.modulesStaff;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleTextModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;
import net.terramc.addon.utils.StaffSettings;

public class ServerStatusModule extends SimpleTextModule {

    @Override
    public String[] getValues() {
        if(Main.isAdmin()) {
            return new String[] {TerraMCnetServer.getServerTps(), TerraMCnetServer.getCpuUsage(), TerraMCnetServer.getHeapUsage()};
        }
        return new String[0];
    }

    @Override
    public String[] getDefaultValues() {
        if(Main.isAdmin()) {
            return new String[] {"0.0", "0.0", "0.0"};
        }
        return new String[0];
    }

    @Override
    public String[] getKeys() {
        if(Main.isAdmin()) {
            return new String[] {"TPS", "CPU", "RAM"};
        }
        return new String[0];
    }

    @Override
    public String[] getDefaultKeys() {
        if(Main.isAdmin()) {
            return new String[] {"TPS", "CPU", "RAM"};
        }
        return new String[0];
    }

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.REDSTONE_TORCH_ON);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getControlName() {
        return "Server-Status";
    }

    @Override
    public String getSettingName() {
        return "ServerStatus";
    }

    @Override
    public String getDescription() {
        return null;
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
        return Main.enabled & Main.isAdmin() & StaffSettings.showServerStatus;
    }
}
