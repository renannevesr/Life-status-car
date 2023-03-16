(ns life-status-car.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[life-status-car started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[life-status-car has shut down successfully]=-"))
   :middleware identity})
