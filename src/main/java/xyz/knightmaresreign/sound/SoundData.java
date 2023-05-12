package xyz.knightmaresreign.sound;

import org.bukkit.Sound;

public class SoundData {
	public static SoundData DEFAULT = new SoundData().setSound(Sound.ENTITY_GENERIC_EXPLODE);
	
	private Sound sound;
	private float pitch = 1;
	private float volume = 0.5f;
	
	public Sound getSound() {
		return sound;
	}
	
	public SoundData setSound(Sound sound) {
		this.sound = sound;
		return this;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public SoundData setPitch(float pitch) {
		this.pitch = pitch;
		return this;
	}
	
	public float getVolume() {
		return volume;
	}
	
	public SoundData setVolume(float volume) {
		this.volume = volume;
		return this;
	}
	
}
