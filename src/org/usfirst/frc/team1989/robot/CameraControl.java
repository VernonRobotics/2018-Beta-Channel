package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Servo;

public class CameraControl implements cmd {
	
	
	//Joystick and Servo
	JsScaled uStick;
	Servo servo;
	//Servo Default Position
	//Can range between 0 and 1
	double setPosition = 0;
	double cameraPosition = 0;
	
	//Constructor
	
	
	
	



	public CameraControl(Servo servo, JsScaled uStick) {
		// TODO Auto-generated constructor stub
		this.servo = servo;
		this.uStick = uStick;
	}














	//Class Methods
	public void cameraReset() {
		servo.set(setPosition);
	}
	
	public void counterCheck(){
		
		if (cameraPosition < 0){
			cameraPosition = 0;
		}
		
		if (cameraPosition > 1){
			cameraPosition = 1;
		}
	}
	
	public void cameraController() {
		/*if (uStick.getPOV() == 0) {
			cameraPosition += 0.025;
		}
		else if (uStick.getPOV() == 180){
			cameraPosition -= 0.025;
		}
		counterCheck();
		servo.set(cameraPosition);*/
	}
	
	
	
	//Robot Methods
	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		cameraReset();
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
		cameraReset();
	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		cameraController();
	}
	
	

}
