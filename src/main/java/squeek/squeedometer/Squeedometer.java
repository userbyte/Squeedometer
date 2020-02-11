package squeek.squeedometer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import squeek.squeedometer.client.SqueedometerHud;

public class Squeedometer implements ClientModInitializer {

	private static FabricKeyBinding keyBinding;
	
	public static SqueedometerHud squeedometerHud = new SqueedometerHud();

	protected static final Logger LOGGER = LogManager.getLogger("Squeedometer");

	// TODO: Draw speed

	@Override
	public void onInitializeClient() {
		System.out.println("[Squeedometer] Loaded");
		// ClientTickCallback.EVENT.register();

		String MOD_NAME = "Squeedometer";

		keyBinding = FabricKeyBinding.Builder.create(new Identifier("squeedometer", "speed"), InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_R, MOD_NAME).build();

		KeyBindingRegistry.INSTANCE.addCategory(MOD_NAME);
		KeyBindingRegistry.INSTANCE.register(keyBinding);

		ClientTickCallback.EVENT.register((client) -> {
			// if (squeedometerHud == null) {
			// 	squeedometerHud = new SqueedometerHud(client);
			// }
		
			// if (keyBinding.isPressed()) {
			// 	// // LOGGER.debug("chunkX, chunkY, chunkZ = (%d, %d, %d)", client.player.chunkX, client.player.chunkY,
			// 	// // 		client.player.chunkZ);
			// 	// Vec3d playerPosVec = client.player.getPos();
			// 	// LOGGER.info("posX, posY, posZ = ({}, {}, {})", playerPosVec.x, playerPosVec.y, playerPosVec.z);

			// 	// LOGGER.info("prevX, prevY, prevZ = ({}, {}, {})", client.player.prevX, client.player.prevY,
			// 	// 		client.player.prevZ);

			// 	// // Calculate
			// 	// double travelledX = playerPosVec.x - client.player.prevX;
			// 	// double travelledY = playerPosVec.y - client.player.prevY;
			// 	// double currentSpeed = MathHelper.sqrt(travelledX * travelledX + travelledY * travelledY);

			// 	// LOGGER.info("currentSpeed = {}", currentSpeed);
			// 	squeedometerHud.render();
			// }
			// System.out.println("[Squeedometer] Game Tick");
			// if (client != null) {
			// 	System.out.printf("[Squeedometer] Loaded %f", client.player.forwardSpeed);
			// }
		});
	}
}
