package me.redepicness.serverstatus.bukkit;

import me.redepicness.socketmessenger.api.Data;
import me.redepicness.socketmessenger.bukkit.SocketAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class main extends JavaPlugin{

    @Override
    public void onEnable() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Data data = new Data();
            ArrayList<String> players = Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toCollection(ArrayList::new));
            data.addObject("players", players);
            data.addInt("playerCount", players.size());
            data.addString("version", Bukkit.getBukkitVersion());
            data.addString("ip", Bukkit.getIp());
            SocketAPI.sendDataToServer("ServerStatus", data);
        }, 20, 20);
    }

}
