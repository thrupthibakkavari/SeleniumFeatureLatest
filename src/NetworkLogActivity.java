import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v123.network.Network;
import org.openqa.selenium.devtools.v123.network.model.Request;
import org.openqa.selenium.devtools.v123.network.model.Response;

public class NetworkLogActivity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChromeDriver driver = new ChromeDriver();

		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		// this is to enable  listen
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
	
		// to listen the request about to be send
		devTools.addListener(Network.requestWillBeSent(), request->{
			Request req = request.getRequest();
		//	System.out.println(req.getUrl());
			
			
		});
		
		// to receive the response back for already sent request
		// Event will be fired
		devTools.addListener(Network.responseReceived(), response -> {

			Response res = response.getResponse();
		//	System.out.println(res.getUrl());
		//	System.out.println(res.getStatus());
			if(res.getStatus().toString().startsWith("4"))
			{
				System.out.println(res.getStatus());
				System.out.println(res.getUrl()+ "is failing");
			}
			

		});
		
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        driver.close();
        
	}

}
