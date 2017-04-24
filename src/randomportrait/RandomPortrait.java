/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package randomportrait;
import javax.swing.ImageIcon;
import java.awt.*;
import mersennetwister.*;

/**
 *
 * @author Spencer
 */
public class RandomPortrait {
	String foldername = "images/random/";
    private ImageIcon[] iconArray;
    private boolean done;
		private final int SCALED_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 7/24;
		private final int SCALED_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 10;


		private MersenneTwister randomizer;
    /**
     * constructor to make a portrait with random features
     */
    // public RandomPortrait(){
    //     done = false;
    //     iconArray = new ImageIcon[10];
		//
    //     iconArray = randomlyGeneratePictures();
    // }

    /**
     * constructor to make a portrait with specific features
     * @param feat a legal list of feature ints
     */
    public RandomPortrait(int[] feat){
			randomizer = new MersenneTwister();
    	done = false;
    	iconArray = new ImageIcon[10];
    	iconArray = forciblyGeneratePictures(feat);
			//SCALED_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 7/24;
			//SCALED_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 10;
    }

    //public accessor method for the array of ImageIcons
    public ImageIcon[] getImages(){
        return iconArray;
    }

    //gets a single layer of the portrait
    public ImageIcon getImageAt(int i){
        if (i < iconArray.length && i > 0){
        return iconArray[i];
        } else return null;
    }

    /**
     * Public method for creating readable String descriptions of features in a corresponding array of ints
     * @param f an int array containing legal feature numbers, beginning at 1, and usually ending in 2, 3, 5, or 10, depending on the feature
     * @return an array of descriptive strings about the corresponding features, arranged in the same order
     */
    public String[] describeFeatures(int[] f){
    	String[] features = new String[14];
    	features[0] = describeSkin(f[0]);
    	features[1] = describeEyes(f[1]);
    	features[2] = describeSex(f[2]);
    	features[3] = describeSmile(f[3]);
    	features[4] = describeLips(f[4]);
    	features[5] = describeHair(f[5]);
    	features[6] = describeBeard(f[6]);
    	features[7] = describeMustache(f[7]);
    	features[8] = describeNose(f[8]);
    	features[9] = describeShirt(f[9]);
    	features[10] = describeHat(f[10]);
    	//features[11] = describeHatStyle(f[11]);
    	features[11] = describeGlasses(f[12]);
    	//features[13] = describeGlassesStyle(f[13]);
    	return features;
    }

    //POSSIBLY UNNECESSARY
    //flag to tell when the portrait has been successfully generated
    public boolean isDone(){
        return done;
    }

    //----------------------------------------------------------------
/**
 * Creates an int[] of random integers corresponding to the parity values of each feature.
 * Runs forciblyGeneratePictures() based on this array.
 * @return an array of ImageIcons (in the proper order) of randomly assembled portrait features.
 */
    // private ImageIcon[] randomlyGeneratePictures() {
    // 	int[] randF;
    // 	randF = new int[14];
    //     randF[0] = Background.randomizer.nextInt(2) + 1;//1 to 2
    //     randF[1] = Background.randomizer.nextInt(5) + 1;
    //     randF[2] = Background.randomizer.nextInt(2) + 1;
    //     randF[3] = Background.randomizer.nextInt(2) + 1;
    //     randF[4] = Background.randomizer.nextInt(2) + 1;
    //     randF[5] = Background.randomizer.nextInt(6) + 1;
    //     if (randF[2] == 1){//boy
    //     randF[6] = Background.randomizer.nextInt(2) + 1;
    //     randF[7] = Background.randomizer.nextInt(2) + 1;
		//
		// 		System.out.println("Mustache number: " + randF[7]);
    //     } else {//girl, none for beard and mustache
    //     	randF[6] = 2;
    //     	randF[7] = 2;
    //     }
    //     randF[8] = Background.randomizer.nextInt(3) + 1;
    //     randF[9] = Background.randomizer.nextInt(/*10*/6) + 1;
    //     randF[10] = Background.randomizer.nextInt(2) + 1;
    //     if (randF[10] == 1)//hat
    //     	randF[11] = Background.randomizer.nextInt(3) + 1;
    //     else //no hat, none for hat style
    //     	randF[11] = 4;
    //     randF[12] = Background.randomizer.nextInt(2) + 1;
    //     if (randF[12] == 1)//glasses
    //     	randF[13] = Background.randomizer.nextInt(4) + 1;
    //     else //no glasses, none for glasses style
    //     	randF[13] = 5;
    //     return forciblyGeneratePictures(randF);
    // }



