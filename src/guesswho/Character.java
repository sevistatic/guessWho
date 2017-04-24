package guesswho;

import javax.swing.*;
import randomportrait.*;

/**
 *
 * @author Spencer
 */
class Character {
  private String[] featureDescriptions;

    RandomPortrait randomCharacter;

    Character() {
      randomCharacter = new RandomPortrait();
    }

    public String[] getFeaturesDescriptions() {
        return randomCharacter.describeFeatures();
    }

    public String getName() {
        return randomCharacter.name;
    }

    public CompoundIcon getPortrait(){
    	return randomCharacter.portrait;
    }

    //TODO implement functions for each feature like
    //public boolean has_hat()
    //and
    //public String getHatStyle()
    //this will let me clean up the askButton considerably
}
