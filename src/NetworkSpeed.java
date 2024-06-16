import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v123.network.Network;

public class NetworkSpeed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ChromeDriver driver = new ChromeDriver();
		DevTools devTools= driver.getDevTools();
		devTools.createSession();
		
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.send(Network.emulateNetworkConditions(false, 3000, 2000, 1000, Optional.empty()));
		
		
		devTools.addListener(Network.loadingFailed(), request->{
			System.out.println(request.getErrorText());
			
			
		});
		
		
		
		
		
		long startTime = System.currentTimeMillis();
		driver.get("https://rahulshettyacademy.com/angularAppdemo");
	    driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
	    
	    long endTime = System.currentTimeMillis();
	    
		driver.close();
		System.out.println(endTime - startTime);
	    
	    
	}

}
