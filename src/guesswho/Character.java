package guesswho;

import javax.swing.*;
import names.*;
import mersennetwister.*;
import randomportrait.*;

/**
 *
 * @author Spencer
 */
class Character {
  private String name;
  private CompoundIcon portrait;
  private String[] featureDescriptions;

    RandomPortrait portraitRandomizer;
    ImageIcon[] portraitParts;

    Character() {
    portraitRandomizer = new RandomPortrait();

        if (portraitRandomizer.features[RandomPortrait.SEX] == RandomPortrait.MALE){
        	name = Names.randomMaleName();
        } else {
        	name = Names.randomFemaleName();
        }
        portraitParts = portraitRandomizer.getImages();

        portrait = new CompoundIcon(CompoundIcon.Axis.Z_AXIS, portraitParts);
    }

    public String[] getFeaturesDescriptions() {
        return portraitRandomizer.describeFeatures();
    }

    public String getName() {
        return name;
    }

    /**
     * an ordered set of semi-transparent images.
     * when displayed in this order will form a coherent portrait.
     * @return an ordered set of semi-transparent images.
     */
    public CompoundIcon getPortrait(){
    	return portrait;
    }

    /**
     * creates a new set of features from scratch based upon commonly found features in the real world.
     * For instance, girls are rarely bald, and rarely have beards or mustaches.
     * @return an ordered list of the features a character has.
     */

}
//TODO fix scope of this class so that it calls out to the portrait generator
//for any specific information about the features of a portrait
