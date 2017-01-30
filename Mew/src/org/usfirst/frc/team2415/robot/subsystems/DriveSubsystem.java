package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.ArcadeDriveCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private CANTalon leftTalon, rightTalon, leftSlave, rightSlave;
	private Encoder leftEncoder, rightEncoder;
	
	public AHRS ahrs;
	
	public DriveSubsystem(){
		leftTalon = new CANTalon(RobotMap.LEFT_TALON);
		rightTalon = new CANTalon(RobotMap.RIGHT_TALON);
		leftSlave = new CANTalon(RobotMap.LEFT_SLAVE);
		rightSlave = new CANTalon(RobotMap.RIGHT_SLAVE);
		
		rightSlave.changeControlMode(TalonControlMode.Follower);
		rightSlave.set(rightTalon.getDeviceID());
		leftSlave.changeControlMode(TalonControlMode.Follower);
		leftSlave.set(leftTalon.getDeviceID());
		
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER[0], RobotMap.LEFT_ENCODER[1]);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER[0], RobotMap.RIGHT_ENCODER[1]);
		
		ahrs = new AHRS(SerialPort.Port.kMXP);
		
	}

    public void initDefaultCommand() {
      setDefaultCommand(new ArcadeDriveCommand());
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
    
    public Encoder[] returnEncoders(){
    	return new Encoder[]{leftEncoder, rightEncoder};
    }
    
    public void resetEncoders(){
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
}

