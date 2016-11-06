(ns kipsufi.helpers.photos
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:import java.io.File))

(def photo-dir "clj/images/photography/")

(defn ^:private child-dirs-for
  "Reads a directory and returns a list of child directories for that directory."
  [directory]
  (let [children (fn [file] (and (not= (.getName file) (.getName directory))
                                 (.isDirectory file)))]
    (filter children (.listFiles directory))))

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

(defn ^:private gallery-country
  "Reads the country attribute of an individual gallery"
  [gallery]
  (read-file photo-dir gallery "country"))

(defn ^:private svg-circle
  "Creates svg circle attriutes for a given gallery."
  [gallery]
  (let [id (str/replace gallery #" " "")
        coord (str/split (read-file photo-dir gallery "coord") #" ")]
    {:id id
     :title gallery
     :cx (first coord)
     :cy (second coord)
     :r "0.2"}))

(defn ^:private directories
  "Returns a list of immediate child directories for a given path."
  [path]
  (let [directory (io/file path)]
    (map (fn [file] (.getName file)) (child-dirs-for directory))))

(defn country-list 
  "Returns a set of country id's with photographs."
  []
  (reduce (fn [a-set dir] (conj a-set (gallery-country dir)))
          #{} 
          (directories photo-dir)))

(defn galleries-for
  "Returns gallery info for a country"
  [country]
  (let [directory-list (directories photo-dir)
        galleries (filter (fn [gallery] (= country (gallery-country gallery))) directory-list)
        gallery-attributes (into [] (map svg-circle directory-list))]
    gallery-attributes))
