package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import liaisonappliBDopta.ContrainteExam;
import liaisonappliBDopta.ContrainteSalle;

class ContrainteExamTest {

	@Test
	void testContrainteExam() {
			ContrainteExam a = new ContrainteExam(5, "AFTER", 4);
			assertEquals("AFTER",a.getType());
			assertEquals(5,a.getArg());
			assertEquals(4,a.getArg2());
		
	}

}
