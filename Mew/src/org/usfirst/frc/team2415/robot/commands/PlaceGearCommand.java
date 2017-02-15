package org.usfirst.frc.team2415.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGearCommand extends CommandGroup {

	//units: feet
	//i did this kinda late at night and tried to fix stuff during chem idk how much is 
	//right or makes sense at all
	
	char liftNum;
	double a1;
	double fieldLength = 27;
	double a2 = fieldLength-a1;
	double baseLine = 7.8125;
	//baseLine-1 = 6.8125
	double dHex;
	double dLeft;
	double d2;
	double dLift;
	
    public PlaceGearCommand() {
    	
    	addSequential(new DriveStraightToCommand(1));
    	addSequential(new TurnToCommand(Math.atan((a2-d2)/(baseLine-1))));
    	addSequential(new DriveStraightToCommand(Math.sqrt(Math.pow(baseLine-1, 2)+Math.pow(a2-d2, 2))));
    	addSequential(new TurnToCommand(90-Math.atan((a2-d2)/(baseLine-1))));
    	addSequential(new DriveStraightToCommand(dLift/Math.sqrt(2)));
    	addSequential(new TurnToCommand(90));
    	addSequential(new PixyBangCommand());
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
