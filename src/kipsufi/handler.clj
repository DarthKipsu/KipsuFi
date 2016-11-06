(ns kipsufi.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [kipsufi.views.layout :as layout]
            [kipsufi.views.main :as main]
            [kipsufi.views.about :as about]
            [kipsufi.views.photos :as photos]
            [kipsufi.views.country :as photo-country]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:gen-class))

(def pages
  "A list of pages displayed with the navigation arrows on the sides of the page."
  [{:href "/" :title "Main page" :key :main}
   {:href "/about" :title "About me" :key :about}
   {:href "/photos" :title "Photography" :key :photos}])

(defroutes app-routes
  (HEAD "/" [] "")
  (GET "/" [] (layout/common-wrapper (main/content) main/options pages))
  (GET "/about" [] (layout/common-wrapper (about/content) about/options pages))
  (GET "/photos" [] (layout/common-wrapper (photos/content) photos/options pages))
  (GET "/photos/:country" [country] (layout/common-wrapper (photo-country/content country) photo-country/options pages))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& [port]]
  (jetty/run-jetty app {:port 5000 :join? false}))

