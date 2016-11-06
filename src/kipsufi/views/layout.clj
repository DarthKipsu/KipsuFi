(ns kipsufi.views.layout
  (:require [hiccup.page :as h]
            [kipsufi.svg.common :as svg]))

(defn ^:private page-index [pages page-key]
  "Returns the index of the current page in the pages list, based on the page :key option."
  (let [page-keys (map (fn [item] (get item :key)) pages)
        current-index (.indexOf page-keys page-key)]
    (if (neg? current-index) 0 current-index)))

(defn ^:private next-pages [pages current-index]
  "Returns a sequence containing all the pages in order following the given page index, not
  including the current page itself. Pages that are in order behind the current index will be
  moved to the end of the list, maintaining the original order."
  (let [n (count pages)]
    (for [x (range (inc current-index) (+ current-index n))]
      (get pages (mod x n)))))

(defn ^:private rotated [a-seq]
  "Rotates the items in the given sequence so they are in opposite order."
  (reduce #(conj %1 %2) '() a-seq))

(defn ^:private arrow [direction href title]
  "Returns a hiccup component representing a navigation arrow."
  [:a {:href href
       :title title}
   (svg/arrow {:direction direction})])

(defn common-wrapper [content options pages]
  (let [current-index (page-index pages (get options :key))
        next-pages (next-pages pages current-index)
        prev-pages (rotated next-pages)]
    (h/html5
      [:head
       [:meta {:charset "utf-8"}]
       [:title (str "darth.kipsu.fi " (:title options))]
       (h/include-css "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css")
       (h/include-css "/css/common.css")
       (h/include-css (str "/css/" (:css options) ".css"))]
      [:body
       [:section {:class (str "content " (if (:full-page? options) "full" "frame"))}
        content 
        (arrow "next" (get (first next-pages) :href) (get (first next-pages) :title)) 
        (arrow "prev" (get (first prev-pages) :href) (get (first prev-pages) :title))]
       (h/include-js "//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js")
       (h/include-js "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")
       (h/include-js "/js/script.js")])))
