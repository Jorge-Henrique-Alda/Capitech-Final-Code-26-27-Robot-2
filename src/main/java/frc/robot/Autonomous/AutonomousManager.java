package frc.robot.Autonomous;

import frc.robot.Subsystems.Driver.DriverSubsystem;

public class AutonomousManager {

    // === SUBSYSTEMS ===
    private final DriverSubsystem drivetrain;

    // === AUTONOMOUS ATUAL ===
    private AutonomousRoutine activeRoutine;

    public AutonomousManager(DriverSubsystem drivetrain) {

        this.drivetrain = drivetrain;

    }
    // === INICIAR AUTONOMOUS ===
    public void start(String selectedAuto) {

        // Encerra qualquer rotina anterior
        stop();

        // Seleciona a rotina
        switch (selectedAuto == null ? "" : selectedAuto) {
            case "AUTO_1":
                activeRoutine = new Saida1Auto(
                        drivetrain
                );

                break;

            case "DEFAULT":
                activeRoutine = null;
                break;

        }
        if (activeRoutine != null) {
            activeRoutine.start();
        }
    }

    // === ATUALIZAR AUTONOMOUS ===
    public void periodic() {
        if (activeRoutine == null) {
            return;
        }
        activeRoutine.periodic();

        if (activeRoutine.isFinished()) {
            activeRoutine.end();

            activeRoutine = null;
        }
    }

    // === ENCERRAR AUTONOMOUS ===
    public void stop() {
        if (activeRoutine != null) {
            activeRoutine.end();

            activeRoutine = null;
        }

        drivetrain.stop();

    }
}
