(ns kipsufi.views.gallery
  (:require [kipsufi.helpers.photos :as ph]))

(def options {:full-page? false
              :key :gallery
              :title ":: Photography"
              :css "gallery"})

(defn pages [const-pages country gallery photo]
  (let [gallery-page (last const-pages)
        rest-pages (drop-last const-pages)
        this-page {:href (str "/photos/" country "/" gallery "/" photo) :title gallery :key :gallery}
        next-page {:href "#" :title "Next photo" :key :next}
        prev-page {:href "#" :title "Previous photo" :key :prev}
        country-page {:href (str "/photos/" country) :title country :key :country}]
    (conj [] this-page next-page country-page gallery-page (first rest-pages) (second rest-pages) prev-page)))

(defn single-item [n image]
  [:div.gallery-photo.hidden {:data-order (inc n)}
   [:img {:src (:url image)}]
   [:p.description (:description image)]])

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
