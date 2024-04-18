package sit707_week5;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {
	private static WeatherController wController;
	private static int nHours;
	private static double[] temperatures;

	@BeforeClass
	public static void setupWController() {
		// Initialize controller once for all tests
		wController = WeatherController.getInstance();

		// Retrieve number of hours once
		nHours = wController.getTotalHours();

		// Store temperatures for all hours once
		temperatures = new double[nHours];
		for (int i = 0; i < nHours; i++) {
			temperatures[i] = wController.getTemperatureForHour(i + 1);
		}
	}

	@AfterClass

	public static void closeWController() {
		// Shutdown controller
		wController.close();

	}

	@Test
	public void testStudentIdentity() {
		String studentId = "s223075053";
		Assert.assertNotNull("Student ID is ", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Ishini Bhagya";
		Assert.assertNotNull("Student name is ", studentName);
	}

	@Test
	public void testTemperatureMin() {
		System.out.println("+++ testTemperatureMin +++");
		System.out.println("Temperature min: " + wController.getTemperatureMinFromCache());
		// Act
		double minTemperature = 1000;
		for (double temperatureVal : temperatures) {
			if (minTemperature > temperatureVal) {
				minTemperature = temperatureVal;
			}
		}
		// Assert
		Assert.assertEquals(wController.getTemperatureMinFromCache(), minTemperature, 0.001);
	}

	@Test
	public void testTemperatureMax() {
		System.out.println("+++ testTemperatureMax +++");
		System.out.println("Temperature max: " + wController.getTemperatureMaxFromCache());
		// Act
		double maxTemperature = -1;
		for (double temperatureVal : temperatures) {
			if (maxTemperature < temperatureVal) {
				maxTemperature = temperatureVal;
			}
		}
		// Assert
		Assert.assertEquals(wController.getTemperatureMaxFromCache(), maxTemperature, 0.001);
	}

	@Test
	public void testTemperatureAverage() {
		System.out.println("+++ testTemperatureAverage +++");
		System.out.println("Temperature avg: " + wController.getTemperatureAverageFromCache());
		// Act
		double sumTemp = 0;
		for (double temperatureVal : temperatures) {
			sumTemp += temperatureVal;
		}
		double averageTemp = sumTemp / nHours;

		// Assert
		Assert.assertEquals(wController.getTemperatureAverageFromCache(), averageTemp, 0.001);
	}
	
	@Test
	public void testTemperatureForHour() {
	    System.out.println("+++ testTemperatureForHour +++");
	    int hour = 2; // Test for the second hour
	    double expectedTemperature = temperatures[hour - 1]; // Temperature for the second hour

	    double actualTemperature = wController.getTemperatureForHour(hour);

	    // Assert
	    Assert.assertEquals(expectedTemperature, actualTemperature, 0.001);
	}

	


}
