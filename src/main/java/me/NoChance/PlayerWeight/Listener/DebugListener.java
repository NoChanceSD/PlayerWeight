package me.NoChance.PlayerWeight.Listener;

import me.NoChance.PlayerWeight.PlayerWeight;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DebugListener implements Listener {
	
	private PlayerWeight plugin;

	public DebugListener(PlayerWeight plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if (plugin.debug) {
			p.sendMessage("Speed: " + p.getWalkSpeed());
		}
	}
}
