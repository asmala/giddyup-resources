(ns giddyup-resources.middleware-test
  (:use [clojure.test]
        [giddyup-resources.middleware])
  (:require [ring.mock.request :as mock]))

(deftest wrap-giddyup-resources-test
  (let [handler (wrap-giddyup-resources (fn [req] {}))
        resources [["/css/bootstrap.css" "text/css"]
                   ["/css/bootstrap.min.css" "text/css"]
                   ["/css/bootstrap-responsive.css" "text/css"]
                   ["/css/bootstrap-responsive.min.css" "text/css"]
                   ["/img/glyphicons-halflings.png" "image/png"]
                   ["/img/glyphicons-halflings-white.png" "image/png"]
                   ["/js/bootstrap.js" "text/javascript"]
                   ["/js/bootstrap.min.js" "text/javascript"]
                   ["/js/jquery.js" "text/javascript"]
                   ["/js/jquery.min.js" "text/javascript"]]]
    (doseq [[resource mime-type] resources]
      (let [response (handler (mock/request :get (str "/giddyup" resource)))]
        (is (= (get-in response [:headers "Content-Type"])
               mime-type))))))

