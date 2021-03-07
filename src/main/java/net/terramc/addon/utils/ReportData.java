package net.terramc.addon.utils;

public class ReportData {

    private int id;
    private String reported;
    private String reporter;
    private String reason;

    public ReportData(int id, String reported, String reporter, String reason) {
        this.id = id;
        this.reported = reported;
        this.reporter = reporter;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public String getReported() {
        return reported;
    }

    public String getReporter() {
        return reporter;
    }

    public String getReason() {
        return reason;
    }

    public static ReportData fromString(String input) {
        String[] splitted = input.split(";");
        int id = Integer.parseInt(splitted[0]);
        String reported = splitted[1];
        String reporter = splitted[2];
        String reason = splitted[3];

        return new ReportData(id, reported, reporter, reason);
    }

}
