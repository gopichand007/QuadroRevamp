package download.com.nvidia.www;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import businessLib.QuadroLookupsDB;
import genericLib.WebDriverCommonUtills;


public class ManualDriverFlows extends QuadroLookupsDB{
	



//inputs 
static String url= "http://www.nvidia.com/Download/index.aspx?lang=en-us";
	
	String productType ="";
	String product = "";
	String os= "";
	String downLoadType ="";
	String language ="";	
	
	 boolean downloadtypeQuadro;
	
//Global variables 
	
	static Select selProductType;
	static Select selProductSeries;
	static Select selectProduct;
	static Select selectOS;
	static Select selDownLoadType;
	static Select selectLanguage;

//web_elements	
public void QuadroLookups (String url, String ProductType ){
	
	String DriverDetailURL= "null";
	String DriverVersionInDTP= "null";
	String relaseDate= "null";
	String OSinDDP= "null";
	String langaugeinDDP= "null";
	String output ="null";

	 String languagetxt;
	 String pstxt;
	 String pttxt;
	 String dttxt;
	 String ostxt;
	 
	
		
	 
	driver.get(url);
	waitForPageToLoad();
	
	for(int language=0;  language<=15; language++ ){
	
		int prdsrct= productSeriesCount(ProductType);
		//for (int ptsr =1; ptsr<= 2; ptsr++){
		for (int ptsr =1; ptsr<= prdsrct; ptsr++){
			
			//if(ptsr>=2){showlessAndShowmoreforOS();}
		
			
		
			 int prdltct= productListCount(ProductType, ptsr);
			 if(prdltct>= 10){ prdltct=10;}
			 //for(int pt= 0; pt<= 3;pt++){
			 for(int pt= 0; pt<= 0;pt++){
				 
				 
				 int osct =operatingSystemCount(ProductType,  ptsr,  pt );
				 //if(osct>= 20){ prdltct=20;}
				//for(int os= 12; os<=osct ; os++){
				 for(int os= 1; os<= osct; os++){
					 
					 
					 int dwntypect =downloadTypeCount(ProductType,  ptsr,  pt, os );
					 for(int dt= 0; dt<= dwntypect; dt++){
						 

						 
				 			DriverDetailURL= "null";
				 			DriverVersionInDTP= "null"; 
				 			relaseDate= "null";
				 			OSinDDP= "null";
				 			langaugeinDDP= "null";
						 
						 
				 			
							 
							   try{
								   
								   System.out.println("-----------main loop----------------"); 
								   waitForElementPresent("selProductSeriesType", "ID");
								   selProductType = new Select(driver.findElement(By.id("selProductSeriesType")));
								   selProductType.selectByVisibleText(ProductType);//Product type
								   Thread.sleep(100);
							   }catch(Exception e){System.out.println("Exception productType" +e.getMessage());}
							
							   try{
								   
								   
								   System.out.println("prdsrct: "+prdsrct); 
								   waitForElementPresent("selProductSeries", "ID");
								   //new Select(driver.findElement(By.id("selProductSeries"))).selectByVisibleText("Show all Product Series");
								   selProductSeries = new Select(driver.findElement(By.id("selProductSeries")));					
								   selProductSeries.selectByIndex(ptsr);
								   selProductSeries.getOptions().size();
								   pstxt = selProductSeries.getFirstSelectedOption().getText();
							   }catch(Exception e){ pstxt = "null"; System.out.println("Exception selProductSeries" +e.getMessage() ); }
							   
							   try{
								   
								   System.out.println("prdltct: "+prdltct);
								   waitForElementPresent("selProductFamily", "ID");
								   selectProduct = new Select(driver.findElement(By.id("selProductFamily")));					
								   selectProduct.selectByIndex(pt);
								   pttxt = selectProduct.getFirstSelectedOption().getText();
							   }catch(Exception e){ pttxt ="null"; System.out.println("Exception selProductFamily "+e.getMessage() );}
							     
							    
								   
							 
							     try{
							    	 System.out.println("os: "+os);
							    	 waitForElementPresent("selOperatingSystem", "ID");
							    	 try{new Select(driver.findElement(By.id("selOperatingSystem"))).selectByVisibleText("Show all Operating Systems");
							 	    }catch (Exception e){System.out.println("Exception in OS show all operating systems" ); }
									selectOS = new Select(driver.findElement(By.id("selOperatingSystem")));					
									selectOS.selectByIndex(os);
									
									ostxt= selectOS.getFirstSelectedOption().getText();
									 }catch(Exception e){ostxt= "null"; System.out.println("Exception selOperatingSystem" +e.getMessage());}
								
								
							     
							     try{
							    	 System.out.println("dwntypect: "+dwntypect);
							    	 if(downloadtypeQuadro()){
							    		 selDownLoadType.selectByIndex(dt);
							    		 
							    	 }else{
							     		 if(downloadtypeUNIX()){
							     			selDownLoadType.selectByIndex(dt);
							     		 }else{
							     			 System.out.println("unknown download type in manin loop");
							     		 }
							    	 
							    		
							    		 
							    	 }//else
							    	
									dttxt= selDownLoadType.getFirstSelectedOption().getText();
									
									 }catch(Exception e){dttxt= "null"; System.out.println("Exception getFirstSelectedOption"+e.getMessage() );}
							     
								
								try{
									 System.out.println("language: "+language);
									 waitForElementPresent("ddlDownloadTypeQuadro", "ID");
									selectLanguage = new Select(driver.findElement(By.id("ddlLanguage")));					
									selectLanguage.selectByIndex(language);
									languagetxt = selectLanguage.getFirstSelectedOption().getText();
							
								 }catch(Exception e){ languagetxt = "null"; System.out.println("Exception ddlDownloadTypeQuadro "+e.getMessage() );}
								
								
								
							
							
								
								try{
									waitForElementPresent("imgSearch", "ID");
									driver.findElement(By.id("imgSearch")).click();
									Thread.sleep(2000);
								}catch(Exception e){
									System.out.println("search button in not found");
									
								}
				 			
								if(checkPageIsReady()){
										if(elementPresnet("Div1")){
											
											DriverDetailURL= driver.getCurrentUrl();
											DriverVersionInDTP= grabDataDriverVersion();
											relaseDate= getrelaseDate();
										    OSinDDP= getOSinDDP();
											langaugeinDDP= getlangaugeinDDP();
											
											
											System.out.println("Product Type: "+ProductType+ " Product Series:"+pstxt+" Product: "+pttxt+ " Operating System: "+ostxt
													+" Download type: "+dttxt+" Language: "+languagetxt+" DriverDetailURL: "+DriverDetailURL
													+" Driver version: "+DriverVersionInDTP+ " Release date"
													+ relaseDate+"OSinDDP:  "+OSinDDP +" langaugeinDDP : "+langaugeinDDP);
											
											 output = ("Product Type: "+ProductType+ " Product Series:"+pstxt+" Product: "+pttxt+ " Operating System: "+ostxt
														+" Download type: "+dttxt+" Language: "+languagetxt+" DriverDetailURL: "+DriverDetailURL
														+" Driver version: "+DriverVersionInDTP+ " Release date"
														+ relaseDate+"OSinDDP:  "+OSinDDP +" langaugeinDDP : "+langaugeinDDP);
											 
											 
											 if(insertData(ProductType, pstxt, pttxt, ostxt, dttxt, languagetxt, DriverDetailURL, DriverVersionInDTP, relaseDate, OSinDDP,langaugeinDDP)==1){
												 System.out.println("------------------inserted----------------- ");
												 }
											 
											
											
											
												 	driver.navigate().back();
										 			waitForPageToLoad();
										 			showlessAndShowmore();

											
													}else {
														 if(insertData( ProductType, pstxt, pttxt, ostxt, dttxt, languagetxt, "No certified downloads were found", "No certified downloads were found", "No certified downloads were found", "No certified downloads were found","No certified downloads were found")==1){System.out.println("------------------inserted-------------------");}
														System.out.println("No result found");
														
														
														System.out.println("Product Type: "+ProductType+ " Product Series:"+pstxt+" Product: "+pttxt+ " Operating System: "+ostxt
																+" Download type: "+dttxt+" Language: "+languagetxt+" DriverDetailURL: No certified downloads were found "+
																" Driver version: No certified downloads were found " +"Release date: No certified downloads were found"
																+"OSinDDP:  No certified downloads were found"
																+" langaugeinDDP : No certified downloads were found");
														
														 /*output = ("Product Type: "+ProductType+ " Product Series:"+pstxt+" Product: "+pttxt+ " Operating System: "+ostxt
																	+" Download type: "+dttxt+" Language: "+languagetxt+" DriverDetailURL: "+DriverDetailURL
																	+" Driver version: "+DriverVersionInDTP+ " Release date"
																	+ relaseDate+"OSinDDP:  "+OSinDDP +" langaugeinDDP : "+langaugeinDDP);
														 */
													}
													
													
									 	
								 }
								
								
							
					

								System.out.println(("-----------main loop end----------------"));
			
					 
					 }//download type 
					 
				 }//os
	
			 }//products 
		
		}//Product series 
	
	}//language

}//Method 




public int productSeriesCount(String productType){
	

	try{
	
		waitForElementPresent("selProductSeriesType", "ID");
		selProductType = new Select(driver.findElement(By.id("selProductSeriesType")));
		selProductType.selectByVisibleText(productType);//Product type
		Thread.sleep(100);
	
		waitForElementPresent("selProductSeries", "ID");
		selProductSeries = new Select(driver.findElement(By.id("selProductSeries")));
		try{selProductSeries.selectByVisibleText("Show all Product Series");
			driver.findElement(By.id("selProductSeries")).click();
		    Thread.sleep(100);
			}catch(Exception e){System.out.println("Exception in productSeriesCount" +e.getMessage());}
		selProductSeries.selectByIndex(1);
		System.out.println("productSeriesCount(productType)-Product Series count: "+(selProductSeries.getOptions().size()-3));
		return selProductSeries.getOptions().size()- 3;//removing default values
		}catch(Exception e){
			System.out.println("Web element not found exception: "+ e.getMessage());
			return 0;
	}
	
	
}//


public int productListCount(String productType, int ptsr ){
	try{
		waitForElementPresent("selProductSeriesType", "ID");
		selProductType = new Select(driver.findElement(By.id("selProductSeriesType")));
		selProductType.selectByVisibleText(productType);//Product type
		Thread.sleep(100);
		
		waitForElementPresent("selProductSeries", "ID");
		selProductSeries = new Select(driver.findElement(By.id("selProductSeries")));					
		selProductSeries.selectByIndex(ptsr); //Product series
		
		waitForElementPresent("selProductFamily", "ID");
	    selectProduct = new Select(driver.findElement(By.id("selProductFamily")));
	    //selectProduct.selectByIndex(0);
	    System.out.println("productListCount(String productType, int ptsr )- Porduct: "+(selectProduct.getOptions().size()- 1));
	    return selectProduct.getOptions().size()- 1 ;
	    
    
	}catch(Exception e){
		System.out.println("Web element not found exception: "+ e.getMessage());
		return 0;
	}
}//

public int operatingSystemCount(String productType, int ptsr, int pt ){
	try{
	
		waitForElementPresent("selProductSeriesType", "ID");
		selProductType = new Select(driver.findElement(By.id("selProductSeriesType")));
		selProductType.selectByVisibleText(productType);//Product type
		Thread.sleep(100);
		
		waitForElementPresent("selProductSeries", "ID");
		selProductSeries = new Select(driver.findElement(By.id("selProductSeries")));					
		selProductSeries.selectByIndex(ptsr); //Product series
		
		waitForElementPresent("selProductFamily", "ID");
	    selectProduct = new Select(driver.findElement(By.id("selProductFamily")));
	    selectProduct.selectByIndex(pt);
		
	    waitForElementPresent("selOperatingSystem", "ID");
	    selectOS = new Select(driver.findElement(By.id("selOperatingSystem")));
	    try{selectOS.selectByVisibleText("Show all Operating Systems");
	    	Thread.sleep(100);
	    	driver.findElement(By.id("selOperatingSystem")).click();
	    }catch (Exception e){System.out.println("Exception in Show all Operating Systems --operatingSystemCount--"); }
	    	selectOS.selectByIndex(1);
			System.out.println("operatingSystemCount(String productType, int ptsr, int pt )- OS list:"+ (selectOS.getOptions().size()-3) );
			return (selectOS.getOptions().size())- 3;// removing default values
		
	}catch (Exception e){
		System.out.println("Exception in operatingSystemCount" + e.getMessage());
		
		return 0;
		
	}
	
}//

public int downloadTypeCount(String productType,  int ptsr, int pt, int os){
	
	try{
		
		waitForElementPresent("selProductSeriesType", "ID");
		selProductType = new Select(driver.findElement(By.id("selProductSeriesType")));
		selProductType.selectByVisibleText(productType);//Product type
		Thread.sleep(100);
		
		waitForElementPresent("selProductSeries", "ID");
		selProductSeries = new Select(driver.findElement(By.id("selProductSeries")));					
		selProductSeries.selectByIndex(ptsr); //Product series
		
		waitForElementPresent("selProductFamily", "ID");
	    selectProduct = new Select(driver.findElement(By.id("selProductFamily")));
	    selectProduct.selectByIndex(pt);
		
	    waitForElementPresent("selOperatingSystem", "ID");
		selectOS = new Select(driver.findElement(By.id("selOperatingSystem")));
		
		selectOS.selectByIndex(os);
		
		if(downloadtypeQuadro()){
			System.out.println("downloadTypeCount(String productType,  int ptsr, int pt, int os)- Downlaod Type:"+ (selDownLoadType.getOptions().size()-1) );
			return (selDownLoadType.getOptions().size())- 1;
			
		}else
			if(downloadtypeUNIX()){
				System.out.println("Downlaod Type:"+ (selDownLoadType.getOptions().size()-1) );
				return (selDownLoadType.getOptions().size())- 1;
				
			}else{
				System.out.println("Unknown driver downlaod option");
				return 0;
				
			}
	
		
	}catch (Exception e){
		System.out.println("Exception in Download type" + e.getMessage());
		return 0;
		
	}
	
}//	

