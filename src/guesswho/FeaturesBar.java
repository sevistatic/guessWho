package guesswho;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

class FeaturesBar extends JPanel{

  public ArrayList<Feature> featuresSet;
  public static ArrayList<JList> jListList;
  private final int BORDER_SIZE = 2;

	private static MouseHandler mouseHandler;

	public FeaturesBar(){
		super();
    featuresSet = new ArrayList<Feature>();
		jListList = new ArrayList<JList>();
		buildFeatures();
		initFeaturesBar();
	}

	public void addMouseListener(MouseHandler mouseHandler){
		this.mouseHandler = mouseHandler;
	}

	public void buildFeatures() {
		featuresSet.add(new Feature("Skin Color", new String[]{"light skin", "dark skin"}));
		featuresSet.add(new Feature("Eye Color", new String[]{"blue eyes", "black eyes", "brown eyes", "green eyes", "grey eyes"}));
		featuresSet.add(new Feature("Sex", new String[]{"boy", "girl"}));
		featuresSet.add(new Feature("Mouth", new String[]{"smiling", "frowning"}));
		featuresSet.add(new Feature("Lips", new String[]{"big lips", "thin lips"}));
		featuresSet.add(new Feature("Hair", new String[]{"blonde hair", "black hair", "brown hair", "red hair", "grey hair", "bald"}));
		featuresSet.add(new Feature("Beard", new String[]{"beard", "no beard"}));
		featuresSet.add(new Feature("Mustache", new String[]{"mustache", "no mustache"}));
		featuresSet.add(new Feature("Nose", new String[]{"big nose", "short nose", "thin nose"}));
		featuresSet.add(new Feature("Shirt", new String[]{"blue shirt", "black shirt", "red shirt", "green shirt", "orange shirt",
									"yellow shirt", "purple shirt"/*, "white shirt", "leopard shirt", "warning shirt"*/}));
		featuresSet.add(new Feature("Headwear", new String[]{"hat", "no hat"}));
		featuresSet.add(new Feature("Eyewear", new String[]{"glasses", "no glasses"}));
	}

	public void initFeaturesBar() {
		setLayout(new GridLayout(4, 2));
		setBorder(new LineBorder(Color.black, BORDER_SIZE));
		for (int i = 0; i < featuresSet.size(); i++) {
			JPanel j = new JPanel();
			j.setLayout(new GridLayout(1, 1));
			String[] data = featuresSet.get(i).getOptions();
			GuessList a = new GuessList(data, featuresSet.get(i).getName());
			a.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListList.add(a);
			a.addMouseListener(mouseHandler);
			j.add(jListList.get(i));
			add(j);
		}
	}

	public void clearFeatures(){
    for (int j = 0; j < jListList.size(); j++) {
      jListList.get(j).setSelectedIndex(0);
      jListList.get(j).clearSelection();
    }
	}
}
