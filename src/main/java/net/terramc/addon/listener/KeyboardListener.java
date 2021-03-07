package net.terramc.addon.listener;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.terramc.addon.Main;
import net.terramc.addon.gui.TerraGUI;
import org.lwjgl.input.Keyboard;

public class KeyboardListener {

    @SubscribeEvent
    public void onKeyboardPress(InputEvent.KeyInputEvent event) {
        if(Main.getGuiKey() == -1) {
            return;
        }

        if(Keyboard.isKeyDown(Main.getGuiKey())) {
            Minecraft.getMinecraft().displayGuiScreen(new TerraGUI());
        }

    }

}
