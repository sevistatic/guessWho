package names;

public class Names {

  static String[] maleNames = {"George", "Thomas", "Samuel", "Adam", "Spencer", "Scott",
    "Mark", "Mike", "Matt", "Chris", "Preston", "Doug", "Peter", "Lucas",
    "Bob Loblaw", "David", "Ronald", "Kevin", "Kenny", "Eric", "Kyle", "Stan",
    "Fred", "Curtis", "Bruce"};

  static String[] femaleNames = {"Martha", "Sally", "Abigail", "Jennifer", "Katie",
    "Cassie", "Julie", "Hanna", "Anne", "Rachel", "Linda", "Melissa",
    "Veronica", "Heather", "Izabelle", "Lindsay", "Diana", "Barbara", "Joyce",
    "Kaylee", "Gabriella", "Nancy"};

  public static String randomMaleName(){
    int index = (int) Math.floor(Math.random() * maleNames.length);
    return maleNames[index];
  }

  public static String randomFemaleName(){
    int index = (int) Math.floor(Math.random() * femaleNames.length);
    return femaleNames[index];
  }
}
