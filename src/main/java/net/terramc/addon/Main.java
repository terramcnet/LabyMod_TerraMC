package net.terramc.addon;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.labymod.api.LabyModAPI;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.*;
import net.labymod.utils.Material;
import net.minecraft.util.ResourceLocation;
import net.terramc.addon.elements.CustomBooleanElement;
import net.terramc.addon.elements.CustomKeyElement;
import net.terramc.addon.listener.KeyboardListener;
import net.terramc.addon.listener.ServerMessageListener;
import net.terramc.addon.modules.*;
import net.terramc.addon.modulesStaff.*;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class Main extends LabyModAddon {

    private static Main instance;

    public static boolean filterPrivateMessages;

    public static boolean displayGameRank;
    public static boolean displayNickName;
    public static boolean displayCoins;

    static boolean autoGGEnabled;

    public static ModuleCategory USER_CATEGORY;
    public static ModuleCategory STAFF_CATEGORY;

    private static int guiKey = 0;

    public static boolean enabled;

    public static String addonVersion = "3.0";

    @Override
    public LabyModAPI getApi() {
        return super.getApi();
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        ModuleCategoryRegistry.loadCategory(USER_CATEGORY = new ModuleCategory("TerraMC", true, new ControlElement.IconData(new ResourceLocation("terramc/textures/Module.png"))));
        ModuleCategoryRegistry.loadCategory(STAFF_CATEGORY = new ModuleCategory("TerraMC-Team", false, new ControlElement.IconData(new ResourceLocation("terramc/textures/Module.png"))));
        this.getApi().registerServerSupport(this, new TerraMCnetServer());
        this.getApi().getEventManager().register(new ServerMessageListener());

        this.getApi().registerModule(new CoinsModule());
        this.getApi().registerModule(new GameRankModule());
        this.getApi().registerModule(new NickModule());

        this.getApi().registerModule(new CurrentReportModule());
        this.getApi().registerModule(new ReportsModule());
        this.getApi().registerModule(new VanishModule());
        this.getApi().registerModule(new ServerStatusModule());

        this.getApi().registerForgeListener(new KeyboardListener());

        //Tabs.getTabUpdateListener().add(map -> map.put("TerraMC", new Class[]{TerraGUI.class}));

        this.getApi().getEventManager().registerOnJoin(serverData -> enabled = serverData.getIp().equalsIgnoreCase("terramc.net"));
        this.getApi().getEventManager().registerOnQuit(serverData -> {
            if(serverData != null) {
                TerraMCnetServer.updateMessageSent = false;
                TerraMCnetServer.getReports().clear();
                TerraMCnetServer.getSupports().clear();
                TerraMCnetServer.resetValues();
                TerraMCnetServer.setCurrentReportData(null);
            }
        });
    }

    @Override
    public void loadConfig() {
        guiKey = (this.getConfig().has("guiKey") ? this.getConfig().get("guiKey").getAsInt() : Keyboard.KEY_MULTIPLY);
        filterPrivateMessages = !this.getConfig().has("filterPrivateMessages") || this.getConfig().get("filterPrivateMessages").getAsBoolean();
        displayGameRank = !this.getConfig().has("displayGameRank") || this.getConfig().get("displayGameRank").getAsBoolean();
        displayCoins = !this.getConfig().has("displayCoins") || this.getConfig().get("displayCoins").getAsBoolean();
        displayNickName = !this.getConfig().has("displayNickName") || this.getConfig().get("displayNickName").getAsBoolean();
        autoGGEnabled = !this.getConfig().has("autoGG") || this.getConfig().get("autoGG").getAsBoolean();
    }

    public void addConfigEntry(String name, Boolean value) {
        this.getConfig().addProperty(name, value);
        this.saveConfig();
    }

    public JsonObject getConfigObject() {
        return this.getConfig();
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

        list.add(new HeaderElement("§7§l§o▎§8§l§o▏ §7GUI"));

        /*list.add(new KeyElement("§8» §fTerra-GUI", new ControlElement.IconData(new ResourceLocation("labymod/textures/settings/category/ingame_gui.png")), guiKey, integer -> {
            if(integer == -1) {
                return;
            }
            guiKey = integer;
            Main.this.getConfig().addProperty("guiKey", integer);
            Main.this.saveConfig();
        }));*/

        list.add(new CustomKeyElement("§8» §fTerra-GUI", new ControlElement.IconData(new ResourceLocation("labymod/textures/settings/category/ingame_gui.png")), guiKey, integer -> {
            if(integer == -1) {
                return;
            }
            guiKey = integer;
            Main.this.getConfig().addProperty("guiKey", integer);
            Main.this.saveConfig();
        }, null, "§7Taste zum öffnen", "§7der Terra-GUI."));

        list.add(new HeaderElement("§7§l§o▎§8§l§o▏ §7Allgemeine Funktionen"));

        list.add(new CustomBooleanElement("§8» §fPrivate Nachrichten filtern", new ControlElement.IconData(Material.SIGN), status -> {
            filterPrivateMessages = status;
            addConfigEntry("filterPrivateMessages", status);
        }, filterPrivateMessages, null, "§7Zeigt Private Nachrichten", "§7im Second-Chat."));

        /*list.add(new BooleanElement("§8» §fPrivate Nachrichten filtern", new ControlElement.IconData(Material.SIGN), status -> {
            filterPrivateMessages = status;
            Main.this.getConfig().addProperty("filterPrivateMessages", status);
            Main.this.saveConfig();
        }, filterPrivateMessages));*/

        list.add(new CustomBooleanElement("§8» §fPlatzierung anzeigen", new ControlElement.IconData(Material.DIAMOND_SWORD), status -> {
            displayGameRank = status;
            addConfigEntry("displayGameRank", status);
        }, displayGameRank, null, "§7Zeigt deine aktuelle", "§7Platzierung im Spielmodus an."));

        /*list.add(new BooleanElement("§8» §fGame-Rang anzeigen", new ControlElement.IconData(Material.DIAMOND_SWORD), status -> {
            displayGameRank = status;
            Main.this.getConfig().addProperty("displayGameRank", status);
            Main.this.saveConfig();
        }, displayGameRank));*/

        list.add(new CustomBooleanElement("§8» §fCoins anzeigen", new ControlElement.IconData(Material.GOLD_INGOT), status -> {
            displayCoins = status;
            addConfigEntry("displayCoins", status);
        }, displayCoins, null, "§7Zeigt deine aktuellen", "§7Coins in der GUI an."));

        /*list.add(new BooleanElement("§8» §fCoins anzeigen", new ControlElement.IconData(Material.GOLD_INGOT), status -> {
            displayCoins = status;
            Main.this.getConfig().addProperty("displayCoins", status);
            Main.this.saveConfig();
        }, displayCoins));*/

        list.add(new HeaderElement("§7§l§o▎§8§l§o▏ §6Premium Funktionen"));

        /*list.add(new BooleanElement("§8» §eAutoGG", new ControlElement.IconData(Material.NETHER_STAR), status -> {
            autoGGEnabled = status;
            Main.this.getConfig().addProperty("autoGG", status);
            Main.this.saveConfig();
        }, autoGGEnabled));*/

        list.add(new CustomBooleanElement("§8» §eAutoGG", new ControlElement.IconData(Material.NETHER_STAR), status -> {

        }, autoGGEnabled, null, "§7Aktivert/Deaktiviert die" , "§7AutoGG-Funktion."));

        list.add(new HeaderElement("§7§l§o▎§8§l§o▏ §5VIP Funktionen"));

        /*list.add(new BooleanElement("§8» §dNick anzeigen", new ControlElement.IconData(Material.NAME_TAG), status -> {
            displayNickName = status;
            Main.this.getConfig().addProperty("displayNickName", status);
            Main.this.saveConfig();
        }, displayNickName));*/

        list.add(new CustomBooleanElement("§8» §dNick anzeigen", new ControlElement.IconData(Material.NAME_TAG), status -> {

        }, displayNickName, null, "§7Zeigt deinen aktuellen", "§7Nickname in der GUI an."));

    }

    public static boolean isAdmin() {
        String rank = TerraMCnetServer.getRank();
        if(rank == null) {
            return false;
        }
        return rank.equals("Inhaber") || rank.equals("Admin");
    }

    public static boolean isStaff() {
        String rank = TerraMCnetServer.getRank();
        if(rank == null) {
            return false;
        }
        return rank.equals("Inhaber") || rank.equals("Admin") || rank.equals("SrDev") || rank.equals("Dev") ||
                rank.equals("Content") || rank.equals("SrMod") || rank.equals("Mod") || rank.equals("SrSup") ||
                rank.equals("Sup") || rank.equals("Designer") ||rank.equals("Builder");
    }

    public static boolean isPremium() {
        String rank = TerraMCnetServer.getRank();
        if(rank == null) {
            return false;
        }
        return rank.equals("Premium") || rank.equals("Hydra") || rank.equals("Terra") || rank.equals("Premium+") ||
                rank.equals("YouTuber") || rank.equals("YouTuber+");
    }

    public static boolean canControlCloud() {
        String rank = TerraMCnetServer.getRank();
        if(rank == null) {
            return false;
        }
        return rank.equals("Inhaber") || rank.equals("Admin") || rank.equals("SrDev") || rank.equals("Dev") || rank.equals("SrMod");
    }

    public static boolean canFullyControlCloud() {
        return isAdmin();
    }

    public static int getGuiKey() {
        return guiKey;
    }

    public static void sendMessageToServer(String messageKey, JsonElement message) {
        LabyMod.getInstance().getLabyModAPI().sendJsonMessageToServer(messageKey, message);
    }

}
