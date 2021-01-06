package JFX.mote.controls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import JFX.mote.components.Text;
import javafx.scene.paint.Color;


public class Email extends TextField {
	private Text errormsg;
	private final static Pattern email = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	public Email(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		super.init();
	}
	/** set the Text of the component.
	 * @param string
	 * 
	 * @see TextField 
	 */
	@Override
	public String getText() {
		String text = super.getText();
		Matcher mat = email.matcher(text);
		if(mat.matches()) {
			setErrorMessage("");
			return text;
		}else {
			setErrorMessage("not a email : examplemail@example.com");
		}
		return null;
	}
	public void setErrorMessage(String value) {
		if(errormsg==null) {
			errormsg = new Text(value);
			errormsg.setColor(Color.RED);
			errormsg.toNode();
			pane.getChildren().add(errormsg);
		}else {
			errormsg.setText(value);
		}
	}
}
