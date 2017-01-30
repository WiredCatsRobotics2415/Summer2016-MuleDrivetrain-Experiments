package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToCommand extends Command implements PIDOutput {

	double distance;
	double speed;
	PIDController encoderController;
	double kP = 0.1, 
		   kI = 0, 
		   kD = 0, 
		   kF = 0,
		   kTolerance = .1;
	
    public DriveToCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);

    	encoderController = new PIDController(kP, kI, kD, kF, Robot.driveSubsystem.returnEncoders()[1], this);
    	encoderController.setOutputRange(-1.0, 1.0);
    	encoderController.setAbsoluteTolerance(kTolerance);
    	
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.setMotors(0, 0);
    	encoderController.setSetpoint(distance*4096*0.1875);
    	encoderController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("encoder position: " + Robot.driveSubsystem.getEncoders()[1]);
    	Robot.driveSubsystem.setMotors(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMotors(0, 0);
    	encoderController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.setMotors(0, 0);
    	encoderController.disable();
    }

	@Override
	public void pidWrite(double output) {
		speed = output;
	}
}
