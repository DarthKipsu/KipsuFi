(ns cljs.about
  (:use [dommy.core :only [add-class! append! create-element create-text-node sel1]]
        [cljs-time.core :only [in-days interval]]))

(enable-console-print!)

(def visited-countries [{:country "AT"
    :name "Austria"
    :visited []}
   {:country "CH"
    :name "Switzerland"
    :visited []}
   {:country "DE"
    :name "Germany"
    :visited []}
   {:country "DK"
    :name "Denmark"
    :visited []}
   {:country "EE"
    :name "Estonia"
    :visited []}
   {:country "ES"
    :name "Spain"
    :visited []}
   {:country "FI"
    :name "Finland"
    :visited []}
   {:country "FR"
    :name "France"
    :visited []}
   {:country "GB"
    :name "United Kingdom"
    :visited []}
   {:country "GR"
    :name "Greece"
    :visited []}
   {:country "IT"
    :name "Italy"
    :visited []}
   {:country "LI"
    :name "Liechenstein"
    :visited []}
   {:country "NO"
    :name "Norway"
    :visited []}
   {:country "SE"
    :name "Sweden"
    :visited []}])

(defn- element-by-id [selector]
  (sel1 (keyword (str "#" selector))))

(defn- element-by-class [selector]
  (sel1 (keyword (str "." selector))))

(defn- set-visited-class! [country] 
  (let [element (element-by-id country)]
    (if (some? element) (add-class! element :visited))))

(defn- paint-visited-countries! []
  (doseq [visit visited-countries] (set-visited-class! (get visit :country))))

(defn- append-element [element class-name]
  (append! (element-by-class class-name) element))

(defn- country-text [country]
  (str (get country :name) ": " (count (get country :visited)) " visits."))

(defn- sum-days [visit-a visit-b]
  (let [days-a (in-days (interval (get visit-a :arrived) (get visit-a :left)))
        days-b (in-days (interval (get visit-b :arrived) (get visit-b :left)))]
    (+ days-a days-b)))
  

(defn- time-spent [visits]
  (let [days (reduce sum-days 0 visits)]
    (str days " days")))


(defn- stayed-for-text [country]
  (str "Stayed for " (time-spent (get country :visited)) " in total."))

(defn- country-info [country]
  (let [div (create-element "div")
        br (create-element "br")
        country-text (create-text-node (country-text country))
        stayed-for-text (create-text-node (stayed-for-text country))]
    (append! div country-text)
    (append! div br)
    (append! div stayed-for-text)
    div))

(defn- append-country-info []
  (doseq [visit visited-countries] (append-element (country-info visit) "mapinfo")))

(println (create-element "div"))

(paint-visited-countries!)
(append-country-info)

