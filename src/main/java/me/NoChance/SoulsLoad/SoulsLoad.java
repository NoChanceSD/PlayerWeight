package me.NoChance.SoulsLoad;

import me.NoChance.SoulsLoad.Listener.DebugListener;
import me.NoChance.SoulsLoad.Listener.PlayerListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SoulsLoad extends JavaPlugin implements Listener {

	public boolean debug;
	public WeightManager wM;
	public static SoulsLoad plugin;

	@Override
	public void onEnable() {
		SoulsLoad.plugin = this;
		saveDefaultConfig();
		this.reloadConfig();
		if (getConfig().getBoolean("Debug"))
			new DebugListener(this);
		new PlayerListener(this);
		this.wM = new WeightManager(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equals("sl")) {
			if (args.length == 1) {
				if (sender instanceof Player && getConfig().getBoolean("Debug")) {
					Player p = (Player) sender;
					if (args[0].equalsIgnoreCase("debug") && p.hasPermission("soulsload.debug")) {
						if (!debug) {
							debug = true;
						} else if (debug) {
							debug = false;
						}
						return true;
					}

				}
				if (args[0].equalsIgnoreCase("reload")) {
					getServer().getPluginManager().disablePlugin(this);
					getServer().getPluginManager().enablePlugin(this);
					for (Player p : getServer().getOnlinePlayers()) {
						wM.sumWeight(p);
					}
					sender.sendMessage("SoulsLoad Reloaded!");
					return true;
				}
			}
		}
		return false;
	}
}