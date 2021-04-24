package net.terramc.addon.elements;

import net.labymod.main.LabyMod;
import net.labymod.settings.elements.KeyElement;
import net.labymod.utils.Consumer;
import net.minecraft.client.Minecraft;

public class CustomKeyElement extends KeyElement {

    private IconData descriptionIconDataNormal, descriptionIconDataHovering;
    private String[] description;

    public CustomKeyElement(String displayName, IconData iconData, int currentValue, Consumer<Integer> toggleListener,
                            IconData descriptionIconData, String... description) {
        super(displayName, iconData, currentValue, toggleListener);
        this.descriptionIconDataNormal = descriptionIconData;
        this.descriptionIconDataHovering = descriptionIconData;
        this.description = description;
    }

    public CustomKeyElement(String displayName, IconData iconData, int currentValue, Consumer<Integer> toggleListener,
                            IconData descriptionIconDataNormal, IconData descriptionIconDataHovering, String... description) {
        super(displayName, iconData, currentValue, toggleListener);
        this.descriptionIconDataNormal = descriptionIconDataNormal;
        this.descriptionIconDataHovering = descriptionIconDataHovering;
        this.description = description;
    }

    @Override
    public void draw(int x, int y, int maxX, int maxY, int mouseX, int mouseY) {
        super.draw(x, y, maxX, maxY, mouseX, mouseY);

        //boolean hoveredIcon = mouseX > (x + 205) && mouseX < (maxX + 35) && mouseY > y && mouseY < (maxY);
        //boolean hoveredIcon = mouseX > x && mouseX < maxX && mouseY > y && mouseY < (maxY);
        boolean hoveredIcon = mouseX > x && mouseX < (maxX - 50) && mouseY > y && mouseY < (maxY);

        if (hoveredIcon) {
            if (this.getIconHovering() != null) {
                if (this.getIconHovering().hasTextureIcon()) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(this.getIconHovering().getTextureIcon());
                    LabyMod.getInstance().getDrawUtils().drawTexture(x + 213, y + 3, 256.0D, 256.0D, 16.0D, 16.0D);
                } else if (this.getIconHovering().hasMaterialIcon())
                    LabyMod.getInstance().getDrawUtils().drawItem(this.getIconHovering().getMaterialIcon().createItemStack(), x + 3, y + 3, null);
            }
        } else {
            if (this.getIconNormal() != null) {
                if (this.getIconNormal().hasTextureIcon()) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(this.getIconNormal().getTextureIcon());
                    LabyMod.getInstance().getDrawUtils().drawTexture(x + 213, y + 3, 256.0D, 256.0D, 16.0D, 16.0D);
                } else if (this.getIconNormal().hasMaterialIcon())
                    LabyMod.getInstance().getDrawUtils().drawItem(this.getIconNormal().getMaterialIcon().createItemStack(), (double) (x + 3), (double) (y + 3), null);
            }
        }

        if (hoveredIcon)
            LabyMod.getInstance().getDrawUtils().drawHoveringText(mouseX, mouseY, this.getDescription());
    }

    public String[] getDescription() { return this.description; }

    public void setDescription(String[] description) { this.description = description; }

    public IconData getIconNormal() { return this.descriptionIconDataNormal; }

    public IconData getIconHovering() { return this.descriptionIconDataHovering; }

    public void setDescriptionIconDataNormal(IconData descriptionIconData) { this.descriptionIconDataNormal = descriptionIconData; }

    public void setDescriptionIconDataHovering(IconData descriptionIconDataHovering) { this.descriptionIconDataHovering = descriptionIconDataHovering; }

}