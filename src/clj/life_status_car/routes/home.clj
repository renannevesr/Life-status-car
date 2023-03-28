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
     (response/found "/item"))

     (defn create-car-page [request]
  (layout/render request "create.html"))


(defn create-item [request]
  (let [params (:params request)]
    (db/create-revision-item params)
    (response/found (str "/"))))

     
(defn create-item-page [request]
  (layout/render request "create-revision-item.html"))

(defn create-revision [request]
  (let [params (assoc (:params request) :created_at (-> (java.time.LocalDate/now)
                                                       (.format (java.time.format.DateTimeFormatter/ofPattern "yyyy-MM-dd")))) 
        car-id (:id_car params)]
    (db/create-revision params)
    (response/found (str "/revisao/" car-id "#success"))))

(defn home-page [request]
  (let [cars (db/get-all-cars)]
    (layout/render request "home.html" {:cars cars})))

(defn add-km-exchange [revisions items]
  (map #(assoc % :km_exchange (+ (:km revisions) (:limit_km %))) items))

(defn new-revision-page [request]
  (let [id (-> request
               (get-in [:path-params :id])
               (Integer/parseInt))
        car (db/get-car-by-id {:id id})
        revisions (db/get-revisions-for-car {:id_car id})
        items (db/get-revision-limits-items-for-car {:id_car id})
        updated-items (add-km-exchange revisions items)]
    (layout/render request "new-revision.html"
                   {:car (assoc car :last-revision revisions)
                    :items updated-items})))


(defn edit-car [request]
  (def id (-> request
                  (get-in [:path-params :id])
                  (Integer/parseInt)))
  (def car (db/get-car-by-id {:id id}))
    (layout/render request "update.html" {:car car}))

(defn delete-car [request]
  (def id (-> request
                    (get-in [:path-params :id])
                    (Integer/parseInt)))
    (db/delete-car {:id id})
    {:status 204
    :body ""})

(defn update-car [request]
  (let [id (-> request
              (get-in [:path-params :id])
              (Integer/parseInt))
        params (-> request
                  (get-in [:params])
                  (select-keys [:brand :model :year]))] 
    (db/update-car (assoc params :id id)) 
    
    (response/found "/")))


(defn diff-in-months [date1 months]
  (let [days-in-month (fn [date]
                        (-> date
                            (.withDayOfMonth 1)
                            (.plusMonths 1)
                            (.minusDays 1)
                            (.getDayOfMonth)))
        now (java.time.LocalDate/now)
        months1 (* (- (.getYear date1) 1900) 12 (+ (.getMonth date1) 1))
        months2 (* (- (.getYear now) 1900) 12 (+ (.getMonth now) 1))
        days1 (days-in-month date1)
        days2 (days-in-month now)]
    (>= (- (+ months1 days1) (+ months2 days2)) months)))

(defn home-routes []
  [ ""
   ["/" {:get home-page}]
   ["/criar" {:get create-car-page}]
   ["/revisao/:id" {:get new-revision-page}]
   ["/add-car" {:post create-car}] 
   ["/add-revision" {:post create-revision}]
   ["/delete-car/:id" {:delete delete-car}]
   ["/editar/:id" {:get edit-car}]
   ["/edit-car/:id" {:post update-car}]
   ["/item" {:get create-item-page}]
   ["/add-revision-item" {:post create-item}] 
   ])

