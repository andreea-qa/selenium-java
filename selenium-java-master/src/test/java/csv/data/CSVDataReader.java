package csv.data;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader {
    WebDriver driver;

    public static String[][] readCsvFile() throws IOException{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/java/csv/data/Users.csv"), ',', '"', 1);
            String[] line;
            List<String[]> userList = new ArrayList<>();
            while((line = csvReader.readNext())!=null)
            {
                userList.add(line);
            }
            int rows = userList.size();
            int cols = userList.get(0).length;
            String[][]  dataFromCSV = new String[rows][cols];
            for (int i=0;i<rows;i++) {
                String[] eachRow = userList.get(i);
                for (int j=0;j<cols;j++) {
                    dataFromCSV[i][j]=eachRow[j];
                }
            }
            return dataFromCSV;
    }

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @MethodSource("readCsvFile")
    @ParameterizedTest
    public void loginTest(String userName, String password){
        driver.findElement(By.id("user-name")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
    }
}