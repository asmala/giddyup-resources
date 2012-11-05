(defproject giddyup-resources "0.3.0"
  :description "Convenience functions for including Bootstrap resources."
  :url "https://github.com/asmala/giddyup-resources"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[compojure "1.1.3"]
                 [hiccup "1.0.1"]]
  :profiles {:1.3 {:dependencies [[org.clojure/clojure "1.3.0"]]}
             :1.4 {:dependencies [[org.clojure/clojure "1.4.0"]]}
             :1.5 {:dependencies [[org.clojure/clojure "1.5.0-alpha3"]]}
             :dev {:plugins [[codox "0.6.1"]]}
             :test {:dependencies [[ring-mock "0.1.3"]]}}
  :aliases {"test-all" ["with-profile" "test:test,1.3:test,1.4:test,1.5" "test"]}
  ::min-lein-version "2.0.0")