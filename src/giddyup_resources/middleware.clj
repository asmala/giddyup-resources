(ns giddyup-resources.middleware
  "Ring middleware for including resources for Bootstrap."
  (:use [compojure.core :only [routes]])
  (:require [compojure.route :as route]))

;; Shamelessly stolen from https://github.com/weavejester/hiccup-bootstrap
(defn wrap-giddyup-resources
  "Adds routes for Bootstrap resources in `handler`.

  ### Example

      (def app-with-giddyup
       (wrap-giddyup-resources app))"
  [handler]
  (routes
   (route/resources "/giddyup" {:root "giddyup/public"})
   handler))