package net.terramc.addon.gui;

import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.terramc.addon.Main;
import net.terramc.addon.utils.PlayerStats;

import java.io.IOException;

public class StatsGUI extends GuiScreen {

    public GuiScreen lastScreen;

    public StatsGUI(GuiScreen lastScreen) {
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
        int x2 = x + 150;
        int x3 = x + 300;

        int y = 55;
        int y2 = y +100;
        int y3 = y +200;

        if(!Main.enabled) {
            drawUtils.drawCenteredString("§4§lKeine Stats verfügbar!", width / 2, 20);
            drawUtils.drawCenteredString("§cBitte verbinde dich auf TerraMC.net", width / 2, 30);
            return;
        }

        drawUtils.drawCenteredString("§8« §aDeine Stats §8»", width / 2, 20, 1.5);

        // Line #1

        // Stats for BuildFFA
        drawUtils.drawItem(new ItemStack(Block.getBlockById(24)), x -100, y, "");
        drawUtils.drawString("§eBuildFFA§8:", x -100, y +20);

        drawUtils.drawString("§aKills §8» §a" + PlayerStats.BuildFFA.kills, x -95, y +35);
        drawUtils.drawString("§cTode §8» §c" + PlayerStats.BuildFFA.deaths, x -95, y +45);
        drawUtils.drawString("§eK/D §8» §e" + PlayerStats.BuildFFA.kd, x -95, y +55);
        drawUtils.drawString("§ePunkte §8» §e" + PlayerStats.BuildFFA.points, x -95, y +65);

        // Stats for KnockBackFFA
        drawUtils.drawItem(new ItemStack(Item.getItemById(280)), x2 -100, y, "");
        drawUtils.drawString("§6KnockBackFFA§8:", x2 -100, y +20);

        drawUtils.drawString("§aKills §8» §a" + PlayerStats.KnockBackFFA.kills, x2 -95, y +35);
        drawUtils.drawString("§cTode §8» §c" + PlayerStats.KnockBackFFA.deaths, x2 -95, y +45);
        drawUtils.drawString("§eK/D §8» §e" + PlayerStats.KnockBackFFA.kd, x2 -95, y +55);
        drawUtils.drawString("§ePunkte §8» §e" + PlayerStats.KnockBackFFA.points, x2 -95, y +65);

        // Stats for SoupTrainer
        drawUtils.drawItem(new ItemStack(Item.getItemById(282)), x3 -100, y, "");
        drawUtils.drawString("§bSoupTrainer§8:", x3 -100, y +20);

        drawUtils.drawString("§aSchüsseln §8» §a" + PlayerStats.SoupTrainer.bowls, x3 -95, y +35);
        drawUtils.drawString("§cSuppen §8» §c" + PlayerStats.SoupTrainer.soups, x3 -95, y +45);


        // Line #2

        // Stats for OneLine
        drawUtils.drawItem(new ItemStack(Item.getItemById(369)), x -100, y2, "");
        drawUtils.drawString("§cOneLine§8:", x -100, y2 +20);

        drawUtils.drawString("§aWins §8» §a" + PlayerStats.OneLine.wins, x -95, y2 +35);
        drawUtils.drawString("§cLoses §8» §c" + PlayerStats.OneLine.loses, x -95, y2 +45);

        // Stats for KnockBackFFA
        drawUtils.drawItem(new ItemStack(Item.getItemById(384)), x2 -100, y2, "");
        drawUtils.drawString("§eXP§8:", x2 -100, y2 +20);

        drawUtils.drawString("§aKills §8» §a" + PlayerStats.XP.kills, x2 -95, y2 +35);
        drawUtils.drawString("§cTode §8» §c" + PlayerStats.XP.deaths, x2 -95, y2 +45);
        drawUtils.drawString("§eK/D §8» §e" + PlayerStats.XP.kd, x2 -95, y2 +55);
        drawUtils.drawString("§eWins §8» §e" + PlayerStats.XP.wins, x2 -95, y2 +65);

        // Stats for TeamDeathMatch
        drawUtils.drawItem(new ItemStack(Item.getItemById(276)), x3 -100, y2, "");
        drawUtils.drawString("§4TeamDeathMatch§8:", x3 -100, y2 +20);

        drawUtils.drawString("§aKills §8» §a" + PlayerStats.TDM.kills, x3 -95, y2 +35);
        drawUtils.drawString("§cTode §8» §c" + PlayerStats.TDM.deaths, x3 -95, y2 +45);
        drawUtils.drawString("§eK/D §8» §e" + PlayerStats.TDM.kd, x3 -95, y2 +55);
        drawUtils.drawString("§eWins §8» §e" + PlayerStats.TDM.wins, x3 -95, y2 +65);
        drawUtils.drawString("§eLoses §8» §e" + PlayerStats.TDM.loses, x3 -95, y2 +75);

        // Line #3

        // Stats for FFA
        drawUtils.drawItem(new ItemStack(Item.getItemById(267)), x -100, y3, "");
        drawUtils.drawString("§cFFA§8:", x -100, y3 +20);

        drawUtils.drawString("§aKills §8» §a" + PlayerStats.FFA.kills, x -95, y3 +35);
        drawUtils.drawString("§cTode §8» §c" + PlayerStats.FFA.deaths, x -95, y3 +45);
        drawUtils.drawString("§eK/D §8» §e" + PlayerStats.FFA.kd, x -95, y3 +55);

        // Stats for WaterFightFFA
        drawUtils.drawItem(new ItemStack(Item.getItemById(326)), x3 -100, y3, "");
        drawUtils.drawString("§9WaterFightFFA§8:", x3 -100, y3 +20);

        drawUtils.drawString("§aKills §8» §a" + PlayerStats.WaterFFA.kills, x3 -95, y3 +35);
        drawUtils.drawString("§cTode §8» §c" + PlayerStats.WaterFFA.deaths, x3 -95, y3 +45);
        drawUtils.drawString("§eK/D §8» §e" + PlayerStats.WaterFFA.kd, x3 -95, y3 +55);
        drawUtils.drawString("§ePunkte §8» §e" + PlayerStats.WaterFFA.points, x3 -95, y3 +65);

        drawUtils.drawString("§7Developed by MisterCore", 5, height - 10, 0.8D);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if(button.id == 0) {
            Minecraft.getMinecraft().displayGuiScreen(this.lastScreen);
        }

    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }
}
