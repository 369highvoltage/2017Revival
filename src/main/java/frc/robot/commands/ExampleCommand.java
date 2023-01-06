// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** An example command that uses an example subsystem. */
public class ExampleCommand extends CommandBase {
  // Include/Omit any of these functions depending on your needs.
  Runnable init, end;
  Supplier<double> input;
  Supplier<boolean> condition;
  Consumer<double> output;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(Runnable initFunction, Supplier<double> inputFunction, Supplier<boolean> conditionFunction, Consumer<double> outputFunction, Runnable endFunction, SubsystemBase[] dependencies) {
    init = initFunction;
    input = inputFunction;
    condition = conditionFunction;
    output = outputFunction;
    end = endFunction;
    
    for(SubsystemBase dependency : dependencies)
      addRequirements(dependency);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    init.run();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double value = input.get();
    double result = doSomethingWithInput(value);
    output.accept(result);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    end.run();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return condition.get();
  }
}
