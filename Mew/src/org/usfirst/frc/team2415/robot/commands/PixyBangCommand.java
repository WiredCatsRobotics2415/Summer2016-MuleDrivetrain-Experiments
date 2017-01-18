package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PixyBangCommand extends Command {

	boolean finisher;
	long startTime;
	
    public PixyBangCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	requires(Robot.retinaSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.setMotors(Robot.retinaSubsystem.pixyCam.pixyPOut(0.5, 0.1), -Robot.retinaSubsystem.pixyCam.pixyPOut(0.5, 0.1));
    	finisher = (Robot.retinaSubsystem.pixyCam.pixyPOut(0.5, 0.1) == 0);
    	if (finisher == false) startTime = System.currentTimeMillis();	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis() - startTime >= 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMotors(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
