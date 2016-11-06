(ns cljs.country
  (:use [dommy.core :only [add-class!
                           append! 
                           create-element 
                           create-text-node 
                           listen! 
                           remove-class! 
                           sel1]]))

(enable-console-print!)

(defn- element-by-class [selector]
  (sel1 (keyword (str "." selector))))

(defn- ^:private svg-view-box []
  (let [svg (sel1 "svg")
        bbox (.getBBox svg)
        viewBox (str (.-x bbox) " " (.-y bbox) " " (.-width bbox) " " (.-height bbox))]
    (.setAttribute svg "viewBox" viewBox)))

(if (not (nil? (element-by-class "photos-country-page")))
  (svg-view-box))
