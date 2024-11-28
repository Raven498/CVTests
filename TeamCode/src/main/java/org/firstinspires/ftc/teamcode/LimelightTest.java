package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

/*
GIVEN:
- Target X (pixels) = xCP
- Target Y (pixels) = yCP
- Target Area (pixels) = aCP
- X Range (meters) = xR_M_MIN, xR_M_MAX
- Y Range (meters) = yR_M_MIN, yR_M_MAX

CALCULATE:
- Target X (meters) = xRM
- Target Y (meters) = yRM
- Target Angle (meters) = ORM
- Pickup State (true/false)
 */
public class LimelightTest extends LinearOpMode {
    double x_cam_pix;
    double x_off_cam_pix;
    double y_cam_pix;
    double y_off_cam_pix;
    double a_cam_pix;
    double x_real_meter;
    double x_off_real_meter;
    double y_off_real_meter;
    double y_real_meter;
    double a_real_meter;

    double x_cam_pix_min;
    double x_cam_pix_max;
    double y_cam_pix_min;
    double y_cam_pix_max;
    double x_real_meter_min;
    double x_real_meter_max;
    double y_real_meter_min;
    double y_real_meter_max;

    double theta_real_meter;

    int blueWide = 0;
    int blueTall = 1;
    int redWide = 2;
    int redTall = 3;

    boolean pickup;
    private Limelight3A limelight;

    @Override
    public void runOpMode() throws InterruptedException {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        telemetry.setMsTransmissionInterval(11);


        limelight.pipelineSwitch(0);

        /*
         * Starts polling for data.
         */
        limelight.start();
        while (opModeIsActive()) {
            limelight.pipelineSwitch(blueWide);
            LLResult blueWideResult = limelight.getLatestResult();
            if (blueWideResult != null) {
                if (blueWideResult.isValid()) {
                    Pose3D botpose = blueWideResult.getBotpose();
                    telemetry.addData("COLOR: ", "BLUE");
                    telemetry.addData("ANGLE: ", "0");

                    //POSITIONAL CHECK
                    if(blueWideResult.getTx() <= x_cam_pix_max &&  blueWideResult.getTx() >= x_cam_pix_min){
                        pickup = true;
                    } else{
                        pickup = false;
                    }

                    telemetry.addData("tx", blueWideResult.getTx());
                    telemetry.addData("ty", blueWideResult.getTy());
                    telemetry.addData("ta", blueWideResult.getTa());
                    telemetry.addData("Botpose", botpose.toString());
                    continue;
                }
            }

            limelight.pipelineSwitch(blueTall);
            LLResult blueTallResult = limelight.getLatestResult();
            if (blueTallResult != null) {
                if (blueTallResult.isValid()) {
                    Pose3D botpose = blueTallResult.getBotpose();
                    telemetry.addData("COLOR: ", "BLUE");
                    telemetry.addData("ANGLE: ", "90");

                    //POSITIONAL CHECK
                    if(blueTallResult.getTx() <= x_cam_pix_max &&  blueTallResult.getTx() >= x_cam_pix_min){
                        pickup = true;
                    } else{
                        pickup = false;
                    }
                    telemetry.addData("tx", blueTallResult.getTx());
                    telemetry.addData("ty", blueTallResult.getTy());
                    telemetry.addData("ta", blueTallResult.getTa());
                    telemetry.addData("Botpose", botpose.toString());
                    continue;
                }
            }

            limelight.pipelineSwitch(redWide);
            LLResult redWideResult = limelight.getLatestResult();
            if (redWideResult != null) {
                if (redWideResult.isValid()) {
                    Pose3D botpose = redWideResult.getBotpose();
                    telemetry.addData("COLOR: ", "RED");
                    telemetry.addData("ANGLE: ", "0");

                    //POSITIONAL CHECK
                    if(redWideResult.getTx() <= x_cam_pix_max &&  redWideResult.getTx() >= x_cam_pix_min){
                        pickup = true;
                    } else{
                        pickup = false;
                    }
                    telemetry.addData("tx", redWideResult.getTx());
                    telemetry.addData("ty", redWideResult.getTy());
                    telemetry.addData("ta", redWideResult.getTa());
                    telemetry.addData("Botpose", botpose.toString());
                    continue;
                }
            }

            limelight.pipelineSwitch(redTall);
            LLResult redTallResult = limelight.getLatestResult();
            if (redTallResult != null) {
                if (redTallResult.isValid()) {
                    Pose3D botpose = redTallResult.getBotpose();
                    telemetry.addData("COLOR: ", "RED");
                    telemetry.addData("ANGLE: ", "90");

                    //POSITIONAL CHECK
                    if(redTallResult.getTx() <= x_cam_pix_max &&  redTallResult.getTx() >= x_cam_pix_min){
                        pickup = true;
                    } else{
                        pickup = false;
                    }
                    telemetry.addData("tx", redTallResult.getTx());
                    telemetry.addData("ty", redTallResult.getTy());
                    telemetry.addData("ta", redTallResult.getTa());
                    telemetry.addData("Botpose", botpose.toString());
                }
            }
        }
    }
}
