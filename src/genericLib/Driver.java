package genericLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {
	
	//static String browserType = "FIREFOX";
	//private static String browserType = PropertyLoader.getPropertyValue("browser.type");
	//public static WebDriver driver=new FirefoxDriver();
	
	 public WebDriver driver = new FirefoxDriver();
	 
	 

	
/*	//@Parameters("browser")	
	//@Test
	public static void multi_browser(String browser) {
		
		if (browser.equalsIgnoreCase("firefox")) {
			d1 = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\gchand\\workspace\\chromedriver.exe");
			d1 = new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("", "");
			d1 = new InternetExplorerDriver();
		}
	}
*/

/*@Test
	public void multibrowser_firefox(){
		multi_browser("firefox");
		
	}

@Test
	public void multibrowser_chrome(){
		multi_browser("chrome");
	}


@Test
	public void multibrowser_ie(){
		//multi_browser("ie");

		
	}
		*/
	
	
}
