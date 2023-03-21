(ns life-status-car.routes.home
  (:require
   [life-status-car.layout :as layout]
   [life-status-car.db.core :as db]
   [clojure.java.io :as io]
   [life-status-car.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))


(defn home-page [request]
  (let [cars (db/get-all-cars)]
    (layout/render request "home.html" {:cars cars})))

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [ "" 
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats
                 ]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]])

