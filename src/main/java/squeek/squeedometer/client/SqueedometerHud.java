package squeek.squeedometer.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class SqueedometerHud {
    private final int RED = 0xFF0000;
    private final int GREEN = 0x00FF00;
    private final int WHITE = 0xFFFFFF;
    private MinecraftClient client;
    private TextRenderer textRenderer;

    private int color = WHITE;
    private int vertColor = WHITE;
    private double lastFrameSpeed = 0.0;
    private double lastFrameVertSpeed = 0.0;
    private float tickCounter = 0.0f;

    public void draw(MatrixStack matrixStack, float tickDelta) {
        this.client = MinecraftClient.getInstance();
        this.textRenderer = client.textRenderer;
        // Calculating Speed
        Vec3d playerPosVec = client.player.getPos();
        double travelledX = playerPosVec.x - client.player.prevX;
        double travelledZ = playerPosVec.z - client.player.prevZ;
        double currentSpeed = (double)MathHelper.sqrt((float)(travelledX * travelledX + travelledZ * travelledZ));
        double currentVertSpeed = playerPosVec.y - client.player.prevY;

        // Every tick determine if speeds are increasing or decreasing and set color accordingly   
        tickCounter += tickDelta;
        if (tickCounter >= 1.0f) {
            if (currentSpeed < lastFrameSpeed) {
                color = RED;
            } else if (currentSpeed > lastFrameSpeed) {
                color = GREEN;
            } else {
                color = WHITE;
            }

            if (currentVertSpeed < lastFrameVertSpeed) {
                vertColor = RED;
            } else if (currentVertSpeed > lastFrameVertSpeed) {
                vertColor = GREEN;
            } else {
                vertColor = WHITE;
            }

            lastFrameSpeed = currentSpeed;
            lastFrameVertSpeed = currentVertSpeed;
            tickCounter = 0.0f;
        }

        // Convert speeds to text
        String currentVertSpeedText = String.format("Vertical: %.2f blocks/sec", currentVertSpeed / 0.05F);
        String currentSpeedText = String.format("Horizontal: %.2f blocks/sec", currentSpeed / 0.05F);

        // Calculate text position
        int width = this.textRenderer.getWidth(currentSpeedText);
        int height = this.textRenderer.fontHeight;
        int paddingX = 2;
        int paddingY = 2;
        int marginX = 4;
        int marginY = 4;
        int left = 0 + marginX;
        int top = 0 + marginY;
        int realHeight = height + paddingY * 2 - 1;

        top += client.getWindow().getScaledHeight() - marginY * 2 - realHeight;

        left += paddingX;
        top += paddingY;
        
        // Render the text
        this.textRenderer.drawWithShadow(matrixStack, currentVertSpeedText, left, top - 10, vertColor);
        this.textRenderer.drawWithShadow(matrixStack, currentSpeedText, left, top, color);

        return;
    }
}