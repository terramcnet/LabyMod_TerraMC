package net.terramc.addon.utils;

public enum PlayerNotify {


    INFO("info", "§eInfo", true),
    CHATFILTER("chatfilter", "§eChatFilter", true),
    KICK("kick", "§eKick", true),
    BAN("ban", "§eBan", true),
    MUTE("mute", "§eMute", true),
    STAFFCHAT("staffchat", "§eTeamChat", true),
    REPORTS("reports", "§eReports", true),
    ANTICHEAT("anticheat", "§eAntiCheat", true),
    SUPPORT("support", "§eSupport", true),
    ALL("all", "§eAlles", true);

    String name;
    String display;
    boolean enabled;

    PlayerNotify(String name, String display, boolean enabled) {
        this.name = name;
        this.display = display;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static PlayerNotify getNotify(String name) {
        for(PlayerNotify playerNotify : PlayerNotify.values()) {
            if(playerNotify.getName().equalsIgnoreCase(name)) {
                return playerNotify;
            }
        }
        return null;
    }

    public static void loadFromString(String message) {
        String[] splitted = message.split(";");
        for(String split : splitted) {
            String name = split.split("=")[0];
            boolean status = Boolean.parseBoolean(split.split("=")[1]);
            if(getNotify(name) != null) {
                getNotify(name).setEnabled(status);
            }
        }
    }

}
