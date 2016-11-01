(defproject kipsufi "0.1.0-SNAPSHOT"
  :aot [kipsufi.handler]
  :cljsbuild {:builds [{:source-paths ["src/cljs"]
                        :jar true
                        :compiler {:output-to "resources/public/js/script.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :dependencies [[compojure "1.5.1"]
                 [com.andrewmcveigh/cljs-time "0.4.0"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [prismatic/dommy "1.0.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "1.5.0"]]
  :description "My personal website"
  :hooks [leiningen.cljsbuild]
  :lesscss-output-path "resources/public/css"
  :lesscss-paths ["src/less"]
  :main kipsufi.handler
  :min-lein-version "2.0.0"
  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-lesscss "1.2"]
            [lein-ring "0.9.7"]]
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.0"]]}}
  :ring {:handler kipsufi.handler/app
         :port 5000})
  :url "darth.kipsu.fi"
