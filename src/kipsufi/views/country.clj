(ns kipsufi.views.country
  (:require [kipsufi.helpers.photos :as ph]
            [kipsufi.svg.europe :as svg]))

(def options {:full-page? false
              :key :country
              :title ":: Photography"
              :css "country"})

(defn content [country]
  (let [galleries (ph/galleries-for country)]
    [:div.photos-country-page
     [:h1.title country]
     (svg/country-map country galleries)]))
