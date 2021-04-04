package net.terramc.addon.utils;

import com.google.gson.JsonObject;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.terramc.addon.Main;

import java.util.ArrayList;
import java.util.List;

public class StaffSettings {

    // Staff

    public static boolean showCurrentReportData;
    public static boolean showReports;
    public static boolean showVanishStatus;

    // Admin

    public static boolean showTPS;
    public static boolean showCpuUsage;
    public static boolean showHeapUsage;

    public static void loadStaffSettings() {
        JsonObject config = Main.getInstance().getConfigObject();
        showReports = !config.has("showReports") || config.get("showReports").getAsBoolean();
        showVanishStatus = !config.has("showVanishStatus") || config.get("showVanishStatus").getAsBoolean();

        showTPS = !config.has("showTPS") || config.get("showTPS").getAsBoolean();
        showCpuUsage = !config.has("showCpuUsage") || config.get("showCpuUsage").getAsBoolean();
        showHeapUsage = !config.has("showHeapUsage") || config.get("showHeapUsage").getAsBoolean();
    }

    public static List<SettingsElement> getStaffSettings() {
        List<SettingsElement> list = new ArrayList<>();

        // Staff functions

        if(Main.isStaff()) {
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
        }

        // Admin functions

        if(Main.isAdmin()) {

            list.add(new HeaderElement("§7§l§o▎§8§l§o▏ §4Admin Funktionen"));

            list.add(new BooleanElement("§8» §fServer-TPS anzeigen", new ControlElement.IconData(Material.REDSTONE_TORCH_ON), new Consumer<Boolean>() {
                @Override
                public void accept(Boolean status) {
                    showTPS = status;
                    Main.getInstance().addConfigEntry("showTPS", status);
                }
            }, showTPS));

            list.add(new BooleanElement("§8» §fCPU Auslastung anzeigen", new ControlElement.IconData(Material.REDSTONE_TORCH_ON), new Consumer<Boolean>() {
                @Override
                public void accept(Boolean status) {
                    showCpuUsage = status;
                    Main.getInstance().addConfigEntry("showCpuUsage", status);
                }
            }, showCpuUsage));

            list.add(new BooleanElement("§8» §fRAM Auslastung anzeigen", new ControlElement.IconData(Material.REDSTONE_TORCH_ON), new Consumer<Boolean>() {
                @Override
                public void accept(Boolean status) {
                    showHeapUsage = status;
                    Main.getInstance().addConfigEntry("showHeapUsage", status);
                }
            }, showCpuUsage));
        }

        return list;
    }

}
