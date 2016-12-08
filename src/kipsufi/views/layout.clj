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

(defn ^:private rotated [a-seq options]
  "Rotates the items in the given sequence so they are in opposite order."
  (let [rotated-seq (reduce #(conj %1 %2) '() a-seq)]
    (cond (= (:key options) :country) 
            (let [gallery (first a-seq)] (conj (drop-last rotated-seq) gallery))
          :else rotated-seq)))

(defn ^:private arrow [direction pages]
  "Returns a hiccup component representing a navigation arrow."
  [:div {:class (str "nav " direction)} 
   [:a {:href (get (first pages) :href)
        :title (get (first pages) :title)}
    (svg/arrow {:direction direction})]
   (reduce conj [:div.links] (map (fn [page] [:p [:a {:href (:href page) :title (:title page)}
                                        (:title page)]]) pages))])

(defn common-wrapper [content options pages]
  (let [current-index (page-index pages (get options :key))
        n-pages (next-pages pages current-index)
        p-pages (rotated n-pages options)]
    (println "prev:" p-pages)
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
        (arrow "next" n-pages) 
        (arrow "prev" p-pages)]
       (h/include-js "//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js")
       (h/include-js "//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")
       (h/include-js "/js/script.js")])))
