package org.usfirst.frc.team1989.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class TowerControl implements cmd {

	CANTalon1989 rightMotor;
	CANTalon1989 leftMotor;
	ArmControl arms;
	JsScaled uStick;
	int towerState = 0;
	double towerScaleTime = 0.75;
	boolean moveSwitch = false;
	boolean moveScale = false;
	boolean towerScale = false;
	Timer timer = new Timer();
	int state = 0;
	double minSpeed = 0.25;
	// state 1 = motors up
	// state 2 = motors down

	// constructor
	public TowerControl(CANTalon1989 leftMotor, CANTalon1989 rightMotor, JsScaled uStick) {
		this.rightMotor = rightMotor;
		this.leftMotor = leftMotor;

		this.uStick = uStick;
	}

	public void towerControl(double speed) {
		rightMotor.set(speed);
		leftMotor.set(speed);
	}

	public void towerStop() {
		rightMotor.set(0);
		leftMotor.set(0);
	}

	public void setMoveSwitch(boolean move) {
		moveSwitch = move;
	}

	public boolean getMoveSwitch() {
		return moveSwitch;
	}

	public void setMoveScale(boolean move) {
		moveScale = move;
	}

	public boolean getMoveScale() {
		return moveScale;
	}

	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		// counter.reset();
	}

	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub

	}

	public void testInit() {
		// TODO Auto-generated method stub

	}

	public void testPeriodic() {
		// TODO Auto-generated method stub

	}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		leftMotor.setNeutralMode(NeutralMode.Brake);
		rightMotor.setNeutralMode(NeutralMode.Brake);

	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub

		towerControl(-uStick.sgetY());
	}

}
