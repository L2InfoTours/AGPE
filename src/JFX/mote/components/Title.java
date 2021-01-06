package JFX.mote.components;

import javafx.scene.text.Font;

/**
 * @author Mote
 *
 */
public class Title extends Text{
	/** Create new instance of Text with a style for title    
	 * @param value
	 * 
	 * @see Text 
	 */
	public Title(String value) {
		super(value);
	}
	/**
	 * This method update the style of the current component;
	 */
	@Override
	protected void updateStyle() {
		text.setTextFill(textColor);
		text.setFont(new Font(64));
	}
}
