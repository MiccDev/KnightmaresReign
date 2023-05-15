package xyz.knightmaresreign.utils;

import java.util.Random;

import org.bukkit.util.Vector;

public class MathUtils {

	private static Random random = new Random(2819823674L);
	
	public static Vector randomPointInSphere(double radius) {
		double x = MathUtils.random.nextDouble() * 2 - 1;
		double y = MathUtils.random.nextDouble() * 2 - 1;
		double z = MathUtils.random.nextDouble() * 2 - 1;
		x *= 1 / Math.sqrt(x * x + y * y + z * z);
		y *= 1 / Math.sqrt(x * x + y * y + z * z);
		z *= 1 / Math.sqrt(x * x + y * y + z * z);
		return new Vector(x * radius, y * radius, z * radius);
	}
	
	public static int getRandomInt(int min, int max) {
		return random.nextInt((max - min) + 1) + min;
	}
	
}
