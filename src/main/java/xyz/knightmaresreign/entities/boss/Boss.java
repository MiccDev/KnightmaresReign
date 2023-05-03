package xyz.knightmaresreign.entities.boss;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.CustomEntity;
import xyz.knightmaresreign.entities.boss.attacks.Attack;
import xyz.knightmaresreign.entities.boss.attacks.AttackPhase;

public abstract class Boss<T extends Enum<T>> extends CustomEntity {

	private Boss<T> boss;
	
	private String displayName;
	private HealthBar bar;
	private BarColor barColour;
	
	public int phase = 1;
	
	private boolean battleStarted;
	
	private BukkitRunnable bossSchedule;
	
	private Class<T> attackStates;
	private boolean hasFinishedAttack;
	public Method currentAttack;
	
	private Map<T, Attack> attacks;
	private Map<String, Method> attackAnnotations;
	
	private List<Player> playersInBossRoom;
	
	public double blockDamage;
	private List<FallingBlock> blocks;
	
	private Location bounds1;
	private Location bounds2;
	
	public Dialog<T> dialog;
	
	private Location location;
	
	public Boss(String id, String displayName, Class<T> attackStates, EntityType type) {
		super(id, KnightmaresReign.getInstance().toComponent(displayName), type);
		this.displayName = displayName;
		this.attackStates = attackStates;
		
		this.attacks = new HashMap<T, Attack>();
		this.attackAnnotations = new HashMap<String, Method>();
		this.hasFinishedAttack = true;
		
		this.playersInBossRoom = new ArrayList<Player>();
		
		blockDamage = 5;
		blocks = new ArrayList<FallingBlock>();
		dialog = new Dialog<T>(this);
		battleStarted = false;
		
		boss = null;
		
		@SuppressWarnings("unchecked")
		Class<? extends Boss<T>> bossClass = (Class<? extends Boss<T>>) getClass();
		Method[] methods = bossClass.getMethods();
		for(Method method : methods) {
			AttackPhase anno = method.getAnnotation(AttackPhase.class);
			if(anno != null) {
				attackAnnotations.put(anno.state(), method);
			}
		}
	}
	
	@Override
	public void setup(Entity entity) {
		LivingEntity e = (LivingEntity) entity;
		
		init(e);
		
		e.setCustomNameVisible(true);
		
		bar = new HealthBar(displayName, barColour, e);
		
		bossSchedule = new BukkitRunnable() {
			@Override
			public void run() {
				if(e.isDead()) {
					onDeath(e);
					cancel();
					return;
				}
				
				location = e.getLocation();
				
				playersInBossRoom  = Bukkit.getOnlinePlayers().stream().filter(p -> {
					return KnightmaresReign.getInstance().isInRegion(
							p.getLocation(),
							bounds1,
							bounds2
					);
				}).collect(Collectors.toList());
				if(playersInBossRoom.isEmpty()) return;

				for(Player player : playersInBossRoom) {
					bar.addPlayer(player);
				}
				
				if(battleStarted && hasFinishedAttack) {
					chooseAttack();
					if(currentAttack == null) return;
					
					try {
						currentAttack.invoke(boss, e);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace(); 
					}
				}
				
				tick(e);
				
				Iterator<FallingBlock> it = blocks.iterator();
				while(it.hasNext()) {
					FallingBlock b = it.next();
					for(Player plr : playersInBossRoom) {
						if(!plr.getWorld().equals(e.getWorld())) continue;
						double distance = plr.getLocation().distance(b.getLocation());
						if(distance <= 1.98) plr.damage(blockDamage);
					}
					if(b.isDead()) it.remove();
				}
			}
		};
		
		bossSchedule.runTaskTimer(KnightmaresReign.getInstance(), 10L, 10L);
	}
	
	public void startBattle() {
		battleStarted = true;
	}
	
	public void onDamage() {
		bar.onDamage();
	}
	
	public void onDeath(LivingEntity e) {
		battleStarted = false;
	}
	
	public void tick(LivingEntity e) {};
	public abstract void init(LivingEntity entity);
	
	public void chooseAttack() {
		EnumSet<T> keyAttackStatesEnumKey = EnumSet.allOf(attackStates);
		List<T> keyAttackStates = keyAttackStatesEnumKey.stream().collect(Collectors.toList());
		int size = keyAttackStates.size();
		
		int random = KnightmaresReign.getInstance().getRandom(0, size);
		T attack = keyAttackStates.get(random);
		
		Method attackMethod = attackAnnotations.get(attack.name());
		if(attackMethod == null) {
			chooseAttack();
			return;
		}
		
		AttackPhase attackAnnotation = attackMethod.getAnnotation(AttackPhase.class);
		if(attackAnnotation.phase() != phase) {
			chooseAttack();
			return;
		}
		
		currentAttack = attackMethod;
		hasFinishedAttack = false;
	}
	
	public FallingBlock spawnBlock(Location l, Material m, Vector vel) {
		FallingBlock fb = l.getWorld().spawnFallingBlock(l, m.createBlockData());
		l.getWorld().playSound(l, Sound.BLOCK_ANVIL_PLACE, 0.5f, 0.5f);
		fb.addScoreboardTag("thrownBlock");
		if(!vel.equals(new Vector(0, 0, 0)))
			fb.setVelocity(vel.normalize());
		fb.setDropItem(false);
		fb.setHurtEntities(true);
		blocks.add(fb);
		return fb;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void addAttack(T state, Attack attack) {
		attacks.put(state, attack);
	}
	
	public void setBarColour(BarColor barColour) {
		this.barColour = barColour;
	}
	
	public void setBoss(Boss<T> boss) {
		this.boss = boss;
	}
	
	public void setRoomBounds(Location bounds1, Location bounds2) {
		this.bounds1 = bounds1;
		this.bounds2 = bounds2;
	}
	
	public List<Player> getPlayersInBossRoom() {
		return playersInBossRoom;
	}
	
	public void finishAttack() {
		hasFinishedAttack = true;
	}

}