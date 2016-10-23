(ns kipsufi.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [kipsufi.views.layout :as layout]
            [kipsufi.views.main :as main]
            [kipsufi.views.about :as about]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (HEAD "/" [] "")
  (GET "/" [] (layout/common-wrapper (main/content) {:full-page? true 
                                                     :next-page main/next-page
                                                     :prev-page main/prev-page
                                                     :title main/title}))
  (GET "/about" [] (layout/common-wrapper (about/content) {:full-page? true 
                                                           :next-page about/next-page
                                                           :prev-page about/prev-page
                                                           :title about/title}))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
