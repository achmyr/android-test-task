# Pollution History App

This is basic Android app that allows you to get information about pollution at given location. 

## Implementation

Time spent: 3 hrs.

Done using MVVM and Clean architecture.

The project was created from my own github template where the basic skeleton was already in place, 
which gave me great advantage in utilising time efficiently. 

App has 1 Activity with 2 Fragments controlled by JetPack Navigation.
 
## Project structure and key elements
  
`implementation 'com.github.acm0x:core-ktx:0.4'` module is a set of base classes and extensions which I use for my personal projects (it was not implemented as part of this exercise) for simple DRY (don't-repeat-yourself). Sources available here:[Core KTX](https://github.com/acm0x/core-ktx)

`buildsystem` folder is set of gradle scripts, an attempt to make highly dynamic module configs. It was taken from core-ktx project.

`buildSrc/main/java/AppConfig.kt` - main file for App-specific settings like API_KEY and API url.

`app` module package structure:

`contract/` - the contract specifics for the core and application relationship. 

`contract.di` has all the Dagger-related Components and Modules. I didn't try to build feature-oriented component graph, as it is a bit excessive for a simple application. The  such action would be required when features split into separate modules for greater flexibility. 

`feature.pollution` - package with all application logic and presentation. Further structure contains `data`,`domain` and `presentation` - the layers described in Clean architecture.

## Assumptions
Conscious of time, following assumptions were made:

- error handing specific for the application was ommited
- Date formatting was also ommited
- no caching strategies were implemented
- the UI look and feel are available as part of project template
- UI is basic and showcases only 1 item from the pollution components. Further step would be to keep extending model and upate mappers to provide appropriate information to the UI 
- Original templates were done in 2019, but as per requirements to use minSDK=28, no effort was done towards updating template, 
just replaced synthetics with view binding.  
