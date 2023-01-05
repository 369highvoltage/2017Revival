// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DriverConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PnuematicSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandPS4Controller m_driverController =
      new CommandPS4Controller(DriverConstants.kDriverControllerPort);

  //Setup the subsystems here
  public static PnuematicSubsystem m_pcm = new PnuematicSubsystem();
  public static DrivetrainSubsystem m_dt = new DrivetrainSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    //^This is how commands should be executed for shooting and whatnot

    if(m_driverController.cross().getAsBoolean()){
    //If X is pressed, then set gear to high      
    m_dt.setGearbox(2);
    }

    else if(m_driverController.square().getAsBoolean()){
    //else if Square is pressed, set gear to low
    m_dt.setGearbox(1);
    }

    //Drive the robot using the controller in tank drive.
    m_dt.tankDrive(m_driverController.getLeftY(), m_driverController.getRightY());
  }
}
