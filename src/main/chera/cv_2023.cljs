(ns chera.cv-2023 
  (:require [goog.dom :as gdom]
            [reagent.dom :as rdom]))

(defn cv []
  [:div "This will be 2023 cv!"])

;; the Edge
(defn mount-app-element []
  (when-let [el (gdom/getElement "app")]
    (rdom/render [:f> cv] el)))

(defn init []
  (mount-app-element))

(defn ^:dev/after-load on-reload []
  (mount-app-element))