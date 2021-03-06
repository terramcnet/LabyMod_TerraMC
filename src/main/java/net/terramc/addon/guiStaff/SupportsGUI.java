package net.terramc.addon.guiStaff;

import net.labymod.gui.elements.Scrollbar;
import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.labymod.utils.UUIDFetcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.terramc.addon.Main;
import net.terramc.addon.TerraMCnetServer;
import net.terramc.addon.utils.SupportData;

import java.io.IOException;
import java.util.List;

public class SupportsGUI extends GuiScreen {

    Scrollbar scrollbar = new Scrollbar(18);

    GuiScreen lastScreen;

    public SupportsGUI(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.scrollbar.init();
        this.scrollbar.setPosition(this.width - 10, 44, this.width - 6, this.height - 32 - 3);
        this.scrollbar.setSpeed(15);

        this.buttonList.add(new GuiButton(0, 5, 5, 70, 20, "§8« §cZurück"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();

        DrawUtils drawUtils = LabyMod.getInstance().getDrawUtils();

        int xMiddle = width / 2;

        if(!Main.enabled) {
            drawUtils.drawCenteredString("§4§lKeine Informationen verfügbar!", xMiddle, 20);
            drawUtils.drawCenteredString("§cBitte verbinde dich auf TerraMC.net", xMiddle, 30);
            return;
        }

        List<SupportData> list = TerraMCnetServer.getSupports();
        int xReports = 90;
        drawUtils.drawCenteredString("§8« §6Offene Supports §8(§e" + list.size() + "§8) »", xMiddle, 5, 1.5);

        double yPos = 30 + this.scrollbar.getScrollY();
        if(list.size() > 0) {
            for(SupportData data : list) {
                if(UUIDFetcher.getUUID(data.getPlayer()) != null) {
                    drawUtils.drawPlayerHead(data.getPlayer(), xReports, (int) yPos, 16);
                }
                String entry = "§8» §e" + data.getId() + " §8| §7Spieler§8: §e" + data.getPlayer() + "§r";
                drawUtils.drawString(entry, xReports + 20, yPos + 5);
                this.buttonList.add(new GuiButton(data.getId(), width - 100, (int) (yPos - 5), 70, 20, "§8» §aAnnehmen"));
                yPos += 20;
            }
        } else {
            drawUtils.drawCenteredString("§cKeine offenen Supports vorhanden.", xMiddle, 30, 1.5);
        }

        this.scrollbar.update(list.size());
        this.scrollbar.draw();

        drawUtils.drawString("§7Developed by MisterCore", 5, height - 10, 0.8D);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if(button.id == 0) {
            Minecraft.getMinecraft().displayGuiScreen(this.lastScreen);
        }

        TerraMCnetServer.getReports().forEach(reportData -> {
            if(button.id == reportData.getId()) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/support accept " + reportData.getId());
            }
        });

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        this.scrollbar.mouseAction(mouseX, mouseY, Scrollbar.EnumMouseAction.CLICKED);

    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);

        this.scrollbar.mouseAction(mouseX, mouseY, Scrollbar.EnumMouseAction.DRAGGING);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        this.scrollbar.mouseAction(mouseX, mouseY, Scrollbar.EnumMouseAction.RELEASED);

    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        this.scrollbar.mouseInput();
    }

}
