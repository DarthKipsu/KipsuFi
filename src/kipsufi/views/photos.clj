(ns kipsufi.views.photos
  (:require [kipsufi.svg.world :as svg]))

(def options {:full-page? false
              :next-page "/"
              :prev-page "/about"
              :title ":: Photography"})

(defn content []
  [:div.photos-page])
