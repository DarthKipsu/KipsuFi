(ns kipsufi.views.layout
  (:require [hiccup.page :as h]))

(defn common-wrapper [content title]
  (h/html5
    [:head
     [:meta {:charset "utf-8"}]
     [:title (str "darth.kipsu.fi " title)]
     (h/include-css "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css")]
    [:body
     [:section.content content]
     (h/include-js "//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js")
     (h/include-js "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")]))
