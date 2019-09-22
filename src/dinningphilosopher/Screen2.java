package dinningphilosopher;

//import javax.swing.JFrame;

public class Screen2 extends javax.swing.JFrame {
        
    //----------------DECLARATIONS-------------------------
        //Chopsticks
        static Chopstick c1=new Chopstick(1);
        static Chopstick c2=new Chopstick(2);
        static Chopstick c3=new Chopstick(3);
        static Chopstick c4=new Chopstick(4);
        static Chopstick c5=new Chopstick(5);

        //array for philosophers
        static Philosopher p[];

        //Thread declarations
        static Thread thAllChp[];
        static Thread thExPh[];
    
        //Variables
        static int ruleChoice=-1;              // For rule Choice
        static int noOfPh;                   // For total number of philosophers
        static boolean DEADLOCK=true;          // Deadlock chk
        static boolean loop=true;
        static boolean exe=false;

    public Screen2()    //Creates new form Screen2
    {
        initComponents();

        //reset interface images
        deadlock_image.setVisible(false);
        stable_image.setVisible(false);
        start_deactive.setVisible(false);
        reset_button.setVisible(false);

        //reset philosophers images
        ph1_wt_0.setVisible(false);
        ph1_wt_L.setVisible(false);
        ph1_wt_R.setVisible(false);
        ph1_eat.setVisible(false);
        ph1_timmer.setVisible(false);
        
        ph2_wt_0.setVisible(false);
        ph2_wt_L.setVisible(false);
        ph2_wt_R.setVisible(false);
        ph2_eat.setVisible(false);
        ph2_timmer.setVisible(false);

        ph3_wt_0.setVisible(false);
        ph3_wt_L.setVisible(false);
        ph3_wt_R.setVisible(false);
        ph3_eat.setVisible(false);
        ph3_timmer.setVisible(false);

        ph4_wt_0.setVisible(false);
        ph4_wt_L.setVisible(false);
        ph4_wt_R.setVisible(false);
        ph4_eat.setVisible(false);
        ph4_timmer.setVisible(false);

        ph5_wt_0.setVisible(false);
        ph5_wt_L.setVisible(false);
        ph5_wt_R.setVisible(false);
        ph5_eat.setVisible(false);
        ph5_timmer.setVisible(false);

        sad_ph5.setVisible(false);
    
        
        //allocate image to chopstick objects
        c1.setImage(chp1_image);
        c2.setImage(chp2_image);
        c3.setImage(chp3_image);
        c4.setImage(chp4_image);
        c5.setImage(chp5_image);

        p=new Philosopher[5];     // Allocating Philosopher array        
        p[0]=new Philosopher(1,0);  p[0].setImgae(ph1_thinking, ph1_wt_0, ph1_wt_L, ph1_wt_R, ph1_eat, ph1_timmer);
        p[1]=new Philosopher(2,0);  p[1].setImgae(ph2_thinking, ph2_wt_0, ph2_wt_L, ph2_wt_R, ph2_eat, ph2_timmer);
        p[2]=new Philosopher(3,0);  p[2].setImgae(ph3_thinking, ph3_wt_0, ph3_wt_L, ph3_wt_R, ph3_eat, ph3_timmer);
        p[3]=new Philosopher(4,0);  p[3].setImgae(ph4_thinking, ph4_wt_0, ph4_wt_L, ph4_wt_R, ph4_eat, ph4_timmer);
        p[4]=new Philosopher(5,0);  p[4].setImgae(ph5_thinking, ph5_wt_0, ph5_wt_L, ph5_wt_R, ph5_eat, ph5_timmer);

        //comments
        hideAllComments();
        cmt_welcom.setVisible(true);
        cmt_select.setVisible(true);        

    }

