package TetrisClone;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public abstract class Panel extends JPanel
{
	int mouseX,mouseY;
	protected Toolkit kit = Toolkit.getDefaultToolkit();
	protected int width = kit.getScreenSize().width, height = kit.getScreenSize().height;
	protected int currentSize = width;
	public abstract void MousePressed(MouseEvent e);
	public void keyPressed(KeyEvent e)
	{
		System.out.print(e.getKeyCode());
	}
	
}
