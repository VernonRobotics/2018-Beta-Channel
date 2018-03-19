package org.usfirst.frc.team1989.robot.AutoRoutines;

import org.usfirst.frc.team1989.robot.AutoCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ExampleButtons {
	static int autoState = 0;
	static int x = 0;
	
	public static void run() {
		
		if(autoState == 0) {
			//AutoCommands.towerMove(.5);
			if(AutoCommands.actionFlag== false) {
				autoState++;
			}
			
		} else if (autoState == 1 && SmartDashboard.getBoolean("DB/Button 0", false)) {
			AutoCommands.autoCartesianTime(	SmartDashboard.getNumber("DB/Slider 0", 0), 0, .6);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 2 && SmartDashboard.getBoolean("DB/Button 1", false)) {
			AutoCommands.autoCartesianTime(SmartDashboard.getNumber("DB/Slider 1", 0), 0, .6);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 3 && SmartDashboard.getBoolean("DB/Button 2", false)) {
			AutoCommands.autoCartesianTime(SmartDashboard.getNumber("DB/Slider 2", 0), 0, .6);				
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
			
		} else if (autoState == 4) {
			AutoCommands.turnToAngle(45);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}   else if (autoState == 5) {
			AutoCommands.towerMove(2);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}else if (autoState == 7) {
			AutoCommands.autoCartesianTime(0.5, 0, 0.3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}else if (autoState == 8) {
			AutoCommands.boxOutput();
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}  else if (autoState == 9) {
			AutoCommands.autoCartesianTime(0.5, 0, -0.3);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		} else if (autoState == 10) {
			AutoCommands.towerMove(-2.5);
			if(AutoCommands.actionFlag == false) {
				autoState++;
			}
		}
		
	}
}
