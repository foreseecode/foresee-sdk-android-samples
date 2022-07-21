# Android SDK Samples

To run these samples, you'll need to import the project in Android studio. First of all, clone the repo

```
git checkout git@github.com:foreseecode/foresee-sdk-android-samples.git
```

Then import the project to Android Studio as described below...

* Load Android Studio

* Select 'File' > 'New' > 'Import Project...' and browse to the location of your new git repo

* Select the repo root folder and click 'OK'

* Android Studio will automatically import the project

The project contains the following examples, all of which can be installed using a similar set of steps:

* Select the module of your choice (see below for examples)

* Run the project by pressing Android Studio's 'Play' button


---

## Advanced Sample

This example shows some more advanced features of the SDK, showcasing different invitation criteria and an example of a custom invitation.

---

## Basic Sample

This sample showcases the simplest implementation of the SDK.

---

## Contact Survey

This simple example shows an app using the CONTACT invitation mode. 

---

## Custom Invite

This example shows how to use custom UI for a survey invite. Two types of custom invitation are shown using the custom invite API provided in the SDK. Also shown is a default CONTACT invite that allows the user to skip the second invitation page by adding contact details.

---

## Feedback Sample

This example shows how to use the Feedback module. 

---

## Kotlin Sample

This example shows how to use the SDK in a kotlin project, and is equivalent to the BasicSample project.

---

## Localization Sample

This example shows how to use localization in the SDK. It's possible not only to use localization in support of different languages, but also to modify the copy used within the invitation process.

---

## Replay Sample

This example shows how to use Replay module. The recording is started using the request recording API provided in the SDK. Also shown is the mask view API provided by the SDK. This mask view is demonstrated on an imageview.

---

## Authenticating with the package repository

You'll need to authenticate with GitHub Packages to download our library. To do so, you'll need a personal key which can be generated from your GitHub account.

To generate the personal key follow these steps;

1) Verify your email address, if it hasn't been verified yet.
2) In the upper-right corner of any page, click your profile photo, then click Settings.
3) In the left sidebar, click  Developer settings.
4) In the left sidebar, click Personal access tokens.
5) Click Generate new token.
6) Give your token a descriptive name.
7) To give your token an expiration, select the Expiration drop-down menu, then click a default or use the calendar picker.
8) Select the scopes, or permissions, you'd like to grant this token. To use your token to access repositories from the command line, select repo. (The token will need the read:packages permission)
9) Click Generate token.

Once you have that key, you should set 2 environment variables on your machine: GITHUB_USERNAME for your username, and GITHUB_PERSONAL_KEY for your personal key - these will be used in the next step

Add the following code to your build.gradle file(s):

```allprojects {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/foreseecode/public-packages")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_PERSONAL_KEY")
            }
        }
    }
}

dependencies {
    implementation 'com.foresee.sdk:cxmeasure:+'
    implementation 'com.foresee.sdk:feedback:+'  // Add this line if you wish to use the Feedback Survey feature
    implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.4'
    implementation 'androidx.appcompat:appcompat:1.0.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
}```



