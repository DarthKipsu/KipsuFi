(ns cljs.about
  (:use [dommy.core :only [add-class! sel1]]))

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

(defn- set-visited-class! [country] 
  (let [element (element-by-id country)]
    (if (some? element) (add-class! element :visited))))

(doseq [visit visited-countries] (set-visited-class! (get visit :country)))

