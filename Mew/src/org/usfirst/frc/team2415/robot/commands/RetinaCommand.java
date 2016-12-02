package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RetinaCommand extends Command {

    public RetinaCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.retinaSubsystem);
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!(Math.abs(Robot.retinaSubsystem.camVoltage() - Robot.retinaSubsystem.goal) < .2)) {
    		if(Robot.retinaSubsystem.camVoltage() < Robot.retinaSubsystem.goal) {
    			Robot.driveSubsystem.setMotors(1, 0);
    		}
    		if(Robot.retinaSubsystem.camVoltage() > Robot.retinaSubsystem.goal) {
    			Robot.driveSubsystem.setMotors(0, 1);
    		}
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
