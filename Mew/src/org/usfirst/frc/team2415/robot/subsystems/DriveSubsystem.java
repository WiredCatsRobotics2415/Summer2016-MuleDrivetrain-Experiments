package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon leftTalon, rightTalon;
	private Encoder leftEncoder, rightEncoder;
	private Ultrasonic ultrasonic;
	
	public DriveSubsystem() {
		leftTalon = new CANTalon(RobotMap.LEFT_TALON);
		rightTalon = new CANTalon(RobotMap.RIGHT_TALON);
		
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER[0], RobotMap.LEFT_ENCODER[1]);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER[0], RobotMap.RIGHT_ENCODER[1]);
		
		ultrasonic = new Ultrasonic(RobotMap.ULTRA_PING, RobotMap.ULTRA_ECHO);
		ultrasonic.setAutomaticMode(true);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveCommand());
    }
    
    public void setMotors(double left, double right){
    	leftTalon.set(-left);
    	rightTalon.set(right);
    }
    
    public double[] getTalons(){
    	return new double[]{leftTalon.get(), rightTalon.get()};
    }
    
    public double[] getEncoders(){
    	return new double[]{-leftEncoder.get(), -rightEncoder.get()};
    }
    
    public void resetEncoders(){
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public double getUltrasonic(){
    	System.out.println(ultrasonic.getRangeInches());
    	return ultrasonic.getRangeInches();
    }
    
    public void updateStatus(){
    	SmartDashboard.putBoolean("Ultrasonic working?", ultrasonic.isRangeValid());
    	SmartDashboard.putNumber("Ultrasonic", getUltrasonic());
    }
    
}

