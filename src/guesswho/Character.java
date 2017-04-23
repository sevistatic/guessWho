package guesswho;

import javax.swing.ImageIcon;
import names.Names;

/**
 *
 * @author Spencer
 */
class Character {
    private String name;
    private int[] features;
    RandomPortrait portraitRandomizer;
    ImageIcon[] portraitParts;

    private final int NUM_OF_FEATURES = 14;

    private final int SKIN_COLOR = 0;
    private final int EYE_COLOR = 1;
    private final int SEX = 2;
      private int MALE = 1;
    private final int MOUTH_SHAPE = 3;
    private final int LIP_SIZE = 4;
    private final int HAIR_COLOR = 5;
    private final int BEARD = 6;
      private final int NO_BEARD = 2;
    private final int MUSTACHE = 7;
      private final int NO_MUSTACHE = 2;
    private final int NOSE_TYPE = 8;
    private final int SHIRT = 9;
    private final int HAT = 10;
      private final int HAS_HAT = 1;
    private final int HAT_STYLE = 11;
      private int NO_HAT_STYLE = 4;
    private final int GLASSES = 12;
      private final int HAS_GLASSES = 1;
    private final int GLASSES_STYLE = 13;
      private final int NO_GLASSES_STYLE = 5;

    Character() {
        features = randomizeFeatures();
        if (features[SEX] == MALE){
        	name = Names.randomMaleName();
        } else {
        	name = Names.randomFemaleName();
        }
        portraitRandomizer = new RandomPortrait(features);
        portraitParts = portraitRandomizer.getImages();
    }

    public String[] getFeaturesDescriptions() {
        return portraitRandomizer.describeFeatures(features);
    }

    public String getName() {
        return name;
    }

    /**
     * an ordered set of semi-transparent images.
     * when displayed in this order will form a coherent portrait.
     * @return an ordered set of semi-transparent images.
     */
    public ImageIcon[] getPortrait(){
    	return portraitParts;
    }

    /**
     * creates a new set of features from scratch based upon commonly found features in the real world.
     * For instance, girls are rarely bald, and rarely have beards or mustaches.
     * @return an ordered list of the features a character has.
     */
    private int[] randomizeFeatures(){
    	int[] features = new int[NUM_OF_FEATURES];
        features[SKIN_COLOR]     =   randomNumInRange(1,2);
        features[EYE_COLOR]      =   randomNumInRange(1,5);
        features[SEX]            =   randomNumInRange(1,2);
        features[MOUTH_SHAPE]    =   randomNumInRange(1,2);
        features[LIP_SIZE]       =   randomNumInRange(1,2);
        if (features[SEX] == MALE){
        	features[HAIR_COLOR]   =   randomNumInRange(1,6);//includes bald
        	features[BEARD]        =   randomNumInRange(1,2);
        } else {
            features[HAIR_COLOR] =   randomNumInRange(1,5);//does not include bald
        	features[BEARD] = NO_BEARD;
        	features[MUSTACHE] = NO_MUSTACHE;
        }
        features[NOSE_TYPE]       =  randomNumInRange(1,3);
        features[SHIRT]           =  randomNumInRange(1,/*10*/6);//extra shirt types
        features[HAT]             =  randomNumInRange(1,2);
        if (features[HAT] == HAS_HAT)
        	features[HAT_STYLE]     =  randomNumInRange(1,3);
        else
        	features[HAT_STYLE] = NO_HAT_STYLE;
        features[GLASSES]         =  randomNumInRange(1,2);
        if (features[GLASSES] == HAS_GLASSES)
        	features[GLASSES_STYLE] =  randomNumInRange(1,4);
        else
        	features[GLASSES_STYLE] = NO_GLASSES_STYLE;
      return features;
    }

    public int randomNumInRange(int rangeBottom, int rangeTop){
      return Background.randomizer.nextInt(rangeTop) + rangeBottom;
    }

}
