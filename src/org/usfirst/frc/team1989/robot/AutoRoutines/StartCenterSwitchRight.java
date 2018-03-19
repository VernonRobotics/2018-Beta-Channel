package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;

public class StartCenterSwitchRight {

	static int autoState = 0;

	public static void run(double delay) {
		if (autoState == 0) {
			AutoCommands.delay(delay);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 1) {
			AutoCommands.towerMove(2);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2) {
			AutoCommands.autoCartesianTime(0.8, 0, 0.4);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 3) {
			AutoCommands.delay(.5);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		
		}else if (autoState == 4) {
			AutoCommands.autoCartesianTime(2.5, .6, 0);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 5) {
			AutoCommands.autoCartesianTime(2.75, 0, 0.4);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 6) {
			AutoCommands.boxOutput();
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
	}

}
