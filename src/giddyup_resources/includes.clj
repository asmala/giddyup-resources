(ns giddyup-resources.includes
  "Hiccup functions for including Bootstrap resources.

  ### Options

  Functions in this namespace accept the following options:

  * `:location`:  If `:cdn`, links to CDN hosted files. Defaults to `:local`.
  * `:minified?`: If true, links to minified files. Defaults to false.
  * `:responsive?`: If true, also links to the responsive stylesheet. Defaults
    to true."
  (:use [hiccup.page :only [include-css include-js]]))

(def ^:private cdn-base
  "http://cdnjs.cloudflare.com/ajax/libs")

(def versions {:bootstrap "2.2.1"
               :jquery "1.8.2"})

(def resource-paths
  {:cdn {:bootstrap-css (str cdn-base "/twitter-bootstrap/"
                             (versions :bootstrap) "/css/")
         :bootstrap-js (str cdn-base "/twitter-bootstrap/"
                            (versions :bootstrap) "/")
         :jquery (str cdn-base "/jquery/" (versions :jquery) "/")}
   :local {:bootstrap-css "/giddyup/css/"
           :bootstrap-js "/giddyup/js/"
           :jquery "/giddyup/js/"}})

(defn- resource-url
  "Returns the URL for the resource indicated by `location`, `path`, and
  `file-components. `location` and `path` are keys in `resource-paths`."
  [location path & file-components]
  (let [path (get-in resource-paths [location path])]
    (apply str path file-components)))

(defn include-bootstrap-css
  "Returns CSS include tags for Bootstrap resources.

  ### Example

      (include-bootstrap-css {:location :cdn :responsive? false})"
  ([] (include-bootstrap-css {}))
  ([{:keys [location minified? responsive?]
     :or {location :local responsive? true}}]
     (let [suffix (if minified? ".min.css" ".css")]
       (list
        (include-css (resource-url location :bootstrap-css "bootstrap" suffix))
        (if responsive?
          (include-css (resource-url location :bootstrap-css
                                     "bootstrap-responsive" suffix)))))))

(defn include-bootstrap-js
  "Returns JavaScript include tags for Bootstrap resources.

  ### Example

      (include-bootstrap-js {:minified? true})"
  ([] (include-bootstrap-js {}))
  ([{:keys [location minified?] :or {location :local}}]
     (let [suffix (if minified? ".min.js" ".js")]
       (include-js (resource-url location :jquery "jquery" suffix)
                   (resource-url location :bootstrap-js "bootstrap" suffix)))))

(defn include-bootstrap
  "Returns CSS and JavaScript include tags for Bootstrap resources.

  ### Example

      (include-bootstrap {:location :cdn})"
  ([] (include-bootstrap {}))
  ([opts]
     (list (include-bootstrap-css opts)
           (include-bootstrap-js opts))))