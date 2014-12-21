package dylans;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class inputHandler implements KeyListener {
	 public static String input="";
	 
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar()=='\n')
			{
				screenManager.cursorX=0;
				screenManager.cursorY++;
				if(screenManager.cursorY==(screenManager.Height-2))
					screenManager.shift();
				System.out.println("["+input+"]");
				input="";
				Terminal.writePrompt();
				return;
			}
			
			if(e.getKeyChar()=='\b' && screenManager.cursorX!=0 && input.length()!=0)
			{
				System.out.println("Backspace!\n");
				input = input.substring(0, input.length()-1);
				screenManager.writeChar(' ', screenManager.cursorX-1, screenManager.cursorY);
				screenManager.cursorX--;
				return;
			}
			
			//System.out.println("Got char value :: "+ (int) e.getKeyChar()+" :: "+e.getKeyCode()+"\n");
			
			if(screenManager.charMap.indexOf(e.getKeyChar()+"") != -1)
			{
				screenManager.writeChar(e.getKeyChar(), screenManager.cursorX, screenManager.cursorY,255,255,255);
				input+=e.getKeyChar();
			}
				
			else
				return;
			
			
			if(screenManager.cursorX<(screenManager.Width-1))
				screenManager.cursorX++;
			else
			{
				screenManager.cursorX=0;
				screenManager.cursorY++;
				if(screenManager.cursorY==(screenManager.Height-2))
					screenManager.shift();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
}
