package me.NoChance.PlayerWeight;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemWeight extends ItemStack {

	private double weight;
	private Material type;
	private int amount;

	public ItemWeight(Material type, int amount) {
		super(type);
		this.type = type;
		this.weight = getConfigWeight();
		this.amount = amount;
	}

	public ItemWeight() {
		this.weight = 0;
	}

	public static ItemWeight getItemWeight(ItemStack i) {
		if (i == null)
			return new ItemWeight();
		else
			return new ItemWeight(i.getType(), i.getAmount());
	}

	public double getWeight() {
		return amount * weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Material getMaterial() {
		return this.type;
	}

	private double getConfigWeight() {
		return PlayerWeight.plugin.getConfig().getDouble(getMaterial().toString());
	}
}
