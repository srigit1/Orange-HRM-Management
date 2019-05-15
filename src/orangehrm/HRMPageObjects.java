
package orangehrm;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HRMPageObjects {

	public HRMPageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='txtUsername']")
	private WebElement loginUserName;

	@FindBy(xpath = "//input[@id='txtPassword']")
	private WebElement loginPassword;

	@FindBy(xpath = "//input[@id='btnLogin']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[@id='menu_admin_UserManagement']")
	private WebElement userManagementLink;

	@FindBy(xpath = "//a[@id='menu_admin_viewSystemUsers']")
	private WebElement usersLink;

	@FindBy(xpath = "//input[@id='btnAdd']")
	private WebElement addButton;

	@FindBy(xpath = "//input[@id='systemUser_employeeName_empName']")
	private WebElement empName;

	@FindBy(xpath = "//input[@id='systemUser_userName']")
	private WebElement empuserName;

	@FindBy(xpath = "//input[@id='systemUser_password']")
	private WebElement userPwd;

	@FindBy(xpath = "//input[@id='systemUser_confirmPassword']")
	private WebElement ConfirmUserPwd;

	@FindBy(xpath = "//input[@id='btnSave']")
	private WebElement saveButton;

	@FindBy(xpath = "//a[@id='welcome']")
	private WebElement welcomeDropdown;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement logoutLink;

	@FindBy(xpath = "//b[contains(text(),'Dashboard')]")
	private WebElement dashboard;

	@FindBy(xpath = "//b[contains(text(),'Admin')]")
	private WebElement adminTab;

	@FindBy(xpath = "//a[@id='menu_admin_Organization']")
	private WebElement organizationTab;

	@FindBy(xpath = "//a[@id='menu_admin_viewOrganizationGeneralInformation']")
	private WebElement GeneralInformationTab;

	@FindBy(xpath = "//b[contains(text(),'PIM')]")
	private WebElement PIMTab;

	@FindBy(xpath = "//a[@id='menu_pim_viewEmployeeList']")
	private WebElement employeeListLink;

	@FindBy(xpath = "//input[@id='empsearch_employee_name_empName']")
	private WebElement searchempName;

	@FindBy(xpath = "//input[@id='searchBtn']")
	private WebElement searchButton;

	@FindBy(xpath = "//tr[@class='odd']//td[3]//a[1]")
	private WebElement selectEmp;

	@FindBy(xpath = "//a[contains(text(),'Contact Details')]")
	private WebElement contactDetails;

	@FindBy(xpath = "//input[@id='btnSave']")
	private WebElement editContactDetails;

	@FindBy(xpath = "//input[@id='contact_emp_mobile']")
	private WebElement mobileNumber;

	@FindBy(xpath = "//input[@id='btnAddAttachment']")
	private WebElement addAttachment;

	@FindBy(xpath = "//input[@id='ufile']")
	private WebElement selectFile;

	@FindBy(xpath = "//textarea[@id='txtAttDesc']")
	private WebElement comment;

	@FindBy(xpath = "//input[@id='btnSaveAttachment']")
	private WebElement uploadAttachment;

	@FindBy(xpath = "//a[@id='menu_pim_addEmployee']")
	private WebElement addEmployee;

	@FindBy(xpath = "//input[@id='firstName']")
	private WebElement empFirstName;

	@FindBy(xpath = "//input[@id='lastName']")
	private WebElement empLastName;

	@FindBy(xpath = "//input[@id='photofile']")
	private WebElement choosePhoto;

	@FindBy(xpath = "//canvas[@class='flot-overlay']")
	private WebElement empDistGraph;

	// This method uses Admin User to login to Orange HRM Application

	public void loginToHRM_Admin() {

		loginUserName.sendKeys("Admin");
		loginPassword.sendKeys("admin123");
		loginButton.click();
	}

	// This method creates ESSUser account

	public void createESSUser() throws InterruptedException {
		adminTab.click();
		Thread.sleep(3000);
		userManagementLink.click();
		usersLink.click();
		addButton.click();
		empName.sendKeys("SampleUser2 Last");
		empuserName.sendKeys("UserTest2");
		userPwd.sendKeys("Dontguess@123");
		ConfirmUserPwd.sendKeys("Dontguess@123");
		saveButton.click();
		welcomeDropdown.click();
		logoutLink.click();

	}

	/*
	 * TS_PIM_01 :This method checks if the user is able to enter the Orange HRM
	 * system with a successful ESS-User account
	 */

	public void loginToHRM_ESSUser() {
		loginUserName.sendKeys("Dontguess@123");
		loginButton.click();

	}

	public void verifyDashboard() {
		dashboard.getText().equals("Dashboard");
	}

	/*
	 * TS_PIM_02 :This method Checks if the user is able to see the “General
	 * Information” in organization tab under admin on logging in the first time
	 */

	public void verifygeneralInformationTab() {
		adminTab.click();
		organizationTab.click();
		String genInformationText = GeneralInformationTab.getText();
		System.out.println("text is" + genInformationText);
		assertEquals(genInformationText, "General Information");
	}

	// TS_PIM_04 This method Adds new Employee under PIM, Employee list

	public void addEmployeeWithPhoto() {
		PIMTab.click();
		addEmployee.click();
		empFirstName.sendKeys("SampleUser2");
		empLastName.sendKeys("Last");

		/*
		 * TS_PIM_05 Following code Checks if the user can upload a picture
		 * format (jpg,png, gif )
		 */

		choosePhoto.sendKeys("C:\\Users\\OrangeHRM\\Photos\\SampleJPGImage.jpg");
		choosePhoto.sendKeys("C:\\Users\\OrangeHRM\\Photos\\SamplePNGImage.png");
		choosePhoto.sendKeys("C:\\Users\\Photos\\SampleGIFImage.gif");
		saveButton.click();

	}

	/*
	 * TS_PIM_03 This method checks if the user is able to edit the fields other
	 * than the given fields and save changes in Contact details
	 */
	public void editContactDetails() {
		PIMTab.click();
		employeeListLink.click();
		searchempName.sendKeys("UserTest2");
		searchButton.click();
		searchempName.click();
		mobileNumber.sendKeys("9999999999");
		saveButton.click();
		addAttachment.click();
		selectFile.sendKeys("C:\\Users\\OrangeHRM\\Photos\\SamplePDFFile.pdf");
		uploadAttachment.click();
	}

	/*
	 * TS_PIM_06 This methods verifies Goto Dashboard Mouse over on Employee
	 * Distribution by subunit graph
	 */

	public void mouseHoverGraph() {

		Actions action = new Actions(HRMPage.driver);

		action.moveToElement(empDistGraph).perform();

		action.click();

		action.perform();
	}

}
