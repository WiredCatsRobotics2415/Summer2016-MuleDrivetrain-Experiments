package org.usfirst.frc.team2415.robot.commands;

import org.usfirst.frc.team2415.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveCommand extends Command{

	public ArcadeDriveCommand(){
		requires(Robot.driveSubsystem);
	}
	
	protected void initalize(){
		Robot.driveSubsystem.setMotors(0, 0);
	}
	
	protected void execute(){
			
		double leftY = Robot.gamepad.leftY();
		double rightX = Robot.gamepad.rightX();
		
		double leftWheels = leftY + rightX;
		double rightWheels = leftY - rightX;
		
		Robot.driveSubsystem.setMotors(leftWheels, rightWheels);
	}
	protected boolean isFinished(){
		return false;
	}
	protected void end(){
		Robot.driveSubsystem.setMotors(0,0);
	}
	protected void interrupted(){
		Robot.driveSubsystem.setMotors(0, 0);
	}
}