    /**
     * Creates an ordered array of ImageIcons for a particular set of features
     * @param f an array of integers of proper parity corresponding to desired features
     * @return an ordered array of ImagIcons corresponding to a single portrait.
     */
    private ImageIcon[] forciblyGeneratePictures(int[] f) {
        iconArray[0] = getSkinColor(f[0]);
        iconArray[1] = getEyeColor(f[1]);
        int sex = f[2];
        iconArray[2] = getSex(sex);
        iconArray[3] = getSmile(f[3], f[4]);
        int haircolor = f[5];
        iconArray[4] = getFacialHair(sex, f[6], f[7], haircolor);
        iconArray[5] = getNose(f[8]);
        iconArray[6] = getShirt(f[9]);
        iconArray[7] = getHair(sex, haircolor);
        iconArray[8] = getHat(f[10], f[11]);
        iconArray[9] = getEyewear(f[12], f[13]);
        done = true;
        return iconArray;
    }

    //------------------------------------------------------------------
    //String descriptions of each feature number
    //------------------------------------------------------------------

    private String describeSkin(int f){
    	switch (f){ //skin
    	case 1: return "light skin";//light
    	case 2: return "dark skin";//dark
    	default: return "skin type error";
    	}
    }

    private String describeEyes(int f){
    	switch (f){ //eyes
    	case 1: return "blue eyes";//blue
    	case 2: return "black eyes";//black
    	case 3: return "brown eyes";//brown
    	case 4: return "green eyes";//green
    	case 5: return "grey eyes";//grey
    	default: return "eye color error";
    	}
    }

    private String describeSex(int f){
    	switch (f){ //sex
    	case 1: return"boy";//boy
    	case 2: return "girl";//girl
    	default: return "sex error";
    	}
    }

    private String describeSmile(int f){
    	switch (f){ //smile
    	case 1: return "smiling";//smile
    	case 2: return "frowning";//frown
    	default: return "smile error";
    	}
    }

    private String describeLips(int f){
    	switch (f){ //lips
    	case 1: return"big lips";//big lips
    	case 2: return "thin lips";//thin lips
    	default: return "lips error";
    	}
    }

    private String describeHair(int f){
    	switch (f){ //hair
    	case 1: return "blonde hair";//blonde
    	case 2: return "black hair";//black
    	case 3: return "brown hair";//brown
    	case 4: return "red hair";//red
    	case 5: return "grey hair";//grey
    	case 6: return "bald";//bald
    	default: return "hair color error";
    	}
    }

    private String describeBeard(int f){
    	switch (f){ //beard?
    	case 1: return "beard";//beard
    	case 2: return "no beard";//no beard
    	default: return "beard error";
    	}
    }

    private String describeMustache(int f){
    	switch (f){ //mustache?
    	case 1: return "mustache";//mustache
    	case 2: return "no mustache";//no mustache
    	default: return "mustache error";
    	}
    }

    private String describeNose(int f){
    	switch (f){ //nose type
    	case 1: return "big nose";
    	case 2: return "short nose";
    	case 3: return "thin nose";
    	default: return "nose error";
    	}
    }

    private String describeShirt(int f){
    	switch (f){ //shirt type
    	case 1: return "blue shirt";
    	case 2: return "black shirt";
    	case 3: return "red shirt";
    	case 4: return "green shirt";
    	case 5: return "orange shirt";
    	case 6: return "yellow shirt";
    	case 7: return "purple shirt";
    	case 8: return "white shirt";
    	case 9: return "leopard shirt";
    	case 10: return "warning shirt";
    	default: return "shirt error";
    	}
    }

    private String describeHat(int f){
    	switch (f){ //hat?
    	case 1: return "hat";
    	case 2: return "no hat";
    	default: return "hat error";
    	}
    }

    /*
    private String describeHatStyle(int f){
    	switch (f){ //hat style
    	case 1: return "baseball cap";
    	case 2: return "cowboy hat";
    	case 3: return "beret";
    	default: return "hat - none";
    	}
    }
    */

    private String describeGlasses(int f){
    	switch (f){ //glasses?
    	case 1: return "glasses";
    	case 2: return "no glasses";
    	default: return "glasses error";
    	}
    }

    /*
    private String describeGlassesStyle(int f){
    	switch (f){ //glasses style
    	case 1: return "thin glasses";
    	case 2: return "thick glasses";
    	case 3: return "eyepatch";
    	case 4: return "monocle";
    	default: return "glasses - none";
    	}
    }
    */


