(ns kipsufi.views.photos
  (:require [kipsufi.svg.europe :as svg]))

(def options {:full-page? false
              :key :photos
              :title ":: Photography"
              :css "photos"})

(defn content []
  [:div.photos-page
   [:h1.title "Photography"]
   (svg/europe-map)])
