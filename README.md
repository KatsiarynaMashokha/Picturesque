# Picturesque App
####  Picturesque is an Android App is written in Java. Published on 11/9/2017.
#### By **Katsiaryna Mashokha**
## Description
Have you ever wondered what pictures were taken around your current location? Or at any location? Picturesque app will help you to answer this question:
just drop a pin in the location that interests you, press the button, and you see the pictures! The app is extremely simple to use.


## Setup/Installation Requirements
Download or clone the following project from the gitHub by tapping "Download" or using 'git clone' from the terminal. Then run it on Android studio emulator or on your Android device.
On order to successfully run the application, you are required to use the following API keys:
1) Google Maps API key (https://developers.google.com/maps/documentation/android-api/signup). The key needs to be places in AndroidManifest.xml file
right below the following line: 'android:name="com.google.android.geo.API_KEY"'
2) Flickr API key (https://www.flickr.com/services/api/misc.api_keys.html). The key need to be placed into build.gradle (Module: app) file
into the following section  buildTypes.each {it.buildConfigField 'String', 'FLICKR_API_KEY', FlickrApiKey} instead of FlickrApiKey.

## Support and contact details
For any concerns or questions email to: katsiarynamashokha@gmail.com

### License
Copyright (c) 2017 **_Katsiaryna Mashokha_**