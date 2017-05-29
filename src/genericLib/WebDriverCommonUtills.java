package genericLib;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverCommonUtills extends Driver{
	Logger log = Logger.getLogger(WebDriverCommonUtills.class);
	
	enum FindBy {ID, NAME, XPATH, LINKTEXT, TAGNAME }

	/**
	 * Implicit wait for PageLoad
	 */
	public void waitForPageToLoad() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/**
	 * Web driver wait until the Element Present in UI, Max wait = 20 seconds 
	 * @param locator
	 * @param findBy
	 */
	public void waitForElementPresent(String locator, String findBy) {
		log.debug("Inside waitForElementPresent method");
		log.info("Locator: " + locator + "| findby: " + findBy);

		FindBy findBylocator = null;
		try {
			findBylocator = FindBy.valueOf(findBy.toUpperCase());
			WebDriverWait wait = new WebDriverWait(driver, 20);

			if (findBylocator != null) {

				switch (findBylocator) {
				case ID:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
					break;

				case NAME:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
					break;

				case XPATH:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					break;

				case LINKTEXT:
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
					break;

				default:
					log.debug("Entering into default XPATH selection");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
					break;
				}
			}else{log.equals("Enum constant value is null");}
		} 
		catch (IllegalArgumentException e) {
			log.error("Enum Constant not defined for the given findBy "
					+ findBy, e);
		}
		catch (Exception e){
			log.error("Exception occured while locating the WebElement", e);
			
		}
		
		log.debug("Out of waitForElementPresent method");
	}

	/**
	 * Method to find the text in UI   <br>
	 * Returns true is the Text is found else return false
	 * @param expectedText
	 * @return true/false
	 */
	public boolean verifyTextPresent(String expectedText) {
		
		log.debug("Inside verifyTextPresent method");
		log.info("Expected text: '" + expectedText + "'");
		boolean flag = false;
		
		String entirePageSource = driver.getPageSource();
		
		if (entirePageSource.contains(expectedText)) {
			log.info("Expected Text found in the page");
			flag = true;
		} 
		else {
			log.warn("Expected Text not found in the page");
		}
		
		log.debug("Out of verifyTextPresent method");
		return flag;
	}

	/** 
	 * Method return list of WebElement 
	 * @param locator
	 * @param findBy
	 * @return List<WebElement>
	 */
/*	public List<WebElement> findElements(String value, String findBy) {

		log.debug("Inside findElements method");
		log.info("value: " + value + "| findby: " + findBy);

		List<WebElement> list = null;

		try {
			FindBy findBylocator = FindBy.valueOf(findBy.toUpperCase());

			if (findBylocator != null) {

				switch (findBylocator) {
				case XPATH:
					list = d1.findElements(By.xpath(value));
					break;

				case TAGNAME:
					list = d1.findElements(By.tagName(value));
					break;

				default:
					log.debug("Entering into default XPATH selection");
					list = d1.findElements(By.xpath(value));
					break;
				}
			}
		} 
		catch (IllegalArgumentException e) {
			log.error("Enum Constant not defined for the given findBy "
					+ findBy, e);
		}
		catch (Exception e){
			log.error("Exception occured while finding the weblements", e);
			captureSreenShot();
		}
		
		log.debug("Out of findElements method");
		return list;
	}
*/
	/**
	 * Method to get the Alert Text
	 * @return alert text
	 */
	public String getAlertText() {
		
		log.debug("Inside getAlertText method");
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		
		log.info("Alert text: '" + alertText + "'");
		log.debug("Out of getAlertText method");
		
		return alertText;
	}
	
	/**
	 * Method to accept the alert
	 */
	public void acceptAlert(){
		log.debug("Inside acceptAlert method");
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		log.info("Alert text: '" + alertText + "'");
		
		alert.accept();
		log.debug("Out of acceptAlert method");
	}
	
	/**
	 * Method to rejectAlert the alert
	 */
	public void rejectAlert(){
		log.debug("Inside dismissAlert method");
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		log.info("Alert text: '" + alertText + "'");
		
		alert.dismiss();
		log.debug("Out of rejectAlert method");
	}
	
	/**
	 * Method to select all element in a WebList
	 * @param element
	 */
	public void selectAllWebListElements(WebElement element) {
		
		log.debug("Inside selectAllWebListElements method");
		Select select = new Select(element);
		List<WebElement> list = select.getOptions();
		
		log.debug("WebList data: ");
		for (int i = 0; i < list.size(); i++) {
			WebElement webElement = list.get(i);
			log.debug(webElement.getText());
			select.selectByIndex(i);
		}
		
		log.debug("Out of selectAllWebListElements method");
	}
	
	public void maxmizeWindow(){
		driver.manage().window().maximize();
	}
	
	public void clickLink(String value){
		driver.findElement(By.linkText(value)).click();
	}
	
	
	public boolean elementPresnet(String webelement){
		try{
			System.out.println("elementPresnet try block: "+webelement);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//new WebDriverWait(d1, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(webelement)));
			//System.out.println("elementPresnet try block after wait"+webelement);
			driver.findElement(By.id(webelement));
			System.out.println("elementPresnet try block after element");
			return true;
			
		}catch(Exception  e){
			System.out.println("elementPresnet catch block");
			System.out.println("No such element found exception: "+ webelement);
			return false;
		}
	}
	
	
	
	 public boolean checkPageIsReady() {
		 boolean returnstat= false;
		 try{
			 
			 Thread.sleep(3000);
		 	JavascriptExecutor js = (JavascriptExecutor)driver;
			if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		   	System.out.println("Page Is loaded.");
		   	returnstat= true;
		 
			}else{
				for(int i=0; i<= 10; i++){
				Thread.sleep(2000);
				  if (js.executeScript("return document.readyState").toString().equals("complete")){ i =10; returnstat= true;}
				}
				
			}
		 }catch(Exception e){System.err.println("Exception: "+ e); }
			 return returnstat;
		 
		}
	
	
}
