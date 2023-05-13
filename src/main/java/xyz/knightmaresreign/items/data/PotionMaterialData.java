package xyz.knightmaresreign.items.data;

import org.bukkit.potion.PotionType;

public class PotionMaterialData extends ItemData {
	private PotionType potionType;

	public PotionMaterialData(PotionType type) {
		this.potionType = type;
	}
	
	public PotionType getPotionType() {
		return potionType;
	}

	public void setPotionType(PotionType potionType) {
		this.potionType = potionType;
	}
	
	
}
