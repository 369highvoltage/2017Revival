// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class DrivetrainSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  
  //1 and 4 are left, 2 and 3 are right

  //Create new TalonSRX (the motorcontrollers used) objects, 
  //and give them a name (i.e. Left 1, Right 1, Left 2, Right 2, etc.);
  public WPI_TalonSRX m_L1,m_L2,m_R1,m_R2;

  //this is how we connect the motor
  //controllers that are on the same 
  //side. 
  MotorControllerGroup m_left, m_right;

  //After determining which side is which,
  //you need to combine them to finish
  //setting up the drive.
  DifferentialDrive m_drive;

  //There is also one double solenoid that
  //controls the gearbox (high, low)
  DoubleSolenoid m_gearbox;

  public DrivetrainSubsystem() {
    //Point the code to what port the TalonSRX is
    //attached to. In this case, the SRXs are using
    //PWM so we need to attach it based on the port it
    //is connected to.

    //left side
    m_L1 = new WPI_TalonSRX(3);
    m_L2 = new WPI_TalonSRX(0); //REPLACE THIS VALUE

    //group controllers together
    m_left = new MotorControllerGroup(m_L1,m_L2);
    //Always make sure to set one side to be inverted!!
    m_left.setInverted(true);

    //right side
    m_R1 = new WPI_TalonSRX(1);
    m_R2 = new WPI_TalonSRX(0); //REPLACE THIS VALUE

    //group controllers togther
    m_right = new MotorControllerGroup(m_R1, m_R2);

    //And after you've assigned the left and right sides,
    //then and only then, can you make a diff. drive for 
    //the motor controllers
    m_drive = new DifferentialDrive(m_left, m_right);

    //You also need to be able to drive the gearbox.
    //For that, intialize the double solenoid based
    //on the ports it's plugged into on the PCM.
    m_gearbox = RobotContainer.m_pcm.pcm.makeDoubleSolenoid(0, 1);
  }

  /**
   * This allows the player (or code) to drive the robot 
   * each side independently in a tank like fashion.
   * leftInput controls left side, and rightInput controls right side.
   */
  public void tankDrive(double leftInput, double rightInput) {
    m_drive.tankDrive(leftInput, rightInput);
  }

  /**
   * this allows the player (or code) to drive the robot
   * in a similar vein to controlling an RC car
   * With driveInput controlling forward and backward
   * and with steeringInput  turning left and right.
   */
  public void arcadeDrive(double driveInput, double steeringInput){
    m_drive.arcadeDrive(driveInput, steeringInput);
  }

  //Initializes the gearbox and sets it based
  //on value that is set.
  //0 is neutral, 1 is low, 2 is high. (NEEDS TESTING)
  public void setGearbox(int gear) {
    switch(gear){
      case 0: 
        m_gearbox.set(Value.kOff);
      case 1:
        m_gearbox.set(Value.kForward);
      case 2:
        m_gearbox.set(Value.kReverse);
    }
  }



}
