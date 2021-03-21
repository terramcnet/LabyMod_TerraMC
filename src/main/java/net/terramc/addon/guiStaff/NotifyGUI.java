package net.terramc.addon.guiStaff;

import com.google.gson.JsonObject;
import net.labymod.main.LabyMod;
import net.labymod.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.terramc.addon.Main;
import net.terramc.addon.utils.PlayerNotify;

import java.io.IOException;

public class NotifyGUI extends GuiScreen {

    private GuiScreen lastScreen;

    public NotifyGUI(GuiScreen lastScreen) {
        this.lastScreen = lastScreen;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiButton(0, 5, 5, 70, 20, "§8« §cZurück"));

        if(Main.isStaff()) {

            // ✔ ✖

            this.buttonList.add(new GuiButton(1, 100, 100, 100, 20, "§eALLGEMEIN"));
            this.buttonList.add(new GuiButton(2, 100 + 130, 100, 100, 20, "§eCHATFILTER"));

            this.buttonList.add(new GuiButton(3, 100, 130, 100, 20, "§eKICK"));
            this.buttonList.add(new GuiButton(4, 100 + 130, 130, 100, 20, "§eBAN"));
            this.buttonList.add(new GuiButton(5, 100 + 130 + 130, 130, 100, 20, "§eMUTE"));

            this.buttonList.add(new GuiButton(6, 100, 160, 100, 20, "§eTEAMCHAT"));
            this.buttonList.add(new GuiButton(7, 100 + 130, 160, 100, 20, "§eREPORTS"));

            this.buttonList.add(new GuiButton(8, 100, 190, 100, 20, "§eANTICHEAT"));
            this.buttonList.add(new GuiButton(9, 100 + 130, 190, 100, 20, "§eSUPPORT"));

            this.buttonList.add(new GuiButton(10, 100, 220, 100, 20, "§eALLE"));

        }

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

        drawUtils.drawCenteredString("§8« §6Benachrichtgungen §8»", xMiddle, 5, 1.5);

    }

    public static void displayNotify(PlayerNotify type, boolean status) {
        String title = "§6Notify";
        String enabled = "{type} §7wurde §aaktiviert§7.";
        String disabled = "{type} §7wurde §cdeaktiviert§7.";

        if(status) {
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement(title, enabled.replace("{type}", type.getDisplay()));
        } else {
            LabyMod.getInstance().getGuiCustomAchievement().displayAchievement(title, disabled.replace("{type}", type.getDisplay()));
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        String messageKey = "terra_notify";
        switch(button.id) {
            case 0:
                Minecraft.getMinecraft().displayGuiScreen(this.lastScreen);
                break;
            case 1:
                if(PlayerNotify.INFO.isEnabled()) {
                    PlayerNotify.INFO.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.INFO.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.INFO, false);
                } else {
                    PlayerNotify.INFO.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.INFO.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.INFO, true);
                }
                break;
            case 2:
                if(PlayerNotify.CHATFILTER.isEnabled()) {
                    PlayerNotify.CHATFILTER.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.CHATFILTER.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.CHATFILTER, false);
                } else {
                    PlayerNotify.CHATFILTER.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.CHATFILTER.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.CHATFILTER, true);
                }
                break;
            case 3:
                if(PlayerNotify.KICK.isEnabled()) {
                    PlayerNotify.KICK.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.KICK.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.KICK, false);
                } else {
                    PlayerNotify.KICK.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.KICK.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.KICK, true);
                }
                break;
            case 4:
                if(PlayerNotify.BAN.isEnabled()) {
                    PlayerNotify.BAN.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.BAN.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.BAN, false);
                } else {
                    PlayerNotify.BAN.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.BAN.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.BAN, true);
                }
                break;
            case 5:
                if(PlayerNotify.MUTE.isEnabled()) {
                    PlayerNotify.MUTE.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.MUTE.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.MUTE, false);
                } else {
                    PlayerNotify.MUTE.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.MUTE.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.MUTE, true);
                }
                break;
            case 6:
                if(PlayerNotify.STAFFCHAT.isEnabled()) {
                    PlayerNotify.STAFFCHAT.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.STAFFCHAT.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.STAFFCHAT, false);
                } else {
                    PlayerNotify.STAFFCHAT.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.STAFFCHAT.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.STAFFCHAT, true);
                }
                break;
            case 7:
                if(PlayerNotify.REPORTS.isEnabled()) {
                    PlayerNotify.REPORTS.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.REPORTS.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.REPORTS, false);
                } else {
                    PlayerNotify.REPORTS.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.REPORTS.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.REPORTS, true);
                }
                break;
            case 8:
                if(PlayerNotify.ANTICHEAT.isEnabled()) {
                    PlayerNotify.ANTICHEAT.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.ANTICHEAT.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.ANTICHEAT, false);
                } else {
                    PlayerNotify.ANTICHEAT.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.ANTICHEAT.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.ANTICHEAT, true);
                }
                break;
            case 9:
                if(PlayerNotify.SUPPORT.isEnabled()) {
                    PlayerNotify.SUPPORT.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.SUPPORT.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.SUPPORT, false);
                } else {
                    PlayerNotify.SUPPORT.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.SUPPORT.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.SUPPORT, true);
                }
                break;
            case 10:
                if(PlayerNotify.ALL.isEnabled()) {
                    PlayerNotify.ALL.setEnabled(false);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.ALL.getName(), false);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.ALL, false);
                } else {
                    PlayerNotify.ALL.setEnabled(true);
                    JsonObject object = new JsonObject();
                    object.addProperty(PlayerNotify.ALL.getName(), true);
                    Main.sendMessageToServer(messageKey, object);
                    displayNotify(PlayerNotify.ALL, true);
                }
                break;
        }

    }

}
