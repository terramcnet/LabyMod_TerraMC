package net.terramc.addon;

import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;
import net.minecraft.client.Minecraft;

public class MessageSendListener implements MessageSendEvent {

    @Override
    public boolean onSend(String message) {
        String command = message.toLowerCase();
        if(command.equalsIgnoreCase("#sd")) {
            if(Main.isAdmin()) {
                LabyMod.getInstance().displayMessageInChat(Main.Prefix + "§7Bitte benutze §a#sd <serverGroup>");
            }
            return true;
        }
        if(command.startsWith("#sd ")) {
            if(Main.isAdmin()) {
                String group = message.split(" ")[1];
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/cloud stopgroup " + group);
            }
            return true;
        }

        if(command.equalsIgnoreCase("#ma")) {
            if(Main.isAdmin()) {
                LabyMod.getInstance().displayMessageInChat(Main.Prefix + "§7Bitte benutze §a#ma <serverGroup>");
            }
            return true;
        }
        if(command.startsWith("#ma ")) {
            if(Main.isAdmin()) {
                String group = message.split(" ")[1];
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/cloud maintenance " + group);
            }
            return true;
        }
        return false;
    }

}
