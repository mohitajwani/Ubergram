# Uber Coding Challenge - Mohit Ajwani
---
The repository contains an Android Application named **Ubergram**. This name was selected 
by merging **Uber** and Insta**gram**.

The application has a very basic UI. No work has been done on the design due to shortage of 
time and a lesser creative mind. The app has the following structure.

* `Ubergram`
* activity
    * `BaseActivity`
    * `HomeActivity`
* adapter
    * `SearchImagesAdapter`
* fragment
    * `BaseFragment`
    * `HomeFragment`
* interfaces
    * `ActivityDestroyedListener`
    * `CurrentActivityListener`
* models
    * `FlickrPhoto`
    * `SearchPhotosResponse`
* network
    * api
        * `Api`
        * `GetSearchImagesApi`
    * asyncTask
        * `AsyncTaskListener`
        * `GetSearchImagesAsyncTask`
    * image
        * `FileCache`
        * `ImageLoader`
        * `MemoryCache`
        * `Utils`
    * parser
        * `SearchImagesApiJSONParser`
* service
    * `PermissionService`
* utils
    * `AsyncTaskUtil`
    * `FragmentUtil`
    * `NetworkUtil`