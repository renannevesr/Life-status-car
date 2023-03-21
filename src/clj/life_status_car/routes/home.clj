(ns life-status-car.routes.home
  (:require
   [life-status-car.layout :as layout]
   [life-status-car.db.core :as db]
   [clojure.java.io :as io]
   [life-status-car.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]
   [ring.util.response :refer [redirect]]))

(defn create-car [request]
  ; (print :params request)
  (def params (:params request))
  ;; (let [params (:params request)])
  (def cidade (get-in params [:form-params :montadora]))
  (print "\n\n\n cidade=")
  (print params)
  (print "\n\n\n")
    ;;  (db/create-car params)
    ;;  (ring.util.response/redirect "/")
     (response/found "/")
     )
    

(defn home-page [request]
  (let [cars (db/get-all-cars)]
    (layout/render request "home.html" {:cars cars})))

(defn about-page [request]
  (layout/render request "create.html"))

(defn home-routes []
  [ ""
  {:middleware [middleware/wrap-csrf
                middleware/wrap-formats
              ]} 
   ["/" {:get home-page}]
   ["/criar" {:get about-page}]
   ["/add-car" {:post create-car}]
   ])

