package ru.rbc.kskabort;

import com.google.common.collect.ImmutableMap;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.Response;
import ru.rbc.kskabort.tests.Stupid_joke;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DriverSetting {
    public static void SetNetConnection(int DownloadSpeed, int UploadSpeed, int Latency, ChromeDriver driver) throws IOException {
        CommandExecutor executor = driver.getCommandExecutor();
        Map<String, Serializable> map = new HashMap<>();
        map.put("offline", false);
        map.put("latency", Latency);
        map.put("download_throughput", DownloadSpeed);
        map.put("upload_throughput", UploadSpeed);
        Response r = executor.execute(new Command(driver.getSessionId(), "setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));
    }
    private static ChromeOptions SetDeviceEmulator(String device/*, ChromeDriver driver*/) throws IOException {

        if (device.equals("mobile_def")) {
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", device);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            return chromeOptions;
        }
        else if (device.equals("mobile"))
            return CreateDevice(360, 640, 3.0, "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
        else if (device.equals("ipad"))
            return CreateDevice(768, 1024, 2.0, "Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");
        else throw new Error("Can't create device like this");

    }

    public static ChromeDriver CreateDriver(String device, ChromeDriver driver) throws IOException {
        if (!device.equals("")) {
            return new ChromeDriver(SetDeviceEmulator(device));
        }
        else return new ChromeDriver();
    }

    private static ChromeOptions CreateDevice(int width, int height, double pixelRatio, String userAgent){
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", width);
        deviceMetrics.put("height", height);
        deviceMetrics.put("pixelRatio", pixelRatio);

        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", userAgent);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        return chromeOptions;
    }

}
