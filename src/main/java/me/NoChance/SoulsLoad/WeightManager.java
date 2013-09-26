package me.NoChance.SoulsLoad;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WeightManager {

	private SoulsLoad plugin;
	private int maxWeight;
	private double lessThan;
	private double between1;
	private double between1_1;
	private double between2;
	private double between2_1;
	private double biggerThan;
	private double speedPercent;
	private double speedPercent1;
	private double speedPercent2;
	private double speedPercent3;

	public WeightManager(SoulsLoad plugin) {
		this.plugin = plugin;
		loadConfigVariables();
	}

	public void sumWeight(Player p) {
		int weight = 0;
		for (ItemStack i : p.getInventory().getContents()) {
			weight += ItemWeight.getItemWeight(i).getWeight();
		}
		for (ItemStack i : p.getInventory().getArmorContents()) {
			weight += ItemWeight.getItemWeight(i).getWeight();
		}
		calculateWeightPercentage(weight, p);
	}

	public void calculateWeightPercentage(int weight, Player p) {
		float weightPercent = (float) weight / getMaxW();
		if (weightPercent > 1)
			p.setExp(1);
		else
			p.setExp(weightPercent);
		calculateSpeed(weightPercent, p);
	}

	public void calculateSpeed(float weightPercent, Player p) {
		if (weightPercent <= lessThan)
			p.setWalkSpeed(speed(speedPercent));
		if (weightPercent >= between1 && weightPercent <= between1_1)
			p.setWalkSpeed(speed(speedPercent1));
		if (weightPercent >= between2 && weightPercent <= between2_1)
			p.setWalkSpeed(speed(speedPercent2));
		if (weightPercent > biggerThan)
			p.setWalkSpeed(speed(speedPercent3));
	}

	public int getMaxW() {
		return this.maxWeight;
	}

	public float speed(double percent) {
		return (float) (0.2 * percent);
	}

	public void loadConfigVariables() {
		maxWeight = plugin.getConfig().getInt("Max Weight");
		lessThan = plugin.getConfig().getDouble("Less And Equal To.Percentage") / 100;
		speedPercent = plugin.getConfig().getDouble("Less And Equal To.SpeedPercent") / 100;
		splitBetween(plugin.getConfig().getString("Between.Percentage"), 1);
		speedPercent1 = plugin.getConfig().getDouble("Between.SpeedPercent") / 100;
		splitBetween(plugin.getConfig().getString("Between1.Percentage"), 2);
		speedPercent2 = plugin.getConfig().getDouble("Between1.SpeedPercent") / 100;
		biggerThan = plugin.getConfig().getDouble("Bigger Than.Percentage") / 100;
		speedPercent3 = plugin.getConfig().getDouble("Bigger Than.SpeedPercent") / 100;
	}

	public void splitBetween(String a, int p) {
		String[] between = a.split(",");
		if (p == 1) {
			between1 = Double.parseDouble(between[0]) / 100;
			between1_1 = Double.parseDouble(between[1]) / 100;
		}
		if (p == 2) {
			between2 = Double.parseDouble(between[0]) / 100;
			between2_1 = Double.parseDouble(between[1]) / 100;
		}
	}
}
