(ns cljs.about
  (:require [domina :as dom]
            [domina.css :as css]))

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

(defn set-colors! [country] 
  (println country)
  (dom/set-styles! (css/sel (str "#" country)) {:fill "red"}))

(doseq [visit visited-countries] (set-colors! (get visit :country)))

