package xyz.knightmaresreign.utils;

import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SkinUtils {

	public static String[] getSkin(String name) {
		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
			InputStreamReader reader = new InputStreamReader(url.openStream());
			@SuppressWarnings("deprecation")
			String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();

			URL url2 = new URL(
					"https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
			InputStreamReader reader2 = new InputStreamReader(url2.openStream());
			@SuppressWarnings("deprecation")
			JsonObject properties = new JsonParser().parse(reader2).getAsJsonObject().get("properties").getAsJsonArray()
					.get(0).getAsJsonObject();
			String texture = properties.get("value").getAsString();
			String signature = properties.get("signature").getAsString();
			
			return new String[] { texture, signature };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] {};
	}
	
	public static String[] getSkinByUUID(String uuid) {
		try {
			URL url = new URL(
					"https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
			InputStreamReader reader = new InputStreamReader(url.openStream());
			@SuppressWarnings("deprecation")
			JsonObject properties = new JsonParser().parse(reader).getAsJsonObject().get("properties").getAsJsonArray()
					.get(0).getAsJsonObject();
			String texture = properties.get("value").getAsString();
			String signature = properties.get("signature").getAsString();
			
			return new String[] { texture, signature };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[] {};
	}
	
}
