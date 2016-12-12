# KipsuFi

My personal website version 4 or something. I am aiming for a more simple design this time. You can use it as a base for your own site if you please and build on it.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

## Running locally

Install dependencies:

    lein deps

To start a web server for the application, run:

    lein ring server

To compile less files to css, run:
    
    lein lesscss
    
To automatically compile css on file change, the most robust way is install [inotifywait][] and running:

    while inotifywait -e close_write src/less/common.less; do lein lesscss; done
   
To compile ClojureScript to JavaScript, run:

    lein cljsbuild once

To automatically compile JavaScript use:

    lein cljsbuild auto
    
## Running in prod

You can run on java environment by creating a jar file:

    lein uberjar

Which will create jar files to *target/* which you can use like any java application.

[leiningen]: //github.com/technomancy/leiningen
[inotifywait]: //github.com/rvoicilas/inotify-tools/wiki

## Using photo galleries

Photo galleries are generated from existing folder structure and not included in your uberjar to make it's size more manageable and allowing you to add photos without generating new uberjar each time. You need to do a bit of setup first and after that using them couldn't be easier.

Firstly, create a folder structure in the root of your project folder: *dev-dependencies/public/images/photography/* . You should add all galleries inside this folder as described in more details below in creating gallery section. You should already have a symlink called clj to link to these folders so that the photo api can read it.

You are now ready to use the galleries locally. To use on production, copy all the galleries you have to your server to a folder called *public/images/photography/* (inside your project root).

Then create a symlinks to the root of your project to be able to read the photos and their descriptions:

    ln- s public/ clj

## Creating a photo gallery

Place a new folder in your photography folder in dev-dependencies created in the previous section. The name of the folder will be used as the gallery name, in addition, the structure inside the folder should contain the following items:

- **Photos:** named photo1.jpg, photo2.jpg, ..., photoN.jpg (you can also use .png photos, if you need other types, add them to photos helper method *read-photos*)
- **Photo descriptions:** named photo1, photo2, ..., photoN (optional)
- **Photo thumbnails:** inside the gallery folder, create another folder called *thumbs* and add a thumbnail of each photo there called photo1.jpg, photo2.pg, ..., photoN.jpg (or .png)
- **Gallery description:** Inside a file called info
- **Gallery date:** Inside a file called date
- **Gallery country:** File called country (this will enable that country to be accessed in the map view to select galleries)
- **Gallery map coordinates:** To allow the gallery to be displayed at a correct point in the country map, you need to provide coordinates. Do this inside a file called coord containing: *x y circle-size*

I have not included my galleries in this repo to save space, but there is an example gallery you can check out with two photos.
