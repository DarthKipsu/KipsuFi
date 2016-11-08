(ns kipsufi.views.gallery
  (:require [kipsufi.helpers.photos :as ph]))

(def options {:full-page? false
              :key :gallery
              :title ":: Photography"
              :css "gallery"})


(defn single-item [n image]
  [:div.gallery-photo.hidden {:data-order (inc n)}
   [:img {:src (:url image)}]
   [:p (:description image)]])

(defn single-thumb [n image]
  [:img.thumb.faded {:src (:thumb image) :data-order (inc n)}])

(defn as-list [photos]
  (map-indexed single-item photos))

(defn thumbnails [photos]
  (map-indexed single-thumb photos))

(defn content [gallery]
  (let [photos (ph/photos gallery)]
    [:div.photos-gallery-page
     [:h1.title gallery]
     [:div.faces (as-list photos)]
     [:div.thumbs (thumbnails photos)]]))
