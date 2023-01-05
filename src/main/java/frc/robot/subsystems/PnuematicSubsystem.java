package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PnuematicSubsystem extends SubsystemBase {
   //This controls the pneumatics of the robot.
   //Initialize this first before drivetrain.
   
   public PneumaticsControlModule pcm;

   public PnuematicSubsystem(){

   //Acquire CAN ID of the PCM via Phoenix
   //Tuner and assign it here.
   pcm = new PneumaticsControlModule(0); 

   } 
   @Override
   public void periodic() {
     // This method will be called once per scheduler run
   }
 
   @Override
   public void simulationPeriodic() {
     // This method will be called once per scheduler run during simulation
   }
   
}

