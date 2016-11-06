(ns kipsufi.views.photos
  (:require [clojure.java.io :as io]
            [kipsufi.helpers.photos :as ph]
            [kipsufi.svg.europe :as svg])
  (:import java.io.File))

(def options {:full-page? false
              :key :photos
              :title ":: Photography"
              :css "photos"})

(def photo-dir "clj/images/photography/")

(defn ^:private child-dirs-for
  "Reads a directory and returns a list of child directories for that directory."
  [directory]
  (let [children (fn [file] (and (not= (.getName file) (.getName directory))
                                 (.isDirectory file)))]
    (filter children (.listFiles directory))))

(defn ^:private read-directories
  "Returns a list of immediate child directories for a given path."
  [path]
  (let [directory (io/file path)]
    (map (fn [file] (.getName file)) (child-dirs-for directory))))

(defn without-newline [string]
    (if (.endsWith string "\n")
          (.substring string 0 (dec (count string)))
          string))

(defn ^:private read-file
  "Reads the contents of a file with the given name, folder and parent path."
  [path folder file]
  (try
    (without-newline
      (slurp (str path folder "/" file)))
    (catch Exception e "")))

(defn ^:private list-countries 
  "Returns a set of country id's with photographs."
  [directories]
  (reduce (fn [a-set dir] (conj a-set (read-file photo-dir dir "country"))) #{} directories))

(defn content []
  (let [countries (ph/country-list)]
  [:div.photos-page
   [:h1.title "Photography"]
   (svg/europe-map countries)]))
