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
  (def params (:params request)) 
     (db/create-car params)
     (response/found "/"))

; (defn create-revision [request]
;   (def params (:params request)) 
;     ;  (db/create-revision params)
;   )

(defn home-page [request]
  (let [cars (db/get-all-cars)]
    (layout/render request "home.html" {:cars cars})))

(defn create-car-page [request]
  (layout/render request "create.html"))

(defn new-revision-page [request]
  (def car-id (-> request
                  (get-in [:params :id])
                  (Integer/parseInt)))
  (def car (db/get-car-by-id car-id))
    (layout/render request "new-revision.html" {:car car})))

; (defn new-revision-page [request]
;   (let [car-id (-> request
;                   (get-in [:params :id])
;                   (Integer/parseInt))
;         car (db/get-car-by-id car-id)]
;     (layout/render request "new-revision.html"
;                    {:car car})))

(defn home-routes []
  [ ""
  {:middleware [middleware/wrap-csrf
                middleware/wrap-formats
              ]} 
   ["/" {:get home-page}]
   ["/criar" {:get create-car-page}]
   ["/revisao/:id" {:get new-revision-page}]
   ["/add-car" {:post create-car}] 
  ;  ["/add-revision" {:post create-revision}]
   ])

