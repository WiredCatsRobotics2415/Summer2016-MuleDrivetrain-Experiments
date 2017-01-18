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
	double goal;
	
	/**
	 * Constructor for the PixyCam object
	 * TODO: set port values
	 * @param AnalogPort the analog input port
	 * @param DigitalPort the digital input port
	 */
	public PixyCam(int AnalogPort, int DigitalPort){
		pixyAim = new AnalogInput(0);
		pixyBool = new DigitalInput(0);
	}
	
	/**
	* setter for the goal
	*/
	public void setGoal(double goal){
		this.goal = goal;
	}
	
	public double pixyPOut(double kP, double steadyState){
		if (Math.abs(getError()) < steadyState){
			return 0;
		} else {
		return getErrorPrime() * kP;
		}
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
		if (getTarget()) return getError();
		else return 0;
	}
	
	/**
	 * finds the offset of the pixy from it's target
	 * @return current pixy position - target position
	 */
	public double getError(){
		return goal - get();
	}
	

}