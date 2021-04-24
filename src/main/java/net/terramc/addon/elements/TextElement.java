package net.terramc.addon.elements;

import net.labymod.main.LabyMod;
import net.labymod.settings.elements.SettingsElement;

public class TextElement extends SettingsElement {

    int height;
    int spaceTop;

    //5 is the space to upper Element, 10 the height.. a "normal" has 7px to upper element and is 22 height. Try to test some other ints

    public TextElement(String text, int spaceTop, int height){
        super(text, null);
        this.spaceTop = spaceTop;
        this.height = height;
    }

    public void init() {}

    public void draw(int x, int y, int maxX, int maxY, int mouseX, int mouseY){
        super.draw(x, y, maxX, maxY, mouseX, mouseY);
        int absoluteY = y + spaceTop;
        LabyMod.getInstance().getDrawUtils().drawString(super.getDisplayName(), x, absoluteY);
    }

    public int getEntryHeight() {
        return height;
    }

    public int getEntryWidth(){
        return 85;
    }

    public void drawDescription(int x, int y, int screenWidth) {}

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}

    public void keyTyped(char typedChar, int keyCode) {}

    public void mouseRelease(int mouseX, int mouseY, int mouseButton) {}

    public void mouseClickMove(int mouseX, int mouseY, int mouseButton) {}

    public void unfocus(int mouseX, int mouseY, int mouseButton) {}

}