	public String grabDataDriverVersion(){
		try{
			System.out.println("grabsDataDriverVersion try block");
			waitForElementPresent("tdVersion", "ID");
			String driververson= driver.findElement(By.id("tdVersion")).getText();
			System.out.println("Driver Version: "+driververson);
			return driververson;
		
		}catch(Exception e){
			System.out.println("No Such element found contentsummaryright");
			return "null";
		}
		
	
}//method 

	public String getrelaseDate(){
	
	try{
	waitForElementPresent("tdReleaseDate", "ID");
	String langaugeinDDP=driver.findElement(By.id("tdReleaseDate")).getText();
	System.out.println("langaugeinDDP : "+langaugeinDDP);
	return langaugeinDDP;
	
	}catch(Exception e){
		System.out.println("Language is is not found in driver detail page" +e.getMessage());
		return null;
	}
}//method

	public String getOSinDDP(){
		
	try{
	waitForElementPresent(".//*[@id='Div1']/table[1]/tbody/tr[3]/td[2]", "XPATH");
	String OSinDDP=driver.findElement(By.xpath(".//*[@id='Div1']/table[1]/tbody/tr[3]/td[2]")).getText();
	System.out.println("OSinDDP : "+OSinDDP);
	return OSinDDP;
	
	}catch(Exception e){
		System.out.println("OS is not found in driver detail page" +e);
		return null;
	}
}//method	
	
