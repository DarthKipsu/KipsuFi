(ns kipsufi.views.layout
  (:require [hiccup.page :as h]
            [kipsufi.svg.common :as svg]))

(defn ^:private next-arrow [href]
  [:a {:href href}
   (svg/arrow {:direction "next"})])

(defn ^:private prev-arrow [href]
  [:a {:href href}
   (svg/arrow {:direction "prev"})])

(defn common-wrapper [content options]
  (h/html5
    [:head
     [:meta {:charset "utf-8"}]
     [:title (str "darth.kipsu.fi " (:title options))]
     (h/include-css "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css")
     (h/include-css "/css/common.css")]
    [:body
     [:section {:class (str "content " (if (:full-page? options) "full" "frame"))}
      content 
      (next-arrow (:next-page options)) 
      (prev-arrow (:prev-page options))]
     (h/include-js "//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js")
     (h/include-js "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")
     (h/include-js "/js/script.js")]))
