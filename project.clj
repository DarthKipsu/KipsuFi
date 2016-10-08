(defproject kipsufi "0.1.0-SNAPSHOT"
  :description "My personal website"
  :url "darth.kipsu.fi"
  :min-lein-version "2.0.0"
  :dependencies [[compojure "1.5.1"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojure "1.8.0"]
                 [ring/ring-defaults "0.2.1"]]
  :plugins [[kremers/lein-beanstalk "0.2.9"]
            [lein-lesscss "1.2"]
            [lein-ring "0.9.7"]]
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.0"]]}}
  :lesscss-paths ["src/less"]
  :lesscss-output-path "resources/public/css/"
  :aws {:beanstalk {:environments [{:name "kipsufi"
                                    :cname-prefix "kipsufi-prod"}]
                    :region "eu-west-1"}}
  :ring {:handler kipsufi.handler/app})
