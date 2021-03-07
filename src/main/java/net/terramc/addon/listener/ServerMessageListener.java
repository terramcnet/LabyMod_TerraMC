package net.terramc.addon.listener;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.labymod.api.events.ServerMessageEvent;
import net.terramc.addon.TerraMCnetServer;
import net.terramc.addon.utils.ReportData;

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
        }
    }
}
