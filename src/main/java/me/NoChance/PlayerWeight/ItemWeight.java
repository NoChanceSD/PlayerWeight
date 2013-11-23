package me.NoChance.PlayerWeight;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemWeight extends ItemStack {

	private int weight;
	private Material type;

	public ItemWeight(Material type) {
		super(type);
		this.type = type;
		this.weight = getConfigWeight();
	}

	public ItemWeight() {
		this.weight = 0;
	}

	public static ItemWeight getItemWeight(ItemStack i) {
		if (i == null)
			return new ItemWeight();
		else
			return new ItemWeight(i.getType());
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Material getMaterial() {
		return this.type;
	}

	private int getConfigWeight() {
		return PlayerWeight.plugin.getConfig().getInt(getMaterial().toString());
	}
}
