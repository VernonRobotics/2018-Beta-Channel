package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;

public class StartLeftScaleLeft {

	static int autoState = 0;
	static int x = 0;

	public static void run(double delay) {
		
		if (autoState == 0) {
			// Delay
			AutoCommands.delay(delay);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
			
		} else if (autoState == 1) {
			// Tower Up Fully
			AutoCommands.towerMove(4);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} /*else if (autoState == 2) {
			// Move Forward
			AutoCommands.autoCartesianTime(5, 0, 0.6);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if(autoState == 3) {
			// Turn to get box in Scale
			AutoCommands.turnToAngle(45);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}else if (autoState == 4) {
			// Output Box
			AutoCommands.boxOutput();
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 5) {
			// Move Back
			AutoCommands.autoCartesianTime(.5, 0, - 0.3);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 6) {
			// Drop Tower
			AutoCommands.towerMove(-4);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		}*/

	}

}