    public static void EXECUTEALGO()
    {       

        DEADLOCK=true;        

        switch(ruleChoice)
        {
            case 0: {  System.out.println("Rule Zero");  comment(6);

                       noOfPh=5;                      // Total Philosophers

                       thAllChp=new Thread[noOfPh];
                       thExPh=new Thread[noOfPh];


                       //set status to waiting
                       for(int i=0;i<noOfPh;i++)
                       {  p[i].upgradePhStatus();        }


                       do
                       {

                      //Threads for allocating chopsticks

                          //Allocate first chopstick
                          for(int j=0;j<noOfPh;j++)
                          {
                             if(p[j].victim!=true)     // check for startvation
                             {
                                switch(p[j].getPhLabel())
                                {
                                    case 1: thAllChp[j]=new Threads(p[j],c2,c1,'A',ruleChoice); break;
                                    case 2: thAllChp[j]=new Threads(p[j],c3,c2,'A',ruleChoice); break;
                                    case 3: thAllChp[j]=new Threads(p[j],c4,c3,'A',ruleChoice); break;
                                    case 4: thAllChp[j]=new Threads(p[j],c5,c4,'A',ruleChoice); break;
                                    case 5: thAllChp[j]=new Threads(p[j],c1,c5,'A',ruleChoice); break;
                                }

                                try{
                                      //System.out.print(thAllChp[j].getName()+" is Alive & delaying...");
                                      thAllChp[j].sleep(1000);
                                      //System.out.println("Done!");
                                      thAllChp[j].join();                                       
                                }catch(InterruptedException e)
                                { System.out.println("Error in Sleep()"); }
                              }

                          }
                        
                       //Wait for threads to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thAllChp[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }


                       //Allocate Second chopstick
                          for(int j=0;j<noOfPh;j++)
                          {
                             if(p[j].victim!=true)     // check for startvation
                             {
                                switch(p[j].getPhLabel())
                                {
                                    case 1: thAllChp[j]=new Threads(p[j],c2,c1,'A',ruleChoice); break;
                                    case 2: thAllChp[j]=new Threads(p[j],c3,c2,'A',ruleChoice); break;
                                    case 3: thAllChp[j]=new Threads(p[j],c4,c3,'A',ruleChoice); break;
                                    case 4: thAllChp[j]=new Threads(p[j],c5,c4,'A',ruleChoice); break;
                                    case 5: thAllChp[j]=new Threads(p[j],c1,c5,'A',ruleChoice); break;

                                }
                                try{
                                      thAllChp[j].sleep(1000);
                                      thAllChp[j].join();
                                }catch(InterruptedException e)
                                { System.out.println("Error in Sleep()"); }
                              }
                             

                          }

                       //Wait for threads to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thAllChp[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }




                       //Threads for executing philosophers
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim!=true)         // check for startvation
                           {  thExPh[j]=new Threads(p[j], null, null, 'E',ruleChoice);
                              System.out.println(thExPh[j].getName()+" eating!");
                           }
                       }

                       //Wait for thread to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thExPh[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }



                       //Check for deadlock
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim==true)
                           {  DEADLOCK=false; break; }
                           else
                           {  DEADLOCK=true;  }
                       }
                       if(DEADLOCK==true)
                       {  deadlock_image.setVisible(true); comment(3); }


