package net.terramc.addon;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.SettingsElement;

import java.util.List;

public class Main extends LabyModAddon {

    //private static ModuleCategory TERRAMCNET_CATEGORY;

    public static String Prefix = "§7•§8● §aTerraMC.net §8┃ ";

    @Override
    public void onEnable() {
        //ModuleCategoryRegistry.loadCategory(TERRAMCNET_CATEGORY = new ModuleCategory("TerraMCnet", true, new ControlElement.IconData("terramcnet/textures/logo.png")));
        this.getApi().registerServerSupport(this, new TerraMCnetServer());
        this.getApi().getEventManager().register(new ServerMessageListener());
        this.getApi().getEventManager().register(new MessageSendListener());

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onDisable() {

    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }

    static boolean isStaff() {
        String rank = TerraMCnetServer.getRank();
        return rank.equals("Inhaber") || rank.equals("Admin") || rank.equals("SrDev") || rank.equals("Dev") ||
                rank.equals("Content") || rank.equals("SrMod") || rank.equals("Mod") || rank.equals("SrSup") ||
                rank.equals("Sup") || rank.equals("Designer") ||rank.equals("Builder");
    }
}
