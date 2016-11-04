(ns kipsufi.views.layout
  (:require [hiccup.page :as h]
            [kipsufi.svg.common :as svg]))

(defn ^:private page-index [pages page-key]
  (let [page-keys (map (fn [item] (get item :key)) pages)]
    (.indexOf page-keys page-key)))

(defn ^:private next-pages [pages current-index]
  (let [n (count pages)]
    (for [x (range (inc current-index) (+ current-index n))]
      (get pages (mod x n)))))

(defn ^:private rotated [a-seq]
  (reduce #(conj %1 %2) '() a-seq))

(defn ^:private next-arrow [href title]
  [:a {:href href
       :title title}
   (svg/arrow {:direction "next"})])

(defn ^:private prev-arrow [href title]
  [:a {:href href
       :title title}
   (svg/arrow {:direction "prev"})])

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
        (next-arrow (get (first next-pages) :href) (get (first next-pages) :title)) 
        (prev-arrow (get (first prev-pages) :href) (get (first prev-pages) :title))]
       (h/include-js "//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js")
       (h/include-js "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")
       (h/include-js "/js/script.js")])))
