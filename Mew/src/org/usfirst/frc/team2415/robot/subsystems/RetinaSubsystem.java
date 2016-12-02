package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.RetinaCommand;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RetinaSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public double goal = 1.6969;
	public AnalogInput pixyCam;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RetinaCommand());
    }
    
	public RetinaSubsystem(){
    	pixyCam = new AnalogInput(0);
	}

    public double camVoltage(){
    	return pixyCam.getVoltage();
    }
    
    public void updateStatus(){
    	SmartDashboard.putNumber("Pixy Voltage", camVoltage());
    }
}

