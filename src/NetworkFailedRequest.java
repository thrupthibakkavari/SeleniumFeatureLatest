import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v123.fetch.Fetch;
import org.openqa.selenium.devtools.v123.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v123.network.model.ErrorReason;


public class NetworkFailedRequest {

	public static void main(String[] args) {

	ChromeDriver driver = new ChromeDriver();

	DevTools devTools = driver.getDevTools();
	devTools.createSession();
	
	
	//java.util.Optional<java.util.List<org.openqa.selenium.devtools.v123.fetch.model.RequestPattern>> patterns
	// java.util.Optional<java.lang.String> urlPattern 
   
	
	
	Optional<List<RequestPattern>> pattern  = Optional.of(Arrays.asList( new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));
	devTools.send(Fetch.enable(pattern, Optional.empty()));
	devTools.addListener(Fetch.requestPaused() , request->{
		devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));	
	});
	
	
	driver.get("https://rahulshettyacademy.com/angularAppdemo");
    driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
	
	
	
	}
	
	
	
	

}
