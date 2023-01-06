import java.util.Map;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;

public class DrivetrainSubsystem extends SubsystemBase {
    private Map<String, WPI_TalonSRX> motorControllers;
    private MotorControllerGroup leftSideMotors, rightSideMotors;
    private DifferentialDrive drivetrain;
    private DoubleSolenoid gearShifter;
    private WPI_PigeonIMU pigeonIMU;
    private WPI_TalonFX encoder;
    SlewRateLimiter filter;

    double encoderConversionFactor;
    boolean isHighGearSet;

    public DrivetrainSubsystem(Constants constants) {
        motorControllers.put("frontLeftMotor", new WPI_TalonFX(constants.getInt("frontLeftMotorPort")));
        motorControllers.put("frontRightMotor", new WPI_TalonFX(constants.getInt("frontRightMotorPort")));
        motorControllers.put("rearLeftMotor", new WPI_TalonFX(constants.getInt("rearLeftMotorPort")));
        motorControllers.put("rearRightMotor", new WPI_TalonFX(constants.getInt("rearRightMotorPort")));
        
        for (WPI_TalonFX motorController : motorControllers.values())
            motorController.setNeutralMode(NeutralMode.Brake);

        leftSideMotors = new MotorControllerGroup(
            motorControllers.get("frontLeftMotor"),
            motorControllers.get("rearLeftMotor")
        );
        rightSideMotors = new MotorControllerGroup(
            motorControllers.get("frontRightMotor"),
            motorControllers.get("rearRightMotor")
        );

        leftSideMotors.setInverted(true);
        drivetrain = new DifferentialDrive(leftSideMotors, rightSideMotors);

        encoder = motorControllers.get(constants.getString("motorControllerAttachedToEncoder"))
        encoder.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        encoderConversionFactor = constants.getDouble("encoderDistanceConversionValue");
        this.resetDistanceSensor();

        gearShifter = new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM, 
            constants.getInt("gearShifterPort1"), 
            constants.getInt("gearShifterPort2")
        );
        isHighGearSet = constants.getBoolean("shifterDefaultHighGear");
        this.setGear(isHighGearSet);

        pigeonIMU = new WPI_PigeonIMU(constants.getInt("pigeonPort"));
        filter = new SlewRateLimiter(constants.getDouble("limiter"));
    }

    public void arcadeDrive(double linearVelocity, double angularVelocity) {
        drivetrain.arcadeDrive(filter.calculate(linearVelocity), angularVelocity);
    }

    public double getAngle() {
        return pigeon.getAngle();
    }
    
    public double getAngularAcceleration(){
        return pigeon.getRate();
    }

    public double getDistance() {
        return encoder.getSelectedSensorPosition() * encoderConversionFactor;
    }

    public void resetAngle() {
        pigeonIMU.setYaw(0);
        pigeonIMU.reset();
    }

    public void resetDistance() {
        encoder.setSelectedSensorPosition(0.0);
    }
    
    private void setGear(boolean highGear) {
        if(highGear)
            gearShifter.set(Value.kForward);
        else
            gearShifter.set(Value.kReverse);
    }

    public void shiftGear() {
        this.isHighGearSet = !this.isHighGearSet;
        this.setGear(this.isHighGearSet);
    }

    public void tankDrive(double leftSideVelocity, double rightSideVelocity) {
        drivetrain.tankDrive(filter.calculate(leftSideVelocity), filter.calculate(rightSideVelocity));
    }
}