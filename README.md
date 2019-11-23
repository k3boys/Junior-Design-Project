# Pave the Way

The task of improving communities is one that requires a great amount of research and planning. To execute this planning, local governments and community leaders first need reliable data to be able to understand the issues they are trying to address. Currently, there is no product on the market that specifically addresses gathering and visualizing this kind of data. Auditors have to manually collect data, and there is no possibility of crowdsourcing this collection. Our team hopes to change that. 

We are building a product to help measure the extent to which lower income communities need assistance by allowing the benchmarking of relevant street-level data, such as property condition, within these communities.

Our solution is an Android application that allows for easy data collection by the Hampton Roads community in Virginia.


## Release Notes

### Features for this release

Our application contains all of the features established in our MVP. Users can select a map location either by scrolling with their finger or entering an address manually. From there, they are able to fill out a detailed report of an address and upload it to a remote database.


### Bug fixes made since last release

* Fixed pin positioning in map view
* Address is now set to whichever address it is centered on when opened
* Fixed application crashing whenever no recent location is obtained
* Adjustments to UI sizing for different screen sizes


### Known Bugs

There are currently no known bugs associated with this release, but if you happen to run into any, please feel free to let us know.


## Install Guide

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.


### Prerequisites

What things you need to install the software and how to install them

* [Android Studio](https://developer.android.com/studio?gclid=Cj0KCQiAq97uBRCwARIsADTziyZEZ2PZB4OKuq5xM_3KZ0mIHQL_-N79WouMa_swFTMs8o5ecp386bcaAlEPEALw_wcB) - version 3.5 or higher.

* [Android Debug Bridge](https://developer.android.com/studio/releases/platform-tools.html) - Any current version should work. Make sure to add ADB to your system path as well


### Dependencies

* [Gradle](https://gradle.org/) - 5.4 or higher.


### Download Instructions

Simply download the project from this repository or follow [this](https://github.com/k3boys/Junior-Design-Project/archive/master.zip) link. Then, simply unzip the project in your desired directory.


### Build Instructions

* Launch Android Studio
* Navigate to File > Settings > Gradle
* Make sure that "Use default Gradle wrapper" is selected
* Navigate to Build > Make Project
* Press "Make Project"
* Navigate to Build > Build Bundle(s) / APK (s) > Build APK (s)
* Press "Build APK (s)"


### Installation

* Open your device's Settings application
* Select "About <device>"
* Tap the build number 7 times. This will make "Developer Options" available
* Enable the "USB Debugging" option
* Navigate to your "Security" and check "Unknown Sources" to allow installation of apps not from the Google Play Store.
* Connect your android device via USB
* Use "adb install <file>" where file is the name of your apk file


### Run Instructions

Now, just navigate to the apps location and select it to run.


## Authors

* **Rodrigo Pontes**
* **Kyle Evoy**
* **Kyle Perras**
* **Arthur Lazzaretti**
* **Fernando Mello**
* **Rafael Hanashiro**