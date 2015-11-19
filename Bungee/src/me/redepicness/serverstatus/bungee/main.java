package me.redepicness.serverstatus.bungee;

import me.redepicness.socketmessenger.api.Data;
import me.redepicness.socketmessenger.api.ReceivedDataEvent;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.ArrayList;

public class main extends Plugin implements Listener {

    @Override
    public void onEnable() {
        BungeeCord.getInstance().getPluginManager().registerListener(this, this);
    }

    @EventHandler
    public void onDataReceive(ReceivedDataEvent e){
        if(e.getChannel().equals("ServerStatus")){
            Data data = e .getData();
            ArrayList<String> players = data.getObject("players");
            int playerCount = data.getInt("playerCount");
            String bukkitVersion = data.getString("version");
            String ip = data.getString("ip");
            BungeeCord.getInstance().broadcast("Server Status update from "+e.getSender()+"!");
            String pl = "";
            for(String name : players){
                pl += ", "+name;
            }
            BungeeCord.getInstance().broadcast("Players: "+pl.substring(2));
            BungeeCord.getInstance().broadcast("Player Count: "+playerCount);
            BungeeCord.getInstance().broadcast("Bukkit Version: "+bukkitVersion);
            BungeeCord.getInstance().broadcast("Ip: "+ip);
        }
    }

}
