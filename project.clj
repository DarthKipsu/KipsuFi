(defproject kipsufi "0.1.0-SNAPSHOT"
  :aot [kipsufi.handler]
  :dependencies [[compojure "1.5.1"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojure "1.8.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "1.5.0"]]
  :description "My personal website"
  :lesscss-output-path "resources/public/css/"
  :lesscss-paths ["src/less"]
  :main kipsufi.handler
  :min-lein-version "2.0.0"
  :plugins [[lein-lesscss "1.2"]
            [lein-ring "0.9.7"]]
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.0"]]}}
  :ring {:handler kipsufi.handler/app
         :port 5000})
  :url "darth.kipsu.fi"
