import java.util.ArrayList;
import java.util.Scanner;

public class Lab10
{
    public static void main(String[] args)
    {
        Scanner myScan = new Scanner(System.in);
        ArrayList<Alarm> alarms = new ArrayList<>();
        int choice = 0;

        do {
            System.out.println("1.Create new alarm\n2.View all alarms\n3.Quit");
            System.out.print("Enter option: ");
            choice = myScan.nextInt();
            myScan.nextLine();

            if(choice == 1)
            {
                System.out.print("\nEnter alarm name: ");
                String name = myScan.nextLine();

                System.out.print("Enter alarm timer in seconds: ");
                String timerInput = myScan.nextLine();

                try
                {
                    int timer = Integer.parseInt(timerInput);
                    if (timer <= 0)
                    {
                        System.out.println("Invalid timer: Timer must be greater than 0.\n");
                    }
                    else
                    {
                        Alarm alarm = new Alarm(name, timer);
                        alarms.add(alarm);
                        alarm.start();
                        System.out.println(alarm);
                        System.out.println();
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid timer: Timer must be a whole number.\n");
                }
            }
            else if(choice == 2)
            {
                for (int i = 0; i < alarms.size(); i++)
                {
                    Alarm a = alarms.get(i);
                    if (!a.isAlive()) {
                        alarms.remove(i);
                        i--;
                    }
                }

                System.out.println("Here are all the alarms still running:\n");
                for (Alarm a : alarms)
                {
                    System.out.println(a);
                }
                System.out.println();
            }
            else if(choice == 3)
            {
                System.out.println("\nStopping all alarms...\n");

                for (Alarm a : alarms)
                {
                    if (a.isAlive())
                    {
                        a.interrupt();
                    }
                }

                for (Alarm a : alarms)
                {
                    try
                    {
                        a.join();
                    }
                    catch (InterruptedException e)
                    {
                    }
                }
                System.out.println("All alarms have been stopped.");
                System.out.println("Shutting off...");
            }

        }while (choice != 3);
    }
}
