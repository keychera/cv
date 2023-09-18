(ns cv)

(defn cv []
  [:div
   [:h3 "I am a CV"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red"]
    " text."]])
