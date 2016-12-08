(ns kipsufi.views.country
  (:require [kipsufi.helpers.photos :as ph]
            [kipsufi.svg.europe :as svg]))

(def options {:full-page? false
              :key :country
              :title ":: Photography"
              :css "country"})

(defn pages [const-pages country]
  (let [gallery (last const-pages)]
  (into [{:href (str "/photos/" country) :title country :key :country}]
        (conj (drop-last const-pages) gallery))))

(defn content [country]
  (let [galleries (ph/galleries-for country)]
    [:div.photos-country-page
     [:h1.title country]
     (svg/country-map country galleries)]))
