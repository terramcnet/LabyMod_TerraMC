package net.terramc.addon.listener;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.labymod.api.events.ServerMessageEvent;
import net.labymod.main.LabyMod;
import net.terramc.addon.TerraMCnetServer;
import net.terramc.addon.guiStaff.NotifyGUI;
import net.terramc.addon.utils.*;

public class ServerMessageListener implements ServerMessageEvent {

    @Override
    public void onServerMessage(String key, JsonElement message) {
        if(key.equals("TerraMod") & message.isJsonObject()) {
            JsonObject object = message.getAsJsonObject();

            if(object.has("LOBBY")) {
                if(object.get("LOBBY").getAsBoolean()) {
                    TerraMCnetServer.resetValues();
                }
            }

            if(object.has("gameRank")) {
                TerraMCnetServer.setGameRank(object.get("gameRank").getAsString());
            }
            if(object.has("globalCoins")) {
                TerraMCnetServer.setCoins(object.get("globalCoins").getAsInt());
            }
            if(object.has("addonVersion")) {
                TerraMCnetServer.checkUpdate(object.get("addonVersion").getAsString());
            }
            if(object.has("playerRank")) {
                TerraMCnetServer.setRank(object.get("playerRank").getAsString());
            }
            if(object.has("joinedRound")) {
                TerraMCnetServer.setInRound(object.get("joinedRound").getAsBoolean());
            }
            if(object.has("playTime")) {
                TerraMCnetServer.setOnlineTime(object.get("playTime").getAsString().replace("_", " "));
            }
            if(object.has("playerJoins")) {
                TerraMCnetServer.setJoins(object.get("playerJoins").getAsInt());
            }

            // Staff

            if(object.has("vanish")) {
                TerraMCnetServer.setVanish(object.get("vanish").getAsBoolean());
            }
            if(object.has("autoVanish")) {
                TerraMCnetServer.setAutoVanish(object.get("autoVanish").getAsBoolean());
            }
            if(object.has("serverTPS")) {
                TerraMCnetServer.setServerTps(object.get("serverTPS").getAsString());
            }
            if(object.has("serverCpuUsage")) {
                TerraMCnetServer.setCpuUsage(object.get("serverCpuUsage").getAsString());
            }
            if(object.has("serverHeapUsage")) {
                TerraMCnetServer.setHeapUsage(object.get("serverHeapUsage").getAsString());
            }

                // Reports

            if(object.has("reportAddonDataAdd")) {
                TerraMCnetServer.getReports().add(ReportData.fromString(object.get("reportAddonDataAdd").getAsString()));
            }
            if(object.has("reportAddonDataDel")) {
                int id = object.get("reportAddonDataDel").getAsInt();
                TerraMCnetServer.getReports().forEach(reportData -> {
                    if(reportData.getId() == id) {
                        TerraMCnetServer.getReports().remove(reportData);
                    }
                });
            }
            if(object.has("currentReportData")) {
                TerraMCnetServer.setCurrentReportData(CurrentReportData.fromString(object.get("currentReportData").getAsString()));
            }

                // Supports

            if(object.has("supportAddonDataAdd")) {
                TerraMCnetServer.getSupports().add(SupportData.fromString(object.get("supportAddonDataAdd").getAsString()));
            }
            if(object.has("supportAddonDataDel")) {
                int id = object.get("supportAddonDataDel").getAsInt();
                TerraMCnetServer.getSupports().forEach(supportData -> {
                    if(supportData.getId() == id) {
                        TerraMCnetServer.getSupports().remove(supportData);
                    }
                });
            }

                // Notify

            if(object.has("notifyLoad")) {
                PlayerNotify.loadFromString(object.get("notifyLoad").getAsString());
                LabyMod.getInstance().getGuiCustomAchievement().displayAchievement("§6Notify", "§7Daten wurden vom Server übertragen§7.");
            }
            if(object.has("notifyChange")) {
                String raw = object.get("notifyChange").getAsString();
                String name = raw.split("=")[0];
                boolean status = Boolean.parseBoolean(raw.split("=")[1]);
                if(PlayerNotify.getNotify(name) != null) {
                    PlayerNotify notify = PlayerNotify.getNotify(name);
                    notify.setEnabled(status);
                    NotifyGUI.displayNotify(notify, status);
                }
            }

            //

            if(object.has("playerStats")) {
                String raw = object.get("playerStats").getAsString();
                String type = raw.split(";")[0];

                if(type.equalsIgnoreCase("BuildFFA")) {
                    PlayerStats.BuildFFA.kills = Integer.parseInt(raw.split(";")[1]);
                    PlayerStats.BuildFFA.deaths = Integer.parseInt(raw.split(";")[2]);
                    PlayerStats.BuildFFA.kd = Double.parseDouble(raw.split(";")[3]);
                    PlayerStats.BuildFFA.points = Integer.parseInt(raw.split(";")[4]);
                }

                if(type.equalsIgnoreCase("KBFFA")) {
                    PlayerStats.KnockBackFFA.kills = Integer.parseInt(raw.split(";")[1]);
                    PlayerStats.KnockBackFFA.deaths = Integer.parseInt(raw.split(";")[2]);
                    PlayerStats.KnockBackFFA.kd = Double.parseDouble(raw.split(";")[3]);
                    PlayerStats.KnockBackFFA.points = Integer.parseInt(raw.split(";")[4]);
                }

                if(type.equalsIgnoreCase("ST")) {
                    PlayerStats.SoupTrainer.bowls = Integer.parseInt(raw.split(";")[1]);
                    PlayerStats.SoupTrainer.soups = Integer.parseInt(raw.split(";")[2]);
                }

                if(type.equalsIgnoreCase("OL")) {
                    PlayerStats.OneLine.wins = Integer.parseInt(raw.split(";")[1]);
                    PlayerStats.OneLine.loses = Integer.parseInt(raw.split(";")[2]);
                }

                if(type.equalsIgnoreCase("XP")) {
                    PlayerStats.XP.kills = Integer.parseInt(raw.split(";")[1]);
                    PlayerStats.XP.deaths = Integer.parseInt(raw.split(";")[2]);
                    PlayerStats.XP.kd = Double.parseDouble(raw.split(";")[3]);
                    PlayerStats.XP.wins = Integer.parseInt(raw.split(";")[4]);
                }

                if(type.equalsIgnoreCase("TDM")) {
                    PlayerStats.TDM.kills = Integer.parseInt(raw.split(";")[1]);
                    PlayerStats.TDM.deaths = Integer.parseInt(raw.split(";")[2]);
                    PlayerStats.TDM.kd = Double.parseDouble(raw.split(";")[3]);
                    PlayerStats.TDM.wins = Integer.parseInt(raw.split(";")[4]);
                    PlayerStats.TDM.loses = Integer.parseInt(raw.split(";")[5]);
                }

                if(type.equalsIgnoreCase("FFA")) {
                    PlayerStats.FFA.kills = Integer.parseInt(raw.split(";")[1]);
                    PlayerStats.FFA.deaths = Integer.parseInt(raw.split(";")[2]);
                    PlayerStats.FFA.kd = Double.parseDouble(raw.split(";")[3]);
                    PlayerStats.FFA.points = Integer.parseInt(raw.split(";")[4]);
                }

                if(type.equalsIgnoreCase("WaterFFA")) {
                    PlayerStats.WaterFFA.kills = Integer.parseInt(raw.split(";")[1]);
                    PlayerStats.WaterFFA.deaths = Integer.parseInt(raw.split(";")[2]);
                    PlayerStats.WaterFFA.kd = Double.parseDouble(raw.split(";")[3]);
                    PlayerStats.WaterFFA.points = Integer.parseInt(raw.split(";")[4]);
                }

            }

        }
    }
}
