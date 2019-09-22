
package dinningphilosopher;

class Chopstick
{
    private int chpLabel;
    private boolean available;
    javax.swing.JLabel img;

    public Chopstick(int l)
    {
        chpLabel=l;        
        available=true;
    }

    //-----------------------------
    public void reset()
    {   img.setVisible(true);
        available=true;
    }

    public void setImage(javax.swing.JLabel i)
    { img=i; }

    //-----------------------------

    public int getChopstickLabel()
    {  return chpLabel; }

    public boolean getChopstickStatus()
    {  return available; }

    public void setChopstickStatus(boolean a)
    {       
           
       available=a;
       
       if(available==true)
       { img.setVisible(true);    }
       else
       { img.setVisible(false); System.out.println("chopstick "+chpLabel+" : "+available); }
           
      

    }

}
