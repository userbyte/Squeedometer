package squeek.squeedometer.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Config(name = "squeedometer")
public class SqueedometerConfig implements ConfigData {
    public boolean enabled = true;
    public boolean showVertical = false;
    public boolean changeColors = true;
    public boolean hideWhenZero = false;

    @ConfigEntry.BoundedDiscrete(max = 100, min = 1)
    @ConfigEntry.Gui.Tooltip(count = 2)
    public int tickInterval = 15;

    @ConfigEntry.ColorPicker
    public int textColor = 0xFFFFFF;

    @ConfigEntry.ColorPicker
    public int acceleratingColor = 0x00FF00;

    @ConfigEntry.ColorPicker
    public int deceleratingColor = 0xFF0000;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Position position = Position.BOTTOM_LEFT;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public SpeedUnit speedUnit = SpeedUnit.METERS_PER_SECOND;

    public static enum Position {
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_MIDDLE,
        TOP_LEFT,
        TOP_RIGHT,
        TOP_MIDDLE,
        ABOVE_CROSSHAIR,
        BELOW_CROSSHAIR
    }

    public static enum SpeedUnit {
        METERS_PER_SECOND,
        KILOMETERS_PER_HOUR
    }
}