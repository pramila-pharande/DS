import java.util.Scanner;

public class Ring 
{

    class Pro 
    {
        int id;
        boolean act;

        Pro(int id) 
        {
            this.id = id;
            act = true;
        }
    }

    int TotalProcess;
    Pro[] process;

    public Ring() { }

    public void initialiseRing() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        TotalProcess = scanner.nextInt();
        process = new Pro[TotalProcess];

        for (int i = 0; i < TotalProcess; i++) 
        {
            process[i] = new Pro(i);
        }
    }

    public void Election() {
        System.out.println("Process no " + process[FetchMaximum()].id + " fails");
        process[FetchMaximum()].act = false;
        System.out.println("Election Initiated by 2");
        int initializedProcess = 2;

        int old = initializedProcess;
        int newer = old + 1;

        while (true) 
        {
            if (process[newer].act)
            {
                System.out.println("Process " + process[old].id + " pass election message to " + process[newer].id);
                old = newer;
            }

            newer = (newer + 1) % TotalProcess;
            if (newer == initializedProcess)
            {
                break;
            }
        }

        System.out.println("Process " + process[FetchMaximum()].id + " becomes coordinator");
        int coord = process[FetchMaximum()].id;

        old = coord;
        newer = (old + 1) % TotalProcess;

        while (true) {
            if (process[newer].act) 
            {
                System.out.println("Process " + process[old].id + " pass Coordinator message to process " + process[newer].id);
                old = newer;
            }
            newer = (newer + 1) % TotalProcess;
            if (newer == coord) 
            {
                System.out.println("End Of Election ");
                break;
            }
        }
    }

    public int FetchMaximum() 
    {
        int Ind = 0;
        int maxId = -9999;
        for (int i = 0; i < process.length; i++) 
        {
            if (process[i].act && process[i].id > maxId) 
            {
                maxId = process[i].id;
                Ind = i;
            }
        }
        return Ind;
    }

    public static void main(String arg[]) 
    {
        Ring object = new Ring();
        object.initialiseRing();
        object.Election();
    }
}
