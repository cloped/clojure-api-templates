(ns service-clojure.core
 (:require [io.pedestal.http.route :as route]
           [io.pedestal.http :as http]))

(defn health-check [request]
 {:status 200 :body (str "Status Ok" (get-in request [:query-params :name]))})

(def routes (route/expand-routes #{["/health" :get health-check :route-name :health-check]}))

(def service-map {::http/routes routes
                  ::http/port   9999
                  ::http/type   :jetty
                  ::http/join?  false})

(http/start (http/create-server service-map))
(println "Started http server")
