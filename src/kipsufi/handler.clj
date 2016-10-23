(ns kipsufi.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [kipsufi.views.layout :as layout]
            [kipsufi.views.main :as main]
            [kipsufi.views.about :as about]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:gen-class))

(defroutes app-routes
  (HEAD "/" [] "")
  (GET "/" [] (layout/common-wrapper (main/content) main/options))
  (GET "/about" [] (layout/common-wrapper (about/content) about/options))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& [port]]
  (jetty/run-jetty app {:port 5000 :join? false}))

