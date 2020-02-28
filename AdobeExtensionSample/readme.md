
# ForeSee Adobe Extension Sample

## Prerequisites

To run this Sample, you need a [Adobe Experience Platform Launch](https://www.adobe.com/experience-platform/launch.html) account, and have set it up to use in mobile Apps.  

## Installing
1.  In the Adobe Launch dashboard, create a new Property for Mobile platform.
2.  Find and install the ForeSee SDK from the extension category. In the Configuration page, you can choose to enable `Debug Logging` and `Skip Pooling Check`<br/>
     ![Configuration](https://raw.githubusercontent.com/foreseecode/foresee-sdk-android-samples/MOBILSDK-2750/AdobeExtensionSample/docresources/configuration.png)
3.  Create a Rule  
    Set the rule to trigger the ForeSee extension's Action: `ForeSee - Check Eligibility`. This will allow ForeSee SDK to check if the user is eligible for a survey. As below:<br/>
    ![Rule](https://raw.githubusercontent.com/foreseecode/foresee-sdk-android-samples/MOBILSDK-2750/AdobeExtensionSample/docresources/rule.png)
4.  Create an Environment.
5.  In Publishing, create a Library.
6.  Add the Rule you created, and make sure the ForeSee extension is installed. Save and Build.
7.  Follow the development environment mobile install instructions.
8.  In the `SampleApplication` class, replace the AppID in  `MobileCore.configureWithAppID("launch-your-appID")` with your own app ID.

## Running the Sample

After launching the sample app, background / foreground the app three times to trigger a CXMeasure invite. You can also update the `foresee_configuration.json` to change the trigger criteria.
