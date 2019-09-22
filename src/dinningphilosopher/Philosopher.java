package dinningphilosopher;

class Philosopher extends Thread
{
   private int phLabel;
   private int phStatus;
   private Chopstick chpLeft;
   private Chopstick chpRight;
   public boolean victim;

   javax.swing.JLabel img_think;
   javax.swing.JLabel img_wt_0;
   javax.swing.JLabel img_wt_L;
   javax.swing.JLabel img_wt_R;   
   javax.swing.JLabel img_eat;
   javax.swing.JLabel img_tim;

   public Philosopher(int l,int s)//,javax.swing.JLabel it, javax.swing.JLabel iw0 , javax.swing.JLabel iwl, javax.swing.JLabel iwr, javax.swing.JLabel iwlr, javax.swing.JLabel ie )
   {
       phLabel=l;
       phStatus=s;
       victim=false;
       chpLeft=null;
       chpRight=null;
/*
       img_think=it;
       img_wt_0=iw0;
       img_wt_L=iwl;
       img_wt_R=iwr;
       img_wt_LR=iwlr;
       img_eat=ie;
 * 
 */
   }

   //----------------------
   public void reset()
   {
       phStatus=0;
       victim=false;
       chpLeft=null;
       chpRight=null;

       resetImage();
       img_think.setVisible(true);
   }

   private void resetImage()
   {
       img_think.setVisible(false);
       img_wt_0.setVisible(false);
       img_wt_L.setVisible(false);
       img_wt_R.setVisible(false);       
       img_eat.setVisible(false);
       img_tim.setVisible(false);
   }

   public void setImgae(javax.swing.JLabel it, javax.swing.JLabel iw0 , javax.swing.JLabel iwl, javax.swing.JLabel iwr, javax.swing.JLabel ie, javax.swing.JLabel itm)
   {
       img_think=it;
       img_wt_0=iw0;
       img_wt_L=iwl;
       img_wt_R=iwr;       
       img_eat=ie;
       img_tim=itm;
   }
   
   //-------------------------

   public void allocatePhChopsticks(Chopstick cL,Chopstick cR,int rule)
   {

       switch(rule)
       {
           case 0: {
                       if( (chpLeft==null && chpRight==null) || (chpLeft==null && chpRight!=null) )
                       {  SelectSlot('L', cL);      }
                       else if(chpLeft!=null && chpRight==null)
                       {  SelectSlot('R', cR);      }
                   }break;

           case 1: {
                       if( (chpLeft==null && chpRight==null) || (chpLeft==null && chpRight!=null) )
                       {  SelectSlot('L', cL);      }
                       else if(chpLeft!=null && chpRight==null)
                       {  SelectSlot('R', cR);      }
                   }break;

           case 2: {
                       if( cL.getChopstickStatus()==true && cR.getChopstickStatus()==true )
                       {
                           SelectSlot('L', cL);
                           SelectSlot('R', cR);
                       }
                   }break;

           case 3: {
                       if(phLabel%2==0)       //even
                       {
                           if(chpLeft==null && chpRight==null)
                           {  SelectSlot('R', cR);      }
                           else if(chpLeft==null && chpRight!=null)
                           {  SelectSlot('L', cL);      }

                       }
                       else        //odd
                       {
                           if(chpLeft==null && chpRight==null)
                           {  SelectSlot('L', cL);      }
                           else if(chpLeft!=null && chpRight==null)
                           {  SelectSlot('R', cR);      }
                       }
                   }break;

       }


   }

   private void SelectSlot(char chNo,Chopstick c)
   {
       switch(chNo)
       {
           case 'L': {   if(c.getChopstickStatus()==true)
                       {  chpLeft = c;                     //allocate
                          c.setChopstickStatus(false);     // change chopstick status
                          upgradePhStatus();
                          return;
                       }
                       else
                       { return;  }                 // cannot allocate

                   }

           case 'R': {   if(c.getChopstickStatus()==true)
                       {  chpRight = c;
                          c.setChopstickStatus(false);
                          upgradePhStatus();
                          return;
                       }
                       else
                       { return;}
                   }
       }

    }

   private void releasePhChopsticks()
   {
       
       chpLeft.setChopstickStatus(true);
       chpLeft=null;
       chpRight.setChopstickStatus(true);
       chpRight=null;

       phStatus=0;    //THINKING
//       resetImage();
//       img_think.setVisible(true);


   }

   public void upgradePhStatus()
   {
       /*
      if(chpLeft==null || chpRight==null)
      { phStatus=1; }
      else
      { phStatus=2; }
       */

       
      if(chpLeft==null && chpRight==null)
      { phStatus=1;
        try{  sleep(400); }
        catch(InterruptedException e)
        { System.out.println("Error in Sleep()"); }
        
        resetImage();        
        img_wt_0.setVisible(true);
      }
      else if(chpLeft != null && chpRight == null)
      { phStatus=1;
        resetImage();
        img_wt_L.setVisible(true);
      }
      else if(chpLeft==null && chpRight!=null)
      { phStatus=1;
        resetImage();
        img_wt_R.setVisible(true);
      }      
      else
      { phStatus=2; 
        resetImage();
        img_wt_L.setVisible(true);
        img_wt_R.setVisible(true);
      }

   }

   public void performAction()
   {
      switch(phStatus)
      {
          case 0: {System.out.println("Philosopher "+phLabel+" is 'THINKING'");
                  }break;

          case 1: {System.out.println("Philosopher "+phLabel+" is 'WAITING'");
                  }break;

          case 2: {System.out.println("Philosopher "+phLabel+" is 'EATING'");


                          resetImage();
                          img_eat.setVisible(true);
                          img_tim.setVisible(true);
                          try
                          { sleep(5000); }
                          catch(InterruptedException e)
                          { System.out.println("Error in Sleep()"); }

                          victim=true;
                          releasePhChopsticks();
                          img_eat.setVisible(false);
                          img_tim.setVisible(false);

                  }break;
      }

   }

   public int getPhLabel()
   { return phLabel;    }

   public int getPhStatus()
   { return phStatus;    }

   public Chopstick getPhChp(char cno)
   {
       if(cno=='L')
       {  return chpLeft;   }
       else
       {  return chpRight;  }
   }

   public void deactivatePH()
   {  phStatus=-1;
      victim=true;
   }

   public void showPhdetails()
   {
       System.out.println("Philosopher Label : "+phLabel);
       System.out.println("State : "+phStatus);

       System.out.print("Chopstick L : ");
       if(chpLeft==null)
       {  System.out.println("null"); }
       else
       {  System.out.println(chpLeft.getChopstickLabel()); }

       System.out.print("Chopstick R : ");
       if(chpRight==null)
       {  System.out.println("null"); }
       else
       {  System.out.println(chpRight.getChopstickLabel()); }

       System.out.println("Victim : "+victim);

       System.out.println("");
   }


}
