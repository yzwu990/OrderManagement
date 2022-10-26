package runprogram;

import javax.swing.JFrame;

import frame.MainFrame;

public class RunProgram {

	public static void main(String[] args) {

		try {
			JFrame MainFrame = new MainFrame();
			MainFrame.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
