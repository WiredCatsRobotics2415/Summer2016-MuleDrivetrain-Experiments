package org.usfirst.frc.team2415.robot.subsystems;

import org.usfirst.frc.team2415.robot.PixyCam;
import org.usfirst.frc.team2415.robot.RobotMap;
import org.usfirst.frc.team2415.robot.commands.PixyBangCommand;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RetinaSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public PixyCam pixyCam;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new PixyBangCommand());
    }
    
	public RetinaSubsystem(){
    	pixyCam = new PixyCam(RobotMap.PIXY_PORT1,RobotMap.PIXY_PORT2);
    	pixyCam.setGoal(1.69);
	}
    
    public void updateStatus(){
    }
}

