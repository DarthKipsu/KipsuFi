(ns kipsufi.views.about
  (:require [kipsufi.svg.world :as svg]))

(def options {:full-page? false
              :next-page "/"
              :prev-page "/"
              :title ":: About Me"})

(defn content []
  [:div.about-page
   [:section.intro
     [:img {:src "images/about.jpg"}]
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
          mind at ease."]]
     [:img {:src "images/about.jpg"}]]

   [:section.bits
    [:div.title "Facts"]
    [:div.subtitle "Currently working on"]
    [:div.table
     [:div.tr
      [:div.td "Workplace:"]
      [:div.td [:a {:href "//google.com"} "Google"]]]
     [:div.tr
      [:div.td "Previous work:"]
      [:div.td [:a {:href "//mehackit.org"} "Mehackit"]]]
     [:div.tr
      [:div.td "Education:"]
      [:div.td "Bachelor of Science (Computer Science), University of Helsinki"]]
     [:div.tr
      [:div.td "Previous education:"]
      [:div.td "Bachelor of Engineering (Environmental Engineering), Lahti University of Applied Sciences"]]]

    [:div.subtitle "Contact details"]
    [:div.table
     [:div.tr
      [:div.td "Email:"]
      [:div.td "darth.kipsu@gmail.com"]]
     [:div.tr
      [:div.td "Github:"]
      [:div.td [:a {:href "//github.com/DarthKipsu"} "DarthKipsu"]]]
     [:div.tr
      [:div.td "LinkedIn:"]
      [:div.td [:a {:href "//linkedin.com/in/vernakoskinen"} "Verna Koskinen"]]]
     [:div.tr
      [:div.td "Facebook:"]
      [:div.td [:a {:href "//facebook.com/verna.koskinen"} "Verna Koskinen"]]]
     [:div.tr
      [:div.td "Lives in:"]
      [:div.td "Zürich, Switzerland"]]
     [:div.tr
      [:div.td "From:"]
      [:div.td "Helsinki, Finland"]]]]

   [:section.countries
    [:div.title "Places I've visited"]
    (svg/world-map)]])
