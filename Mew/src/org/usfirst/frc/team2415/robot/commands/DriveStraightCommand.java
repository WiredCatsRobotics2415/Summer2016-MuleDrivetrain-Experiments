package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightCommand extends Command implements PIDOutput {


	PIDController turnController;
	double rotateToAngleRate;
	double angle;
	
	double kP = .025;
	double kI = 0;
	double kD = .0;
	double kF = 0;
	
	double kTolerance = 2.0;
	
	long zeroWaitTime;
	
	long startTime;
	boolean finisher;
	
	double distance;
	

	
	public DriveStraightCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {	
    	Robot.driveSubsystem.setMotors(0, 0);
    	turnController = new PIDController(kP, kI, kD, kF, Robot.driveSubsystem.ahrs, this);
    	turnController.setInputRange(-180.0f,  180.0f);
    	turnController.setOutputRange(-1.0, 1.0);
    	turnController.setAbsoluteTolerance(kTolerance);
    	turnController.setContinuous(true);
    	turnController.enable();
    	turnController.setSetpoint(Robot.driveSubsystem.ahrs.getYaw());
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Yaw: " + Robot.driveSubsystem.ahrs.getYaw() + "\tsetpoint: " + turnController.getSetpoint());
    	Robot.driveSubsystem.setMotors(-rotateToAngleRate - .5, rotateToAngleRate - .5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMotors(0, 0);
    	turnController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.setMotors(0, 0);
    	turnController.disable();
    }

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}