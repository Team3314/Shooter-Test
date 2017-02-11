package org.usfirst.frc.team3314.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
		CANTalon talon1;
		CANTalon talon2;
		CANTalon talon3;
		CANTalon talon4;
		Joystick stick;
		PIDController shooterPID;
		
		double kShooterKp = .2;
		double kShooterKi = 0;
		double kShooterKd = 0;
		double kShooterKf = 2;
		int kShooterIZone = 0;
		double kShooterRampRate = 0;
		
		

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		talon1 = new CANTalon(0);
		talon2 = new CANTalon(1);
		talon3 = new CANTalon(2);
		talon4 = new CANTalon(3);
		stick = new Joystick(0);
		
		/*
		
		
				talon1.setReverseSoftLimit(0);
		
		shooterPID = new PIDController(0, 0, 0, 0, talon1, talon1);
		shooterPID.setOutputRange(0, 1);*/
		
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
	
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
	
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopInit() {
		
		talon2.reverseSensor(false);
		talon2.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		talon2.changeControlMode(CANTalon.TalonControlMode.Speed);
		talon2.setPID(kShooterKp, kShooterKi, kShooterKd, kShooterKf, kShooterIZone, kShooterRampRate, 0);
		talon2.configNominalOutputVoltage(+0.0f, -0.0f);
		talon2.configPeakOutputVoltage(+12.0f, -12.0f);
		talon2.configEncoderCodesPerRev(2048);
		talon2.setPosition(0);
		talon2.setReverseSoftLimit(0);
		talon2.setForwardSoftLimit(1000);
		
	}
	
	@Override
	public void teleopPeriodic() {
		double targetSpeed = ((stick.getZ() + 1) /2) * 90; 
		SmartDashboard.putNumber("Talon1 Voltage", talon1.getOutputVoltage());
		SmartDashboard.putNumber("Talon2 Voltage", talon2.getOutputVoltage());
		SmartDashboard.putNumber("Talon3 Voltage", talon3.getOutputVoltage());
		SmartDashboard.putNumber("Talon4 Voltage", talon4.getOutputVoltage());
		SmartDashboard.putNumber("Encoder1 Position", talon1.getEncPosition());
		SmartDashboard.putNumber("Encoder2 Position", talon2.getEncPosition());
		SmartDashboard.putNumber("Encoder3 Position", talon3.getEncPosition());
		SmartDashboard.putNumber("Encoder4 Position", talon4.getEncPosition());
		SmartDashboard.putNumber("Throttle", (stick.getZ() + 1)/2);
		SmartDashboard.putNumber("Motor Speed1", talon1.getSpeed());
		SmartDashboard.putNumber("Motor Speed2", talon2.getSpeed());
		SmartDashboard.putNumber("Motor Speed3", talon3.getSpeed());
		SmartDashboard.putNumber("Motor Speed4", talon4.getSpeed());
		SmartDashboard.putNumber("Talon1 Current", talon1.getOutputCurrent());
		SmartDashboard.putNumber("Talon2 Current", talon2.getOutputCurrent());
		SmartDashboard.putNumber("Talon3 Current", talon3.getOutputCurrent());
		SmartDashboard.putNumber("Talon4 Current", talon4.getOutputCurrent());
		talon1.set((stick.getZ() + 1)/2);
		talon2.set((stick.getZ() + 1)/2);
		talon3.set((stick.getZ() + 1)/2);
		
		talon4.set((stick.getZ() + 1)/2);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