    //--------------------------------------------------------
    //GETS THE IMAGE FILES FOR EACH RESPECTIVE FEATURE NUMBER IN THE ARRAY
    //--------------------------------------------------------
    private ImageIcon getSkinColor(int i) {
    	int kind = randomizer.nextInt(3) + 1;
			ImageIcon icon;
			Image resizedImage;
        if (i == 1) {
        	switch (kind){
        		case 0:
							icon = new ImageIcon(foldername + "lightskin1.png");
							break;
        		case 1:
							icon = new ImageIcon(foldername + "lightskin2.png");
							break;
        		default:
							icon = new ImageIcon(foldername + "lightskin3.png");
        	}
        } else {
        	switch (kind){
    				case 0:
						 	icon = new ImageIcon(foldername + "darkskin1.png");
							break;
    				case 1:
						 	icon = new ImageIcon(foldername + "darkskin2.png");
							break;
    				default:
						 	icon = new ImageIcon(foldername + "darkskin3.png");
    			}
        }
				resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
				return new ImageIcon(resizedImage);
    }

    private ImageIcon getNose(int i) {
			ImageIcon icon;
			Image resizedImage;
				switch(i) {
					case 1:
						icon = new ImageIcon(foldername + "bignose.png");
						break;
					case 2:
						icon = new ImageIcon(foldername + "shortnose.png");
						break;
					default:
						icon = new ImageIcon(foldername + "thinnose.png");
				}
				resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
				return new ImageIcon(resizedImage);
    }

    private ImageIcon getEyeColor(int i) {
			ImageIcon icon;
			Image resizedImage;
        switch (i) {
            case 1:
                icon = new ImageIcon(foldername + "blueeyes.png");
								break;
            case 2:
                icon = new ImageIcon(foldername + "blackeyes.png");
								break;
            case 3:
                icon = new ImageIcon(foldername + "browneyes.png");
								break;
            case 4:
                icon = new ImageIcon(foldername + "greeneyes.png");
								break;
            case 5:
                icon = new ImageIcon(foldername + "greyeyes.png");
								break;
						default:
		        	icon = new ImageIcon(foldername + "blank.png");
        }
				resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
				return new ImageIcon(resizedImage);
    }

    private ImageIcon getSex(int i) {
    	int kind = randomizer.nextInt(2) + 1;
			ImageIcon icon;
			Image resizedImage;
        if (i == 1) {
        	switch (kind){
        	case 1:
						icon = new ImageIcon(foldername + "boy.png");
						break;
        	default:
						icon = new ImageIcon(foldername + "boy2.png");
        	}

        } else {
        	switch (kind){
        	case 1:
						icon = new ImageIcon(foldername + "girl.png");
						break;
        	default:
						icon = new ImageIcon(foldername + "girl2.png");
        	}
        }
				resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
				return new ImageIcon(resizedImage);
    }

    private ImageIcon getSmile(int i, int j) {
			ImageIcon icon;
			Image resizedImage;
        if (i == 1) {
            if (j == 1) {
                icon = new ImageIcon(foldername + "bigsmile.png");
            } else {
                icon = new ImageIcon(foldername + "smallsmile.png");
            }
        } else {
            if (j == 1) {
                icon = new ImageIcon(foldername + "bigfrown.png");
            } else {
                icon = new ImageIcon(foldername + "smallfrown.png");
            }
        }
				resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
				return new ImageIcon(resizedImage);
    }

