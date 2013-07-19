
package com.qualcomm.addbookmarks;

//Import the uiautomator libraries
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class AddBookmarks extends UiAutomatorTestCase {

    public void testDemo() throws UiObjectNotFoundException {

        // Simulate a short press on the HOME button.
        getUiDevice().pressHome();

        // We’re now in the home screen. Next, we want to simulate
        // a user bringing up the All Apps screen.
        // If you use the uiautomatorviewer tool to capture a snapshot
        // of the Home screen, notice that the All Apps button’s
        // content-description property has the value “Apps”. We can
        // use this property to create a UiSelector to find the button.
        UiObject allAppsButton = new UiObject(new UiSelector()
                .description("Apps"));

        // Simulate a click to bring up the All Apps screen.
        allAppsButton.clickAndWaitForNewWindow();

        // In the All Apps screen, the Settings app is located in
        // the Apps tab. To simulate the user bringing up the Apps tab,
        // we create a UiSelector to find a tab with the text
        // label “Apps”.
        UiObject appsTab = new UiObject(new UiSelector()
                .text("Apps"));

        // Simulate a click to enter the Apps tab.
        appsTab.click();

        // Next, in the apps tabs, we can simulate a user swiping until
        // they come to the Settings app icon. Since the container view
        // is scrollable, we can use a UiScrollable object.
        UiScrollable appViews = new UiScrollable(new UiSelector()
                .scrollable(true));

        // Set the swiping mode to horizontal (the default is vertical)
//        appViews.setAsHorizontalList();

        // Create a UiSelector to find the Settings app and simulate
        // a user click to launch the app.
        UiObject settingsApp = appViews.getChildByText(new UiSelector()
                .className(android.widget.TextView.class.getName()),
                "Browser");
        settingsApp.clickAndWaitForNewWindow();

        // // Validate that the package name is the expected one
        // UiObject settingsValidation = new UiObject(new UiSelector()
        // .packageName("com.android.settings"));
        // assertTrue("Unable to detect Settings",
        // settingsValidation.exists());

        addBookmark("Baidu", "http://wap.baidu.com");
        addBookmark("Text", "http://218.206.177.209:8080/waptest/fileDownLoad?file=Text&groupname=11&fenzu=WAP2.0");
        addBookmark("Mp3", "http://218.206.177.209:8080/waptest/fileDownLoad?file=mp3&groupname=11&fenzu=WAP2.0");
        addBookmark("Jpg", "http://218.206.177.209:8080/waptest/fileDownLoad?file=Picture&groupname=11&fenzu=WAP2.0");
        addBookmark("3gp", "http://218.206.177.209:8080/waptest/fileDownLoad?file=Video&groupname=11&fenzu=WAP2.0");
        addBookmark("Streaming", "http://211.136.165.53/adapted/terminaltest.jsp?pass=mobiletv");

        exit();
    }

    private void addBookmark(String label, String address) throws UiObjectNotFoundException {
        getUiDevice().pressMenu();
        new UiObject(new UiSelector().text("Bookmarks/History")).click();
        getUiDevice().pressMenu();
        new UiObject(new UiSelector().text("Add bookmark")).click();
        new UiObject(new UiSelector().focused(true)).setText(label);
        getUiDevice().pressEnter();
        new UiObject(new UiSelector().focused(true)).setText(address);
        new UiObject(new UiSelector().text("OK")).click();
        UiObject exitUiO = new UiObject(new UiSelector().textContains("Save to Bookmarks"));
        if (exitUiO.exists()) {
            new UiObject(new UiSelector().text("OK")).click();
        }
        getUiDevice().pressBack();
    }

    private void exit() throws UiObjectNotFoundException {
        getUiDevice().pressMenu();
        new UiObject(new UiSelector().text("Exit")).click();
        new UiObject(new UiSelector().text("Quit")).click();
    }
}
