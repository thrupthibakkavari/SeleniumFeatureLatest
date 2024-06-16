import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v123.emulation.Emulation;

public class MobileEmulatorTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeDriver driver = new ChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		////// send commiandds to cdp

		devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, java.util.Optional.empty(),
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(),
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(),
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));

		driver.get("https://rahulshettyacademy.com/angularAppdemo");
		driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Library")).click();

		driver.close();

	}

}
