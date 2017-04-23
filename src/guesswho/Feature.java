package guesswho;
/**
 *
 * @author Spencer
 *Used entirely to describe the features used in the JLists found in Background.java.
 *An example of a feature is sex.  It has a parity of 2, because there can be a boy or a girl.
 *boy and  girl would be the options in this case.
 */
class Feature {
  private String name;//name: beard
  private int parity;//parity: 2
  private String[] options;//options = {yes, no}
  Feature(String name, String[] options){
      parity = options.length;
      this.name = name;
      this.options = options;
  }

  public String getName() {
      return name;
  }

  public String[] getOptions() {
      return options;
  }

  public int getParity() {
      return parity;
  }
}
//-----------------------------------------------------------------------------
class SkinColorFeature extends Feature {
  SkinColorFeature(){
    super("Skin Color", new String[]{"light skin", "dark skin"});
  }
}
class EyeColorFeature extends Feature{
  EyeColorFeature(){
    super("Eye Color", new String[]{"blue eyes", "black eyes", "brown eyes", "green eyes", "grey eyes"});
  }
}
class SexFeature extends Feature {
  SexFeature(){
    super("Sex", new String[]{"boy", "girl"});
  }
}
class MouthShapeFeature extends Feature{
  MouthShapeFeature(){
    super("Mouth", new String[]{"smiling", "frowning"});
  }
}
class LipsFeature extends Feature {
  LipsFeature(){
    super("Lips", new String[]{"big lips", "thin lips"});
  }
}
class HairFeature extends Feature{
  HairFeature(){
    super("Hair", new String[]{"blonde hair", "black hair", "brown hair", "red hair", "grey hair", "bald"});
  }
}
class BeardFeature extends Feature {
  BeardFeature(){
    super("Beard", new String[]{"beard", "no beard"});
  }
}
class MustacheFeature extends Feature{
  MustacheFeature(){
    super("Mustache", new String[]{"mustache", "no mustache"});
  }
}
class NoseFeature extends Feature {
  NoseFeature(){
    super("Nose", new String[]{"big nose", "short nose", "thin nose"});
  }
}
class ShirtFeature extends Feature{
  ShirtFeature(){
    super("Shirt", new String[]{"blue shirt", "black shirt", "red shirt", "green shirt", "orange shirt",
              "yellow shirt", "purple shirt", "white shirt", "leopard shirt", "warning shirt"});
  }
}
class HatFeature extends Feature {
  HatFeature(){
    super("Headwear", new String[]{"hat", "no hat"});
  }
}
class EyewearFeature extends Feature{
  EyewearFeature(){
    super("Eyewear", new String[]{"glasses", "no glasses"});
  }
}
