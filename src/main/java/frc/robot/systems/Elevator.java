package frc.robot.systems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import frc.robot.Util;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class Elevator extends System {
   private double motorPosition;

   private double largeElevatorSpeed = 0.3;
   private double mediumElevatorSpeed = 0.15;
   private double smallElavatorSpeed = 0.01;

   private double motorVelocity = 0;

   private int desiredElevatorPosition = 0;
   private int minElevatorLevel = 0;
   private int maxElevatorLevel = 5;

   private double elevatorPosition0 = 0;
   private double elevatorPosition1 = 0.01;
   private double elevatorPosition2 = 0.02;
   private double elevatorPosition3 = 0.03;
   private double elevatorPosition4 = 0.04;
   private double elevatorPosition5 = 0.05;

   private double largeRotations = 2;
   private double mediumRotations = 0.3;
   //private double smallRotations = 2;

    private SparkMax elevatorMotor1 = new SparkMax(10, MotorType.kBrushless);
    private SparkMax elevatorMotor2 = new SparkMax(13, MotorType.kBrushless);

    public Elevator(){
        
        SparkMaxConfig motor1Config = new SparkMaxConfig();
        motor1Config.inverted(false);
        motor1Config.softLimit
        .forwardSoftLimitEnabled(false)
        .forwardSoftLimit(0.2)
        .reverseSoftLimitEnabled(false)
        .reverseSoftLimit(0.2);
        motor1Config.idleMode(IdleMode.kBrake);
        elevatorMotor1.configure(motor1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


        SparkMaxConfig motor2Config = new SparkMaxConfig();
        motor2Config.inverted(true);
        motor2Config.softLimit
        .forwardSoftLimitEnabled(false)
        .forwardSoftLimit(0.2)
        .reverseSoftLimitEnabled(false)
        .reverseSoftLimit(0.2);
        motor2Config.idleMode(IdleMode.kBrake);
        elevatorMotor2.configure(motor2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
        elevatorMotor1.getEncoder().setPosition(0.0);
        elevatorMotor2.getEncoder().setPosition(0.0);

       motorPosition = elevatorMotor1.getEncoder().getPosition(); // This value should be 0
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
    
    public void update(){
        motorPosition = elevatorMotor1.getEncoder().getPosition();
        
        switch(desiredElevatorPosition){
            case 0:


                if (elevatorPosition0 < motorPosition){
                    if (Math.abs(elevatorPosition0 - motorPosition) >= largeRotations){
                        motorVelocity = -largeElevatorSpeed;
                    } else if (Math.abs(elevatorPosition0 - motorPosition) >= mediumRotations){
                        motorVelocity = -mediumElevatorSpeed;
                    } else {
                        motorVelocity = -smallElavatorSpeed;
                    }
                } else if (elevatorPosition0 > motorPosition){
                    motorVelocity = 0;
                } else {
                    motorVelocity = 0;
                }
                break;
            case 1:



                if (elevatorPosition1 < motorPosition){
                    if (Math.abs(elevatorPosition1 - motorPosition) >= largeRotations){
                        motorVelocity = -largeElevatorSpeed;
                    } else if (Math.abs(elevatorPosition1 - motorPosition) >= mediumRotations){
                        motorVelocity = -mediumElevatorSpeed;
                    } else {
                        motorVelocity = -smallElavatorSpeed;
                    }
                } else if (elevatorPosition1 > motorPosition){
                    if (Math.abs(elevatorPosition1 - motorPosition) >= largeRotations){
                        motorVelocity = largeElevatorSpeed;
                    } else if (Math.abs(elevatorPosition1 - motorPosition) >= mediumRotations){
                        motorVelocity = mediumElevatorSpeed;
                    } else {
                        motorVelocity = smallElavatorSpeed;
                    }
                } else {
                    motorVelocity = 0;
                }


                break;
            case 2:


            if (elevatorPosition2 < motorPosition){
                if (Math.abs(elevatorPosition2 - motorPosition) >= largeRotations){
                    motorVelocity = -largeElevatorSpeed;
                } else if (Math.abs(elevatorPosition2 - motorPosition) >= mediumRotations){
                    motorVelocity = -mediumElevatorSpeed;
                } else {
                    motorVelocity = -smallElavatorSpeed;
                }
            } else if (elevatorPosition2 > motorPosition){
                if (Math.abs(elevatorPosition2 - motorPosition) >= largeRotations){
                    motorVelocity = largeElevatorSpeed;
                } else if (Math.abs(elevatorPosition2 - motorPosition) >= mediumRotations){
                    motorVelocity = mediumElevatorSpeed;
                } else {
                    motorVelocity = smallElavatorSpeed;
                }
            } else {
                motorVelocity = 0;
            }


                break;
            case 3:


            if (elevatorPosition3 < motorPosition){
                if (Math.abs(elevatorPosition3 - motorPosition) >= largeRotations){
                    motorVelocity = -largeElevatorSpeed;
                } else if (Math.abs(elevatorPosition3 - motorPosition) >= mediumRotations){
                    motorVelocity = -mediumElevatorSpeed;
                } else {
                    motorVelocity = -smallElavatorSpeed;
                }
            } else if (elevatorPosition3 > motorPosition){
                if (Math.abs(elevatorPosition3 - motorPosition) >= largeRotations){
                    motorVelocity = largeElevatorSpeed;
                } else if (Math.abs(elevatorPosition3 - motorPosition) >= mediumRotations){
                    motorVelocity = mediumElevatorSpeed;
                } else {
                    motorVelocity = smallElavatorSpeed;
                }
            } else {
                motorVelocity = 0;
            }



                break;
            case 4:



            if (elevatorPosition4 < motorPosition){
                if (Math.abs(elevatorPosition4 - motorPosition) >= largeRotations){
                    motorVelocity = -largeElevatorSpeed;
                } else if (Math.abs(elevatorPosition4 - motorPosition) >= mediumRotations){
                    motorVelocity = -mediumElevatorSpeed;
                } else {
                    motorVelocity = -smallElavatorSpeed;
                }
            } else if (elevatorPosition4 > motorPosition){
                if (Math.abs(elevatorPosition4 - motorPosition) >= largeRotations){
                    motorVelocity = largeElevatorSpeed;
                } else if (Math.abs(elevatorPosition4 - motorPosition) >= mediumRotations){
                    motorVelocity = mediumElevatorSpeed;
                } else {
                    motorVelocity = smallElavatorSpeed;
                }
            } else {
                motorVelocity = 0;
            }



                break;
            case 5:



                if (elevatorPosition5 < motorPosition){
                    if (Math.abs(elevatorPosition5 - motorPosition) >= largeRotations){
                        motorVelocity = -largeElevatorSpeed;
                    } else if (Math.abs(elevatorPosition5 - motorPosition) >= mediumRotations){
                        motorVelocity = -mediumElevatorSpeed;
                    } else {
                        motorVelocity = -smallElavatorSpeed;
                    }
                } else if (elevatorPosition5 > motorPosition){
                    if (Math.abs(elevatorPosition5 - motorPosition) >= largeRotations){
                        motorVelocity = largeElevatorSpeed;
                    } else if (Math.abs(elevatorPosition5 - motorPosition) >= mediumRotations){
                        motorVelocity = mediumElevatorSpeed;
                    } else {
                        motorVelocity = smallElavatorSpeed;
                    }
                } else {
                    motorVelocity = 0;
                }
                break;
        }
        elevatorMotor1.set(motorVelocity);
        elevatorMotor2.set(motorVelocity);
    }

}
