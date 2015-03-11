package tetris.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;

import tetris.Configuration;
import tetris.TetrisFrame;
import tetris.control.ControllerInput;
import tetris.uielement.ControlSelectionPane;

public class ConfigPanel extends AbsPanel {
	private static final long serialVersionUID = 1L;

	List<ControlSelectionPane> changers;
	ControllerInput[] controllers;
	
	public ConfigPanel() {
		controllers = new ControllerInput[]{Configuration.getInstance().getController()};
		Set<Entry<String, Integer>> entrySet = controllers[0].getMap().entrySet();

		changers = new ArrayList<>(entrySet.size());
		for(Entry<String, Integer> input : entrySet){
			ControlSelectionPane newChanger = new ControlSelectionPane(input.getKey(), input.getValue());
			changers.add(newChanger);
			add(newChanger);
		}
		
		JButton setControls = new JButton("Set & Return");
		add(setControls);
		setControls.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, Integer> controls = getNewControls();
				ControllerInput c = new ControllerInput(controls);
				Configuration config = Configuration.getInstance();
				config.updateController(c);
				config.writeXml();
				TetrisFrame.getInstance().setPanel(HOME_PANEL);
			}
		});
	}

	public Map<String, Integer> getNewControls() {
		Map<String, Integer>controls = new LinkedHashMap<>(changers.size());
		for (ControlSelectionPane cc : changers) {
			controls.put(cc.getControl(), cc.getKeyCode());
		}
		return controls;
	}
}
