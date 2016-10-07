(ns kipsufi.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [kipsufi.views.layout :as layout]
            [kipsufi.views.main :as main]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (layout/common-wrapper (main/content) main/title))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
