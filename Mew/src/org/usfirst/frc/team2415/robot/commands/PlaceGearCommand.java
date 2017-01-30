package org.usfirst.frc.team2415.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGearCommand extends CommandGroup {

    public PlaceGearCommand() {
    	addSequential(new DriveToCommand(4));
    	addSequential(new TurnToCommand(45));
    	addSequential(new DriveToCommand(6));
    	addSequential(new TurnToCommand(-90));
    	addSequential(new DriveToCommand(4));
    	addSequential(new TurnToCommand(-90));
    	addSequential(new PixyBangCommand());
    }

}
