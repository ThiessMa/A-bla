import ea.*;
public class SSK 
extends Game 
implements Ticker
{
     private Rechteck boden, Back, box1;
     private Figur fidle, fcrouch, fcrouched, frun, fjump, brick;
     private ActionFigur yoruba;
     private int a=0, b=0,c=0, score=0;
     private Figur Play, Highscore, Optionen, Exit, coin; 
     private Knoten menu, spiel;
    public static void main(String[] args) 
    {
    new SSK();
    }
    public SSK()
    {
        super(1000, 600, "Yoruba in the Jungle");
        menu();
        
    }
    public void tasteReagieren(int x)
    { 
     switch(x)
     {
       case Taste.OBEN: 
           if(a==0)//Play noch nicht aufgerufen
             {
                b=b-1;
    
                if(b==-1)//wenn man von Play nach oben geht kommt man bei Exit raus
                {
                 b=3;
                }
                
                if(b==0)//Play markieren
                {
                 Play.animationsBildSetzen(1);
                 Highscore.animationsBildSetzen(0);
                 Optionen.animationsBildSetzen(0);
                 Exit.animationsBildSetzen(0);
                }
                
                if(b==1)//Highscore markieren
                {
                 Play.animationsBildSetzen(0);
                 Highscore.animationsBildSetzen(1);
                 Optionen.animationsBildSetzen(0);
                 Exit.animationsBildSetzen(0);
                }
                
                if(b==2)//Optionen markieren
                {
                 Play.animationsBildSetzen(0);
                 Highscore.animationsBildSetzen(0);
                 Optionen.animationsBildSetzen(1);
                 Exit.animationsBildSetzen(0);
                }
                
                if(b==3)//Exit markieren
                {
                 Play.animationsBildSetzen(0);
                 Highscore.animationsBildSetzen(0);
                 Optionen.animationsBildSetzen(0);
                 Exit.animationsBildSetzen(1);
                }
   
             }
           else if(a==1)//Play bereits aufgerufen
             {  
            if(yoruba.aktuellesVerhalten()=="crouched") //aus dem geduckten Zustand aufstehen
             {
              yoruba.zustandSetzen("idle");  
             }
             
             else //springen wenn er nicht geduckt ist
             {
              yoruba.sprung(5);  
             }
            }
         break;
            
       case Taste.UNTEN: 
           if(a==0)//Play noch nicht aufgerufen
            {
                b=b+1;
                
                if(b==4)//wenn man von Exit runter geht kommt man wieder bei Play raus
                {
                 b=0;
                }
                
                if(b==0)//Play markieren
                {
                 Play.animationsBildSetzen(1);
                 Highscore.animationsBildSetzen(0);
                 Optionen.animationsBildSetzen(0);
                 Exit.animationsBildSetzen(0);
                }
                
                if(b==1)//Highscore markieren
                {
                 Play.animationsBildSetzen(0);
                 Highscore.animationsBildSetzen(1);
                 Optionen.animationsBildSetzen(0);
                 Exit.animationsBildSetzen(0);
                }
                
                if(b==2)//Optionen markieren
                {
                 Play.animationsBildSetzen(0);
                 Highscore.animationsBildSetzen(0);
                 Optionen.animationsBildSetzen(1);
                 Exit.animationsBildSetzen(0);
                }
                
                if(b==3)//Exit markieren
                {
                 Play.animationsBildSetzen(0);
                 Highscore.animationsBildSetzen(0);
                 Optionen.animationsBildSetzen(0);
                 Exit.animationsBildSetzen(1);
                }
                
            }
            
           else if(a==1)//Play bereits aufgerufen
            {
            if(yoruba.aktuellesVerhalten()=="idle")//steht Yoruba soll er sich ducken
            {
                 yoruba.zustandSetzen("crouched");
                 yoruba.aktionSetzen("crouch");
             
            }
                
            }
            
           break;
         case Taste.ENTER:
            if(b==0&&a==0) //Play ausgewählt 
            {
             //Hauptmenü löschen
             wurzel.entfernen(menu);
             a=1;
             //Spiel starten
             starten();        
            }
            
            if(b==1) //Highscore ausgewählt
            {
             //highscore();
            }
            
            if(b==2) //Optionen ausgewählt
            {
             //optionen();
            }
            
            if(b==3) //Exit ausgewählt
            {   
             beenden();
            }  
            
     }
         
    }
    
    public void menu() //Das Hauptmenü
    {
        //Knoten erzeugen unter dem alle Objekte des Menüs gespeichert werden
        menu = new Knoten();
        wurzel.add(menu);
        
        //Hintergrund
        Back = new Rechteck(-500,-100,2000,2000);
        Back.farbeSetzen("rot");
        menu.add(Back);
        
        //Die 4 Buttons
        Play = new Figur(100, 100,"Graphics/Play.eaf");
        menu.add(Play);
        Play.animiertSetzen(false);
        Play.faktorSetzen(4);
        
        Highscore = new Figur(100, 200,"Graphics/Score.eaf");
        menu.add(Highscore);
        Highscore.faktorSetzen(4);
        Highscore.animiertSetzen(false);
        
        Optionen = new Figur(100, 300,"Graphics/Options.eaf");
        menu.add(Optionen);
        Optionen.faktorSetzen(4);
        Optionen.animiertSetzen(false);
        
        Exit = new Figur(100, 400,"Graphics/Exit.eaf");
        menu.add(Exit);
        Exit.faktorSetzen(4);
        Exit.animiertSetzen(false);
        
        cam.fokusSetzen(Highscore);
        
        //ersten Button markieren
        Play.animationsBildSetzen(1);
        Highscore.animationsBildSetzen(0);
        Optionen.animationsBildSetzen(0);
        Exit.animationsBildSetzen(0);
    }
    
    public void starten() //Play öffnet das Level
    {
        wurzel.entfernen(menu);
        spiel = new Knoten();
        wurzel.add(spiel);
        
        //Boden erzeugen
        boden = new Rechteck(-400,100,1000,10);
        boden.farbeSetzen("weiß");
        spiel.add(boden);
        boden.passivMachen();
        
        //Wand erzeugen
        box1 = new Rechteck(200,-100,100,600);
        box1.farbeSetzen("blau");
        
        box1.passivMachen();
        box1.erzeugeCollider();
        spiel.add(box1);

        //Dekoblock erzeugen
        brick = new Figur(100, 100,"Graphics/brick.eaf");
        spiel.add(brick);
        brick.passivMachen();
        
        //Yorubas verschiedene Animationen
        fidle = new Figur("Graphics/YorubaIdle.eaf");
        fidle.aktivMachen();
        fidle.animationsGeschwindigkeitSetzen(1000);
        fcrouch = new Figur("Graphics/YorubaCrouch.eaf");
        fcrouch.aktivMachen();
        fcrouched = new Figur("Graphics/YorubaCrouched.eaf");
        fcrouched.aktivMachen();
        frun = new Figur("Graphics/YorubaRun.eaf");
        frun.aktivMachen();
        fjump = new Figur("Graphics/YorubaJump.eaf");
        fjump.aktivMachen();
        
        //Yoruba an sich
        yoruba = new ActionFigur(fidle, "idle");
        
        
        //Yorubas Aktionen
        yoruba.neueAktion(fcrouch, "crouch");
        yoruba.neueAktion(frun, "run");
        
        //Yorubas Zustände
        yoruba.neuerZustand(fcrouched, "crouched");
        yoruba.neuerZustand(frun, "running");
        yoruba.neuerZustand(fjump, "jumping");
        
        //Kamera  fokus setzen
        cam.fokusSetzen(yoruba);
        
        spiel.add(yoruba);
        yoruba.aktivMachen();
        yoruba.schwerkraftAktivSetzen(true);  
        yoruba.erzeugeCollider();
        
        
        
        coin = new Figur("Graphics/Coin.eaf");
        
        //starten des Tickers
        if(c==0)
        {
          manager.anmelden(this, 10);
        }
    }
    
    public void tick()
    { 
     if(a==1)
     {
      if(yoruba.aktuellesVerhalten()!="crouched") //Yoruba darf nur laufen wenn er sich nicht duckt
       {
        if(tasteGedrueckt(Taste.RECHTS)) //nach rechts laufen
         {
           yoruba.spiegelXSetzen(true);
           yoruba.verschieben(3, 0);
         }
         
        if(tasteGedrueckt(Taste.LINKS)) //nach links laufen
         {
           yoruba.spiegelXSetzen(false);
           yoruba.verschieben(-3, 0);
         }
         
        if(tasteGedrueckt(Taste.LINKS) || tasteGedrueckt(Taste.RECHTS)) //Laufanimation
         {
           yoruba.zustandSetzen("running");    
         }
         
        else if(tasteGedrueckt(Taste.UNTEN)) //Ducken
         {
          yoruba.zustandSetzen("crouched");
         }
        
        else if(yoruba.steht()==true)
        {
           yoruba.zustandSetzen("idle");
        }
        else //Rumstehen
         {
           yoruba.zustandSetzen("jumping");  
         }
        
      }
      if(yoruba.positionY()>300)
      {
      Tod();  
      }
     }
     c=1;
    }
    
    public void Tod()
    {
      wurzel.entfernen(spiel);
      a=0;
      wurzel.add(menu);
      cam.fokusSetzen(Highscore);
    }
}