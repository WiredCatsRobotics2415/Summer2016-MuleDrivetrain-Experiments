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
		System.out.println("CIM: " + kP*Robot.retinaSubsystem.pixyCam.getErrorPrime());
    	if(Math.abs(Robot.retinaSubsystem.pixyCam.getErrorPrime()) >= 0.1){
        	Robot.driveSubsystem.setMotors(-kP*Robot.retinaSubsystem.pixyCam.getErrorPrime(), kP*Robot.retinaSubsystem.pixyCam.getErrorPrime());
    	}
//    	finisher = (Math.abs(Robot.retinaSubsystem.pixyCam.getErrorPrime()) <= 0.3);
//    	if (finisher == false) startTime = System.currentTimeMillis();
//    	System.out.println("Raw: " + Robot.retinaSubsystem.pixy.getVoltage() + "\t<1: " + Robot.retinaSubsystem.pixy.getVoltage()*0.25);
//    	Robot.driveSubsystem.setMotors(Robot.retinaSubsystem.pixy.getVoltage()*0.25, Robot.retinaSubsystem.pixy.getVoltage()*0.25);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return System.currentTimeMillis() - startTime >= 3;
    	return false;
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
