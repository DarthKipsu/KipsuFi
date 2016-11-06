(ns kipsufi.views.photos
  (:require [kipsufi.helpers.photos :as ph]
            [kipsufi.svg.europe :as svg]))

(def options {:full-page? false
              :key :photos
              :title ":: Photography"
              :css "photos"})

(defn content []
  (let [countries (ph/country-list)]
  [:div.photos-page
   [:h1.title "Photography"]
   (svg/europe-map countries)]))
