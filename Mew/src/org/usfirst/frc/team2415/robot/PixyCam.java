package org.usfirst.frc.team2415.robot;

import java.util.LinkedList;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * A class for the PixyCam
 * @author omarimatthews
 *
 */
public class PixyCam implements PIDSource {
	
	AnalogInput pixyAim;
	DigitalInput pixyBool;
	double goal;
	int kSampleSize = 25;
	double kTolerance = 0.5;
	static double prev;
	double coeff = 0.5;
	boolean test = true;
	double maxErr = 1; //this is some made up value. adjust as needed
	double error = 0;
	double prevError = 0;
	double prev1;
	/**
	 * Constructor for the PixyCam object
	 * TODO: set port values
	 * @param AnalogPort the analog input port
	 * @param DigitalPort the digital input port
	 */
	public PixyCam(int AnalogPort, int DigitalPort){
		pixyAim = new AnalogInput(AnalogPort);
		pixyBool = new DigitalInput(DigitalPort);
	}
	
	/**
	* setter for the goal
	*/
	public void setGoal(double goal){
		this.goal = goal;
	}
	
	public double POut(double kP, double steadyState){
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
	
	public double getRavi(){
		prev1 =  Math.abs(prev1-get()) > maxErr ? prev1 : get();
		return prev1;
	}
	
	public double getFiltered(){
		prev = coeff*get() + (1 - coeff)*prev;
		return prev;
	}
	
	public double getPrime(){
		return getTarget() ? get() : 0;
	}
	
	/**
	 * basically only do vision if you can see the target
	 * @return the error if the target is in bounds, otherwise 0
	 */
	public double getErrorPrime(){
		return getTarget() ? getError() : 0;
	}
	
	/**
	 * finds the offset of the pixy from it's target
	 * @return current pixy position - target position
	 */
	public double getError(){		
		error =  Math.abs(goal - get()) > maxErr ? prevError : (goal - get());
		prevError = error;
		return error;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		if(Math.abs(getError()) <= kTolerance) {
			return getFiltered();
		} else {
			prev = get();
			return get();
		}
	}
	

}
