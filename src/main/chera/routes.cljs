(ns chera.routes
  (:require ["antd" :refer [Button Card Col Row Space]]
            ["react-router-dom" :refer [BrowserRouter Link Route Routes]]
            [chera.cv-2019 :as cv-2019]
            [chera.cv-2023 :as cv-2023]
            [chera.cv-2023-30sept :as cv-2023-30sept]
            [reagent.core :refer [as-element]]))

(def root
  [:> Row {:align "middle" :style {:height "100%"}}
   [:> Col {:align "center" :style {:width "100%"}}
    [:> Card {:title "keychera's cv"}
     [:> Space
      [:> Card {:size "small" :direction "vertical"}
       [:> Space {:direction "vertical"}
        [:> Link {:to "/2023"} [:> Button "2023 CV"]]
        [:> Link {:to "/2023-30sept"} [:> Button {:style {:scale "80%" :color "lightgray"}} "old version - 30 sept"]]]]
      [:> Link {:to "/2019"} [:> Button "2019 CV"]]]]]])

(defn routes []
  ;; hardcoded URL matching github page url/repo name
  [:> BrowserRouter {:basename "/cv"}
   [:> Routes
    [:> Route {:path "/2023" :element (as-element (cv-2023/root))}]
    [:> Route {:path "/2023-30sept" :element (as-element (cv-2023-30sept/root))}]
    [:> Route {:path "/2019" :element (as-element (cv-2019/root))}]
    [:> Route {:path "/" :element (as-element root)}]]])