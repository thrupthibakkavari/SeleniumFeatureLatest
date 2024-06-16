import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v123.fetch.Fetch;

public class NetworkMocking {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
		devTools.addListener(Fetch.requestPaused(), request -> {

			if (request.getRequest().getUrl().contains("=shetty")) {

				String mockUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
				System.out.println(mockUrl);

				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockUrl), Optional.empty(),
						Optional.empty(), Optional.empty(), Optional.empty()));
			} else {

				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.empty(), Optional.empty(),
						Optional.empty(), Optional.empty(), Optional.empty()));
			}

		});

		driver.get("https://rahulshettyacademy.com/angularAppdemo");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.cssSelector("p")).getText());
		driver.close();
	}

}
