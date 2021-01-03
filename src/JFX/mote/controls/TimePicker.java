package JFX.mote.controls;

import JFX.mote.Component;
import JFX.mote.MathUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class TimePicker extends Component {
	Canvas table;
	private GraphicsContext ctx;
	@Override
	public void init() {
		setOnMouseMoved(event->{
			double x = event.getX();
			double y = event.getY();
			MathUtils.cosh(x,y);
		});
	}
	

}
