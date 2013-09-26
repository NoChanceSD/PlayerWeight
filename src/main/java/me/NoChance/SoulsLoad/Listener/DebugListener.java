package me.NoChance.SoulsLoad.Listener;

import me.NoChance.SoulsLoad.SoulsLoad;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DebugListener implements Listener {
	
	private SoulsLoad plugin;

	public DebugListener(SoulsLoad plugin) {
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
