package net.terramc.addon.guiStaff;

import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.terramc.addon.Main;
import net.terramc.addon.utils.StaffSettings;

import java.io.IOException;

public class StaffSettingsGUI extends GuiScreen {

    public GuiScreen lastScreen;

    public StaffSettingsGUI(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiButton(0, 5, 5, 70, 20, "§8« §cZurück"));

        int xMiddle = width / 2;

        if(Main.isStaff()) {
            this.buttonList.add(new GuiButton(1, xMiddle -120-80, 50, 120, 20, "§8» §4Reports anzeigen"));
            this.buttonList.add(new GuiButton(2, xMiddle -60, 50, 120, 20, "§8» §eVanish-Status"));
            this.buttonList.add(new GuiButton(3, xMiddle+80, 50, 120, 20, "§8» §4Aktueller Report"));
        }

        if(Main.isAdmin()) {
            this.buttonList.add(new GuiButton(10, xMiddle -60, 90, 120, 20, "§8» §cServer-Status"));
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        DrawUtils drawUtils = LabyMod.getInstance().getDrawUtils();

        int xMiddle = width / 2;
        int yMiddle = height / 2;

        if(!Main.isStaff()) {
            drawUtils.drawCenteredString("§4§nDu bist kein Teammitglied!", xMiddle, yMiddle);
            return;
        }

        drawUtils.drawCenteredString("§8« §aTeam-Einstellungen §8»", xMiddle, 5, 1.5);

        drawUtils.drawString("§7Developed by MisterCore", 5, height - 10, 0.8D);

    }

    public static void notify(String name, boolean status) {
        String title = "§aEinstellungen";
        String enabled = "{type} §7wurde §aaktiviert§7.";
        String disabled = "{type} §7wurde §cdeaktiviert§7.";

        if(status) {
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement(title, enabled.replace("{type}", name));
        } else {
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement(title, disabled.replace("{type}", name));
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch(button.id) {
            case 0:
                Minecraft.getMinecraft().displayGuiScreen(this.lastScreen);
                break;
            case 1: // Reports
                if(StaffSettings.showReports) {
                    StaffSettings.showReports = false;
                    notify("Reports anzeigen", false);
                    Main.getInstance().addConfigEntry("showReports", false);
                } else {
                    StaffSettings.showReports = true;
                    notify("Reports anzeigen", true);
                    Main.getInstance().addConfigEntry("showReports", true);
                }
                break;
            case 2: // Vanish
                if(StaffSettings.showVanishStatus) {
                    StaffSettings.showVanishStatus = false;
                    notify("Vanish anzeigen", false);
                    Main.getInstance().addConfigEntry("showVanishStatus", false);
                } else {
                    StaffSettings.showVanishStatus = true;
                    notify("Vanish anzeigen", true);
                    Main.getInstance().addConfigEntry("showVanishStatus", true);
                }
                break;
            case 3: // CurrentReport
                if(StaffSettings.showCurrentReportData) {
                    StaffSettings.showCurrentReportData = false;
                    notify("Aktueller Report", false);
                    Main.getInstance().addConfigEntry("showCurrentReportData", false);
                } else {
                    StaffSettings.showCurrentReportData = true;
                    notify("Aktueller Report", true);
                    Main.getInstance().addConfigEntry("showCurrentReportData", true);
                }
                break;
            case 10: // ServerStatus
                if(StaffSettings.showServerStatus) {
                    StaffSettings.showServerStatus = false;
                    notify("ServerStatus", false);
                    Main.getInstance().addConfigEntry("showServerStatus", false);
                } else {
                    StaffSettings.showServerStatus = true;
                    notify("ServerStatus", true);
                    Main.getInstance().addConfigEntry("showServerStatus", true);
                }
                break;
        }

    }
}
