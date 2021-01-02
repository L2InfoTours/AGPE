package app;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import JFX.mote.Frame;
import gui.page.Login;
import gui.page.MainApp;
import gui.page.TimeExamElement;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("app");
		Frame frame = new Frame("AGPE");
		Login login = new Login("login");
		login.setErrorMessage("invalid");
		login.setSubmitAction(event->{
			System.out.println(login.getUsername());
			System.out.println(login.getPassWord());
			login.next();
		});
		List<TimeExamElement> diary = Arrays.asList(
				new TimeExamElement("F 021","Fran�ais",LocalDateTime.of(2020, 12, 30, 12, 30),LocalTime.of(1, 30),null),
				new TimeExamElement("F 021","Fran�ais",LocalDateTime.of(2020, 12, 31, 11, 30),LocalTime.of(1, 00),null),
				new TimeExamElement("F 021","Fran�ais",LocalDateTime.of(2021, 01, 1, 12, 15),LocalTime.of(1, 45),null)
				);
		List<List<TimeExamElement>> diaries = Arrays.asList(
				diary,diary
				);
		MainApp maz = new MainApp("app");
		System.out.println(diaries);
		maz.getCalendar().setDiaries(diaries);
		login.setNext("app");
		frame.setPanel(login);
		frame.show();
	}

}