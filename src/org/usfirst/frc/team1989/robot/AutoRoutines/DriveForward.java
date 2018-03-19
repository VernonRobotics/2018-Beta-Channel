package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.Components;

public class DriveForward {
	static int autoState = 0;
	static int x = 0;
	
	public static void run() {
		if (autoState == 0)  {
			Components.timer.start();
			autoState++;
		} else if(autoState == 1 ) {
			Components.driveStick.setpY(0.5);
			if(Components.timer.get() > 3) {
				autoState++;
			}
		} else if(autoState == 2) {
			Components.driveStick.setpY(0);
			Components.timer.stop();
			autoState++;
		} else if(autoState == 3) {
			System.out.println("State 3");
		}
	}
}
