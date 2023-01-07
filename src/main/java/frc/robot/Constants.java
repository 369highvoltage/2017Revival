import java.lang.Double;
import java.lang.Integer;
import java.util.Properties;

public final class Constants {
    private Properties constants;

    public Constants() {
        /** Store constants in Properties object and just pass the object into Subsystems or Commands that need them.
         *  More Info: https://www.google.com/search?q=java+properties+object&client=firefox-b-1-d&sxsrf=ALiCzsYwUum2Z9OmQsOl0uaqunttR9yzBg%3A1668309924973&ei=pGNwY_bVOs6k5NoPnqWi0AY&ved=0ahUKEwj2t8_jmqr7AhVOElkFHZ6SCGoQ4dUDCA8&uact=5&oq=java+properties+object&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIFCAAQgAQyBggAEAcQHjIGCAAQBxAeMgYIABAHEB4yBggAEAcQHjIGCAAQBxAeMgQIABAeMgQIABAeMgYIABAIEB4yBggAEAgQHjoKCAAQRxDWBBCwAzoHCCMQsAIQJzoICAAQCBAHEB5KBAhNGAFKBAhBGABKBAhGGABQghNY7hZgzxhoAXAAeACAAXGIAa8CkgEDMy4xmAEAoAEByAEIwAEB&sclient=gws-wiz-serp
        **/
        this.constants = new Properties();
        this.PDPConfiguration();
        this.CANConfiguration();
        this.drivetrainConfiguration();
        /* Add subsystem configuration functions here */
        // this.shooterConfiguration();
        // this.climberConfiguration();
        this.generalConfiguration();
    }

    private void setConstant(String k, boolean v) {
        this.constants.put(k, v);
    }

    private void setConstant(String k, double v) {
        this.constants.put(k, v);
    }

    private void setConstant(String k, int v) {
        this.constants.put(k, v);
    }

    private void setConstant(String k, String v) {
        this.constants.put(k, v);
    }

    /* Pin Mapping Section */

    // Set pins for PDP here
    private void PDPConfiguration() {
        // Example for limit switch pin
        this.setConstant("limitSwitch", 9);
    }

    // Set pins for CANBus here
    private void CANConfiguration() {
        this.setConstant("frontLeftMotorPort", 1);
        this.setConstant("rearLeftMotorPort", 2));
        this.setConstant("frontRightMotorPort", 3);
        this.setConstant("rearRightMotorPort", 4);
        this.setConstant("pigeonPort", 5);
        this.setConstant("PCMPort", 11);
    }

    /* Subsystem-specific Configuration Section */

    // DrivetrainSubsystem configuration
    private void drivetrainConfiguration() {
        
        // TODO: Set these values
        this.setConstant("distanceConversionValue", 0.0); // No idea, set this value
        this.setConstant("limiter", 0.0);
        this.setConstant("motorControllerAttachedtoEncoder", "frontLeftMotor");
    }

    /* Miscellaneous Configuration Section */

    // Set up miscellaneous configuration values here
    private void generalConfiguration() {
        this.setConstant("driverControllerPort", 0);
        this.setConstant("operatorControllerPort", 1); 
    }

    /* Functions to get values from Properties object */
    
    public String getString(String key) {
        return this.constants.getProperty(key);
    }

    public int getInt(String Key) {
        return Integer.parseInt(this.constants.get(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(this.constants.get(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(this.constants.get(key))
    }
}