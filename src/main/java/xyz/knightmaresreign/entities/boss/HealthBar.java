package xyz.knightmaresreign.entities.boss;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import xyz.knightmaresreign.KnightmaresReign;

public class HealthBar {

	private String bossTitle;
	private BossBar bar;
	private BarColor c;
	private LivingEntity e;
	
	public HealthBar(String bossTitle, LivingEntity e) {
		this(bossTitle, BarColor.PURPLE, e);
	}	
	
	public HealthBar(String bossTitle, BarColor c, LivingEntity e) {
		this.bossTitle = bossTitle;
		this.c = c;
		this.e = e;
		
		createBar();
	}
	
	public void addPlayer(Player player) {
		bar.addPlayer(player);
	}
	
	public BossBar getBar() {
		return bar;
	}
	
	public void createBar() {
		bar = Bukkit.createBossBar(KnightmaresReign.getInstance().toColour(bossTitle), c, BarStyle.SEGMENTED_10);
		bar.setVisible(true);
	}
	
	public void onDamage() {
		if(e.isDead()) {
			bar.removeAll();
			bar.setVisible(false);
			return;
		}
		double maxHealth = e.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		bar.setProgress(e.getHealth() / maxHealth);
	}
	
}
