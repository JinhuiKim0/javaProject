package seoulbin;

import java.awt.Font;

import javax.swing.UIManager;

public class FontSet {
	public static void setGlobalFont(Font font) {
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		UIManager.put("CheckBox.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);
		UIManager.put("OptionPane.messageFont", font);
		UIManager.put("OptionPane.buttonFont", font);
	}
}