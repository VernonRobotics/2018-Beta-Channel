package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class JsScaled extends Joystick {
    public double low = .9; // set to 1 /full drive if above
    public double deadzone = .15; //set to 0 if below
    public Double pY = 0.0; 
    public Double pX = 0.0;
    public Double pTwist = 0.0;
    public boolean[] buttons = new boolean[15];
    public double cycleTime; //to be determined needs testing
    Timer pwmTimer;
    Timer rampTimer = new Timer();
    
    
    public JsScaled(int port, double low, double deadzone) {
	this(port);
	this.low = low;
	this.deadzone = deadzone;
	// TODO Auto-generated constructor stub
    }

    public JsScaled(int port) {
	super(port);
	// TODO Auto-generated constructor stub
    }

    public JsScaled(int port, int numAxisTypes, int numButtonTypes, double low,
	    double deadzone) {
	this(port, numAxisTypes, numButtonTypes);
	this.low = low;
	this.deadzone = deadzone;
    }

    //public JsScaled(int port, int numAxisTypes, int numButtonTypes) {
	//super(port, numAxisTypes, numButtonTypes);
	// TODO Auto-generated constructor stub
    //}

    public double sgetX() {
	return scale(super.getX());
    }

    public double sgetY() {
	return scale(super.getY());
    }

    public double sgetTwist() {
	return scale(scale(super.getTwist()));
    }

    public double getThrottle() {
	return scale(super.getThrottle());
    }
    
    public double getpX() {
    	return pX;
    }
    
    public double getpY() {
    	return pY;
    }
    
    public double getpTwist() { return pTwist;}
    
    
    public void setpX(double x) {
    	pX = x;
    }
    
    public void setpY(double y) {
    	pY = y;
    }
    
    public void setpTwist(double twist) {
    	pTwist = twist;
    }
    
   
    
    
    public void killVStick() {
    	pX = pY = pTwist = 0.0;
    }

    // Scales Analog Joystick value in exponentially
    public double scale(double v) {
	if (v > 0 - deadzone && v < deadzone) {
	    v = 0;
	}
	else if (v>low)
	{v=1;}
	else if (v<0-low)
	{v=-1;}
	else if (v >= deadzone) {
	    v *= (v * low);
	    v += deadzone -(deadzone*deadzone);
	} else if (v <= 0 - deadzone) {
	    v *= (-v * low);
	    v -= deadzone +(deadzone*deadzone);
	}

	return v;
    }
 
    int rampState = 0;
    double speed = 0;
   double  speedChange;
    public double speedRamp(double currentSpeed, double newSpeed, double rampTime) {
    	if (rampState ==0) {
    		speedChange = newSpeed - currentSpeed;
    		rampTimer.stop();
    		rampTimer.reset();
    		rampTimer.start();
    		rampState = 1;
    		speed = speedChange/4;
    	} else if(rampState == 1) {
	    	if (rampTimer.get() > rampTime/4) {
	    		speed += speedChange/4;
	    		rampTimer.reset();
	    	}
    		if(speed >= newSpeed) {
    			rampState = 2;
    		}
    		
    	} else if (rampState == 2) {
    		rampTimer.stop();
    		rampTimer.reset();
    		rampState = 0;
    	}
    	
    	
    	
    	
    	return speed;
    }
    
    
    public double pwmDrive(double jsReading){  //method for modulating power sent to motors
    	
    	double driveTime = cycleTime * Math.abs(jsReading);// variable representing time spent driving
    	if (jsReading <= .15 || jsReading >= -.15){ // deadzone used to prevent joystick from being to responsive
    		return 0;
    	} else{
    		if(pwmTimer.get() <= driveTime){ //sounds power to motors for a percentage of cycletime based on joystick input
    			if(jsReading > 0){ 
    				return 1.0;
    			}
    			if(jsReading < 0){
    				return -1.0;
    			}
    		} else if(pwmTimer.get() >= driveTime && pwmTimer.get() <= cycleTime ){ // kills power if over the desired percentage cycle time
    			return 0.0;
    		} else{ // resets the Timer and restarts it
    			pwmTimer.stop();
    			pwmTimer.reset();
    			pwmTimer.start();
    			return 0.0;
    		}
    		
    	}
		return 0.0;
    }
}
