(ns clj-producer.core
  (:gen-class)
  (:require [org.httpkit.server :refer :all]
            [clj-producer.producer :as producer]
            [clojure.core.async :refer [chan close! go <! >! >!!]])
  (:import [java.net InetAddress]))

(def host-name (.getHostName (. InetAddress getLocalHost)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Clojure Kafka Producer Main")
  (def chatatoms (atom {}))

  (defn handler [req]
    (println "Web Socket Handler")
    (producer/broadcast "shit")
    
    (with-channel req channel
      

      (println "WebSocket channel")
      (println "HTTP channel")
   
      (on-receive channel (fn [msg]    (println "on-receive:" msg)))

      (on-close channel (fn [status]
                          (println "channel closed")))))

(run-server handler {:port 8080}))
