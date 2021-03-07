package net.terramc.addon.gui;

import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.terramc.addon.Main;

import java.io.IOException;

public class StatsGUI extends GuiScreen {

    public GuiScreen lastScreen;

    public StatsGUI(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiButtonExt(0, 5, 5, 70, 20, "§8« §cZurück"));

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

        drawUtils.drawString("§aKills §8» §a0", x -95, y +35);
        drawUtils.drawString("§cTode §8» §c0", x -95, y +45);
        drawUtils.drawString("§eK/D §8» §e0.0", x -95, y +55);
        drawUtils.drawString("§ePunkte §8» §e0", x -95, y +65);

        // Stats for KnockBackFFA
        drawUtils.drawItem(new ItemStack(Item.getItemById(280)), x2 -100, y, "");
        drawUtils.drawString("§6KnockBackFFA§8:", x2 -100, y +20);

        drawUtils.drawString("§aKills §8» §a0", x2 -95, y +35);
        drawUtils.drawString("§cTode §8» §c0", x2 -95, y +45);
        drawUtils.drawString("§eK/D §8» §e0.0", x2 -95, y +55);
        drawUtils.drawString("§ePunkte §8» §e0", x2 -95, y +65);

        // Stats for SoupTrainer
        drawUtils.drawItem(new ItemStack(Item.getItemById(282)), x3 -100, y, "");
        drawUtils.drawString("§bSoupTrainer§8:", x3 -100, y +20);

        drawUtils.drawString("§aKills §8» §a0", x3 -95, y +35);
        drawUtils.drawString("§cTode §8» §c0", x3 -95, y +45);
        drawUtils.drawString("§eK/D §8» §e0.0", x3 -95, y +55);
        drawUtils.drawString("§ePunkte §8» §e0", x3 -95, y +65);


        // Line #2

        // Stats for BuildFFA
        drawUtils.drawItem(new ItemStack(Item.getItemById(369)), x -100, y2, "");
        drawUtils.drawString("§cOneLine§8:", x -100, y2 +20);

        drawUtils.drawString("§aKills §8» §a0", x -95, y2 +35);
        drawUtils.drawString("§cTode §8» §c0", x -95, y2 +45);
        drawUtils.drawString("§eK/D §8» §e0.0", x -95, y2 +55);
        drawUtils.drawString("§ePunkte §8» §e0", x -95, y2 +65);

        // Stats for KnockBackFFA
        drawUtils.drawItem(new ItemStack(Item.getItemById(384)), x2 -100, y2, "");
        drawUtils.drawString("§eXP§8:", x2 -100, y2 +20);

        drawUtils.drawString("§aKills §8» §a0", x2 -95, y2 +35);
        drawUtils.drawString("§cTode §8» §c0", x2 -95, y2 +45);
        drawUtils.drawString("§eK/D §8» §e0.0", x2 -95, y2 +55);
        drawUtils.drawString("§ePunkte §8» §e0", x2 -95, y2 +65);

        // Stats for SoupTrainer
        drawUtils.drawItem(new ItemStack(Item.getItemById(276)), x3 -100, y2, "");
        drawUtils.drawString("§4TeamDeathMatch§8:", x3 -100, y2 +20);

        drawUtils.drawString("§aKills §8» §a0", x3 -95, y2 +35);
        drawUtils.drawString("§cTode §8» §c0", x3 -95, y2 +45);
        drawUtils.drawString("§eK/D §8» §e0.0", x3 -95, y2 +55);
        drawUtils.drawString("§ePunkte §8» §e0", x3 -95, y2 +65);

        // Line #3


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
