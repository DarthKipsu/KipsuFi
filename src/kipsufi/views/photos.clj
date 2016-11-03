(ns kipsufi.views.photos
  (:require [kipsufi.svg.europe :as svg]))

(def options {:full-page? false
              :next-page "/"
              :prev-page "/about"
              :title ":: Photography"
              :css "photos"})

(defn content []
  [:div.photos-page
   (svg/europe-map)])
