package frc.robot;

import java.util.function.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TeleopDriveCommand extends CommandBase {
    private final Supplier<double> leftStickInput, rightStickInput;
    private final BiConsumer<double, double> driveOutput;

    public TeleopDriveCommand(Supplier<double> leftStickInputFunction, Supplier<double> rightStickInputFunction, BiConsumer<double> driveOutputFunction, SubsystemBase[] dependencies) {
        this.leftStickInput = leftStickInputFunction;
        this.rightStickInput = rightStickInputFunction;
        this.driveOutput = driveOutputFunction; 

        for(SubsystemBase dependency : dependencies)
            addRequirements(dependency);
    }
    
    @Override
    public void execute() {
        this.driveOutput.accept(this.leftStickInput.get(), this.rightStickInput.get());
    }
  
    @Override
    public void end(boolean interrupted) {
        this.driveOutput.accept(0.0, 0.0);
    }
}
