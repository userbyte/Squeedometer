package squeek.squeedometer.client;

import squeek.squeedometer.config.SqueedometerConfig;

public class SpeedCalculator {
    public static String speedText(double speed, SqueedometerConfig.SpeedUnit speedUnit) {
        return switch (speedUnit) {
            case BLOCKS_PER_SECOND -> String.format("%.2f m/s", metersPerSecond(speed));
            case KILOMETERS_PER_HOUR -> String.format("%.2f km/h", kilometersPerSecond(speed));
        };
    }

    private static double metersPerSecond(double speed) {
        return speed / 0.05F;
    }

    private static double kilometersPerSecond(double speed) {
        return metersPerSecond(speed) * 3.6;
    }
}
