(ns main 
  (:require [cv]
            [goog.dom :as gdom]
            [reagent.dom :as rdom]))

;; the Edge
(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [el]
  (rdom/render [cv/cv] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

(defn init []
  (mount-app-element))

(defn ^:dev/after-load on-reload []
  (mount-app-element))
