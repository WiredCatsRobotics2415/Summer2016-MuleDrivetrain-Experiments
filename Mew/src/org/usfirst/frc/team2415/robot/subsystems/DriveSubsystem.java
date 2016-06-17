package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon leftTalon, rightTalon;
	private Encoder leftEncoder, rightEncoder;
	public byte talonMode = 1;
	//0 - throttle mode
	//1 - position mode
	//2 - speed mode
	
	public DriveSubsystem(){

		leftTalon = new CANTalon(RobotMap.LEFT_TALON);
		rightTalon = new CANTalon(RobotMap.RIGHT_TALON);
		
		if(talonMode == 1){ //position mode
			
			leftTalon.changeControlMode(TalonControlMode.Position);
			leftTalon.reverseOutput(true);
			leftTalon.set(0);
			leftTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			rightTalon.changeControlMode(TalonControlMode.Position);
			rightTalon.set(0);
			rightTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			
		} else { //throttle mode
			
			leftTalon.setInverted(true);
		}
		
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER[0], RobotMap.LEFT_ENCODER[1]);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER[0], RobotMap.RIGHT_ENCODER[1]);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveCommand());
    }
    
    public void setMotors(double left, double right){
    	leftTalon.set(left);
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
    
}
