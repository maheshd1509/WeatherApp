## Description
This project main purpose to show detail of weather bases on US city by openweathermap.org APIs.

# Weather check app
<!-- to comment use such block-->
<!--[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)-->

<p align="center">
  <a href="https://android-arsenal.com/api?level=21" target="_blank" rel="noopener noreferrer">
    <img src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/>
  </a>
</p>

<p float="left">
<img src="https://user-images.githubusercontent.com/41982681/232591999-cbbff67f-8bb5-4c76-a81e-b23a4ac48c3d.png" width="260" height="400"/>
<img src="https://user-images.githubusercontent.com/41982681/232592177-90ace5c8-6a5b-4e7a-aac2-ed15234739c7.png" width="260" height="400"/>
<img src="https://user-images.githubusercontent.com/41982681/232592232-a026c280-24ec-437c-be2d-dc2040460fd5.png" width="260" height="400"/>
<img src="https://user-images.githubusercontent.com/41982681/232592366-df26427b-51dc-4734-b785-6390e5b3c336.png" width="260" height="400"/>
</p>  


## API
openweathermap.org APIs
* http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid={API key}(Geo API)
* https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid={API key}
* https://api.openweathermap.org/data/2.5/forecast?lat=44.34&lon=10.99&appid={API key}

## Tech stack
* Minimum SDK level 21
* [Kotlin](https://kotlinlang.org/) based + Coroutines for asynchronous.
* Dagger for dependency injection.
* JetPack
  * LiveData - notify domain layer data to views.
  * Lifecycle - dispose of observing data when lifecycle state changes.
  * ViewModel - UI related data holder, lifecycle aware.
  * Navigation Component - handle everything needed for in-app navigation.
* Architecture
  * MVVM Architecture (View- ViewModel - Model)
  * Repository pattern
* [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and fetch data from network.
* mockito-test library




