package com.bq.qa.pareto.apps.server;

import com.bq.qa.pareto.apps.ParetoApp;
import com.bq.qa.pareto.apps.config.ParetoAppConfig;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ServerFactory {
    public static final String IOS = "ios";
    public static final String ANDROID = "android";

    public static AppiumServer initServer(String UDID, String SO){
        if(SO.equals(ANDROID)){
            return new AppiumServer(getAppiumCapabilities(ParetoApp.getAndroidConfig()),UDID);
        }else{
            return new AppiumServer(getAppiumCapabilities(ParetoApp.getIOSConfig()),UDID);
        }

    }

    /**
     * Gets the Appium desired capabilities read from the properties file
     *
     * @return desired capabilities instance
     */
    public static DesiredCapabilities getAppiumCapabilities(ParetoAppConfig paretoAppConfig) {
        DesiredCapabilities appiumDesiredCapabilities = new DesiredCapabilities();
        setUpAppiumCap(appiumDesiredCapabilities,paretoAppConfig);
        return appiumDesiredCapabilities;
    }

    private static void setUpAppiumCap(DesiredCapabilities capabilities,ParetoAppConfig paretoAppConfig){
        setCapability(capabilities, MobileCapabilityType.AUTOMATION_NAME, paretoAppConfig.appium_automationName());
        setCapability(capabilities, MobileCapabilityType.APP, paretoAppConfig.binary_path());
        setCapability(capabilities, MobileCapabilityType.BROWSER_NAME, paretoAppConfig.appium_browserName());
        setCapability(capabilities, MobileCapabilityType.NEW_COMMAND_TIMEOUT, paretoAppConfig.appium_newCommandTimeout());
        setCapability(capabilities, MobileCapabilityType.LOCALE, paretoAppConfig.appium_locale());
        setCapability(capabilities, MobileCapabilityType.ORIENTATION, paretoAppConfig.appium_orientation());
        setCapability(capabilities, MobileCapabilityType.AUTO_WEBVIEW, paretoAppConfig.appium_autoWebview());
        setCapability(capabilities, MobileCapabilityType.NO_RESET, paretoAppConfig.appium_noReset());
        setCapability(capabilities, MobileCapabilityType.FULL_RESET, paretoAppConfig.appium_fullReset());
    }

    private static void setCapability(DesiredCapabilities cap, String key, Object value){
        if(value!=null){
            cap.setCapability(key,value);
        }
    }
}