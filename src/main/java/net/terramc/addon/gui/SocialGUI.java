package net.terramc.addon.gui;

import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class SocialGUI extends GuiScreen {

    public GuiScreen lastScreen;

    public SocialGUI(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiButton(0, 5, 5, 70, 20, "§8« §cZurück"));

        int x = width / 2 - 70;

        int size = width / 2;

        int xPos = width / 4;

        this.buttonList.add(new GuiButton(1, xPos, 50, size, 20, "§8» §aForum"));
        this.buttonList.add(new GuiButton(2, xPos, 80, size, 20, "§8» §9Discord"));
        this.buttonList.add(new GuiButton(3, xPos, 110, size, 20, "§8» §bTwitter"));
        this.buttonList.add(new GuiButton(4, xPos, 140, size, 20, "§8» §eInstagram"));
        this.buttonList.add(new GuiButton(5, xPos, 170, size, 20, "§8» §5YouTube"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        DrawUtils drawUtils = LabyMod.getInstance().getDrawUtils();

        drawUtils.drawCenteredString("§8« §aTerraMC.net §8- §aSocialMedia §8»", width / 2, 20, 1.5);

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
                LabyMod.getInstance().openWebpage("https://terramc.net", false);
                break;
            case 2:
                LabyMod.getInstance().openWebpage("https://terramc.net/discord", false);
                break;
            case 3:
                LabyMod.getInstance().openWebpage("https://terramc.net/twitter", false);
                break;
            case 4:
                LabyMod.getInstance().openWebpage("https://terramc.net/instagram", false);
                break;
            case 5:
                LabyMod.getInstance().openWebpage("https://terramc.net/go/yt", false);
                break;
        }

    }

}
