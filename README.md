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
