package net.terramc.addon.gui;

import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.terramc.addon.Main;
import net.terramc.addon.guiStaff.CloudControlGUI;
import net.terramc.addon.guiStaff.StaffGUI;
import net.terramc.addon.guiStaff.StaffSettingsGUI;

import java.io.IOException;

public class TerraGUI extends GuiScreen {

    @Override
    public void initGui() {
        super.initGui();

        int buttonLength = 90;

        this.buttonList.add(new GuiButton(0, 5, 5, 70, 20, "§c✖ Schließen"));

        this.buttonList.add(new GuiButton(10, width -75, height -25, 70, 20, "§bSocial"));

        if(!Main.enabled) {
            this.buttonList.add(new GuiButton(1, width / 2 - 75, 70, 150, 20,  "§8» §aVerbinden"));
        } else {
            this.buttonList.add(new GuiButton(2, 100, 50, buttonLength, 20, "§eStats"));
            this.buttonList.add(new GuiButton(3, 100, 80, buttonLength, 20, "§6Übersicht"));

            if(Main.isStaff()) {
                this.buttonList.add(new GuiButton(4, 100, 110, buttonLength, 20, "§aTeam"));
                this.buttonList.add(new GuiButton(5, width -230, 80, 130, 20, "§aTeam-Einstellungen"));
            }

            if(Main.canControlCloud()) {
                this.buttonList.add(new GuiButton(11, width -230, 50, 130, 20, "§aCloud-Steuerung"));
            }

        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        DrawUtils drawUtils = LabyMod.getInstance().getDrawUtils();

        int x = width / 2;

        drawUtils.drawCenteredString("§8« §aTerraMC.net §8- §aGUI §8»", x, 20, 1.5);

        if(!Main.enabled) {
            drawUtils.drawCenteredString("§cBitte verbinde dich auf TerraMC.net", x, 60);
        }

        drawUtils.drawString("§7Developed by MisterCore", 5, height - 10, 0.8D);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch(button.id) {
            case 0:
                Minecraft.getMinecraft().thePlayer.closeScreen();
                break;
            case 1:
                LabyMod.getInstance().connectToServer("terramc.net");
                break;
            case 2:
                Minecraft.getMinecraft().displayGuiScreen(new StatsGUI(this));
                break;
            case 3:
                Minecraft.getMinecraft().displayGuiScreen(new PlayerInfoGUI(this));
                break;
            case 4:
                Minecraft.getMinecraft().displayGuiScreen(new StaffGUI(this));
                break;
            case 5:
                Minecraft.getMinecraft().displayGuiScreen(new StaffSettingsGUI(this));
                break;
            case 10:
                Minecraft.getMinecraft().displayGuiScreen(new SocialGUI(this));
                break;
            case 11:
                Minecraft.getMinecraft().displayGuiScreen(new CloudControlGUI(this));
                break;
        }

    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

}
