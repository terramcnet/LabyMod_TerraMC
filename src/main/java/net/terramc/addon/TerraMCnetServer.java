package net.terramc.addon;

import net.labymod.api.events.TabListEvent;
import net.labymod.ingamegui.moduletypes.ColoredTextModule;
import net.labymod.main.LabyMod;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;

import java.util.Collections;
import java.util.List;

public class TerraMCnetServer extends Server {

    // Normal functions

    private boolean filterPrivateMessages;
    private boolean displayGameRank;
    private boolean displayCoins;

    private static String gameRank;
    private static int coins;

    // VIP functions

    private boolean displayNickname;

    private static String nickName = null;

    // Staff functions

    private boolean displayReports;
    private boolean displayVanish;
    private boolean displayAutoVanish;

    private static int openReports = 0;

    private static String rank = null;
    private static boolean vanish;
    private static boolean autoVanish;

    TerraMCnetServer() {
        super("terramc", "terramc.net");
    }

    @Override
    public void addModuleLines(List<DisplayLine> lines) {
        super.addModuleLines(lines);
        try {
            if((nickName != null) & (this.displayNickname)) {
                lines.add(new Server.DisplayLine("Nick", Collections.singletonList(ColoredTextModule.Text.getText(nickName))));
            }

            if((gameRank != null) & (this.displayGameRank)) {
                lines.add(new Server.DisplayLine("Rang", Collections.singletonList(ColoredTextModule.Text.getText(gameRank))));
            }

            if(this.displayCoins) {
                lines.add(new Server.DisplayLine("GCoins",Collections.singletonList(ColoredTextModule.Text.getText(String.valueOf(coins)))));
            }

            if((openReports > 0) & (Main.isStaff()) & (this.displayReports)) {
                lines.add(new Server.DisplayLine("Offene Reports", Collections.singletonList(ColoredTextModule.Text.getText(String.valueOf(openReports)))));
            }

            if(this.displayVanish & Main.isStaff()) {
                String status = vanish ? "§aAktiv §8[§a✔§8]" : "§cDeaktiviert §8[§c✖§8]";
                lines.add(new Server.DisplayLine("Vanish", Collections.singletonList(ColoredTextModule.Text.getText(status))));
            }

            if(this.displayAutoVanish & Main.isStaff()) {
                String status = autoVanish ? "§aAktiv §8[§a✔§8]" : "§cDeaktiviert §8[§c✖§8]";
                lines.add(new Server.DisplayLine("Auto. Vanish", Collections.singletonList(ColoredTextModule.Text.getText(status))));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onJoin(ServerData serverData) {
        resetValues();
    }

    @Override
    public ChatDisplayAction handleChatMessage(String clean, String formatted) {
        try {
            if((this.filterPrivateMessages) & (clean.toLowerCase().contains(LabyMod.getInstance().getPlayerName().toLowerCase())) &
                    (clean.startsWith("•● Freunde ┃ ") & (clean.contains(" » ")))) {
                return ChatDisplayAction.SWAP;
            }

            String nickPrefix = "•● Nick ┃ ";
            if(clean.startsWith(nickPrefix + "Du spielst nun als: ")) {
                nickName = clean.replace(nickPrefix + "Du spielst nun als: ", "");
            }
            if(clean.startsWith(nickPrefix + "You are now playing as:")) {
                nickName = clean.replace(nickPrefix + "You are now playing as: ", "");
            }
            if(clean.startsWith(nickPrefix + "Dein Nickname wurde zurückgesetzt.") ||
                    clean.startsWith(nickPrefix + "Your nickname has been reset.")) {
                nickName = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ChatDisplayAction.NORMAL;
    }

    @Override
    public void handlePluginMessage(String tag, PacketBuffer packetBuffer) {}

    @Override
    public void handleTabInfoMessage(TabListEvent.Type type, String formatted, String unformatted) {}

    @Override
    public void loadConfig() {
        this.displayNickname = getBooleanAttribute("displayNickname", true);
        this.filterPrivateMessages = getBooleanAttribute("filterPrivateMessages", false);
        this.displayGameRank = getBooleanAttribute("displayGameRank", false);
        this.displayCoins = getBooleanAttribute("displayCoins", false);

        this.displayReports = getBooleanAttribute("displayReports", false);
        this.displayVanish = getBooleanAttribute("displayVanish", false);
        this.displayAutoVanish = getBooleanAttribute("displayAutoVanish", false);
    }

    @Override
    public void fillSubSettings(List<SettingsElement> list) {
        list.add(new HeaderElement("§8•● §7Allgemeine Funktionen §8●•"));
        list.add(new BooleanElement("§7•§8● §fFilter Private Messages", this, new ControlElement.IconData(Material.SIGN), "filterPrivateMessages"));
        list.add(new BooleanElement("§7•§8● §fGame-Rang anzeigen", this, new ControlElement.IconData(Material.DIAMOND_SWORD), "displayGameRank"));
        list.add(new BooleanElement("§7•§8● §fCoins anzeigen", this, new ControlElement.IconData(Material.GOLD_INGOT), "displayCoins"));

        list.add(new HeaderElement("§8•● §5VIP Funktionen §8●•"));
        list.add(new BooleanElement("§7•§8● §fNick anzeigen", this, new ControlElement.IconData(Material.NAME_TAG), "displayNickname"));

        list.add(new HeaderElement("§8•● §aTeam Funktionen §8●•"));
        list.add(new BooleanElement("§7•§8● §fReports anzeigen", this, new ControlElement.IconData(Material.REDSTONE), "displayReports"));
        list.add(new BooleanElement("§7•§8● §fVanish Status", this, new ControlElement.IconData(Material.QUARTZ), "displayVanish"));
        list.add(new BooleanElement("§7•§8● §fAuto. Vanish Status", this, new ControlElement.IconData(Material.QUARTZ), "displayAutoVanish"));
    }

    static void resetValues() {
        nickName = null;
        gameRank = null;
    }

    static void setGameRank(String value) {
        gameRank = value;
    }

    public static void setCoins(int value) {
        coins = value;
    }

    static void setOpenReports(int value) {
        openReports = value;
    }

    static void setRank(String value) {
        rank = value;
    }

    static String getRank() {
        return rank;
    }

    public static void setVanish(boolean value) {
        vanish = value;
    }

    public static void setAutoVanish(boolean value) {
        autoVanish = value;
    }

}
