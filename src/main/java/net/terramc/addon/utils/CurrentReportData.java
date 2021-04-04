package net.terramc.addon.utils;

public class CurrentReportData {

    private int id;
    private String player;
    private String reportedBy;
    private String reason;

    public CurrentReportData(int id, String player, String reportedBy, String reason) {
        this.id = id;
        this.player = player;
        this.reportedBy = reportedBy;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public String getPlayer() {
        return player;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public String getReason() {
        return reason;
    }

    public static CurrentReportData fromString(String input) {
        String[] splitted = input.split(";");
        int id = Integer.parseInt(splitted[0]);
        String player = splitted[1];
        String reportedBy = splitted[2];
        String reason = splitted[3];

        return new CurrentReportData(id, player, reportedBy, reason);
    }

}