	public String getlangaugeinDDP(){
	
	try{
	waitForElementPresent(".//*[@id='Div1']/table[1]/tbody/tr[5]/td[2]", "XPATH");
	String langaugeinDDP=driver.findElement(By.xpath(".//*[@id='Div1']/table[1]/tbody/tr[5]/td[2]")).getText();
	System.out.println("langaugeinDDP : "+langaugeinDDP);
	return langaugeinDDP;
	
	}catch(Exception e){
		System.out.println("Language is not found in driver detail page" +e);
		return null;
	}
}//method
	

 public void showlessAndShowmore(){
	 
		try{
			waitForElementPresent("selProductSeries", "ID");
			selProductSeries = new Select(driver.findElement(By.id("selProductSeries")));
			selProductSeries.selectByVisibleText("Show all Product Series");
			driver.findElement(By.id("selProductSeries")).click();
			Thread.sleep(1000);
			
			selProductSeries.selectByIndex(1);
			Thread.sleep(1000);
			try{
				waitForElementPresent("selOperatingSystem", "ID");
				selectOS = new Select(driver.findElement(By.id("selOperatingSystem")));
				selectOS.selectByVisibleText("Show all Operating Systems");
				Thread.sleep(1000);
				driver.findElement(By.id("selOperatingSystem")).click();
				Thread.sleep(1000);
				selectOS.selectByIndex(1);
			}catch(Exception e1){
				System.out.println("Exception in showlessAndShowmore os "+ e1.getMessage());
			}

		} catch(Exception e){
			System.out.println("Show less and show more elements not found -selProductSeries"+e.getMessage());
		}

 }
 
 
 public void showlessAndShowmoreforOS(){
	 
		
			try{
				waitForElementPresent("selOperatingSystem", "ID");
				selectOS.selectByVisibleText("Show all Operating Systems");
				Thread.sleep(100);
				driver.findElement(By.id("selOperatingSystem")).click();
				selectOS.selectByIndex(1);
				Thread.sleep(100);
			}catch(Exception e1){
				System.out.println("Exception in showlessAndShowmoreforOS ");
			}

	
}	
	
