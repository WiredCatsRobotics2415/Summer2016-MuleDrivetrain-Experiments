package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDDriveSubsystem extends PIDSubsystem {

	private CANTalon leftTalon, rightTalon;
	private Encoder leftEncoder, rightEncoder;
	
    // Initialize your subsystem here
    public PIDDriveSubsystem() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("Drive Subsystem", 1, 2, 1);
    	setAbsoluteTolerance(0.05);
    	
		leftTalon = new CANTalon(RobotMap.LEFT_TALON);
		rightTalon = new CANTalon(RobotMap.RIGHT_TALON);
		
		//change the talon mode
		
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER[0], RobotMap.LEFT_ENCODER[1]);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER[0], RobotMap.RIGHT_ENCODER[1]);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return (leftEncoder.get() + rightEncoder.get()) / 2;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	leftTalon.set(-output);
    	rightTalon.set(output);
    }
    
    public void changeSetpoint(double setpoint){
    	setSetpoint(setpoint);
    }
    
    public void setMotors(double left, double right){
    	leftTalon.set(-left);
    	rightTalon.set(right);
    }
    
    public double[] getTalon(){
    	return new double[]{leftTalon.get(), rightTalon.get()};
    }
    
    public double[] getEncoder(){
    	return new double[]{-leftEncoder.get(), -rightEncoder.get()};
    }
    
    public void resetEncoders(){
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void enablePID(){
    	enable();
    }
    
    public void disablePID(){
    	disable();
    }
}
