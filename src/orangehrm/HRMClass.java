package orangehrm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HRMPage {

	HRMPageObjects hrmPage;
	public static WebDriver driver;

	@Test
	public void launchApplication() throws InterruptedException {

		hrmPage = new HRMPageObjects(driver);

		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		if (driver == null) {
			driver = new ChromeDriver();
		}
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		hrmPage = new HRMPageObjects(driver);
		// login to hrm as admin
		hrmPage.loginToHRM_Admin();
		// Scenario 4-add employee and upload photo
		hrmPage.addEmployeeWithPhoto();
		// create ess user
		hrmPage.createESSUser();
		// scenario 1-login with ess user
		hrmPage.loginToHRM_ESSUser();
		hrmPage.verifyDashboard();
		// Scenario 2-verify general information tab
		hrmPage.verifygeneralInformationTab();
		// Scenario 3-edit contact details
		hrmPage.editContactDetails();
		// Scenario 5-mouse hover
		hrmPage.mouseHoverGraph();
		driver.quit();

	}
}

