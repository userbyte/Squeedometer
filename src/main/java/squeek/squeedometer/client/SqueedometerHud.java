package squeek.squeedometer.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import squeek.squeedometer.config.ConfigWrapper;
import squeek.squeedometer.config.SqueedometerConfig.Position;

@Environment(EnvType.CLIENT)
public class SqueedometerHud {
    
    // Vars
    private MinecraftClient client;
    private TextRenderer textRenderer;

    private int color = ConfigWrapper.config.textColor;
    private int vertColor = ConfigWrapper.config.textColor;
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

        if (ConfigWrapper.config.changeColors) {
            // Every tick determine if speeds are increasing or decreasing and set color accordingly   
            tickCounter += tickDelta;
            if (tickCounter >= (float)ConfigWrapper.config.tickInterval) {
                if (currentSpeed < lastFrameSpeed) {
                    color = ConfigWrapper.config.deceleratingColor;
                } else if (currentSpeed > lastFrameSpeed) {
                    color = ConfigWrapper.config.acceleratingColor;
                } else {
                    color = ConfigWrapper.config.textColor;
                }

                if (currentVertSpeed < lastFrameVertSpeed) {
                    vertColor = ConfigWrapper.config.deceleratingColor;
                } else if (currentVertSpeed > lastFrameVertSpeed) {
                    vertColor = ConfigWrapper.config.acceleratingColor;
                } else {
                    vertColor = ConfigWrapper.config.textColor;
                }

                lastFrameSpeed = currentSpeed;
                lastFrameVertSpeed = currentVertSpeed;
                tickCounter = 0.0f;
            }
        }

        String currentVertSpeedText = "";
        String currentSpeedText = "";
        // Convert speeds to text
        if (ConfigWrapper.config.showVertical) {
            currentVertSpeedText = String.format("Vertical: %s", SpeedCalculator.speedText(currentVertSpeed, ConfigWrapper.config.speedUnit));
            currentSpeedText = String.format("Horizontal: %s", SpeedCalculator.speedText(currentSpeed, ConfigWrapper.config.speedUnit));
        } else {
            currentSpeedText = SpeedCalculator.speedText(currentSpeed, ConfigWrapper.config.speedUnit);
        }
        // Calculate text position
        int horizWidth = this.textRenderer.getWidth(currentSpeedText);
        int vertWidth = this.textRenderer.getWidth(currentVertSpeedText);
        int height = this.textRenderer.fontHeight;
        int paddingX = 2;
        int paddingY = 2;
        int marginX = 4;
        int marginY = 4;
        int left = 0 + marginX;
        int vertLeft = 0 + marginX;
        int top = 0 + marginY;
        int realHorizWidth = horizWidth + paddingX * 2 - 1;
        int realVertWidth = vertWidth + paddingX * 2 - 1;
        int realHeight = height + paddingY * 2 - 1;

        if (ConfigWrapper.config.position == Position.BOTTOM_LEFT) {
            top += client.getWindow().getScaledHeight() - marginY * 2 - realHeight;

            left += paddingX;
            vertLeft += paddingX;
            top += paddingY;
        }

        if (ConfigWrapper.config.position == Position.BOTTOM_RIGHT) {
            top += client.getWindow().getScaledHeight() - marginY * 2 - realHeight;
            left += client.getWindow().getScaledWidth() - marginX * 2 - realHorizWidth;
            vertLeft += client.getWindow().getScaledWidth() - marginX * 2 - realVertWidth;

            left += paddingX;
            vertLeft += paddingX;
            top += paddingY;
        }

        if (ConfigWrapper.config.position == Position.BOTTOM_MIDDLE) {
            top += client.getWindow().getScaledHeight() - marginY * 2 - realHeight;
            top -= 45; // adjust top var so text is above hotbar
        }

        if (ConfigWrapper.config.position == Position.TOP_LEFT) {
            left += paddingX;
            vertLeft += paddingX;
            top += paddingY;

            if (ConfigWrapper.config.showVertical) {
                top += 10;
            }
        }

        if (ConfigWrapper.config.position == Position.TOP_RIGHT) {
            left += client.getWindow().getScaledWidth() - marginX * 2 - realHorizWidth;
            vertLeft += client.getWindow().getScaledWidth() - marginX * 2 - realVertWidth;

            left += paddingX;
            vertLeft += paddingX;
            top += paddingY;

            if (ConfigWrapper.config.showVertical) {
                top += 10;
            }
        }

        if (ConfigWrapper.config.position == Position.TOP_MIDDLE) {
            top += paddingY;

            if (ConfigWrapper.config.showVertical) {
                top += 10;
            }
        }

        if (ConfigWrapper.config.position == Position.ABOVE_CROSSHAIR) {
            top += client.getWindow().getScaledHeight()/2 - marginY * 2 - realHeight;
        }

        if (ConfigWrapper.config.position == Position.BELOW_CROSSHAIR) {
            top += client.getWindow().getScaledHeight()/2 - marginY * 2 - realHeight;
            top += 25;
        }

        if (ConfigWrapper.config.hideWhenZero) {
            // if hideWhenZero is enabled, and both currentVertSpeed and currentSpeed
            if (ConfigWrapper.config.showVertical) {
                // only check if both are 0 if showVertical is enabled
                // this prevents the shit from being shown when vertical speed is not 0, but horizontal speed is
                if (currentVertSpeed == 0 && currentSpeed == 0) { return; }
            } else {
                // if showVertical is not enabled, dont even bother checking its speed
                if (currentSpeed == 0) { return; }
            }
        }

        // Render the text
        if (ConfigWrapper.config.position == Position.TOP_MIDDLE | ConfigWrapper.config.position == Position.BOTTOM_MIDDLE | ConfigWrapper.config.position == Position.ABOVE_CROSSHAIR | ConfigWrapper.config.position == Position.BELOW_CROSSHAIR) {
            // if the position is a centered position, do this shit
            this.textRenderer.drawWithShadow(matrixStack, currentVertSpeedText, client.getWindow().getScaledWidth()/2 - vertWidth/2, top - 10, vertColor);
            this.textRenderer.drawWithShadow(matrixStack, currentSpeedText, client.getWindow().getScaledWidth()/2 - horizWidth/2, top, color);
        } else {
            // if the position is left/right justified, pass in the calculated position variables
            this.textRenderer.drawWithShadow(matrixStack, currentVertSpeedText, vertLeft, top - 10, vertColor);
            this.textRenderer.drawWithShadow(matrixStack, currentSpeedText, left, top, color);   
        }
        return;
    }
}