package net.terramc.addon;

import net.labymod.api.events.TabListEvent;
import net.labymod.main.LabyMod;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;
import net.terramc.addon.utils.ReportData;

import java.util.ArrayList;
import java.util.List;

public class TerraMCnetServer extends Server {

    private static String rank = "Spieler";

    // Normal functions

    private static String gameRank = null;
    private static int coins;

    // VIP functions

    private static String nickName = null;

    //

    private static boolean inRound = false;
    private static boolean ggSent = false;

    // Staff functions

    private static List<ReportData> reports = new ArrayList<>();

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

            if(formatted.contains("§7Die Runde wurde beendet.") || formatted.contains("§7The round ended.")) {
                if(Main.autoGGEnabled & (Main.isStaff() || Main.isPremium())) {
                    if(inRound & !ggSent) {
                        ggSent = true;
                        if(nickName != null) {
                            Minecraft.getMinecraft().thePlayer.sendChatMessage("GG");
                        } else {
                            if(Main.isStaff() || getRank().equals("Terra") || getRank().equals("YouTuber+")) {
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("&8&k|&7&k|&r &eGG &7&k|&8&k|");
                            } else {
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("GG");
                            }
                        }
                    }
                }
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

    public static void resetValues() {
        nickName = null;
        gameRank = null;
        inRound = false;
        ggSent = false;
    }

    public static void setGameRank(String value) {
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

    public static String getRank() {
        return rank;
    }

    public static void setRank(String value) {
        rank = value;
    }

    public static void setInRound(boolean status) {
        inRound = status;
    }

    public static List<ReportData> getReports() {
        return reports;
    }

    public static void checkUpdate(String version) {
        if(!version.equals(Main.addonVersion)) {
            LabyMod labyMod = LabyMod.getInstance();
            labyMod.displayMessageInChat("§a§lTerraMC §7§lLabyMod-Addon");
            labyMod.displayMessageInChat("§7Es ist eine neue Version §8[§e" + version + "§8] §7verfügbar!");
            labyMod.displayMessageInChat("");
            if(Main.versionChanges.size() > 0) {
                labyMod.displayMessageInChat("§7Was ist neu in der Version?§8:");
                for(String entry : Main.versionChanges) {
                    labyMod.displayMessageInChat("§8» §7" + entry);
                }
                labyMod.displayMessageInChat("");
            }
            labyMod.displayMessageInChat("§7Du kannst sie dir unter §ehttps://terramc.net/laby §7herunterladen.");
        }
    }

}
