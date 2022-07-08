package mylava.update.retailer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.TreeMap;

public class Retailer_update4 {

    public static void main(String[] args) throws IOException {

        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        // reading CSV file
        Map<String, String> retailer_details = CSV_File_Reader.ReadCsv();

        // setting the chrome driver exe file path
        //WebDriverManager.chromedriver().setup();
        Driver_load.browser("chrome");
        // creating and lunching the chrome browser
        WebDriver driver = new ChromeDriver();

        // entering the url to search in chrome
        driver.get("https://mylava.indlava.com/login");
        // this will maximize the window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // login with mylava nhq admin
        WebElement useridInput = driver.findElement(By.id("userid"));
        useridInput.sendKeys("nhqadmin");
        WebElement passInput = driver.findElement(By.name("pass"));
        passInput.sendKeys("nhqadmin");
        WebElement btnLogin = driver.findElement(By.id("btnLogin"));
        btnLogin.click();

        // getting the value of map one by one
        int i = 1;
        for (Map.Entry<String, String> entry : retailer_details.entrySet()) {
            // getting the retailer update window
            try {
                driver.get("https://mylava.indlava.com/UpdateUser/update_mobile");
                String incident = entry.getKey();
                String r_code_mobile = entry.getValue();
                String[] code_mobile = r_code_mobile.split(" ");
                System.out.println("incident id=" + incident + " code=" + code_mobile[0] + " mobile=" + code_mobile[1]);

                WebElement inputRetailer = driver.findElement(By.id("retailer_code"));
                // inputing retailer code
                inputRetailer.sendKeys(code_mobile[0]);
                // searching retailer edit_btn
                WebElement btn_submit = driver.findElement(By.id("btnSubmit"));
                btn_submit.click();
                WebElement btn_edit = driver.findElement(By.id("edit_btn"));
                btn_edit.click();

                // retailer_mobile_no
                WebElement inputRetailer_mobile = driver.findElement(By.id("retailer_mobile_no"));
                // inputing retailer mobile number and clestNGar the previous number
                inputRetailer_mobile.clear();
                inputRetailer_mobile.sendKeys(code_mobile[1]);// update_no
                WebElement btn_update_no = driver.findElement(By.id("update_no"));
                btn_update_no.click();
                driver.switchTo().alert().accept();
                WebElement update_messeses = driver.findElement(By.className("alert"));
                String messege = update_messeses.getText();

                System.out.println((i) + "-- key-" + entry.getKey() + "" + "value-" + entry.getValue() + " " + messege);

                // Adding message into arraylist

                data.put(String.valueOf(i), new Object[]{entry.getKey(), entry.getValue(), messege});

                /*----------------- closing ticket-------------*/

                driver.get("https://mylava.indlava.com/AssignedIncidents/open");
                WebElement incident_search_box = driver.findElement(By.id("incident_id"));
                incident_search_box.sendKeys(incident);
                // btnSearch
                WebElement incident_search_btn = driver.findElement(By.id("btnSearch"));
                incident_search_btn.click();
                // fa-eye
                WebElement incident_eye_btn = driver.findElement(By.className("fa-eye"));
                incident_eye_btn.click();

                // comments
                WebElement incident_textarea = driver.findElement(By.id("comments"));
                incident_textarea.sendKeys(messege);

                // resolve
                WebElement resolve_btn = driver.findElement(By.id("resolve"));
                resolve_btn.click();
                i++;

            } catch (Exception e) {
                System.out.println((i) + "-- key-" + entry.getKey() + "" + "value-" + entry.getValue() + " " + "invalid retailer code");
                // Adding message into arraylist
                data.put(String.valueOf(i), new Object[]{entry.getKey(), entry.getValue(), "invalid retailer code"});

            }

        }
        // writing output message into the file
        XLSXWriter.write_data_into_sheet(data);
        driver.close();
        driver.quit();

    }

}
