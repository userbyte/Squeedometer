package squeek.squeedometer.client;

import com.mojang.blaze3d.systems.RenderSystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
// import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class SqueedometerHud extends DrawableHelper {
    protected static final Logger LOGGER = LogManager.getLogger();
    private MinecraftClient client;
    private TextRenderer textRenderer;

    // public SqueedometerHud(MinecraftClient client) {
    //     this.client = client;
    //     this.textRenderer = client.textRenderer;
    // }
    public SqueedometerHud() {

    }
    // private void updateValues() {
    //     return;
    // }

    private void draw() {
        this.client = MinecraftClient.getInstance();
        this.textRenderer = client.textRenderer;
        // Calculating Speed
        Vec3d playerPosVec = client.player.getPos();
        double travelledX = playerPosVec.x - client.player.prevX;
        double travelledZ = playerPosVec.z - client.player.prevZ;
        double currentSpeed = MathHelper.sqrt(travelledX * travelledX + travelledZ * travelledZ);
        // LOGGER.info("posX, posY, posZ = ({}, {}, {})", playerPosVec.x, playerPosVec.y, playerPosVec.z);
        // LOGGER.info("prevX, prevY, prevZ = ({}, {}, {})", client.player.prevX, client.player.prevY, client.player.prevZ);

        String currentSpeedText = String.format("%.2f blocks/sec", currentSpeed / 0.05F);

        // String aligntmentX = "bottom";

        int width = this.textRenderer.getStringWidth(currentSpeedText);
        int height = this.textRenderer.fontHeight;
        int paddingX = 2;
        int paddingY = 2;
        int marginX = 4;
        int marginY = 4;
        int left = 0 + marginX;
        int top = 0 + marginY;
        int realHeight = height + paddingY * 2 - 1;

        // left += client.getWindows().getScaledWidth();
        top += client.getWindow().getScaledHeight() - marginY * 2 - realHeight;

        // client

        left += paddingX;
        top += paddingY;

        // LOGGER.info("currentSpeed = {}", currentSpeed);

        // TODO: Drawbox
        // FIXME: Text Position
        // RenderSystem.pushMatrix();
        // // this.speedText = new LiteralText(String.format("%.2f", currentSpeed));
        // fill(5, 5, 15, 15, 0xFFFFFF);
        this.drawString(this.textRenderer, currentSpeedText, left, top, 14737632);
        // RenderSystem.popMatrix();
        return;
    }

    public void render() {
        this.draw();
    }
}