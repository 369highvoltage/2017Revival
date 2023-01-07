// Mandatory - Import this for any subsystems.
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Import necessary object packages.
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.WPI_pigeonIMU;

// Your class (SampleSubsystem) must extend SubsystemBase
public class SampleSubsystem extends SubsystemBase {
    /** Declare any objects here - this depends on what kind of subsystem this is. Objects can be:
     * 
     *  Actuators
     *  - Motor controllers (Talons, Sparks, etc)
     *  - Pneumatic controllers
     *  - etc.
     *  
     *  Sensors
     *  - Distance sensors (Ultrasounds, LIDARs) 
     *  - Encoders
     *  - IMUs (Pigeons, Accelerometers, Gyroscapes etc)
     *  - Limit switches (Mechanical, Magnetic, Optical break beams)
     *  - etc.
     *  
     *  You'll want to group objects together based on function.
     *  Also, all these objects should be private.
    **/
    private WPI_TalonFX sampleMotor;
    private WPI_pigeonIMU pigeonIMU;

    /** Declare any variables or constants here
     *  
     *  This will be basic types like:
     *  - Booleans
     *  - Doubles
     *  - Ints
     *  - Strings
     *
     *  Variables & constants should also be private.
     *  - Constants should have their values set immediately
     *  - Variables should have their values set in the constructor.
    **/
    private final double zeroAngle = 0.0;   // Constant
    private double sampleDefaultAngle;      // Variable

    /** This is a constructor.
     *  
     *  - Basic data types have default values: https://www.google.com/search?q=java+primitive+default+values
     *  - Custom Objects (in this case SampleSubsystem) do not have default values.
     *  - The constructor is where you set default values for everything inside the object.
    **/
    public SampleSubsystem() {
        // Setup objects - Talons & Pigeons are objects that have their own internal constructors that set up their own values.
        sampleMotor = new WPI_TalonFX();
        pigeonIMU = new pigeonIMU();

        // Give variables default values
        sampleDefaultAngle = 0.0;
    }

    /** After:
     *  - Declaring objects
     *  - Declaring constants
     *  - Declaring variables
     *  - Writing the constructor
     *
     *  We can then write custom/subsystem-specific functions. A few (empty) example functions can be shown below.
     *  - These should all be public.
     *  
     *  For more concrete examples, view the other example subsystems.
    **/

    public double getAngle() {
        
    }

    public void move() {

    }

    public void rotate() {

    }

    public void stop() {

    }