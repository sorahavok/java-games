package tetris.panel;

import java.util.List;
import java.util.Map;
import java.util.stream.*;

import javax.swing.JButton;

import tetris.Configuration;
import tetris.TetrisFrame;
import tetris.control.ControllerInput;
import tetris.uielement.ControlSelectionPane;

public class ConfigPanel extends AbsPanel {
	private static final long serialVersionUID = 1L;

	private final List<ControlSelectionPane> changers;
	private final ControllerInput[] controllers;
	
	public ConfigPanel() {
		controllers = new ControllerInput[]{Configuration.getInstance().getController()};
		
		changers = controllers[0].getMap().entrySet().stream().map(input -> {
			ControlSelectionPane newChanger = new ControlSelectionPane(input.getKey(), input.getValue());
			add(newChanger);
			return newChanger;
		}).collect(Collectors.toList());
				
		JButton setControls = new JButton("Set & Return");
		add(setControls);
		setControls.addActionListener( event -> {
				ControllerInput c = new ControllerInput(getNewControls());
				Configuration config = Configuration.getInstance();
				config.updateController(c);
				config.writeXml();
				TetrisFrame.getInstance().setPanel(HOME_PANEL);
				});
	}

	public Map<String, Integer> getNewControls() {
		// Take the changer and convert it into a map with <Control,KeyCode>
		return changers.stream().collect(Collectors.toMap(c -> c.getControl(), c-> c.getKeyCode()));
	}
}
