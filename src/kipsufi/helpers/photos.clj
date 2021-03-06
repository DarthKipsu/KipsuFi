(ns kipsufi.helpers.photos
  (:require [clojure.java.io :as io]
            [clojure.string :as string])
  (:import java.io.File))

; PRIVATE

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
      (slurp (string/trim (str path folder "/" file))))
    (catch Exception e "")))

(defn ^:private gallery-country
  "Reads the country attribute of an individual gallery"
  [gallery]
  (try (read-file photo-dir gallery "country")
    (catch Exception e "")))

(defn ^:private svg-circle
  "Creates svg circle attributes for a given gallery."
  [gallery]
  (let [id (string/replace gallery #" " "")
        coord (string/split (read-file photo-dir gallery "coord") #" ")
        gallerylist (string/split (read-file photo-dir gallery "gallerylist") #",")]
    {:id id
     :title gallery
     :cx (first coord)
     :cy (second coord)
     :r (nth coord 2)
     :gallerylist (if (= gallerylist [""]) [] gallerylist)}))

(defn ^:private directories
  "Returns a list of immediate child directories for a given path."
  [path]
  (let [directory (io/file path)]
    (map (fn [file] (.getName file)) (child-dirs-for directory))))

(defn read-photos
  "Reads a list of photos in a given directory"
  [directory]
  (filter (fn [file] (or (.endsWith (.getName file) ".jpg")
                         (.endsWith (.getName file) ".png")))
          (.listFiles directory)))

(defn photo-object
  [directory gallery photo]
  (let [photo-name (.getName photo)
        id (.substring photo-name 5 (- (count photo-name) 4))]
    {:url (str directory gallery "/" photo-name)
     :thumb (str directory gallery "/thumbs/" photo-name)
     :id (Integer/parseInt id)
     :gallery gallery
     :description (read-file (str "clj" directory) gallery (str "photo" id))}))

; PUBLIC

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
        gallery-attributes (into [] (map svg-circle galleries))]
    gallery-attributes))

(defn photos
  "Returns a list of photos belongin to given gallery."
  [gallery]
  (let [photos (read-photos (io/file photo-dir gallery))]
    (sort-by :id (map (partial photo-object (str (.substring photo-dir 3 (count photo-dir))) gallery) photos))))

(defn next-photo
  "returns the next photo in a gallery given a photo"
  [gallery photo]
  (let [photo-i (read-string photo)
        photos (read-photos (io/file photo-dir gallery))]
    (if (= photo-i (count photos)) 1 (inc photo-i))))

(defn prev-photo
  "Returns the previous photo in gallery givn a photo"
  [gallery photo]
  (let [photo-i (read-string photo)
        photos (read-photos (io/file photo-dir gallery))]
    (if (= photo-i 1) (count photos) (dec photo-i))))
