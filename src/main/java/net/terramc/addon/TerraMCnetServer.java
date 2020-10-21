package net.terramc.addon;

import net.labymod.api.events.TabListEvent;
import net.labymod.main.LabyMod;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;

import java.util.List;

public class TerraMCnetServer extends Server {

    // Normal functions

    private static String gameRank;
    private static int coins;

    // VIP functions

    private static String nickName = null;

    TerraMCnetServer() {
        super("terramc", "terramc.net");
    }


    @Override
    public void addModuleLines(List<DisplayLine> lines) {
        super.addModuleLines(lines);
    }

    @Override
    public void onJoin(ServerData serverData) {
        resetValues();
    }

    @Override
    public ChatDisplayAction handleChatMessage(String clean, String formatted) {
        try {
            if((Main.filterPrivateMessages) & (clean.toLowerCase().contains(LabyMod.getInstance().getPlayerName().toLowerCase())) &
                    (clean.startsWith("▎▏ Freunde » ") & (clean.contains(" » ")))) {
                return ChatDisplayAction.SWAP;
            }

            String nickPrefix = "▎▏ Nick » ";
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
    public void loadConfig() {}

    @Override
    public void fillSubSettings(List<SettingsElement> list) {}

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

    public static int getCoins() {
        return coins;
    }

    public static String getGameRank() {
        return gameRank;
    }

    public static String getNickName() {
        return nickName;
    }

    public static void checkUpdate(String version) {
        if(!version.equals(Main.addonVersion)) {
            LabyMod labyMod = LabyMod.getInstance();
            labyMod.displayMessageInChat("§a§lTerraMC §7§lLabyMod-Addon");
            labyMod.displayMessageInChat("§7Es ist eine neue Version §8[§e" + version + "§8] §7verfügbar!");
            labyMod.displayMessageInChat("");
            labyMod.displayMessageInChat("§7Du kannst sie dir unter §ehttps://terramc.net/laby §7herunterladen.");
        }
    }

}
