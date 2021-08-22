package squeek.squeedometer;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import squeek.squeedometer.client.SqueedometerHud;
import squeek.squeedometer.config.SqueedometerConfig;

public class Squeedometer implements ClientModInitializer {

	public static SqueedometerHud squeedometerHud = new SqueedometerHud();

	@Override
	public void onInitializeClient() {
		System.out.println("[Squeedometer] Loaded");

		String MOD_NAME = "Squeedometer";
		AutoConfig.register(SqueedometerConfig.class, JanksonConfigSerializer::new);
		squeedometerHud.loadConfig(); 
	}
}
