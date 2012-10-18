(ns giddyup-resources.includes
  (:use [hiccup.page :only [include-css include-js]]))

(def ^:private cdn-base
  "http://cdnjs.cloudflare.com/ajax/libs")

(defn include-bootstrap-css
  "Returns CSS include tags for Bootstrap resources."
  [& {:keys [cdn? minified? responsive?]}]
  (let [prefix (if cdn?
                 (str cdn-base "/twitter-bootstrap/2.1.1/css/")
                 "/giddyup/css/")
        suffix (if minified? ".min.css" ".css")]
    (list
     (include-css (str prefix "bootstrap" suffix))
     (if responsive?
       (include-css (str prefix "bootstrap-responsive" suffix))))))

(defn include-bootstrap-js
  "Returns JavaScript include tags for Bootstrap resources."
  [& {:keys [cdn? minified?]}]
  (let [suffix (if minified? ".min.js" ".js")
        jquery (if cdn?
                 (str cdn-base "/jquery/1.8.2/jquery" suffix)
                 (str "/giddyup/js/jquery" suffix))
        bootstrap (if cdn?
                    (str cdn-base "/twitter-bootstrap/2.1.1/bootstrap" suffix)
                    (str "/giddyup/js/bootstrap" suffix))]
    (include-js jquery bootstrap)))

(defn include-bootstrap
  "Returns CSS and JavaScript include tags for Bootstrap resources."
  [& args]
  (list (apply include-bootstrap-css args)
        (apply include-bootstrap-js args)))