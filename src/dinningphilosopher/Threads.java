package dinningphilosopher;

class Threads extends Thread
{
    private Philosopher thPh;
    private Chopstick thCL,thCR;
    private char ThFunc;
    private int rl;
    javax.swing.JLabel chpImg;

    public Threads(Philosopher p,Chopstick cL,Chopstick cR,char f,int r)
    {
        this.thPh=p;
        this.thCL=cL;
        this.thCR=cR;
        this.ThFunc=f;
        rl=r;        
        start();
    }

    public void AllocateMethod()
    {
        //System.out.println("Allocating Philosopher "+thPh.getPhLabel());
        
           thPh.allocatePhChopsticks(thCL, thCR,rl);           

        //System.out.println("Allocating Philosopher "+thPh.getPhLabel()+" Finished");
    }

    public void ExecuteMethod()
    {        
           thPh.performAction();              
    }

    public void run()
    {     
        if(ThFunc=='A')
        { AllocateMethod();      }
        else if(ThFunc=='E')
        { ExecuteMethod();    }
        
    }
}
