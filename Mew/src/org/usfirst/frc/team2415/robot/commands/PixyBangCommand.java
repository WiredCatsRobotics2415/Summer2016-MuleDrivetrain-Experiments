package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class PixyBangCommand extends Command implements PIDOutput {

	boolean finisher;
	long startTime;
	double rotation;
	double kP = 1.125, 
		   kI = 0, 
		   kD = 0.5, 
		   kF = 0,
		   kTolerance = .023;
	
	PIDController turnController;
	
    public PixyBangCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	requires(Robot.retinaSubsystem);
    	
    	turnController = new PIDController(kP, kI, kD, kF, Robot.retinaSubsystem.pixyCam, this);
    	turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kTolerance);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.setMotors(0, 0);
    	turnController.setSetpoint(1.69);
    	turnController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double speed = Robot.retinaSubsystem.pixyCam.getTarget() ? rotation : 0;
		System.out.println("CIM: " + speed + "\tleft_E: " + Robot.driveSubsystem.getEncoders()[0] + "\tright_E: " + Robot.driveSubsystem.getEncoders()[1]);
        Robot.driveSubsystem.setMotors(-speed, speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.setMotors(0,0);
    	turnController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.setMotors(0,0);
    	turnController.disable();
    }

	@Override
	public void pidWrite(double output) {
		rotation = output;
		
	}
}
