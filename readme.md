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

To download the SDK for the samples, you'll need to be set up to access GitHub Packages (see [**Authenticating with the package repository**](#authenticating-with-the-package-repository))

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

## Digital Sample

This example shows how to use the Digital module. 

---

## Kotlin Sample

This example shows how to use the SDK in a kotlin project, and is equivalent to the BasicSample project.

---

## Localization Sample

This example shows how to use localization in the SDK. It's possible not only to use localization in support of different languages, but also to modify the copy used within the invitation process.

---

## DBA Sample

This example shows how to use DBA module. The recording is started using the request recording API provided in the SDK. Also shown is the mask view API provided by the SDK. This mask view is demonstrated on an imageview.

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

Once you have that key, you should add the credentials to your local.properties file:
```
github.user=<YOUR_GITHUB_USERNAME>
github.key=<YOUR_GITHUB_KEY>
```

Those credentials will be picked up by the following lines in the sample project's [build.gradle](./build.gradle) file
```
allprojects {
    Properties properties = new Properties()
    properties.load(project.rootProject.file('local.properties').newDataInputStream())

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/foreseecode/public-packages")
            credentials {
                username = properties.getProperty('github.user')
                password = properties.getProperty('github.key')
            }
        }
    }
}
```



