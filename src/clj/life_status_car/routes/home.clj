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

; (defn create-car [request] 
;   (let [params (:params request)] 
;     (db/with-transaction [conn]
;       (try
;         (let [car (db/create-car conn params)]
;           (db/insert-revision! conn {:id_car (:id car)
;                                      :last_revision_date (:last_revision_date params)
;                                      :created_at ("2011-12-03T10:15:30")
;                                      :km (:km params)})
;           (response/found "/"))
;         (catch Exception e
;           (db/rollback! conn)
;           (throw e))))))    

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

