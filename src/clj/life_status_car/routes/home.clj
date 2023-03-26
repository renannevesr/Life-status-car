(ns life-status-car.routes.home
  (:require
   [life-status-car.layout :as layout]
   [life-status-car.db.core :as db]
   [clojure.java.io :as io]
   [life-status-car.middleware :as middleware]
   [ring.util.response :as request]
   [ring.util.http-response :as response]
   [ring.util.response :refer [redirect]]))

(defn create-car [request] 
  (def params (:params request)) 
     (db/create-car params)
     (response/found "/"))

(defn create-revision [request]
  (let [params (assoc (:params request) :created_at "2022-03-01")
        car-id (:id_car params)]
    (db/create-revision params)
    (response/found (str "/revisao/" car-id "#success"))))

(defn create-revision [request]
  (let [params (assoc (:params request) :created_at (-> (java.time.LocalDate/now)
                                                       (.format (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd")))) 
        car-id (:id_car params)]
    (db/create-revision params)
    (response/found (str "/revisao/" car-id "#success"))))

(defn home-page [request]
  (let [cars (db/get-all-cars)]
    (layout/render request "home.html" {:cars cars})))

(defn create-car-page [request]
  (layout/render request "create.html"))

(defn new-revision-page [request]
  (def id (-> request
                  (get-in [:path-params :id])
                  (Integer/parseInt)))
  (def cars (db/get-all-cars))
    (def car (first (filter #(= (:id %) id) cars)))
    (layout/render request "new-revision.html" {:car car}))

(defn delete-car [request]
(def id (-> request
                  (get-in [:path-params :id])
                  (Integer/parseInt)))
  (db/delete-car {:id id})
  {:status 204
   :body ""})

(defn home-routes []
  [ ""
   ["/" {:get home-page}]
   ["/criar" {:get create-car-page}]
   ["/revisao/:id" {:get new-revision-page}]
   ["/add-car" {:post create-car}] 
   ["/add-revision" {:post create-revision}]
   ["/delete-car/:id" {:delete delete-car}]
   ])

