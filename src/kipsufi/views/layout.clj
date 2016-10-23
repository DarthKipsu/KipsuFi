(ns kipsufi.views.layout
  (:require [hiccup.page :as h]
            [kipsufi.svg.common :as svg]))

(defn common-wrapper [content options]
  (h/html5
    [:head
     [:meta {:charset "utf-8"}]
     [:title (str "darth.kipsu.fi " (:title options))]
     (h/include-css "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css")
     (h/include-css "/css/common.css")]
    [:body
     (if (:full-page? options) [:section.content.full 
                                content 
                                (svg/arrow {:rotated "0" :direction "next"}) 
                                (svg/arrow {:rotated "180" :direction "prev"})]
       [:section.content.frame 
        content 
        (svg/arrow {:rotated "90" :direction "next"}) 
        (svg/arrow {:rotated "270" :direction "prev"})])
     (h/include-js "//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js")
     (h/include-js "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")]))
