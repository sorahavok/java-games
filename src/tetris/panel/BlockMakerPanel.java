package tetris.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;

import tetris.factories.ColorFactory;
import tetris.uielement.BlockBuilder;
import tetris.uielement.PieceSelectorScroll;

public class BlockMakerPanel extends AbsPanel {

	private final PieceSelectorScroll scroll;
	private final JComboBox<String> gridSize, colors;

	public BlockMakerPanel() {
		gridSize = new JComboBox<>(new String[] { "3 x 3", "4 x 4", "5 x 5" });

		Vector<String> colorsVector = new Vector<>(ColorFactory.getColorNameArray());
		colorsVector.remove(0);
		colors = new JComboBox<>(colorsVector);

		scroll = new PieceSelectorScroll();

		BlockBuilder blockBuilder = new BlockBuilder(3, colors.getSelectedIndex() + 1);

		JButton save = new JButton("Save");

		addSaveListener(blockBuilder, save);

		addDropDownsListener(blockBuilder);

		add(blockBuilder);
		add(gridSize);
		add(colors);
		add(save);
		add(scroll);
		add(returnHomeButton());
	}

	private void addSaveListener(final BlockBuilder blockBuilder, JButton save) {
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scroll.updateScrollPane(blockBuilder.getPiece());
			}
		});
	}

	private void addDropDownsListener(final BlockBuilder blockBuilder) {
		colors.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					blockBuilder.setColor(colors.getSelectedIndex() + 1); // black was removed so shift everything
				}
			}
		});
		
		gridSize.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					blockBuilder.setGirdSize(gridSize.getSelectedIndex());
				}
			}
		});
	}
}