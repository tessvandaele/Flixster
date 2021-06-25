# Project 1 - *Flixster*

**Flixster** is an android app that allows a user to view a list of movies currently playing in theatre. The user can also explore additional data 
on each movie such as movie poster, title, overview, rating, and trailer. 

Submitted by: **Tess Van Daele**

Time spent: **10** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **view and scroll through a list of movies** with information such as movie poster, title, and overview
* [x] User can use the app in both **landscape and portrait mode**
* [x] User can view a **placeholder graphic** while the movie images are being loaded
* [x] User's list of movies **remains up to date** through the use of an API to access a movie data base
* [x] User can view a **details page** with more information for the movie that was clicked

The following **bonus** features are implemented:

* [x] **Additional UI features** were added: 
        * rounded edges for images
        * change of color to background and toolbar
        *  movie overview was made scrollable
        * rating number appears next to the rating bar
        * back button was added to the details screen for easier navigation in app
* [x] User can **view movie trailer** within the app. Video is displayed using a **thumbnail** image and an android play icon. 

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='FlixsterDemo.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCAP](https://www.cockos.com/licecap/).

## Notes

Adding the trailer feature became quite complex and I had difficulty parsing the access url correctly. Once I did, however, the video retrieval was similar to the data retrieval accomplished initiallly. The rounded edges feature caused some formatting issues that I had to make sure to handle.

## License

    Copyright [2021] [Tess Van Daele]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