 public boolean  downloadtypeQuadro(){
	 try{
		 if(driver.findElement(By.id("ddlDownloadTypeQuadro")).isDisplayed()){
			 waitForElementPresent("ddlDownloadTypeQuadro", "ID");
			 selDownLoadType = new Select(driver.findElement(By.id("ddlDownloadTypeQuadro")));
			
			 return true;
			 
		 }else{
			 return false;
		 }

		
		 	}catch(Exception e1){
		 		System.out.println("Exception in downloadtypeQuadro "+ e1.getMessage());
		 		return false;
		}
		 
	 
	 
 }//method
 
 
 public boolean  downloadtypeUNIX(){

		 try{
			 if(driver.findElement(By.id("ddlDownloadTypeQuadroUx")).isDisplayed()){
			 waitForElementPresent("ddlDownloadTypeQuadroUx", "ID");
	    	 selDownLoadType = new Select(driver.findElement(By.id("ddlDownloadTypeQuadroUx")));
	    	 return true;
			 }else{
				 return false;
			 }
		 	}catch(Exception e1){
		 		System.out.println("Exception in downloadtypeQuadro "+ e1.getMessage());
		 		return false;
			 
		 }
		 
	 
	 
 }//method
 

	public static void main(String args[]){
		ManualDriverFlows mdf = new ManualDriverFlows();
		mdf.WHQLLookupsDBGetConnection();
		mdf.createTable();
		mdf.QuadroLookups(url,"Quadro");
		mdf.closeConnection();
		
	}//main method
	
}//class
