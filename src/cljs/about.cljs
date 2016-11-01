(ns cljs.about
  (:use [dommy.core :only [add-class! append! create-element create-text-node sel1]]
        [cljs-time.core :only [date-time in-days interval now]]))

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
    :visited [{:arrived (date-time 1985 4 28) :left (date-time 2010 1 14)}
              {:arrived (date-time 2010 1 16) :left (date-time 2010 7 10)}
              {:arrived (date-time 2010 7 11) :left (date-time 2010 7 20)}
              {:arrived (date-time 2010 7 26) :left (date-time 2011 1 16)}
              {:arrived (date-time 2011 1 23) :left (date-time 2011 7 23)}
              {:arrived (date-time 2011 7 30) :left (date-time 2011 9 4)}
              {:arrived (date-time 2011 9 6) :left (date-time 2012 4 7)}
              {:arrived (date-time 2012 4 13) :left (date-time 2012 7 15)}
              {:arrived (date-time 2012 7 18) :left (date-time 2012 8 19)}
              {:arrived (date-time 2012 8 20) :left (date-time 2013 6 3)}
              {:arrived (date-time 2013 6 3) :left (date-time 2013 9 27)}
              {:arrived (date-time 2013 9 29) :left (date-time 2014 5 2)}
              {:arrived (date-time 2014 5 2) :left (date-time 2014 6 20)}
              {:arrived (date-time 2014 6 21) :left (date-time 2014 7 17)}
              {:arrived (date-time 2014 7 18) :left (date-time 2015 7 4)}
              {:arrived (date-time 2015 9 26) :left (date-time 2016 2 22)}
              {:arrived (date-time 2016 2 24) :left (date-time 2016 5 14)}
              {:arrived (date-time 2016 8 27) :left (date-time 2016 9 12)}
              {:arrived (date-time 2016 9 13) :left (now)}]}
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

(defn- days-in-visit [visit]
  (if (nil? visit) 0
    (if (integer? visit) visit
      (in-days (interval (get visit :arrived) (get visit :left))))))

(defn- sum-days [visit-a visit-b]
  (+ (days-in-visit visit-a) (days-in-visit visit-b)))
  

(defn- time-spent [visits]
  (println visits)
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

