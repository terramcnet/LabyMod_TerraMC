package net.terramc.addon.utils;

import com.google.gson.JsonObject;
import net.terramc.addon.Main;

public class StaffSettings {

    // Staff

    public static boolean showCurrentReportData;
    public static boolean showReports;
    public static boolean showVanishStatus;

    // Admin

    public static boolean showServerStatus;

    public static void loadStaffSettings() {
        JsonObject config = Main.getInstance().getConfigObject();
        showReports = !config.has("showReports") || config.get("showReports").getAsBoolean();
        showCurrentReportData = !config.has("showCurrentReportData") || config.get("showCurrentReportData").getAsBoolean();
        showVanishStatus = !config.has("showVanishStatus") || config.get("showVanishStatus").getAsBoolean();

        showServerStatus = !config.has("showServerStatus") || config.get("showServerStatus").getAsBoolean();
    }

    /*public static List<SettingsElement> getStaffSettings() {
        List<SettingsElement> list = new ArrayList<>();

        // Staff functions

        list.add(new HeaderElement("§7§l§o▎§8§l§o▏ §eTeam Funktionen"));

        list.add(new BooleanElement("§8» §fReports anzeigen", new ControlElement.IconData(Material.REDSTONE), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean status) {
                showReports = status;
                Main.getInstance().addConfigEntry("showReports", status);
            }
        }, showReports));

        list.add(new BooleanElement("§8» §fVanish-Status", new ControlElement.IconData(Material.QUARTZ), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean status) {
                showVanishStatus = status;
                Main.getInstance().addConfigEntry("showVanishStatus", status);
            }
        }, showVanishStatus));

        // Admin functions


        list.add(new HeaderElement("§7§l§o▎§8§l§o▏ §4Admin Funktionen"));

        list.add(new BooleanElement("§8» §fServer-Status anzeigen", new ControlElement.IconData(Material.REDSTONE_TORCH_ON), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean status) {
                showServerStatus = status;
                Main.getInstance().addConfigEntry("showServerStatus", status);
            }
        }, showServerStatus));

        return list;
    }*/

}
