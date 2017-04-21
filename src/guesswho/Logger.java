package guesswho;
import java.io.*;
import java.awt.event.*;

class Logger{
  private static PrintWriter outFile;
  private static FileWriter fw;
  static final int START_TIME = 99;
  static int systemStartTime;
  static double startTime;

  private static String LOG_FOLDER = "logs";

  public Logger(){
    try{
      fw = new FileWriter(LOG_FOLDER + "/log" + GuessWho.trial.getTrialNum() + ".txt");
      outFile = new PrintWriter(fw);
      init();
    }
    catch(Exception e){
      System.err.println(e.getMessage());
    }
  }

  private void init(){
    //set start time for logging
    systemStartTime = (int)(System.currentTimeMillis() / 1000);

    log("Begin Logging");
    startTime = currentTime();
    logf("Participant Start Time: %.3f", startTime);
    log();
    logf("Trial Start Time: %.3f", startTime);
    log();
    log("Trial Number: " + GuessWho.trial.getTrialNum());
    log("----------------------------------------");
    flush();
    flush();
  }

  public void newTrial(){
    outFile.close();
    try{
    	fw = new FileWriter(LOG_FOLDER + "/log" + GuessWho.trial.trialNum + ".txt");
    } catch (Exception e){
    	System.out.println(e.getMessage());
    }

    log("Begin Logging\n");
    logf("Participant Start Time: %.3f", startTime);
    log();
    logf("Trial Start Time: %.3f", currentTime());
    log();
    log("Trial Number: " + GuessWho.trial.trialNum);
    flush();
  }

  public void logMouseClick(MouseEvent e){
    log("Event: Mouse Clicked");
    logf("Time: %.3f", currentTime());
    log();
    log("Cursor Location: (" + e.getXOnScreen() + ", " + e.getYOnScreen() + ")");
    log("Object Clicked: " + e.getSource().toString());
    log("Button Used: " + e.getButton());
    log("Current Trial: " + GuessWho.trial.trialNum);
    log("Current Trial Score: " + GuessWho.trial.getTrialPoints());
    log("Current Total Score: " + GuessWho.trial.getTotalPoints());
  }

  public static double currentTime() {
    return (System.currentTimeMillis() / 1000.0) - systemStartTime;
  }

  public void log(String s){
    outFile.println(s);
  }
  public void log(){
    outFile.println();
  }
  public void logf(String s, Float f){
    outFile.printf(s, f);
  }
  public void logf(String s, Double d){
    outFile.printf(s, d);
  }
  public void flush(){
    outFile.flush();
  }
}
