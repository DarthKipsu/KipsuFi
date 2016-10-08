(ns kipsufi.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [kipsufi.views.layout :as layout]
            [kipsufi.views.main :as main]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (HEAD "/" [] "")
  (GET "/" [] (layout/common-wrapper (main/content) {:full-page? true :title main/title}))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
