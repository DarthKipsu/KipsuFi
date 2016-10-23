(ns kipsufi.views.about)

(def options {:full-page? false
              :next-page "/"
              :prev-page "/"
              :title ":: About Me"})

(defn content []
  [:div.about-page
   [:section.intro
    [:div.left
     [:div.title "About me"]
     [:div.about 
      [:p "I'm Verna Koskinen, a hacker, builder and explorer. I have a previous degree in 
          environmental engineering, but discovered programming during my winter vacation in 2013 
          and have been knocking about the field ever since."]
      [:p "Currently I'm most interested in learning to build interactive gadgets and making them
          behave using machine learning and other programmatic sorcery."]
      [:p "When not working or learning more about coding,
          I like to get outside with my dog and spouse and discover things within my new home
          country in Switzerland. Nature is really important to me and I think caring for
          the environment and spending time in the nature is one of the best ways to keep ones
          mind at ease."]]]
    [:div.right
     [:img {:src "images/about.jpg"}]]]
   [:section.bits
    [:div.title "Facts"]]])
