package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {
	
	double leftVal, rightVal;

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.driveSubsystem);
    	
    }
    
    public DriveCommand(double leftVal, double rightVal) {
    	
    	requires(Robot.driveSubsystem);
    	
    		this.leftVal = leftVal;
    		this.rightVal = rightVal;
    
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.setMotors(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveSubsystem.talonMode == 0){
    		Robot.driveSubsystem.setMotors(Robot.gamepad.leftY(), Robot.gamepad.rightY());
    	} else {
    		Robot.driveSubsystem.setMotors(leftVal, rightVal);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.setMotors(0, 0);
    }
}
