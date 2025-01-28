package Engine;


import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import java.io.*;
import java.nio.file.Paths;

public class Utils
{
    public static WebDriver driver;
    // Method to start and stop the Allure server
    public static void startAndStopAllureServe() throws IOException, InterruptedException {
        // Construct the Maven command for running Allure serve
        String mavenCommand = "allure serve \"target/allure-results\"";
        String projectpath = Paths.get("").toAbsolutePath().toString();
        // Start the Allure server process using ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", mavenCommand);
        processBuilder.directory(new java.io.File(projectpath)); // Set project directory

        // Start the Allure server
        Process allureProcess = processBuilder.start();

        // Capture the output from the server process
        BufferedReader reader = new BufferedReader(new InputStreamReader(allureProcess.getInputStream()));

        // Start a thread to read and display the output from the Allure server
        Thread outputThread = new Thread(() -> {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);  // Print the output from the server
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        outputThread.start();

        // Simulate some work or delay (e.g., let the server run for 10 seconds)
        Thread.sleep(10000); // You can adjust the time duration here

        // Stop the Allure server
        if (allureProcess.isAlive()) {
            allureProcess.destroy();  // Gracefully stop the process
            System.out.println("Allure server stopped gracefully.");
        } else {
            System.out.println("No Allure server process to stop.");
        }


    }
//Get Test Data from JSON file
    public static String getTestData(String Data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Parse the JSON file
            JsonNode rootNode = objectMapper.readTree(new File("src/test/java/TestData/e2eTests.json"));

            // Split the key to support dot notation for nested JSON
            String[] keys = Data.split("\\.");
            JsonNode currentNode = rootNode;

            // Traverse through the JSON structure
            for (String k : keys) {
                if (currentNode == null || !currentNode.has(k)) {
                    throw new IllegalArgumentException("Data not found in JSON: " + Data);
                }
                currentNode = currentNode.get(k);
            }

            // Return the value as text if it's a valid node
            if (currentNode != null && currentNode.isValueNode()) {
                return currentNode.asText();
            } else {
                throw new IllegalArgumentException("The key does not point to a valid value: " + Data);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " , e);
        }
    }

}
