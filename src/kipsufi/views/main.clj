(ns kipsufi.views.main)

(def options {:full-page? true 
              :key :main
              :title ""
              :css "main"})

(defn content []
  [:div.main-page
   [:div.quote "Be the change you wish to see in the world"]
   [:div.author "-MAHATMA GANDHI"]
   [:div.title "darth.kipsu.fi"]])
