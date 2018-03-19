package org.usfirst.frc.team1989.robot;


public class ArmControl implements cmd {
	//Attributes
	CANTalon1989 leftMotor;
	CANTalon1989 rightMotor;
	JsScaled uStick;
	
	//Basic Constructor
	public ArmControl(CANTalon1989 leftMotor, CANTalon1989 rightMotor, JsScaled utilityStick) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		uStick = utilityStick;
	}
	
	//Class Methods
	public void boxIn() {
		leftMotor.set(1);
		rightMotor.set(1);
	}
	public void boxOut() {
		leftMotor.set(-1);
		rightMotor.set(-1);
	}
	public void boxStop() {
		leftMotor.set(0);
		rightMotor.set(0);
	}
	
	public void boxSpinLeftIn() {
		leftMotor.set(1);
	}
	
	public void boxSpinLeftOut() {
		leftMotor.set(-1);
	}
	
	public void boxSpinRightIn() {
		rightMotor.set(1);
	}
	public void boxSpinRightOut() {
		rightMotor.set(-1);
	}
	
	//Cmd List Methods
	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		if(uStick.getRawButton(2))
			boxIn();
		else if(uStick.getRawButton(1))
			boxOut();
		else if(uStick.getRawButton(3)) 
			boxSpinLeftIn();
		else if(uStick.getRawButton(5))
			boxSpinLeftOut();
		else if(uStick.getRawButton(4))
			boxSpinRightIn();
		else if(uStick.getRawButton(6)) 
			boxSpinRightOut();
		else boxStop();
		
		
		
		
		
		
	}
	
	
	

}

