(ns kipsufi.views.country
  (:require [clojure.java.io :as io]
            [kipsufi.svg.europe :as svg])
  (:import java.io.File))

(def options {:full-page? false
              :key :country
              :title ":: Photography"
              :css "photos"})

(defn content [country]
  (let []
  [:div.photos-country-page
   [:h1.title country]
   (svg/country-map country)]))
