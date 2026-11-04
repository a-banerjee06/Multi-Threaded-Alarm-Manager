public class Alarm extends Thread {
    private int timer;
    private String name;
    private int id;
    private static int nextId = 1;

    public Alarm(String name, int timerInSeconds) {
        this.id = nextId++;
        this.timer = timerInSeconds * 1000;

        if (name == null || name.isEmpty()) {
            this.name = "Alarm " + this.id;
        } else {
            this.name = name;
        }
    }

    @Override
    public void run()
    {
        int seconds = timer / 1000;
        while (seconds > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(name + " has been interrupted at " + seconds + " seconds.");
                return;
            }
            seconds--;
            timer = seconds * 1000;
            if (seconds == 10) {
                System.out.println(name + " will go off in 10 seconds.");
            }
        }
        System.out.println(name + " has gone off!");
    }

    @Override
    public String toString()
    {
        return name + " is currently at " + (timer / 1000) + " seconds";
    }
}
