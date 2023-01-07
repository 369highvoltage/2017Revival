package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.commands.TeleopDriveCommand;

public class RobotContainer {
    // Declare objects here
    private final Constants constants;
    
    private final CommandPS4Controller driverController;
    private final CommandPS4Controller operatorController;
    
    private final PneumaticsControlModule pneumatics;

    public static SendableChooser<> autonomousChooser;

    DriveSubsystem driveSubsystem;

    public RobotContainer() {
        this.constants = new Constants();

        this.driverController = new CommandPS4Controller(constants.getInt("driverControllerPort"));
        this.operatorController = new CommandPS4Controller(constants.getInt("operatorControllerPort"));
        
        this.pneumatics = new PneumaticsControlModule(constants.getInt("PCMPort"));
        this.drivetrainSubsystem = new DrivetrainSubsystem(constants);

        this.autonomousChooser = new SendableChooser<>();
        this.autonomousChooser.setDefaultOption("Middle", new MiddleAutonomousCommand());
        this.autonomousChooser.addOption("Left", new LeftAutonomousCommand());
        //SmartDashboard.putData(autonomousChooser);
        
        // Separate functions for button bindings & default commands
        this.configureButtonBindings();
        this.configureDefaultCommands();
    }

    private void configureButtonBindings() {
        this.operatorController.cross().debounce(0.1).whileTrue(new ShootCommand(
            () -> operatorController.getR2Axis(),
            (speed) -> shooterSubsystem.setShooterSpeed(speed),
            [shooterSubsystem]
        ));
    }

    public void configureDefaultCommands() {
        this.drivetrainSubsystem.setDefaultCommand(new TeleopDriveCommand(
            () -> driverController.getLeftY(),
            () -> driverController.getRightY(),
            (left, right) -> drivetrainSubsystem.tankDrive(left, right),
            [drivetrainSubsystem]
        ));
    }

    public Command getAutonomousCommand() {
        return this.autonomousChooser.getSelected();
    }
}