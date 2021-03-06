package net.terramc.addon;

import net.labymod.api.events.TabListEvent;
import net.labymod.main.LabyMod;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;
import net.terramc.addon.utils.CurrentReportData;
import net.terramc.addon.utils.ReportData;
import net.terramc.addon.utils.StaffSettings;
import net.terramc.addon.utils.SupportData;

import java.util.ArrayList;
import java.util.List;

public class TerraMCnetServer extends Server {

    private static String rank = "Spieler";

    // Normal functions

    private static String gameRank = null;
    private static int coins = 0;

    private static String onlineTime = null;
    private static int joins = 0;

    // VIP functions

    private static String nickName = null;

    // Premium functions

    private static boolean inRound = false;
    private static boolean ggSent = false;

    // Staff functions

    private static List<ReportData> reports = new ArrayList<>();
    private static List<SupportData> supports = new ArrayList<>();
    private static CurrentReportData currentReportData = null;

    private static boolean vanish;
    private static boolean autoVanish;

    // Admin functions

    private static String serverTps;
    private static String cpuUsage;
    private static String heapUsage;

    //

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
            String nickPrefixOld = "•● Nick ┃ ";

            if(clean.startsWith(nickPrefix + "Du spielst nun als: ")) {
                nickName = clean.replace(nickPrefix + "Du spielst nun als: ", "");
            }
            if(clean.startsWith(nickPrefixOld + "Du spielst nun als: ")) {
                nickName = clean.replace(nickPrefixOld + "Du spielst nun als: ", "");
            }

            if(clean.startsWith(nickPrefix + "You are now playing as:")) {
                nickName = clean.replace(nickPrefix + "You are now playing as: ", "");
            }
            if(clean.startsWith(nickPrefixOld + "You are now playing as:")) {
                nickName = clean.replace(nickPrefixOld + "You are now playing as: ", "");
            }

            if(clean.startsWith(nickPrefix + "Dein Nickname wurde zurückgesetzt.") ||
                    clean.startsWith(nickPrefix + "Your nickname has been reset.")) {
                nickName = null;
            }
            if(clean.startsWith(nickPrefixOld + "Dein Nickname wurde zurückgesetzt.") ||
                    clean.startsWith(nickPrefixOld + "Your nickname has been reset.")) {
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

            String cloudPrefix = "▎▏ Cloud » ";

            if(clean.startsWith(cloudPrefix + "Der Service ") & clean.contains(" wird gestoppt...")) {
                if(StaffSettings.cloudMessageAsAchievement) {
                    String service = clean.replace(cloudPrefix + "Der Service ", "").replace(" wird gestoppt...", "");
                    LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§aCloud", "§7Cloud-Service §e" + service + " §cgestoppt§7...");
                    return ChatDisplayAction.HIDE;
                }
            }

            if(clean.startsWith(cloudPrefix + "Der Service ") & clean.contains(" wird gestartet...")) {
                if(StaffSettings.cloudMessageAsAchievement) {
                    String service = clean.replace(cloudPrefix + "Der Service ", "").replace(" wird gestartet...", "");
                    LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§aCloud", "§7Cloud-Service §e" + service + " §agestartet§7...");
                    return ChatDisplayAction.HIDE;
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
        if(Main.isStaff()) {
            StaffSettings.loadStaffSettings();
        }
    }

    public static void setInRound(boolean status) {
        inRound = status;
    }

    public static List<ReportData> getReports() {
        return reports;
    }

    public static List<SupportData> getSupports() {
        return supports;
    }

    public static CurrentReportData getCurrentReportData() {
        return currentReportData;
    }

    public static void setCurrentReportData(CurrentReportData currentReportData) {
        TerraMCnetServer.currentReportData = currentReportData;
    }

    public static void setVanish(boolean value) {
        vanish = value;
    }

    public static void setAutoVanish(boolean value) {
        autoVanish = value;
    }

    public static boolean isVanish() {
        return vanish;
    }

    public static boolean isAutoVanish() {
        return autoVanish;
    }

    public static void setServerTps(String value) {
        serverTps = value;
    }

    public static String getServerTps() {
        return serverTps;
    }

    public static void setCpuUsage(String value) {
        cpuUsage = value;
    }

    public static String getCpuUsage() {
        return cpuUsage;
    }

    public static void setHeapUsage(String value) {
        heapUsage = value;
    }

    public static String getHeapUsage() {
        return heapUsage;
    }

    public static String getOnlineTime() {
        return onlineTime;
    }

    public static void setOnlineTime(String value) {
        onlineTime = value;
    }

    public static int getJoins() {
        return joins;
    }

    public static void setJoins(int value) {
        joins = value;
    }

    public static boolean updateMessageSent = false;

    public static void checkUpdate(String version) {
        if(!version.equals(Main.addonVersion) & !updateMessageSent) {
            updateMessageSent = true;
            LabyMod labyMod = LabyMod.getInstance();
            labyMod.displayMessageInChat("§a§lTerraMC §7§lLabyMod-Addon");
            labyMod.displayMessageInChat("§7Es ist eine neue Version §8[§e" + version + "§8] §7verfügbar!");
            labyMod.displayMessageInChat("");
            labyMod.displayMessageInChat("§7Du kannst sie dir unter §ehttps://terramc.net/laby §7herunterladen.");
        }
    }

}
