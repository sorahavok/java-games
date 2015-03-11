package TetrisClone;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GUI{
		
	public static Container frame = new Container();
	
	public static void main(String[] args)
	{
		
		java.awt.Dimension screenSize = frame.getToolkit().getScreenSize();
//		frame.setUndecorated(true);
//Non-Full Screen	
		frame.setSize(615,835);
		frame.setLocation(screenSize.width/2, 0);
			
//Full Screen
//		frame.setSize(screenSize);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	protected static class Container extends JFrame implements MouseListener, KeyListener
	{
		public ArrayList<Panel> children = new ArrayList<Panel>();
		public Container()
		{
			addMouseListener(this);
			addKeyListener(this);
			GameField main = new GameField();
			children.add(main);
			add(main);
		}
		
		public void changePanel(Panel p)
		{
			add(p);
			remove(children.get(0));
			children.remove(0);
			children.add(p);
			validate();
		}
		@Override
		public void mouseReleased(MouseEvent e){
			int button = e.getButton();
//			System.out.print(button);
//			if(button==1)
//				System.out.println("Selected --> X: "+e.getX()+"\tY: "+e.getY());
			if(button==3)
				System.exit(0);
			for(Panel p:children)
				p.MousePressed(e);
			}		
		
		@Override
		public void keyPressed(KeyEvent e)
		{
			for(Panel p:children)
				p.keyPressed(e);
		}
		
		@Override public void mouseClicked(MouseEvent e){}
		@Override public void mouseEntered(MouseEvent e){}
		@Override public void mouseExited(MouseEvent e){}
		@Override public void mousePressed(MouseEvent e){}
		@Override public void keyReleased(KeyEvent arg0){}
		@Override public void keyTyped(KeyEvent arg0){}
	}
}
