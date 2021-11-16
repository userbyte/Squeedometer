package squeek.squeedometer.client;

import squeek.squeedometer.config.SqueedometerConfig;

public class SpeedCalculator {
    public static String speedText(double speed, SqueedometerConfig.SpeedUnit speedUnit) {
        if (speedUnit == SqueedometerConfig.SpeedUnit.BLOCKS_PER_SECOND) {
            return String.format("%.2f blocks/sec", metersPerSecond(speed));
        } else if (speedUnit == SqueedometerConfig.SpeedUnit.KILOMETERS_PER_HOUR) {
            return String.format("%.2f km/h", kilometersPerSecond(speed));
        } else {
            return null;
        }
    }

    private static double metersPerSecond(double speed) {
        return speed / 0.05F;
    }

    private static double kilometersPerSecond(double speed) {
        return metersPerSecond(speed) * 3.6;
    }
}
