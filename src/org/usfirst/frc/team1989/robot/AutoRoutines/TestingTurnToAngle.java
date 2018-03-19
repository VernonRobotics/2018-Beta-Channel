package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;

public class TestingTurnToAngle {

	static int autoState = 0;

	public static void run(double delay) {
		if (autoState == 0) {
			AutoCommands.turnToAngle(45);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 1) {
			AutoCommands.delay(1);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2) {
			AutoCommands.turnToAngle(90);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 3) {
			AutoCommands.delay(.5);
			if (AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
		
		
		
	}
}
