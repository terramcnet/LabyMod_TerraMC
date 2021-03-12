package net.terramc.addon.gui;

import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.terramc.addon.Main;
import net.terramc.addon.guiStaff.CloudControlGUI;
import net.terramc.addon.guiStaff.NotifyGUI;
import net.terramc.addon.guiStaff.ReportsGUI;

import java.io.IOException;

public class StaffGUI extends GuiScreen {

    private GuiScreen lastScreen;

    public StaffGUI(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiButton(0, 5, 5, 70, 20, "§8« §cZurück"));

        if(Main.isStaff()) {
            this.buttonList.add(new GuiButton(1, 100, 50, 130, 20, "§8» §4Offene Reports"));
            this.buttonList.add(new GuiButton(2, 100, 80, 150, 20, "§8» §6Benachrichtigungen"));
        }
        if(Main.isAdmin()) {
            this.buttonList.add(new GuiButton(10, width -150, 50, 130, 20, "§8» §aCloud-Steuerung"));
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        DrawUtils drawUtils = LabyMod.getInstance().getDrawUtils();

        if(!Main.enabled) {
            drawUtils.drawCenteredString("§4§lKeine Informationen verfügbar!", width / 2, 20);
            drawUtils.drawCenteredString("§cBitte verbinde dich auf TerraMC.net", width / 2, 30);
            return;
        }

        drawUtils.drawCenteredString("§8« §aTerraMC.net §8- §aTeamGUI §8»", width / 2, 20, 1.5);

        drawUtils.drawString("§7Developed by MisterCore", 5, height - 10, 0.8D);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch(button.id) {
            case 0:
                Minecraft.getMinecraft().displayGuiScreen(this.lastScreen);
                break;
            case 1:
                Minecraft.getMinecraft().displayGuiScreen(new ReportsGUI(this));
                break;
            case 2:
                Minecraft.getMinecraft().displayGuiScreen(new NotifyGUI(this));
                break;
            case 10:
                Minecraft.getMinecraft().displayGuiScreen(new CloudControlGUI(this));
                break;
        }

    }



}
