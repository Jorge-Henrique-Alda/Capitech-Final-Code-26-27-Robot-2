
package frc.robot.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Subsystems.Driver.DriverSubsystem;

public class Saida1Auto implements AutonomousRoutine {

    private final DriverSubsystem drivetrain;

    // === TEMPORIZADOR ===
    private final Timer timer = new Timer();

    // === STATE MACHINE ===
    private enum AutoState {
        PREPARING,
        DRIVER,
        FINISH
    }

    private AutoState currentState = AutoState.FINISH;

    // === CONSTRUTOR ===
    public Saida1Auto(DriverSubsystem drivetrain) {
        this.drivetrain = drivetrain;
    }

    // === INICIAR AUTONOMOUS ===
    @Override
    public void start() {

        drivetrain.stop();

        currentState = AutoState.PREPARING;

        timer.restart();
    }

    // === EXECUTAR STATE MACHINE ===
    @Override
    public void periodic() {

        switch (currentState) {

            case PREPARING:

                drivetrain.stop();

                if (timer.hasElapsed(0.25)) {

                    timer.restart();

                    currentState = AutoState.DRIVER;
                }

                break;

            case DRIVER:

                drivetrain.arcadeDrive(0.5, 0.0);

                if (timer.hasElapsed(1.0)) {

                    timer.restart();

                    drivetrain.arcadeDrive(-0.5, 0.0);

                    currentState = AutoState.FINISH;
                }

                break;

            case FINISH:

                drivetrain.stop();

                timer.stop();

                break;
        }
    }

    // === VERIFICAR FINALIZAÇÃO ===
    @Override
    public boolean isFinished() {

        return currentState == AutoState.FINISH;
    }

    // === ENCERRAR AUTONOMOUS ===
    @Override
    public void end() {

        timer.stop();

        drivetrain.stop();

        currentState = AutoState.FINISH;
    }
}
