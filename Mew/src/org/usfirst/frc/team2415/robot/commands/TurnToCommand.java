package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToCommand extends Command implements PIDOutput {


	PIDController turnController;
	double rotateToAngleRate;
	double angle;
	
	double kP = .02;
	double kI = 0;
	double kD = .05;
	double kF = 0;
	
	double kTolerance = 2.0;
	
	long zeroWaitTime;
	

	
	public TurnToCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.driveSubsystem);
		this.angle = 90;
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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.joystick.buttons[1].get()) Robot.driveSubsystem.ahrs.zeroYaw();
    	turnController.setSetpoint(angle);
    	System.out.println("Yaw: " + Robot.driveSubsystem.ahrs.getYaw());
    	Robot.driveSubsystem.setMotors(-rotateToAngleRate, rotateToAngleRate);
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

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}
