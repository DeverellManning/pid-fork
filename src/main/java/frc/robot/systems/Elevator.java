package frc.robot.systems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;

import frc.robot.Util;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class Elevator extends System {

   private int desiredElevatorPosition = 0;
   private int minElevatorLevel = 0;
   private int maxElevatorLevel = 5;
   private double elevatorPosition0 = 0;
   private double elevatorPosition1 = 10;
   private double elevatorPosition2 = 15;
   private double elevatorPosition3 = 20;
   private double elevatorPosition4 = 25;
   private double elevatorPosition5 = 30;

   private double largeRotations = 3;
   private double mediumRotations = 0.5;
   //private double smallRotations = 2;



    private SparkMax elevatorMotor1 = new SparkMax(10, MotorType.kBrushless);
    private SparkMax elevatorMotor2 = new SparkMax(13, MotorType.kBrushless);

    SparkClosedLoopController pid1;
    SparkClosedLoopController pid2;
    double target;

    RelativeEncoder encoder;

    public Elevator(){
        elevatorMotor1.getEncoder().setPosition(0.0);
        elevatorMotor2.getEncoder().setPosition(0.0);
        SparkMaxConfig motor1Config = new SparkMaxConfig();
        motor1Config.inverted(false);
        motor1Config.softLimit
        .forwardSoftLimitEnabled(true)
        .forwardSoftLimit(41)
        .reverseSoftLimitEnabled(true)
        .reverseSoftLimit(0);
        motor1Config.idleMode(IdleMode.kBrake);
        elevatorMotor1.configure(motor1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motor1Config.closedLoop.pid(0.01, 0, 0);
        pid1 = elevatorMotor1.getClosedLoopController();


        SparkMaxConfig motor2Config = new SparkMaxConfig();
        motor2Config.inverted(true);
        motor2Config.softLimit
        .forwardSoftLimitEnabled(true)
        .forwardSoftLimit(41)
        .reverseSoftLimitEnabled(true)
        .reverseSoftLimit(0);
        motor2Config.idleMode(IdleMode.kBrake);
        elevatorMotor2.configure(motor2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motor2Config.closedLoop.pid(0.01, 0, 0);
        pid2 = elevatorMotor2.getClosedLoopController();
        
       encoder = elevatorMotor1.getEncoder();
    }

    public void elevatorUp(){
        desiredElevatorPosition++;
        if(desiredElevatorPosition > maxElevatorLevel){
            desiredElevatorPosition = maxElevatorLevel;
        }
    }

    public void elevatorDown(){
        desiredElevatorPosition--;
        if (desiredElevatorPosition < minElevatorLevel){
            desiredElevatorPosition = minElevatorLevel;
        }
    }

    public double getEncoder(int motor){


        if (motor == 1){
            return elevatorMotor1.getEncoder().getPosition();
        } else if (motor == 2){
            return elevatorMotor2.getEncoder().getPosition();
        } else {
            return 0;
        }
    }
    public boolean encoderSetZero(){
        elevatorMotor1.getEncoder().setPosition(0.0);
        elevatorMotor2.getEncoder().setPosition(0.0);
        return true;
    }

    public boolean setElevatorPosition(int position){
        if(position > maxElevatorLevel){
            return false;
        } else if (position < minElevatorLevel){
            return false;
        } else {
            desiredElevatorPosition = position;
            return true;
        }
    }

    public void test(double speed){
        elevatorMotor1.set(speed);
        elevatorMotor2.set(speed);
    }

    public void moveToSetpoint(){
        pid1.setReference(target, ControlType.kPosition);
        pid2.setReference(target, ControlType.kPosition);
    }

    
    public void update(){
        
        switch(desiredElevatorPosition){
            case 0:
                target = -elevatorPosition0;
                break;
            case 1:
                target = -elevatorPosition1;
                break;
            case 2:
                target = -elevatorPosition2;
                break;
            case 3:
                target = -elevatorPosition3;
                break;
            case 4:
                target = -elevatorPosition4;
                break;
            case 5:
                target = -elevatorPosition5;
                break;
            default:
            break;
        }
        moveToSetpoint();
        Util.log(String.valueOf(target) +":::::::::::::::::"+ String.valueOf(elevatorMotor1.get()));
    }

}
