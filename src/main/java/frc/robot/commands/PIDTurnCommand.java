package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import java.util.function.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PIDTurnCommand extends PIDCommand {
    public PIDTurnCommand(DoubleSupplier currentAngleInputFunction, DoubleConsumer motorTurnOutputFunction, double targetValue, double kP, double kI, double kD, SubsystemBase[] dependencies) {
        super(
            new PIDController(kP, kI, kD),
            currentAngleInputFunction,
            targetValue,
            motorTurnOutputFunction,
            dependencies
        );
    }

    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}