package net.terramc.addon;

import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.labymod.utils.ServerData;
import net.minecraft.util.ResourceLocation;
import net.terramc.addon.modules.CoinsModule;
import net.terramc.addon.modules.GameRankModule;
import net.terramc.addon.modules.NickModule;

import java.util.List;

public class Main extends LabyModAddon {

    public static boolean filterPrivateMessages;

    public static boolean displayGameRank;
    public static boolean displayNickName;
    public static boolean displayCoins;

    public static ModuleCategory TERRAMCNET_CATEGORY;

    public static boolean enabled;

    public static String addonVersion = "2.2";

    @Override
    public void onEnable() {
        ModuleCategoryRegistry.loadCategory(TERRAMCNET_CATEGORY = new ModuleCategory("TerraMC", true, new ControlElement.IconData(new ResourceLocation("terramc/textures/Module.png"))));
        this.getApi().registerServerSupport(this, new TerraMCnetServer());
        this.getApi().getEventManager().register(new ServerMessageListener());

        this.getApi().registerModule(new CoinsModule());
        this.getApi().registerModule(new GameRankModule());
        this.getApi().registerModule(new NickModule());

        this.getApi().getEventManager().registerOnJoin(new Consumer<ServerData>() {
            @Override
            public void accept(ServerData serverData) {
                enabled = serverData.getIp().equalsIgnoreCase("terramc.net");
            }
        });
    }

    @Override
    public void loadConfig() {
        filterPrivateMessages = !this.getConfig().has("filterPrivateMessages") || this.getConfig().get("filterPrivateMessages").getAsBoolean();
        displayGameRank = !this.getConfig().has("displayGameRank") || this.getConfig().get("displayGameRank").getAsBoolean();
        displayCoins = !this.getConfig().has("displayCoins") || this.getConfig().get("displayCoins").getAsBoolean();
        displayNickName = !this.getConfig().has("displayNickName") || this.getConfig().get("displayNickName").getAsBoolean();
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        list.add(new HeaderElement("§8•● §7Allgemeine Funktionen §8●•"));

        list.add(new BooleanElement("§7•§8● §fPrivate Nachrichten filtern", new ControlElement.IconData(Material.SIGN), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean status) {
                filterPrivateMessages = status;
                Main.this.getConfig().addProperty("filterPrivateMessages", status);
                Main.this.saveConfig();
            }
        }, filterPrivateMessages));

        list.add(new BooleanElement("§7•§8● §fGame-Rang anzeigen", new ControlElement.IconData(Material.DIAMOND_SWORD), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean status) {
                displayGameRank = status;
                Main.this.getConfig().addProperty("displayGameRank", status);
                Main.this.saveConfig();
            }
        }, displayGameRank));

        list.add(new BooleanElement("§7•§8● §fCoins anzeigen", new ControlElement.IconData(Material.GOLD_INGOT), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean status) {
                displayCoins = status;
                Main.this.getConfig().addProperty("displayCoins", status);
                Main.this.saveConfig();
            }
        }, displayCoins));

        list.add(new HeaderElement("§8•● §5VIP Funktionen §8●•"));

        list.add(new BooleanElement("§7•§8● §fNick anzeigen", new ControlElement.IconData(Material.NAME_TAG), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean status) {
                displayNickName = status;
                Main.this.getConfig().addProperty("displayNickName", status);
                Main.this.saveConfig();
            }
        }, displayNickName));

    }

}
