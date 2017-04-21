package guesswho;

class Trial{
  public int trialNum;
  private int points;
  private int guesses;
  private final int NUMBER_OF_STARTING_GUESSES = 6;

  public Trial(){
    trialNum = 0;
    points = 0;
    guesses = NUMBER_OF_STARTING_GUESSES;
  }

  public void newTrial(){
    trialNum++;
  }

  public int getTrialPoints() {
    return guesses;
  }

  public int getTrialNum() {
    return trialNum;
  }

  public int getTotalPoints() {
    return points;
  }

  public void resetTrialPoints() {
    guesses = NUMBER_OF_STARTING_GUESSES;
  }

  public void deductTrialPoints(int points) {
  	if ((guesses - points) >= 0) {
    	guesses -= points;
    } else {
    	guesses = 0;
    }
  }

  public void increaseTotalPoints(double pts) {
    points += pts;
  }
}