                       //Check for loop
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim==false)
                           {  loop=true; break; }
                           else
                           {  loop=false;  }
                       }


                       }while(loop==true && DEADLOCK==false);

                       if(DEADLOCK==false)
                       {  stable_image.setVisible(true); comment(4); }


                    }break;

            case 1: {  System.out.println("Rule One");   comment(6);//4 philosopher sitting at at time

                       noOfPh=4;

                       //hide philosopher5
                       ph5_thinking.setVisible(false);
                       sad_ph5.setVisible(true);

                       thAllChp=new Thread[noOfPh];
                       thExPh=new Thread[noOfPh];


                       //set status to waiting
                       for(int i=0;i<noOfPh;i++)
                       {  p[i].upgradePhStatus();        }

                       do
                       {

                      //Threads for allocating chopsticks
                          //Allocate first chopstick
                          for(int j=0;j<noOfPh;j++)
                          {
                             if(p[j].victim!=true )     // check for startvation
                             {
                                switch(p[j].getPhLabel())
                                {
                                    case 1: thAllChp[j]=new Threads(p[j],c2,c1,'A',ruleChoice); break;
                                    case 2: thAllChp[j]=new Threads(p[j],c3,c2,'A',ruleChoice); break;
                                    case 3: thAllChp[j]=new Threads(p[j],c4,c3,'A',ruleChoice); break;
                                    case 4: thAllChp[j]=new Threads(p[j],c5,c4,'A',ruleChoice); break;
                                    //case 5: thAllChp[j]=new Threads(p[j],c1,c5,'A',ruleChoice); break;
                                }
                                try{
                                      thAllChp[j].sleep(1000);
                                      thAllChp[j].join();
                                }catch(InterruptedException e)
                                { System.out.println("Error in Sleep()"); }
                              }

                          }

                       //Wait for threads to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thAllChp[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }


                       //Allocate Second chopstick
                          for(int j=0;j<noOfPh;j++)
                          {
                             if(p[j].victim!=true)     // check for startvation
                             {
                                switch(p[j].getPhLabel())
                                {
                                    case 1: thAllChp[j]=new Threads(p[j],c2,c1,'A',ruleChoice); break;
                                    case 2: thAllChp[j]=new Threads(p[j],c3,c2,'A',ruleChoice); break;
                                    case 3: thAllChp[j]=new Threads(p[j],c4,c3,'A',ruleChoice); break;
                                    case 4: thAllChp[j]=new Threads(p[j],c5,c4,'A',ruleChoice); break;
                                    //case 5: thAllChp[j]=new Threads(p[j],c1,c5,'A',ruleChoice); break;
                                }
                                try{
                                      thAllChp[j].sleep(1000);
                                      thAllChp[j].join();
                                }catch(InterruptedException e)
                                { System.out.println("Error in Sleep()"); }
                              }

                          }

                       //Wait for threads to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thAllChp[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }




                       //Threads for executing philosophers
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim!=true)         // check for startvation
                           {  thExPh[j]=new Threads(p[j], null, null, 'E',ruleChoice);  }
                       }

                       //Wait for thread to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thExPh[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }


                       //Check for deadlock
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim==true)
                           {  DEADLOCK=false; break; }
                           else
                           {  DEADLOCK=true;  }
                       }
                       if(DEADLOCK==true)
                       {   deadlock_image.setVisible(true);  comment(3);}

                       //Check for loop
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim==false)
                           {  loop=true; break; }
                           else
                           {  loop=false;  }
                       }


                       }while(loop==true && DEADLOCK==false);

                       if(DEADLOCK==false)
                       {  stable_image.setVisible(true);  comment(4); }

                    }break;


            case 2: {  System.out.println("Rule Two");    comment(6);/// eat if both chopstick are available

                       noOfPh=5;                      // Total Philosophers

                       thAllChp=new Thread[noOfPh];
                       thExPh=new Thread[noOfPh];


                       //set status to waiting
                       for(int i=0;i<noOfPh;i++)
                       {  p[i].upgradePhStatus();        }


                       do
                       {

                      //Threads for allocating chopsticks
                          //Allocate both chopstick
                          for(int j=0;j<noOfPh;j++)
                          {
                             if(p[j].victim!=true)     // check for startvation
                             {
                                switch(p[j].getPhLabel())
                                {
                                    case 1: thAllChp[j]=new Threads(p[j],c2,c1,'A',ruleChoice); break;
                                    case 2: thAllChp[j]=new Threads(p[j],c3,c2,'A',ruleChoice); break;
                                    case 3: thAllChp[j]=new Threads(p[j],c4,c3,'A',ruleChoice); break;
                                    case 4: thAllChp[j]=new Threads(p[j],c5,c4,'A',ruleChoice); break;
                                    case 5: thAllChp[j]=new Threads(p[j],c1,c5,'A',ruleChoice); break;

                                }
                                try{
                                      thAllChp[j].sleep(1000);
                                      thAllChp[j].join();
                                }catch(InterruptedException e)
                                { System.out.println("Error in Sleep()"); }
                              }
                             try
                             {
                               thAllChp[j].join();
                             }catch(InterruptedException e)
                              { System.out.println("Main Interrupted!");  }

                          }

                       //Wait for threads to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thAllChp[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }


                       //Threads for executing philosophers
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim!=true)         // check for startvation
                           {  thExPh[j]=new Threads(p[j], null, null, 'E',ruleChoice);  }
                       }

                       //Wait for thread to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thExPh[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }


                       //Check for deadlock
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim==true)
                           {  DEADLOCK=false; break; }
                           else
                           {  DEADLOCK=true;  }
                       }
                       if(DEADLOCK==true)
                       {   deadlock_image.setVisible(true);   comment(3); }


                       //Check for loop
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim==false)
                           {  loop=true; break; }
                           else
                           {  loop=false;  }
                       }


                       }while(loop==true && DEADLOCK==false);

                       if(DEADLOCK==false)
                       {  stable_image.setVisible(true); comment(4); }

                    }break;

            case 3: {  System.out.println("Rule Three");   comment(6);// Asymmetric Soln

                       noOfPh=5;                      // Total Philosophers

                       thAllChp=new Thread[noOfPh];
                       thExPh=new Thread[noOfPh];

                       //set status to waiting
                       for(int i=0;i<noOfPh;i++)
                       {  p[i].upgradePhStatus();        }


                       do
                       {

                      //Threads for allocating chopsticks

                          //Allocate first chopstick
                          for(int j=0;j<noOfPh;j++)
                          {
                             if(p[j].victim!=true)     // check for startvation
                             {
                                switch(p[j].getPhLabel())
                                {
                                    case 1: thAllChp[j]=new Threads(p[j],c2,c1,'A',ruleChoice); break;
                                    case 2: thAllChp[j]=new Threads(p[j],c3,c2,'A',ruleChoice); break;
                                    case 3: thAllChp[j]=new Threads(p[j],c4,c3,'A',ruleChoice); break;
                                    case 4: thAllChp[j]=new Threads(p[j],c5,c4,'A',ruleChoice); break;
                                    case 5: thAllChp[j]=new Threads(p[j],c1,c5,'A',ruleChoice); break;

                                }
                                try{
                                      thAllChp[j].sleep(1000);
                                      thAllChp[j].join();
                                }catch(InterruptedException e)
                                { System.out.println("Error in Sleep()"); }
                              }

                          }

                       //Wait for threads to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thAllChp[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }


                       //Allocate Second chopstick
                          for(int j=0;j<noOfPh;j++)
                          {
                             if(p[j].victim!=true)     // check for startvation
                             {
                                switch(p[j].getPhLabel())
                                {
                                    case 1: thAllChp[j]=new Threads(p[j],c2,c1,'A',ruleChoice); break;
                                    case 2: thAllChp[j]=new Threads(p[j],c3,c2,'A',ruleChoice); break;
                                    case 3: thAllChp[j]=new Threads(p[j],c4,c3,'A',ruleChoice); break;
                                    case 4: thAllChp[j]=new Threads(p[j],c5,c4,'A',ruleChoice); break;
                                    case 5: thAllChp[j]=new Threads(p[j],c1,c5,'A',ruleChoice); break;

                                }
                                try{
                                      thAllChp[j].sleep(1000);
                                      thAllChp[j].join();
                                }catch(InterruptedException e)
                                { System.out.println("Error in Sleep()"); }
                                
                              }

                          }

                       //Wait for threads to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thAllChp[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }




                       //Threads for executing philosophers
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim!=true)         // check for startvation
                           {  thExPh[j]=new Threads(p[j], null, null, 'E',ruleChoice);  }
                       }

                       //Wait for thread to finish
                       try
                       {
                            for(int j=0;j<noOfPh;j++)
                            {  thExPh[j].join();    }
                       }catch(InterruptedException e)
                       { System.out.println("Main Interrupted!");  }


                       //Check for deadlock
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim==true)
                           {  DEADLOCK=false; break; }
                           else
                           {  DEADLOCK=true;  }
                       }
                       if(DEADLOCK==true)
                       {   deadlock_image.setVisible(true);  comment(3); }

                       //Check for loop
                       for(int j=0;j<noOfPh;j++)
                       {
                           if(p[j].victim==false)
                           {  loop=true; break; }
                           else
                           {  loop=false;  }
                       }


                       }while(loop==true && DEADLOCK==false);

                       if(DEADLOCK==false)
                       {  stable_image.setVisible(true); comment(4); }

                    }break;

        }//end of switch

        //start_deactive.setVisible(false);
        if(ruleChoice!=-1)
        {    start_deactive.setVisible(false);
             reset_button.setVisible(true);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_rule = new javax.swing.ButtonGroup();
        cmt_welcom = new javax.swing.JLabel();
        cmt_select = new javax.swing.JLabel();
        cmt_deadlock = new javax.swing.JLabel();
        cmt_stable = new javax.swing.JLabel();
        cmt_optSel = new javax.swing.JLabel();
        cmt_optRun = new javax.swing.JLabel();
        cmt_moreInfo = new javax.swing.JLabel();
        ph1_eat = new javax.swing.JLabel();
        ph1_thinking = new javax.swing.JLabel();
        ph1_wt_0 = new javax.swing.JLabel();
        ph1_wt_L = new javax.swing.JLabel();
        ph1_wt_R = new javax.swing.JLabel();
        ph1_timmer = new javax.swing.JLabel();
        ph2_eat = new javax.swing.JLabel();
        ph2_thinking = new javax.swing.JLabel();
        ph2_wt_0 = new javax.swing.JLabel();
        ph2_wt_L = new javax.swing.JLabel();
        ph2_wt_R = new javax.swing.JLabel();
        ph2_timmer = new javax.swing.JLabel();
        ph3_eat = new javax.swing.JLabel();
        ph3_thinking = new javax.swing.JLabel();
        ph3_wt_0 = new javax.swing.JLabel();
        ph3_wt_L = new javax.swing.JLabel();
        ph3_wt_R = new javax.swing.JLabel();
        ph3_timmer = new javax.swing.JLabel();
        ph4_eat = new javax.swing.JLabel();
        ph4_thinking = new javax.swing.JLabel();
        ph4_wt_0 = new javax.swing.JLabel();
        ph4_wt_L = new javax.swing.JLabel();
        ph4_wt_R = new javax.swing.JLabel();
        ph4_timmer = new javax.swing.JLabel();
        ph5_eat = new javax.swing.JLabel();
        ph5_thinking = new javax.swing.JLabel();
        ph5_wt_0 = new javax.swing.JLabel();
        ph5_wt_L = new javax.swing.JLabel();
        ph5_wt_R = new javax.swing.JLabel();
        ph5_timmer = new javax.swing.JLabel();
        sad_ph5 = new javax.swing.JLabel();
        chp1_image = new javax.swing.JLabel();
        chp2_image = new javax.swing.JLabel();
        chp3_image = new javax.swing.JLabel();
        chp4_image = new javax.swing.JLabel();
        chp5_image = new javax.swing.JLabel();
        deadlock_image = new javax.swing.JLabel();
        stable_image = new javax.swing.JLabel();
        rdBtn_0 = new javax.swing.JRadioButton();
        rdBtn_1 = new javax.swing.JRadioButton();
        rdBtn_2 = new javax.swing.JRadioButton();
        rdBtn_3 = new javax.swing.JRadioButton();
        prbStmt_button = new javax.swing.JButton();
        about_button = new javax.swing.JButton();
        start_button = new javax.swing.JButton();
        reset_button = new javax.swing.JButton();
        start_deactive = new javax.swing.JLabel();
        screen2_bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Dining Philosopher Problem | (c) 2011Gagandeep Singh");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(0, 0, 900, 700));
        setMinimumSize(new java.awt.Dimension(900, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        cmt_welcom.setFont(new java.awt.Font("Comic Sans MS", 0, 16));
        cmt_welcom.setText("Welcome to Dining Philosopher Problem......");
        getContentPane().add(cmt_welcom);
        cmt_welcom.setBounds(180, 597, 360, 30);

        cmt_select.setFont(new java.awt.Font("Comic Sans MS", 0, 15));
        cmt_select.setText("Please Select an option and click 'Start' Button!");
        getContentPane().add(cmt_select);
        cmt_select.setBounds(180, 617, 380, 30);

        cmt_deadlock.setFont(new java.awt.Font("Comic Sans MS", 0, 16));
        cmt_deadlock.setText("Deadlock occured !");
        getContentPane().add(cmt_deadlock);
        cmt_deadlock.setBounds(180, 593, 230, 40);

        cmt_stable.setFont(new java.awt.Font("Comic Sans MS", 0, 16));
        cmt_stable.setText("System is Stable !");
        getContentPane().add(cmt_stable);
        cmt_stable.setBounds(180, 595, 150, 40);

        cmt_optSel.setFont(new java.awt.Font("Comic Sans MS", 0, 16));
        cmt_optSel.setText("Option Selected!");
        getContentPane().add(cmt_optSel);
        cmt_optSel.setBounds(180, 593, 330, 50);

        cmt_optRun.setFont(new java.awt.Font("Comic Sans MS", 0, 16));
        cmt_optRun.setText("Running......");
        getContentPane().add(cmt_optRun);
        cmt_optRun.setBounds(180, 593, 310, 50);

        cmt_moreInfo.setFont(new java.awt.Font("Arial", 0, 11));
        cmt_moreInfo.setText("For More Information click 'Problem Statement'.");
        getContentPane().add(cmt_moreInfo);
        cmt_moreInfo.setBounds(237, 646, 230, 14);

        ph1_eat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph1_eating.gif"))); // NOI18N
        getContentPane().add(ph1_eat);
        ph1_eat.setBounds(272, 87, 180, 130);

        ph1_thinking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph1_thinking.gif"))); // NOI18N
        getContentPane().add(ph1_thinking);
        ph1_thinking.setBounds(260, 79, 241, 124);

        ph1_wt_0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph1_wt_0.gif"))); // NOI18N
        getContentPane().add(ph1_wt_0);
        ph1_wt_0.setBounds(260, 60, 250, 160);

        ph1_wt_L.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph1_wt_L.gif"))); // NOI18N
        getContentPane().add(ph1_wt_L);
        ph1_wt_L.setBounds(261, 76, 250, 130);

        ph1_wt_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph1_wt_R.gif"))); // NOI18N
        getContentPane().add(ph1_wt_R);
        ph1_wt_R.setBounds(261, 66, 240, 150);

        ph1_timmer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Timmer.gif"))); // NOI18N
        getContentPane().add(ph1_timmer);
        ph1_timmer.setBounds(272, 75, 140, 30);

        ph2_eat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph2_eating.gif"))); // NOI18N
        getContentPane().add(ph2_eat);
        ph2_eat.setBounds(447, 218, 150, 140);

        ph2_thinking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph2_thinking.gif"))); // NOI18N
        getContentPane().add(ph2_thinking);
        ph2_thinking.setBounds(439, 205, 250, 150);

        ph2_wt_0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph2_wt_0.gif"))); // NOI18N
        getContentPane().add(ph2_wt_0);
        ph2_wt_0.setBounds(440, 210, 250, 140);

        ph2_wt_L.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph2_wt_L.gif"))); // NOI18N
        getContentPane().add(ph2_wt_L);
        ph2_wt_L.setBounds(440, 205, 250, 150);

        ph2_wt_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph2_wt_R.gif"))); // NOI18N
        ph2_wt_R.setText("jLabel1");
        getContentPane().add(ph2_wt_R);
        ph2_wt_R.setBounds(440, 205, 250, 150);

        ph2_timmer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Timmer.gif"))); // NOI18N
        getContentPane().add(ph2_timmer);
        ph2_timmer.setBounds(530, 210, 140, 30);

        ph3_eat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph3_eating.gif"))); // NOI18N
        getContentPane().add(ph3_eat);
        ph3_eat.setBounds(368, 410, 180, 130);

        ph3_thinking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph3_thinking.gif"))); // NOI18N
        getContentPane().add(ph3_thinking);
        ph3_thinking.setBounds(360, 390, 270, 160);

        ph3_wt_0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph3_wt_0.gif"))); // NOI18N
        getContentPane().add(ph3_wt_0);
        ph3_wt_0.setBounds(360, 400, 270, 140);

        ph3_wt_L.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph3_wt_L.gif"))); // NOI18N
        getContentPane().add(ph3_wt_L);
        ph3_wt_L.setBounds(360, 400, 270, 140);

        ph3_wt_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph3_wt_R.gif"))); // NOI18N
        getContentPane().add(ph3_wt_R);
        ph3_wt_R.setBounds(360, 405, 270, 130);

        ph3_timmer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Timmer.gif"))); // NOI18N
        getContentPane().add(ph3_timmer);
        ph3_timmer.setBounds(490, 510, 140, 30);

        ph4_eat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph4_eating.gif"))); // NOI18N
        getContentPane().add(ph4_eat);
        ph4_eat.setBounds(169, 405, 140, 130);

        ph4_thinking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph4_thinking.gif"))); // NOI18N
        getContentPane().add(ph4_thinking);
        ph4_thinking.setBounds(39, 406, 260, 130);

        ph4_wt_0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph4_wt_0.gif"))); // NOI18N
        getContentPane().add(ph4_wt_0);
        ph4_wt_0.setBounds(39, 406, 260, 130);

        ph4_wt_L.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph4_wt_L.gif"))); // NOI18N
        getContentPane().add(ph4_wt_L);
        ph4_wt_L.setBounds(39, 397, 270, 150);

        ph4_wt_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph4_wt_R.gif"))); // NOI18N
        getContentPane().add(ph4_wt_R);
        ph4_wt_R.setBounds(39, 401, 270, 140);

        ph4_timmer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Timmer.gif"))); // NOI18N
        getContentPane().add(ph4_timmer);
        ph4_timmer.setBounds(160, 520, 140, 30);

        ph5_eat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph5_eating.gif"))); // NOI18N
        getContentPane().add(ph5_eat);
        ph5_eat.setBounds(104, 222, 150, 130);

        ph5_thinking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph5_thinking.gif"))); // NOI18N
        getContentPane().add(ph5_thinking);
        ph5_thinking.setBounds(16, 176, 240, 190);

        ph5_wt_0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph5_wt_0.gif"))); // NOI18N
        getContentPane().add(ph5_wt_0);
        ph5_wt_0.setBounds(16, 178, 220, 187);

        ph5_wt_L.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph5_wt_L.gif"))); // NOI18N
        getContentPane().add(ph5_wt_L);
        ph5_wt_L.setBounds(16, 176, 230, 190);

        ph5_wt_R.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/ph5_wt_R.gif"))); // NOI18N
        getContentPane().add(ph5_wt_R);
        ph5_wt_R.setBounds(16, 176, 230, 190);

        ph5_timmer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Timmer.gif"))); // NOI18N
        getContentPane().add(ph5_timmer);
        ph5_timmer.setBounds(20, 190, 140, 30);

        sad_ph5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Sad_ph5.gif"))); // NOI18N
        getContentPane().add(sad_ph5);
        sad_ph5.setBounds(10, 130, 130, 160);

        chp1_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/chopstick1.gif"))); // NOI18N
        getContentPane().add(chp1_image);
        chp1_image.setBounds(262, 229, 60, 70);

        chp2_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/chopstick2.gif"))); // NOI18N
        getContentPane().add(chp2_image);
        chp2_image.setBounds(369, 237, 43, 59);

        chp3_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/chopstick3.gif"))); // NOI18N
        getContentPane().add(chp3_image);
        chp3_image.setBounds(385, 343, 80, 40);

        chp4_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/chopstick4.gif"))); // NOI18N
        getContentPane().add(chp4_image);
        chp4_image.setBounds(332, 380, 20, 80);

        chp5_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/chopstick5.gif"))); // NOI18N
        getContentPane().add(chp5_image);
        chp5_image.setBounds(218, 342, 70, 40);

        deadlock_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Deadlock_2.gif"))); // NOI18N
        getContentPane().add(deadlock_image);
        deadlock_image.setBounds(697, 555, 190, 80);

        stable_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Stable_2.gif"))); // NOI18N
        getContentPane().add(stable_image);
        stable_image.setBounds(698, 606, 190, 70);

        buttonGroup_rule.add(rdBtn_0);
        rdBtn_0.setBorder(null);
        rdBtn_0.setContentAreaFilled(false);
        rdBtn_0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdBtn_0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_1.gif"))); // NOI18N
        rdBtn_0.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_2.gif"))); // NOI18N
        rdBtn_0.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_4.gif"))); // NOI18N
        rdBtn_0.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_3.gif"))); // NOI18N
        rdBtn_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtn_0ActionPerformed(evt);
            }
        });
        getContentPane().add(rdBtn_0);
        rdBtn_0.setBounds(700, 250, 20, 19);

        buttonGroup_rule.add(rdBtn_1);
        rdBtn_1.setContentAreaFilled(false);
        rdBtn_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdBtn_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_1.gif"))); // NOI18N
        rdBtn_1.setMaximumSize(new java.awt.Dimension(19, 19));
        rdBtn_1.setMinimumSize(new java.awt.Dimension(19, 19));
        rdBtn_1.setPreferredSize(new java.awt.Dimension(19, 19));
        rdBtn_1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_2.gif"))); // NOI18N
        rdBtn_1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_4.gif"))); // NOI18N
        rdBtn_1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_3.gif"))); // NOI18N
        rdBtn_1.setVerifyInputWhenFocusTarget(false);
        rdBtn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtn_1ActionPerformed(evt);
            }
        });
        getContentPane().add(rdBtn_1);
        rdBtn_1.setBounds(695, 340, 30, 20);

        buttonGroup_rule.add(rdBtn_2);
        rdBtn_2.setContentAreaFilled(false);
        rdBtn_2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdBtn_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_1.gif"))); // NOI18N
        rdBtn_2.setMaximumSize(new java.awt.Dimension(19, 19));
        rdBtn_2.setMinimumSize(new java.awt.Dimension(19, 19));
        rdBtn_2.setPreferredSize(new java.awt.Dimension(19, 19));
        rdBtn_2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_2.gif"))); // NOI18N
        rdBtn_2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_4.gif"))); // NOI18N
        rdBtn_2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_3.gif"))); // NOI18N
        rdBtn_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtn_2ActionPerformed(evt);
            }
        });
        getContentPane().add(rdBtn_2);
        rdBtn_2.setBounds(695, 388, 30, 19);

        buttonGroup_rule.add(rdBtn_3);
        rdBtn_3.setContentAreaFilled(false);
        rdBtn_3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdBtn_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_1.gif"))); // NOI18N
        rdBtn_3.setMaximumSize(new java.awt.Dimension(19, 19));
        rdBtn_3.setMinimumSize(new java.awt.Dimension(19, 19));
        rdBtn_3.setPreferredSize(new java.awt.Dimension(19, 19));
        rdBtn_3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_2.gif"))); // NOI18N
        rdBtn_3.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_4.gif"))); // NOI18N
        rdBtn_3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/Radio_button_3.gif"))); // NOI18N
        rdBtn_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtn_3ActionPerformed(evt);
            }
        });
        getContentPane().add(rdBtn_3);
        rdBtn_3.setBounds(695, 434, 40, 19);

        prbStmt_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/PrbSt_button_1.gif"))); // NOI18N
        prbStmt_button.setBorder(null);
        prbStmt_button.setBorderPainted(false);
        prbStmt_button.setContentAreaFilled(false);
        prbStmt_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prbStmt_button.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/PrbSt_button_3.gif"))); // NOI18N
        prbStmt_button.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/PrbSt_button_2.gif"))); // NOI18N
        prbStmt_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prbStmt_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(prbStmt_button);
        prbStmt_button.setBounds(730, 140, 130, 40);

        about_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/About_button_1.gif"))); // NOI18N
        about_button.setBorder(null);
        about_button.setBorderPainted(false);
        about_button.setContentAreaFilled(false);
        about_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        about_button.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/About_button_3.gif"))); // NOI18N
        about_button.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/About_button_2.gif"))); // NOI18N
        about_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                about_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(about_button);
        about_button.setBounds(730, 100, 130, 40);

        start_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/start_button_1.gif"))); // NOI18N
        start_button.setBorder(null);
        start_button.setBorderPainted(false);
        start_button.setContentAreaFilled(false);
        start_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        start_button.setFocusPainted(false);
        start_button.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/start_button_4.gif"))); // NOI18N
        start_button.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/start_button_2.gif"))); // NOI18N
        start_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(start_button);
        start_button.setBounds(540, 550, 150, 120);

        reset_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/reset_button_1.gif"))); // NOI18N
        reset_button.setBorderPainted(false);
        reset_button.setContentAreaFilled(false);
        reset_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reset_button.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/reset_button_3.gif"))); // NOI18N
        reset_button.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/reset_button_2.gif"))); // NOI18N
        reset_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(reset_button);
        reset_button.setBounds(550, 550, 130, 110);

        start_deactive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/start_button_4.gif"))); // NOI18N
        getContentPane().add(start_deactive);
        start_deactive.setBounds(546, 550, 150, 120);

        screen2_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinningphilosopher/screen2_bg.jpg"))); // NOI18N
        getContentPane().add(screen2_bg);
        screen2_bg.setBounds(0, 0, 900, 675);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void about_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about_buttonActionPerformed
        new AboutScreen().setVisible(true);
    }//GEN-LAST:event_about_buttonActionPerformed

    private void prbStmt_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prbStmt_buttonActionPerformed
        // TODO add your handling code here:
        new PrbStScreen().setVisible(true);
    }//GEN-LAST:event_prbStmt_buttonActionPerformed

    private void rdBtn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtn_1ActionPerformed
        ruleChoice=1;
        comment(5);
    }//GEN-LAST:event_rdBtn_1ActionPerformed

    //Start Button
    private void start_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_buttonActionPerformed

        if(ruleChoice!=-1)
        {
            exe=true;
            System.out.println("Value of 'exe' :"+exe);
            start_button.setVisible(false);
            start_deactive.setVisible(true);
        }

        
    }//GEN-LAST:event_start_buttonActionPerformed

    private void rdBtn_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtn_0ActionPerformed
        ruleChoice=0;
        comment(5);
    }//GEN-LAST:event_rdBtn_0ActionPerformed

    private void rdBtn_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtn_2ActionPerformed
        ruleChoice=2;
        comment(5);
    }//GEN-LAST:event_rdBtn_2ActionPerformed

    private void rdBtn_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtn_3ActionPerformed
        ruleChoice=3;
        comment(5);
    }//GEN-LAST:event_rdBtn_3ActionPerformed

    private void reset_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_buttonActionPerformed

        for(int i=0;i<noOfPh;i++)
        {  p[i].reset();   }

        c1.reset();
        c2.reset();
        c3.reset();
        c4.reset();
        c5.reset();

        deadlock_image.setVisible(false);
        stable_image.setVisible(false);
        start_button.setVisible(true);
        reset_button.setVisible(false);
        ph5_thinking.setVisible(true);
        sad_ph5.setVisible(false);

        start_button.setVisible(true);
        start_deactive.setVisible(false);

        comment(2);
    }//GEN-LAST:event_reset_buttonActionPerformed


    
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Screen1().setVisible(true);
                //new Screen2().setVisible(true);
            }
        });

        do
        {
            if(exe==true)
            {  System.out.println("Running algo....");
               EXECUTEALGO();
               exe=false;
            }
            
        }while(true);
        
    }

    private static void hideAllComments()
    {

        cmt_welcom.setVisible(false);
        cmt_select.setVisible(false);
        cmt_deadlock.setVisible(false);
        cmt_stable.setVisible(false);        
        cmt_optSel.setVisible(false);
        cmt_optRun.setVisible(false);
    }

    public static void comment(int c)
    {
        hideAllComments();

        switch(c)
        {
            case 1: cmt_welcom.setVisible(true); break;
            case 2: cmt_select.setVisible(true); break;
            case 3: cmt_deadlock.setVisible(true); break;
            case 4: cmt_stable.setVisible(true); break;
            case 5: cmt_optSel.setVisible(true); break;
            case 6: cmt_optRun.setVisible(true); break;
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton about_button;
    private javax.swing.ButtonGroup buttonGroup_rule;
    public javax.swing.JLabel chp1_image;
    private javax.swing.JLabel chp2_image;
    private javax.swing.JLabel chp3_image;
    private javax.swing.JLabel chp4_image;
    private javax.swing.JLabel chp5_image;
    private static javax.swing.JLabel cmt_deadlock;
    private static javax.swing.JLabel cmt_moreInfo;
    private static javax.swing.JLabel cmt_optRun;
    private static javax.swing.JLabel cmt_optSel;
    private static javax.swing.JLabel cmt_select;
    private static javax.swing.JLabel cmt_stable;
    private static javax.swing.JLabel cmt_welcom;
    private static javax.swing.JLabel deadlock_image;
    private javax.swing.JLabel ph1_eat;
    private javax.swing.JLabel ph1_thinking;
    private javax.swing.JLabel ph1_timmer;
    private javax.swing.JLabel ph1_wt_0;
    private javax.swing.JLabel ph1_wt_L;
    private javax.swing.JLabel ph1_wt_R;
    private javax.swing.JLabel ph2_eat;
    private javax.swing.JLabel ph2_thinking;
    private javax.swing.JLabel ph2_timmer;
    private javax.swing.JLabel ph2_wt_0;
    private javax.swing.JLabel ph2_wt_L;
    private javax.swing.JLabel ph2_wt_R;
    private javax.swing.JLabel ph3_eat;
    private javax.swing.JLabel ph3_thinking;
    private javax.swing.JLabel ph3_timmer;
    private javax.swing.JLabel ph3_wt_0;
    private javax.swing.JLabel ph3_wt_L;
    private javax.swing.JLabel ph3_wt_R;
    private javax.swing.JLabel ph4_eat;
    private javax.swing.JLabel ph4_thinking;
    private javax.swing.JLabel ph4_timmer;
    private javax.swing.JLabel ph4_wt_0;
    private javax.swing.JLabel ph4_wt_L;
    private javax.swing.JLabel ph4_wt_R;
    private javax.swing.JLabel ph5_eat;
    private static javax.swing.JLabel ph5_thinking;
    private javax.swing.JLabel ph5_timmer;
    private javax.swing.JLabel ph5_wt_0;
    private javax.swing.JLabel ph5_wt_L;
    private javax.swing.JLabel ph5_wt_R;
    private javax.swing.JButton prbStmt_button;
    private javax.swing.JRadioButton rdBtn_0;
    private javax.swing.JRadioButton rdBtn_1;
    private javax.swing.JRadioButton rdBtn_2;
    private javax.swing.JRadioButton rdBtn_3;
    private static javax.swing.JButton reset_button;
    private static javax.swing.JLabel sad_ph5;
    private javax.swing.JLabel screen2_bg;
    private static javax.swing.JLabel stable_image;
    private static javax.swing.JButton start_button;
    private static javax.swing.JLabel start_deactive;
    // End of variables declaration//GEN-END:variables

    
}
