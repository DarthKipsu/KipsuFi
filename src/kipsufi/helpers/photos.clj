(ns kipsufi.helpers.photos
  (:require [clojure.java.io :as io])
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

(defn ^:private directories
  "Returns a list of immediate child directories for a given path."
  [path]
  (let [directory (io/file path)]
    (map (fn [file] (.getName file)) (child-dirs-for directory))))

(defn country-list 
  "Returns a set of country id's with photographs."
  []
  (reduce (fn [a-set dir] (conj a-set (read-file photo-dir dir "country")))
          #{} 
          (directories photo-dir)))
