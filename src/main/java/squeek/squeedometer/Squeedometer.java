package squeek.squeedometer;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import squeek.squeedometer.client.SqueedometerHud;
import squeek.squeedometer.config.ConfigWrapper;
import squeek.squeedometer.config.SqueedometerConfig;

public class Squeedometer implements ClientModInitializer {

	public static SqueedometerHud squeedometerHud;

	@Override
	public void onInitializeClient() {
		System.out.println("[Squeedometer] Loaded");

		AutoConfig.register(SqueedometerConfig.class, JanksonConfigSerializer::new);
		ConfigWrapper.loadConfig();
		squeedometerHud = new SqueedometerHud(); 
	}
}
