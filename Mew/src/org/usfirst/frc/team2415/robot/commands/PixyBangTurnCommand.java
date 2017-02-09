package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class PixyBangTurnCommand extends Command implements PIDOutput {

	

	double rotation;
	double kP = 0.25, 
		   kI = 0.025, 
		   kD = 0.075, 
		   kF = 0,
		   kTolerance = .2;
	
	PIDController turnController;
	
    public PixyBangTurnCommand() {
    	
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
    	Robot.retinaSubsystem.pixyCam.setGoal(turnController.getSetpoint());
    	turnController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double angle = Robot.retinaSubsystem.pixyCam.getTarget() ? rotation : 0;
    	double straight = Robot.retinaSubsystem.pixyCam.getTarget() && Robot.joystick.buttons[1].get() ? .5 - .3*Math.abs(angle) : 0;
    	
//    	straight = Robot.retinaSubsystem.pixyCam.getTarget() && Robot.joystick.buttons[3].get() ? -.5 : straight;
		System.out.println("CIM: " + angle);
		if (Robot.joystick.buttons[6].get())
        Robot.driveSubsystem.setMotors(-angle + straight,angle + straight);
		else 
		Robot.driveSubsystem.setMotors(-angle - straight,angle - straight);

    	
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
