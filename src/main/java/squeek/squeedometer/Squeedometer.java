package squeek.squeedometer;

import net.fabricmc.api.ClientModInitializer;
import squeek.squeedometer.client.SqueedometerHud;

public class Squeedometer implements ClientModInitializer {

	public static SqueedometerHud squeedometerHud = new SqueedometerHud();

	@Override
	public void onInitializeClient() {
		System.out.println("[Squeedometer] Loaded");

		String MOD_NAME = "Squeedometer";
	}
}
