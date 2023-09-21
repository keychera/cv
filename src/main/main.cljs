(ns main 
  (:require [old-cv]
            [goog.dom :as gdom]
            [reagent.dom :as rdom]))

;; the Edge
(defn get-app-element []
  (gdom/getElement "app"))

(defn mount [el]
  (rdom/render [old-cv/root] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

(defn init []
  (mount-app-element))

(defn ^:dev/after-load on-reload []
  (mount-app-element))