    private ImageIcon getFacialHair(int sex, int beard, int mustache, int k) {
			ImageIcon icon;
			Image resizedImage;
			final int MALE = 1;
			final int HAS_BEARD = 1;
			final int HAS_MUSTACHE = 1;
      if (sex == MALE){//only men have beards or mustaches, usually
      	if (beard == HAS_BEARD) {
          switch (k) {
	          case 1:
	            icon = new ImageIcon(foldername + "blondebeard.png");
							break;
	          case 2:
	            icon = new ImageIcon(foldername + "blackbeard.png");
							break;
	          case 3:
	            icon = new ImageIcon(foldername + "brownbeard.png");
							break;
	          case 4:
	            icon = new ImageIcon(foldername + "redbeard.png");
							break;
	          case 5:
	            icon = new ImageIcon(foldername + "greybeard.png");
							break;
						default:
							icon = new ImageIcon(foldername + "blank.png");
          }
        } else if (mustache == HAS_MUSTACHE) {
          switch (k) {
            case 1:
              icon = new ImageIcon(foldername + "blondemustache.png");
							break;
            case 2:
              icon = new ImageIcon(foldername + "blackmustache.png");
							break;
            case 3:
              icon = new ImageIcon(foldername + "brownmustache.png");
							break;
            case 4:
              icon = new ImageIcon(foldername + "redmustache.png");
							break;
            case 5:
              icon = new ImageIcon(foldername + "greymustache.png");
							break;
						default:
							icon = new ImageIcon(foldername + "blank.png");
          }
        } else {
					icon = new ImageIcon(foldername + "blank.png");
				}
      }
			else{
				icon = new ImageIcon(foldername + "blank.png");
			}
			resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImage);
    }

    private ImageIcon getShirt(int shirt) {
			ImageIcon icon;
			Image resizedImage;
        switch (shirt) {
            case 1:
                icon = new ImageIcon(foldername + "blueshirt.png");
								break;
            case 2:
                icon = new ImageIcon(foldername + "blackshirt.png");
								break;
            case 3:
                icon = new ImageIcon(foldername + "redshirt.png");
								break;
            case 4:
                icon = new ImageIcon(foldername + "greenshirt.png");
								break;
            case 5:
                icon = new ImageIcon(foldername + "orangeshirt.png");
								break;
            case 6:
                icon = new ImageIcon(foldername + "yellowshirt.png");
								break;
            case 7:
                icon = new ImageIcon(foldername + "purpleshirt.png");
								break;
            case 8:
                icon = new ImageIcon(foldername + "whiteshirt.png");
								break;
            case 9:
                icon = new ImageIcon(foldername + "leopardshirt.png");
								break;
            case 10:
                icon = new ImageIcon(foldername + "warningshirt.png");
								break;
						default:
								icon = new ImageIcon(foldername + "blank.png");
        }
				resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
				return new ImageIcon(resizedImage);
    }

    private ImageIcon getHair(int constraint, int hairColor) {
			ImageIcon icon;
			Image resizedImage;
				final int MALE = 1;
        if (constraint == MALE) {
            switch (hairColor) {
                case 1:
                    icon = new ImageIcon(foldername + "blondehairboy.png");
										break;
                case 2:
                    icon = new ImageIcon(foldername + "blackhairboy.png");
										break;
                case 3:
                    icon = new ImageIcon(foldername + "brownhairboy.png");
										break;
                case 4:
                    icon = new ImageIcon(foldername + "redhairboy.png");
										break;
                case 5:
                    icon = new ImageIcon(foldername + "greyhairboy.png");
										break;
                case 6:
                	icon = new ImageIcon(foldername + "blank.png");
									break;
								default:
					        icon = new ImageIcon(foldername + "blank.png");
            }
        } else {
            switch (hairColor) {
                case 1:
                    icon = new ImageIcon(foldername + "blondehairgirl.png");
										break;
                case 2:
                    icon = new ImageIcon(foldername + "blackhairgirl.png");
										break;
                case 3:
                    icon = new ImageIcon(foldername + "brownhairgirl.png");
										break;
                case 4:
                    icon = new ImageIcon(foldername + "redhairgirl.png");
										break;
                case 5:
                    icon = new ImageIcon(foldername + "greyhairgirl.png");
										break;
								default:
						        icon = new ImageIcon(foldername + "blank.png");
            }
        }
			resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImage);
    }

    private ImageIcon getHat(int constraint, int hatType) {
			ImageIcon icon;
			Image resizedImage;
			final int HAS_HAT = 1;
      if (constraint == HAS_HAT) {
        switch (hatType) {
          case 1:
            icon = new ImageIcon(foldername + "baseballcap.png");
						break;
          case 2:
            icon = new ImageIcon(foldername + "cowboyhat.png");
						break;
          case 3:
            icon = new ImageIcon(foldername + "beret.png");
						break;
					default:
						icon = new ImageIcon(foldername + "blank.png");
        }
      } else {
        icon = new ImageIcon(foldername + "blank.png");
			}
			resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImage);
    }

    private ImageIcon getEyewear(int constraint, int eyewearType) {
			ImageIcon icon;
			Image resizedImage;
			final int HAS_EYEWEAR = 1;
      if (constraint == HAS_EYEWEAR) {
        switch (eyewearType) {
          case 1:
            icon = new ImageIcon(foldername + "thinglasses.png");
						break;
          case 2:
            icon = new ImageIcon(foldername + "thickglasses.png");
						break;
          case 3:
            icon = new ImageIcon(foldername + "eyepatch.png");
						break;
          case 4:
            icon = new ImageIcon(foldername + "monocle.png");
						break;
					default:
		        icon = new ImageIcon(foldername + "blank.png");
        }
      } else {
        icon = new ImageIcon(foldername + "blank.png");
			}
			resizedImage = icon.getImage().getScaledInstance(SCALED_WIDTH, SCALED_HEIGHT, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImage);
    }
}
