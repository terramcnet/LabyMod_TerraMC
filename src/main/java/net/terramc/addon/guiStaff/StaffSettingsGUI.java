package net.terramc.addon.guiStaff;

import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.terramc.addon.Main;
import net.terramc.addon.utils.PlayerNotify;

import java.io.IOException;

public class StaffSettingsGUI extends GuiScreen {

    private GuiScreen lastScreen;

    public StaffSettingsGUI(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiButton(0, 5, 5, 70, 20, "§8« §cZurück"));

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

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch(button.id) {
            case 0:
                Minecraft.getMinecraft().displayGuiScreen(this.lastScreen);
                break;
        }

    }
}
