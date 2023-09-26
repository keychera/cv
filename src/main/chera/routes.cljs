(ns chera.routes
  (:require ["antd" :refer [Button Card Row Col Space]]
            ["react-router-dom" :refer [BrowserRouter Link Route Routes]]
            [chera.cv-2019 :as cv-2019]
            [reagent.core :refer [as-element]]))

(def root
  [:> Row {:align "middle" :style {:height "100%"}}
   [:> Col {:align "center" :style {:width "100%"}}
    [:> Card {:title "keychera's cv"}
     [:> Space
      [:> Link {:to "/2023"} [:> Button "current CV"]]
      [:> Link {:to "/2019"} [:> Button "2019 CV"]]]]]])

(defn routes []
  [:> BrowserRouter
   [:> Routes
    [:> Route {:path "/2019" :element (as-element (cv-2019/root))}]
    [:> Route {:path "/" :element (as-element root)}]]])