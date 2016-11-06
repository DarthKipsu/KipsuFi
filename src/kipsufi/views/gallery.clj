(ns kipsufi.views.gallery
  (:require [kipsufi.helpers.photos :as ph]
            [kipsufi.svg.europe :as svg]))

(def options {:full-page? false
              :key :gallery
              :title ":: Photography"
              :css "gallery"})

(defn content [gallery]
  (let []
    [:div.photos-gallery-page
     [:h1.title gallery]]))
