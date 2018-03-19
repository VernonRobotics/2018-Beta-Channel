package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;
import org.usfirst.frc.team1989.robot.Components;


public class StartRightMoveForward {


	static int autoState = 0;
	static int x = 0;
	
	public static void run() {
		if(autoState == 0) {
			
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		} else if (autoState == 1) {
			AutoCommands.delay(1);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2) {
			//AutoCommands.boxOutputSwitch();
			AutoCommands.towerMove(autoState);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} 		
	} 
}
	
