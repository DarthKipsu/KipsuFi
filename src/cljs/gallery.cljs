(ns cljs.gallery
  (:require [clojure.string :as str]
            [dommy.core :as dom]))

(enable-console-print!)

(def has-thumbnails "(min-width: 400px) and (min-height: 200px) and (orientation: landscape)")

(defn- element-by-class [selector]
  (dom/sel1 (keyword (str "." selector))))

(defn- is-gallery-page-with-thumbnails []
  (if (not (nil? (element-by-class "photos-gallery-page")))
    (.-matches (.matchMedia js/window has-thumbnails))
    false))

(defn- display-id-from-pathname []
  (let [id (get (str/split js/window.location.pathname "/") 4)]
    (if id id "1")))

(def displaying (atom (display-id-from-pathname)))

(defn- photo-by-order [order]
  (first (filter (fn [item] (= order (dom/attr item :data-order)))
                   (dom/sel :.gallery-photo))))

(defn- display-photo! [id]
  (let [prev-display (photo-by-order @displaying)
        photo (photo-by-order id)]
    (reset! displaying id)
    (dom/add-class! prev-display :hidden)
    (dom/remove-class! photo :hidden)
    (if (is-gallery-page-with-thumbnails) (.replaceState js/window.history {} "" id))))

(defn- add-thumbnail-listeners! []
  (let [thumbs (dom/sel :.thumb)]
    (doseq [thumb thumbs]
      (dom/listen! thumb :click (fn [e] (display-photo! (dom/attr (.-target e) :data-order)))))))

(if (is-gallery-page-with-thumbnails)
  (do (display-photo! (display-id-from-pathname))
    (add-thumbnail-listeners!)))
