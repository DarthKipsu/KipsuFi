(ns kipsufi.views.about)

(def options {:full-page? false
              :next-page "/"
              :prev-page "/"
              :title ":: About Me"})

(defn content []
  [:div.about-page
   [:section.intro
    [:div.left
     [:div.title "About me"]]
    [:div.right
     [:img {:src "images/about.jpg"}]]]])
