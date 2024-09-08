# SunflowerClone

A sample project to demonstrate the practices of the Android app architecture illustrated below. 
It is a clone of the Android Sunflower app on Github.

Check the Medium article below for more details.

 [`Android Architecture Showcase: Layered Feature Modular Architecture in Sunflower Clone`](https://medium.com/@b9915034/android-application-architecture-showcase-sunflower-clone-dee729f6e1f2).

 
**Note**: To see the original Sunflower project, checkout the [`Sunflower`](https://github.com/android/sunflower).

## Screenshots

<p float="left">
<img src="docs/screenshots/Screenshot_1.png" width = 360> &nbsp;
<img src="docs/screenshots/Screenshot_2.png" width = 360> &nbsp;
<img src="docs/screenshots/Screenshot_3.png" width = 360> &nbsp;
<img src="docs/screenshots/Screenshot_4.png" width = 360> &nbsp;
</p>

## Architecture Overview

<img src="docs/diagrams/Android App Architecture Overview.png"/>
<img src="docs/diagrams/Sunflower Clone Dependency Graph.png"/>

## Requirements

### Unsplash API key

Same as the original Sunflower, SunflowerClone uses the [Unsplash API](https://unsplash.com/developers) to load pictures on the gallery
screen. You will need to obtain a free developer API key to make the gallery screen work. Please See the
[Unsplash API Documentation](https://unsplash.com/documentation) for instructions.

Once you have your API key, you can add it by replacing the metadata in the manifest in :config:sunflower_clone module.

<img src="docs/screenshots/Screenshot_Unsplash_API_Key.png"/>

## Third Party Content

Select text used for describing the plants (in `plants_9eabcfec0e4b4af18f213dad403f3e47.json`) are used from Wikipedia via CC BY-SA 3.0 US (license in `ASSETS_LICENSE`).

"[seed](https://thenounproject.com/search/?q=seed&i=1585971)" by [Aisyah](https://thenounproject.com/aisyahalmasyira/) is licensed under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/us/legalcode)
