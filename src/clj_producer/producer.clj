(ns clj-producer.producer
  (:require [cheshire.core :refer :all])
  (:import [org.apache.kafka.clients.producer KafkaProducer ProducerRecord]
           [org.apache.kafka.common.serialization ByteArraySerializer]))

(def host (if-let [host (System/getenv "HOST")] host "192.168.99.100"))

(def p-cfg {"value.serializer" ByteArraySerializer
            "key.serializer" ByteArraySerializer
            "bootstrap.servers" (str host ":9092")})

(def producer (KafkaProducer. p-cfg))



(defn broadcast
  [data]
  (println "Producer Shit")    
  (.send producer (ProducerRecord. "test" (.getBytes "Hello This is a test")))
  {:status 200
   :headers {"Content-Type" "text/html; charset=utf-8"}})
