(ns life-status-car.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [life-status-car.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[life-status-car started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[life-status-car has shut down successfully]=-"))
   :middleware wrap-dev})
