(ns cljs.gallery
  (:require [clojure.string :as str]
            [dommy.core :as dom]))

(def has-thumbnails "(min-width: 400px) and (min-height: 200px) and (orientation: landscape)")

(defn- element-by-class [selector]
  (dom/sel1 (keyword (str "." selector))))

(defn- is-gallery-page-with-thumbnails []
  (if (not (nil? (element-by-class "photos-gallery-page")))
    (.-matches (.matchMedia js/window has-thumbnails))
    false))

(if (is-gallery-page-with-thumbnails)
  (do (println "thumbs")))
