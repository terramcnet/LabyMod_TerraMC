package net.terramc.addon.guiStaff;

import com.google.gson.JsonObject;
import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.terramc.addon.Main;

import java.io.IOException;

public class CloudControlGUI extends GuiScreen {

    GuiScreen lastScreen;

    public CloudControlGUI(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {
        super.initGui();

        super.drawDefaultBackground();

        this.buttonList.add(new GuiButton(0, 5, 5, 70, 20, "§8« §cZurück"));

        if(Main.canControlCloud()) {

            int middleX = width / 2;

            // Proxy
            this.buttonList.add(new GuiButton(1, middleX - 30, 90, 60, 20, "§cStoppen"));

            // Lobby - Stop
            this.buttonList.add(new GuiButton(3, 150 -65, 140, 60, 20, "§cStoppen"));

            // PremiumLobby - Stop
            this.buttonList.add(new GuiButton(5, middleX -65, 140, 60, 20, "§cStoppen"));

            // SilentLobby - Stop
            this.buttonList.add(new GuiButton(7, width - 150 -65, 140, 60, 20, "§cStoppen"));

            // TDM-2x1 - Stop
            this.buttonList.add(new GuiButton(9, 150 -65, 190, 60, 20, "§cStoppen"));

            // BuildFFA - Stop
            this.buttonList.add(new GuiButton(11, middleX -65, 190, 60, 20, "§cStoppen"));

            // XP - Stop
            this.buttonList.add(new GuiButton(13, width - 150 -65, 190, 60, 20, "§cStoppen"));

            // TDM-2x2 - Stop
            this.buttonList.add(new GuiButton(15, 150 -65, 240, 60, 20, "§cStoppen"));

            // FFA - Stop
            this.buttonList.add(new GuiButton(17, middleX -65, 240, 60, 20, "§cStoppen"));

            // OneLine - Stop
            this.buttonList.add(new GuiButton(19, width - 150 -65, 240, 60, 20, "§cStoppen"));

            // TDM-2x4 - Stop
            this.buttonList.add(new GuiButton(21, 150 -65, 290, 60, 20, "§cStoppen"));

            // WaterFightFFA - Stop
            this.buttonList.add(new GuiButton(23, middleX -65, 290, 60, 20, "§cStoppen"));

            // KnockBackFFA - Stop
            this.buttonList.add(new GuiButton(25, width - 150 -65, 290, 60, 20, "§cStoppen"));

            if(Main.canFullyControlCloud()) {

                // Lobby - Maintenance
                this.buttonList.add(new GuiButton(4, 150 +5, 140, 60, 20, "§6Wartungen"));

                // PremiumLobby - Maintenance
                this.buttonList.add(new GuiButton(6, middleX +5, 140, 60, 20, "§6Wartungen"));

                // SilentLobby - Maintenance
                this.buttonList.add(new GuiButton(8, width - 150 +5, 140, 60, 20, "§6Wartungen"));

                // TDM-2x1 - Maintenance
                this.buttonList.add(new GuiButton(10, 150 +5, 190, 60, 20, "§6Wartungen"));

                // BuildFFA - Maintenance
                this.buttonList.add(new GuiButton(12, middleX +5, 190, 60, 20, "§6Wartungen"));

                // XP - Maintenance
                this.buttonList.add(new GuiButton(14, width - 150 +5, 190, 60, 20, "§6Wartungen"));

                // TDM-2x2 - Maintenance
                this.buttonList.add(new GuiButton(16, 150 +5, 240, 60, 20, "§6Wartungen"));

                // FFA - Maintenance
                this.buttonList.add(new GuiButton(18, middleX +5, 240, 60, 20, "§6Wartungen"));

                // OneLine - Maintenance
                this.buttonList.add(new GuiButton(20, width - 150 +5, 240, 60, 20, "§6Wartungen"));

                // TDM-2x4 - Maintenance
                this.buttonList.add(new GuiButton(22, 150 +5, 290, 60, 20, "§6Wartungen"));

                // WaterFightFFA - Maintenance
                this.buttonList.add(new GuiButton(24, middleX +5, 290, 60, 20, "§6Wartungen"));

                // KnockBackFFA - Maintenance
                this.buttonList.add(new GuiButton(26, width - 150 +5, 290, 60, 20, "§6Wartungen"));

            }

        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        DrawUtils drawUtils = LabyMod.getInstance().getDrawUtils();

        int middleX = width / 2;
        int middleY = height / 2;

        if(!Main.canControlCloud()) {
            drawUtils.drawCenteredString("§c§nDu bist kein Admin!", middleX, middleY);
            return;
        }

        drawUtils.drawCenteredString("§8« §aCloud-Steuerung §8»", middleX, 20, 1.5);

        drawUtils.drawCenteredString("§a§lProxy", middleX, 80);

        // Row #1

        drawUtils.drawCenteredString("§7Lobby", 150, 130);

        drawUtils.drawCenteredString("§6PremiumLobby", middleX, 130);

        drawUtils.drawCenteredString("§4SilentLobby", width - 150, 130);

        // Row #2

        drawUtils.drawCenteredString("§4TDM-2x1", 150, 180);

        drawUtils.drawCenteredString("§eBuildFFA", middleX, 180);

        drawUtils.drawCenteredString("§eXP", width - 150, 180);

        // Row #3

        drawUtils.drawCenteredString("§4TDM-2x2", 150, 230);

        drawUtils.drawCenteredString("§cFFA", middleX, 230);

        drawUtils.drawCenteredString("§cOneLine", width - 150, 230);

        // Row #4

        drawUtils.drawCenteredString("§4TDM-2x4", 150, 280);

        drawUtils.drawCenteredString("§9WaterFightFFA", middleX, 280);

        drawUtils.drawCenteredString("§6KnockBackFFA", width - 150, 280);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        String messageKeyStop = "terra_cc_stop";
        String messageKeyMain = "terra_cc_main";

        switch(button.id) {
            case 0:
                Minecraft.getMinecraft().displayGuiScreen(this.lastScreen);
                break;

                // Proxy
            case 1: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "Proxy");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;

              // Lobby
            case 3: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "Lobby");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 4: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "Lobby");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

              // PremiumLobby
            case 5: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "PremiumLobby");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 6: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "PremiumLobby");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // SilentLobby
            case 7: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "SilentLobby");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 8: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "SilentLobby");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // TDM-2x1
            case 9: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "TDM-2x1");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 10: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "TDM-2x1");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // BuildFFA
            case 11: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "BuildFFA");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 12: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "BuildFFA");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // XP
            case 13: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "XP");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 14: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "XP");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // TDM-2x2
            case 15: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "TDM-2x2");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 16: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "TDM-2x2");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // FFA
            case 17: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "FFA");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 18: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "FFA");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // OneLine
            case 19: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "OneLine");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 20: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "OneLine");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // TDM-2x4
            case 21: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "TDM-2x4");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 22: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "TDM-2x4");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // WaterFightFFA
            case 23: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "WaterFFA");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 24: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "WaterFFA");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;

            // KnockBackFFA
            case 25: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "KBFFA");
                Main.sendMessageToServer(messageKeyStop, object);
            }
                break;
            case 26: {
                JsonObject object = new JsonObject();
                object.addProperty("group", "KBFFA");
                Main.sendMessageToServer(messageKeyMain, object);
            }
                break;
        }

    }

}
