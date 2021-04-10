package net.terramc.addon.guiStaff;

import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.terramc.addon.Main;

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
            this.buttonList.add(new GuiButton(2, 100, 80, 150, 20, "§8» §6Offene Supports"));
            this.buttonList.add(new GuiButton(3, 100, 110, 150, 20, "§8» §6Benachrichtigungen"));
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        DrawUtils drawUtils = LabyMod.getInstance().getDrawUtils();

        int x = width / 2;

        if(!Main.enabled) {
            drawUtils.drawCenteredString("§4§lKeine Informationen verfügbar!", x, 20);
            drawUtils.drawCenteredString("§cBitte verbinde dich auf TerraMC.net", x, 30);
            return;
        }

        drawUtils.drawCenteredString("§8« §aTerraMC.net §8- §aTeamGUI §8»", x, 20, 1.5);

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
                Minecraft.getMinecraft().displayGuiScreen(new SupportsGUI(this));
                break;
            case 3:
                Minecraft.getMinecraft().displayGuiScreen(new NotifyGUI(this));
                break;
        }

    }



}
