package org.usfirst.frc.team2415.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * A class for the PixyCam
 * @author omarimatthews
 *
 */
public class PixyCam {
	
	AnalogInput pixyAim;
	DigitalInput pixyBool;
	double goal = 1.6969;
	
	/**
	 * Constructor for the PixyCam object
	 * 
	 * @param AnalogPort the analog input port
	 * @param DigitalPort the digital input port
	 * @param goal the value that the pixy returns when it is perfectly lined up
	 */
	public PixyCam(int AnalogPort, int DigitalPort, double goal){
		pixyAim = new AnalogInput(RobotMap.PIXY_PORT1);
		pixyBool = new DigitalInput(RobotMap.PIXY_PORT2);
		this.goal = goal;
	}
	
	/**
	 * use this function to determine if the pixy actually sees the target
	 * @return true if the target is in sight
	 */
	public boolean getTarget(){
		return pixyBool.get();
	}
	
	/**
	 * use this function to do vision
	 * @return the voltage of the pixy
	 */
	public double get(){
		return pixyAim.getVoltage();
	}
	
	/**
	 * basically only do vision if you can see the target
	 * @return the error if the target is in bounds, otherwise 0
	 */
	public double getErrorPrime(){
		if (getTarget()) return get();
		else return 0;
	}
	
	/**
	 * finds the offset of the pixy from it's target
	 * @return current pixy position - target position
	 */
	public double getError(){
		return get() - goal;
	}
	

}
