package net.terramc.addon.utils;

public class SupportData {

    private int id;
    private String player;

    public SupportData(int id, String player) {
        this.id = id;
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public String getPlayer() {
        return player;
    }

    public static SupportData fromString(String input) {
        String[] splitted = input.split(";");
        int id = Integer.parseInt(splitted[0]);
        String player = splitted[1];

        return new SupportData(id, player);
    }

}
