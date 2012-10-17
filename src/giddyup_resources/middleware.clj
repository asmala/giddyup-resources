(ns giddyup-resources.middleware
  (:use [ compojure.core :only [routes]])
  (:require [compojure.route :as route]))

;; Shamelessly stolen from https://github.com/weavejester/hiccup-bootstrap
(defn wrap-giddyup-resources
  "Adds routes for Bootstrap resources in `handler`."
  [handler]
  (routes
   (route/resources "/giddyup" {:root "giddyup/public"})
   handler))