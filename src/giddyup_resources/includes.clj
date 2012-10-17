(ns giddyup-resources.includes
  (:use [hiccup.page :only [include-css include-js]]))

(defn include-bootstrap-css
  "Returns CSS include tags for Bootstrap resources."
  [& {:keys [minified? responsive?]}]
  (let [prefix "/giddyup/css/"
        suffix (if minified? ".min.css" ".css")]
    (list
     (include-css (str prefix "bootstrap" suffix))
     (if responsive?
       (include-css (str prefix "bootstrap-responsive" suffix))))))

(defn include-bootstrap-js
  "Returns JavaScript include tags for Bootstrap resources."
  [& {:keys [jquery? minified?] :or {jquery? true}}]
  (let [prefix "/giddyup/js/"
        suffix (if minified? ".min.js" ".js")]
    (list
     (if jquery?
       (include-js (str prefix "jquery" suffix)))
     (include-js (str prefix "bootstrap" suffix)))))

(defn include-bootstrap
  "Returns CSS and JavaScript include tags for Bootstrap resources."
  ([]
     (list (include-bootstrap-css)
           (include-bootstrap-js)))
  ([& args]
     (list (apply include-bootstrap-css args)
           (apply include-bootstrap-js args))))