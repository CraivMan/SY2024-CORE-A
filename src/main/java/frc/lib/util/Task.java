package frc.lib.util;

public class Task {

    /**
     * 0 = No current task;
     * 1 = Task initalized;
     * 2 = Task begun;
     * 3 = Task finished;
     */
    private int stage;

    public Task() {
        stage = 0;
    }


    public void reset() {
        setStage(0);
    }

    public void start() {
        setStage(1);
    }


    public int getStage() {
        return stage;
    }

    public void setStage(int newStage) {
        stage = newStage;

        if (stage > 3) {
            stage = 0;

            System.out.println("Task stage ceiling exceeded. Task has been reset to default values.");
        }

        System.out.println("Stage has been set to " + stage);
    }

    public void advanceStage() {
        setStage(stage + 1);
    }
}
