package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PixyBangCommand extends Command {

	boolean finisher;
	long startTime;
	double kP = 0.75;
	
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
    	double speed = Robot.retinaSubsystem.pixyCam.POut(0.75, 0.1);
		System.out.println("CIM: " + speed + "\t finisher: " + (System.currentTimeMillis() - startTime));
        Robot.driveSubsystem.setMotors(-speed, speed);
        
    	finisher = (Math.abs(Robot.retinaSubsystem.pixyCam.getErrorPrime()) <= 0.3);
    	if (finisher == false) startTime = System.currentTimeMillis();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(System.currentTimeMillis() - startTime >= 3){
        	System.out.println("DONE");
        	return true;
        } else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMotors(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.setMotors(0,0);
    }
}
