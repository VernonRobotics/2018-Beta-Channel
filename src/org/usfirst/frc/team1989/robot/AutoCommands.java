package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;

/*
 * public class AutoCommands{}
 * 
 * This class includes commands to be used in autonomous.
 * All commands use actionState in order to keep track of what step they are on.
 * All commands must manipulate actionFlag in order for the autonomous routines to function
 * 
 * @author Team 1989 Programmers
 * @version 1.0
 * @since February 20th, 2018
 * 
 */
public class AutoCommands {
	static int actionState = 0;// to be used to differentiate between different states in an individual command
	static double integral = 0;
	static double error = 0;
	static double currentAngle = 0;

	public static boolean actionFlag = false;
	int autoState = 0;// to be used to differentiate between different commands in a preset auto
	static Timer rampTimer = new Timer();
	public static double switchTime = 0.5;
	public static double scaleTime = 2.5;

	/*
	 * boxOutputSwitch()
	 * 
	 * Method is currently here for compliance within the autonomous routines. When
	 * complete, method will output the power cube to the switch game piece
	 * 
	 */

	public static void moveTillShmack() {
		switch (actionState) {
		case 0:
			actionFlag = true;
			actionState++;
			break;
		case 1:
			Components.driveStick.setpY(0.3);
			Components.driveStick.setpTwist((-Components.gyro.getAngle() + currentAngle) * Components.mDrive.kP
					+ integral * Components.mDrive.kI);

			if (Components.frontLeft.getOutputCurrent() > 35 || Components.frontRight.getOutputCurrent() > 35)
				actionState++;
			break;
		case 2:
			Components.driveStick.setpY(0);
			Components.driveStick.setpTwist(0);
			break;
		}
	}
	
	
	// Positive value goes up - Negative value goes down
	public static void towerMove(double liftTime) {
		if (liftTime >= 0) {
			switch (actionState) {
			case 0:
				actionFlag = true;
				Components.timer.stop();
				Components.timer.reset();
				Components.timer.start();
				actionState = 1;
				break;
			case 1:
				if (Components.timer.get() < liftTime) {
					Components.tower.towerControl(0.65);
				} else {
					actionState = 2;
				}
				break;
			case 2:
				Components.tower.towerStop();
				Components.timer.stop();
				Components.timer.reset();
				actionState = 0;
				actionFlag = false;
				break;
			}
		} else {
			switch (actionState) {
			case 0:
				actionFlag = true;
				Components.timer.stop();
				Components.timer.reset();
				Components.timer.start();
				actionState = 1;
				break;
			case 1:
				if (Components.timer.get() < Math.abs(liftTime)) {
					Components.tower.towerControl(-0.5);
				} else {
					actionState = 2;
				}
				break;
			case 2:
				Components.tower.towerStop();
				Components.timer.stop();
				Components.timer.reset();
				actionState = 0;
				actionFlag = false;
				break;
			}
		}

	}

	
	public static void boxOutput() {
		switch (actionState) {
		case 0:
			actionFlag = true;
			Components.timer.stop();
			Components.timer.reset();
			Components.timer.start();
			actionState = 1;
			break;
		case 1:
			if (Components.timer.get() < 1) {
				Components.arms.boxOut();
			} else {
				actionState = 2;
			}
			break;
		case 2:
			Components.tower.towerStop();
			Components.timer.stop();
			Components.timer.reset();
			actionState = 0;
			actionFlag = false;
			break;

		}

	}

	/*
	 * autoCartesianTime(double time, double speedX, double speedY) {
	 * 
	 * Autonomous method which uses time and speed to navigate during autonomous.
	 * 
	 * @param time The time to run the robot in a specific direction.
	 * 
	 * @param speedX The speed at which the robot should move in the x direction.
	 * 
	 * @param speedY The speed at which the robot should move in the y direction.
	 */
	public static void autoCartesianTime(double time, double speedX, double speedY) {
		switch (actionState) {
		case 0:
			actionFlag = true;
			Components.timer.stop();
			Components.timer.reset();
			Components.timer.start();
			actionState = 1;
			break;
		case 1:
			if (Components.timer.get() < time) {
				if (time > 1) {
					Components.driveStick.setpY(rampSpeedTime(time, speedY));
					Components.driveStick.setpX( speedX);
				}
				else {
					Components.driveStick.setpY(speedY);
					Components.driveStick.setpX(speedX);
				}
				integral += Components.gyro.getAngle() * 0.02;
				Components.driveStick.setpTwist((-Components.gyro.getAngle() + currentAngle) * Components.mDrive.kP
						+ integral * Components.mDrive.kI);
			} else {
				actionState = 2;
			}
			break;
		case 2:
			Components.driveStick.setpY(0);
			Components.driveStick.setpX(0);
			Components.driveStick.setpTwist(0);

			actionState = 0;
			actionFlag = false;
			integral = 0;
			break;
		}

	}

	/*
	 * turnToAngle(double angle)
	 * 
	 * This method makes use of a gyro to turn the robot to a specific angle.
	 * 
	 * @param angle The angle to turn the robot during autonomous.
	 */
	public static void turnToAngle(double angle) {
		switch (actionState) {
		case 0:
			actionFlag = true;
			actionState++;
			break;
		case 1:
			if (Math.abs(Components.gyro.getAngle()) < Math.abs(angle)) {
				error = angle - Components.gyro.getAngle();
				integral += error * 0.02;
				Components.driveStick.setpTwist(error * Components.mDrive.kP + integral * Components.mDrive.kI);
			} else {
				actionState++;
			}
			break;
		case 2:
			error = 0;
			integral = 0;
			currentAngle = angle;
			Components.driveStick.killVStick();
			actionState++;
			break;
		case 3:
			actionState = 0;
			actionFlag = false;
			break;
		}
	}

	public static void delay(double time) {
		switch (actionState) {
		case 0:
			Components.timer.stop();
			Components.timer.reset();
			Components.timer.start();
			actionFlag = true;
			actionState++;
			break;
		case 1:
			if (Components.timer.get() > time) {
				actionState++;
			}
			break;
		case 2:
			Components.timer.stop();
			Components.timer.reset();
			actionState = 0;
			actionFlag = false;
			break;
		}
	}

	/*
	 * double rampSpeedTime(double time, double speed)
	 * 
	 * @param time Amount of time to ramp.
	 * 
	 * @param speed How fast you want to move at the end of the ramp.
	 * 
	 */
	static double currentSpeed = 0;

	public static double rampSpeedTime(double time, double speed) {

		double ramp = speed / 40;
		int rampState;

		if (Components.timer.get() < 0.4)
			rampState = 0;
		else if (Components.timer.get() > time - 0.4)
			rampState = 2;
		else
			rampState = 1;

		if (speed > 0) {
			switch (rampState) {
			case 0:
				currentSpeed += ramp;
				if (currentSpeed > speed)
					currentSpeed = speed;
				break;
			case 1:
				currentSpeed = speed;
				break;

			case 2:
				currentSpeed -= ramp;
				if (currentSpeed < 0)
					currentSpeed = 0;
				break;
			}
		} else {
			switch (rampState) {
			case 0:
				currentSpeed -= ramp;
				if (currentSpeed > speed)
					currentSpeed = speed;
				break;
			case 1:
				currentSpeed = speed;
				break;

			case 2:
				currentSpeed += ramp;
				if (currentSpeed < 0)
					currentSpeed = 0;
				break;
			}

		}

		return currentSpeed;
	}

}
