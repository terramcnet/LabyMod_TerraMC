package net.terramc.addon.gui;

import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;

import java.io.IOException;

public class PlayerInfoGUI extends GuiScreen {

    GuiScreen lastScreen;

    public PlayerInfoGUI(GuiScreen lastScreen) {
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

        int x = width / 2 - 70;

        if(!Main.enabled) {
            drawUtils.drawCenteredString("§4§lKeine Informationen verfügbar!", x, 20);
            drawUtils.drawCenteredString("§cBitte verbinde dich auf TerraMC.net", x, 30);
            return;
        }

        drawUtils.drawString("§8« §6Deine Übersicht §8»", x, 20, 1.5);

        // Rank
        drawUtils.drawItem(new ItemStack(Item.getItemById(315)), x -100, 35, "");
        drawUtils.drawString("§7Rang §8» §e" + TerraMCnetServer.getRank(), x -75, 39);

        // Global-Coins
        drawUtils.drawItem(new ItemStack(Item.getItemById(266)), x -100, 55, "");
        drawUtils.drawString("§7Global-Coins §8» §6" + TerraMCnetServer.getCoins(), x - 75, 59);

        // Nick

        drawUtils.drawItem(new ItemStack(Item.getItemById(421)), x -100, 75, "");
        drawUtils.drawString("§7Nickname §8» " + (TerraMCnetServer.getNickName() != null ? "§d" + TerraMCnetServer.getNickName() : "§cNicht genickt"), x -75, 79);

        // GameRank
        drawUtils.drawItem(new ItemStack(Item.getItemById(399)), x -100, 95, "");
        drawUtils.drawString("§7GameRank §8» " + (TerraMCnetServer.getGameRank() == null ? "§cNicht in einer Runde" : "§e" + TerraMCnetServer.getGameRank()), x -75, 99);

        // OnlineTime
        drawUtils.drawItem(new ItemStack(Item.getItemById(347)), x -100, 115, "");
        drawUtils.drawString("§7Online-Zeit §8» " + (TerraMCnetServer.getOnlineTime() != null ? TerraMCnetServer.getOnlineTime() : "§cKeine Zeit erfasst"), x -75, 119);

        // Joins
        drawUtils.drawItem(new ItemStack(Item.getItemById(381)), x -100, 135, "");
        drawUtils.drawString("§7Joins §8» §e" + TerraMCnetServer.getJoins(), x -75, 139);

        drawUtils.drawString("§7Developed by MisterCore", 5, height - 10, 0.8D);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if(button.id == 0) {
            Minecraft.getMinecraft().displayGuiScreen(this.lastScreen);
        }

    }

}
