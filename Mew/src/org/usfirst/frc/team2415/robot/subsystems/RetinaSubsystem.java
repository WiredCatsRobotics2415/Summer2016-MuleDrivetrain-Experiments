package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.PixyCam;
import org.usfirst.frc.team2415.robot.commands.RetinaCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RetinaSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public PixyCam pixyCam;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RetinaCommand());
    }
    
	public RetinaSubsystem(){
    	pixyCam = new PixyCam(0,1,1.6969);
	}
    
    public void updateStatus(){
    	SmartDashboard.putNumber("Pixy Voltage", pixyCam.get());
    	SmartDashboard.putNumber("Smart Pixy Voltage", pixyCam.getErrorPrime());
    	SmartDashboard.putBoolean("Target Exist?", pixyCam.getTarget());
    }
}

