(ns kipsufi.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [kipsufi.views.layout :as layout]
            [kipsufi.views.main :as main]
            [kipsufi.views.about :as about]
            [kipsufi.views.photos :as photos]
            [kipsufi.views.country :as photo-country]
            [kipsufi.views.gallery :as photo-gallery]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as response])
  (:gen-class))

(def pages
  "A list of pages displayed with the navigation arrows on the sides of the page."
  [{:href "/" :title "Main page" :key :main}
   {:href "/about" :title "About me" :key :about}
   {:href "/photos" :title "Photography" :key :photos}])

(defn ^:private gallery-back
  "Allows arrows to be used as back buttons for a gallery"
  [url title a-key]
  [{:key :this}
   {:href url :title title :key a-key}])

(defroutes app-routes
  (HEAD "/" [] "")
  (GET "/" [] (layout/common-wrapper (main/content) main/options pages))
  (GET "/about" [] (layout/common-wrapper (about/content) about/options pages))
  (GET "/photos" [] (layout/common-wrapper (photos/content) photos/options pages))
  (GET "/photos/:country" [country]
       (layout/common-wrapper 
         (photo-country/content country)
         photo-country/options
         (photo-country/pages pages country)))
  (GET "/photos/:country/:gallery" [country gallery]
       (response/redirect (str "/photos/" country "/" gallery "/1") 307))
  (GET "/photos/:country/:gallery/:photo" [country gallery photo]
       (layout/common-wrapper
         (photo-gallery/content gallery) 
         photo-gallery/options
         (photo-gallery/pages pages country gallery photo)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& [port]]
  (jetty/run-jetty app {:port 5000 :join? false}))

