package org.usfirst.frc.team1321.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.mindsensors.CANLight;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();

	CANLight lights;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		
		lights = new CANLight(3);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		
		flashLongOnShortOff();
        //USAandFIRSTcolors();
        //alternateColors();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	private void flashLongOnShortOff() {
        // index time red green blue
lights.writeRegister(0, 1.0, 0,  0,  255); // on one second
lights.writeRegister(1, 0.5, 0,  0,    0); // off half second
lights.cycle(0, 1);
}

private void USAandFIRSTcolors() {
lights.writeRegister(1, 1.0, 255,   0,   0); // red
lights.writeRegister(2, 1.0, 255, 255, 255); // white
lights.writeRegister(3, 1.0,   0,   0, 255); // blue
lights.cycle(1, 3);
}

private void alternateColors() {
lights.writeRegister(1, 0.5, 255,   0,   0); // red
lights.writeRegister(2, 0.3,   0,   0,   0);
lights.writeRegister(3, 0.5,   0,   0, 255); // blue
lights.writeRegister(4, 0.3,   0,   0,   0);
lights.cycle(1, 4);
}
}

