package tetris.factories;

import tetris.panel.AbsPanel;

public class PanelFactory {

	public static AbsPanel getPanel(String panelName){
		try {
			return (AbsPanel) Class.forName("tetris.panel." + panelName).newInstance();
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.err.println("Class tetris.panel." + panelName +" Not Found!");
		return null;
	}

}
