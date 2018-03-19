package org.usfirst.frc.team1989.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class NewMecDriveCmd implements cmd{
	//attributes
	CANTalon1989 driveFrontLeft;
	CANTalon1989 driveFrontRight;
	CANTalon1989 driveBackLeft;
	CANTalon1989 driveBackRight;
	ADXRS450_Gyro gyro;
	
	double kP = 0.00975;
	double kI = 0.0;
	double kD = 0.0;
	
	//PIDController pidFrontLeft = new PIDController(kP, kI, kD, gyro, driveFrontLeft);
	//PIDController pidFrontRight = new PIDController(kP, kI, kD, gyro, driveFrontRight);
	//PIDController pidBackLeft = new PIDController(kP, kI, kD, gyro, driveBackLeft);
	//PIDController pidBackRight = new PIDController(kP, kI, kD, gyro, driveBackRight);



	Timer timer = new Timer();
	
	double gyroAngle;
	
	MecanumDrive mecDrive;
	JsScaled driveStick;

	boolean isPID;
	
	

	//constructor for regular CANTalon1989
	public NewMecDriveCmd(CANTalon1989 driveFrontLeft, CANTalon1989 driveBackLeft, CANTalon1989 driveFrontRight, CANTalon1989 driveBackRight,
			JsScaled driveStick, ADXRS450_Gyro gyro) {
		this.driveFrontLeft = driveFrontLeft;
		this.driveFrontRight = driveFrontRight;
		this.driveBackLeft = driveBackLeft;
		this.driveBackRight = driveBackRight;
		mecDrive = new MecanumDrive(driveFrontLeft,driveBackLeft,driveFrontRight, driveBackRight);
		this.driveStick = driveStick;
		this.gyro = gyro;
		
		}
	
	




//Robotics Methods

	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		driveBackLeft.setNeutralMode(NeutralMode.Brake);
		driveBackRight.setNeutralMode(NeutralMode.Brake);
		driveFrontLeft.setNeutralMode(NeutralMode.Brake);
		driveFrontRight.setNeutralMode(NeutralMode.Brake);
		
		
		
		timer.stop();
		timer.reset();
		timer.start();
		
		
	}



	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
	
		
		
		mecDrive.driveCartesian(-driveStick.pX, -driveStick.pY, -driveStick.pTwist);
		
		
		
	
	
	
	}




	public void testInit() {
		// TODO Auto-generated method stub
		
	}




	public void testPeriodic() {
		// TODO Auto-generated method stub
		mecDrive.driveCartesian(-driveStick.pX, -driveStick.pY, -driveStick.pTwist);

	}



	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		gyroAngle = gyro.getAngle();
		driveBackLeft.setNeutralMode(NeutralMode.Brake);
		driveBackRight.setNeutralMode(NeutralMode.Brake);
		driveFrontLeft.setNeutralMode(NeutralMode.Brake);
		driveFrontRight.setNeutralMode(NeutralMode.Brake);
	}

	int strafeStraight = 0;
	double angle = 0;
	double driveConstant = 1;
	public void strafeLeft() {
		if(strafeStraight == 0) {
			angle = gyro.getAngle();
			strafeStraight = 1;
		}else if(strafeStraight == 1){
			mecDrive.driveCartesian(0.5*driveConstant,driveConstant*driveStick.sgetY()/2, -driveConstant*driveStick.sgetTwist()/2, (angle-gyro.getAngle())*0.0099);
		}
		
		
	}
	
	public void strafeRight() {
		if(strafeStraight == 0) {
			angle = gyro.getAngle();
			strafeStraight = 1;
		}else if(strafeStraight == 1){
			mecDrive.driveCartesian(-0.5*driveConstant,driveStick.sgetY()*driveConstant, -driveStick.sgetTwist()*driveConstant, (angle-gyro.getAngle())*0.0099);
		}
	}
	
	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		
		if (driveStick.getRawButton(1))
			driveConstant = 0.5;
		else 
			driveConstant = 1;
		
		if(driveStick.getRawButton(2))
			driveConstant *= -1;
		
		
		
		
	
		 if(driveStick.getRawButton(9) == true) {
			strafeLeft();
		}else if(driveStick.getRawButton(10) == true) {
			strafeRight();
		}
		else {
			mecDrive.driveCartesian(-driveStick.getX()*driveConstant,driveStick.getY()*driveConstant, -driveStick.sgetTwist()*Math.abs(driveConstant));
			strafeStraight = 0;
		}
			
		
		
		
		
	
		
		
	
	}

	
	
	
}
