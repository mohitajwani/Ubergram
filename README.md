# Uber Coding Challenge - Mohit Ajwani
---
The repository contains an Android Application named **Ubergram**. This name was selected 
by merging **Uber** and Insta**gram**.

The application has a very basic UI. No work has been done on the design due to shortage of 
time and a lesser creative mind. The app has the following structure.

* `Ubergram`
* activity
    * `BaseActivity` *(Base class for handling common functionality for all `Activities`)*
    * `HomeActivity`
* adapter
    * `SearchImagesAdapter` *(Adapter class for handling data in the Home Screen)*
* fragment
    * `BaseFragment` *(Base class for handling common functionality for all `Fragments`)*
    * `HomeFragment`
* interfaces
    * `ActivityDestroyedListener`
    * `CurrentActivityListener`
* models
    * `FlickrPhoto` *(Model for Flickr Photo Object)*
    * `SearchPhotosResponse` *(Model for API Response)*
* network
    * api
        * `Api` *(Base class for making API Call)*
        * `GetSearchImagesApi`
    * asyncTask
        * `AsyncTaskListener` *(Listener Interface for handling common functionality for `AsyncTask` callbacks)*
        * `GetSearchImagesAsyncTask`
    * image
        * `FileCache` *(Class for handling File Cache while Loading Images from storage)*
        * `ImageLoader` *(Class for handling Image Loading)*
        * `MemoryCache` *(Class to maintain memory cache while Loading Images via network)*
        * `Utils`
    * parser
        * `SearchImagesApiJSONParser` *(Class for parsing API response)*
* service
    * `PermissionService` *(Class for handling Dynamic Permissions)*
* utils
    * `AsyncTaskUtil`
    * `FragmentUtil`
    * `NetworkUtil`
    
## Probable Improvements for future
* Improve Image Caching Library to use local cache and avoid external storage.
* Use Serialization/Deserialization libraries to avoid writing long parser classes.
* Handle error codes with `Api` class and send meaningful callbacks.
* Improve Design to make it more fluid and follow Material Guidelines.
* Write UI and Unit Test cases. (Will need use of Third Party Libraries, hence avoided)
* Refactor code to follow a Clean Architecture guideline and improve usage of interfaces and dependency injection to avoid tight coupling between classes.