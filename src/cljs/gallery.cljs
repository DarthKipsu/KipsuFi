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

(defn- display-id []
  (let [id (get (str/split js/window.location.pathname "/") 4)]
    (if id id "1")))

(defn- display-photo! [id]
  (.log js/console id)
  (.log js/console (dom/sel :.gallery-photo))
  (let [photo (first (filter (fn [item] (= id (dom/attr item :data-order))) (dom/sel :.gallery-photo)))]
    (.log js/console photo)
    (dom/remove-class! photo :hidden)))

(if (is-gallery-page-with-thumbnails)
  (do (display-photo! (display-id))
    (println (display-id))))
