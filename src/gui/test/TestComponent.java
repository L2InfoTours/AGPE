package gui.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import JFX.mote.MathUtils;

public class TestComponent {
	@Test
	public void MathUtilsMax(){
		//max
		assertEquals(JFX.mote.MathUtils.max(1,2,3), 3);
		assertEquals(JFX.mote.MathUtils.max(1,2,0), 2);
	}
}
