package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;


public class StartLeftScaleRight {

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
		} else if (autoState == 2) {
			// Move Forward
			AutoCommands.autoCartesianTime(3.5, 0, 0.6);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 3) {
			// Turn to go between scale and switch
			AutoCommands.turnToAngle(90);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} /* else if (autoState == 4) {
			// Drive between scale and switch
			AutoCommands.autoCartesianTime(3, .6, 0);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 5) {
			AutoCommands.turnToAngle(0);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 6) {
			// Output Box
			AutoCommands.boxOutput();
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 7) {
			// Move Back
			AutoCommands.autoCartesianTime(.5, 0, -0.3);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 8) {
			// Drop Tower
			AutoCommands.towerMove(-4);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		}*/

	}

}
