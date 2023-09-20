(ns old-cv
  (:require ["antd" :refer [Avatar Card Col Row Tag Typography]]
            [reagent.core :refer [as-element]]))

(def Text Typography.Text)
(def Meta Card.Meta)

(defn Link [{:keys [href children]}]
  [:a {:href href :target "_blank" :rel "noopener noreferrer"} (or children href)])

(def content
  {:name "Kevin Erdiza Yogatama"
   :about "An aspiring individual that has several experiences in web, mobile, and game development using various tools and platform"
   :contact
   [{:name "twitter", :display "@keychera", :link "https://twitter.com/keychera"}
    {:name "github", :display "keychera", :link "https://github.com/keychera"}
    {:name "linkedin", :display "keychera", :link "https://www.linkedin.com/in/keychera/"}]
   :projects
   [{:type "group", :title "React Native projects",
     :items
     [{:type "project", :title "Mock MyAnimeList client mobile app", :time "July - August 2020", :desc "Kevin joined a React-native training class and built a client app for the database about anime named MyAnimeList for its final task.", :tools ["react-native" "expo" "javascript"]}
      {:type "project", :title "Mock QA platform mobile app", :time "August - September 2020", :desc "Kevin joined an advanced React-native training class, continuing the previous one. For its final assignment, Kevin built a mock QA platform inspired by Brainly and Stackoverflow.", :tools ["react-native" "javascript"]}]}
    {:type "project", :title "Potions for Ruby", :subtitle "a game made with Godot engine", :desc "Kevin made a game intended for a game submission event held by a YouTuber named Jonas Tyroller. \"Potion for Ruby\" is a small little story game that involves some puzzles gameplay.", :time "May - June 2020", :tools ["godot" "gdscript"]}
    {:type "project", :title "Savant", :subtitle "University's final year project", :desc "Savant is the name of a bug localization technique, which is the main focus of Kevin's final year project at his university. Kevin attempted to build a replica of Savant's implementation, which resulted in a multi-language system that runs several tools and frameworks in different languages to make Savant works.", :time "August 2019 - April 2020", :tools ["perl" "java" "shellscript" "python"]}
    {:type "group", :title "Graphic Programming projects",
     :items
     [{:type "project", :title "Opengl animation", :time "August - September 2020 ", :desc "Combining the interest in art and programming, Kevin started learning Opengl to understand what's going on behind the scene of graphical processing. So far, Kevin managed to make one animated piece that he is proud of.", :tools ["c++" "opengl" "glsl"]}
      {:type "project", :title "2D/3D art with Blender", :time "September 2020", :desc "Having the vision to build a programmable visual, Kevin pursued learning Blender. Kevin has finished several tutorials that managed to make impressive visuals with a minimal amount of effort.", :tools ["blender"]}]}
    {:type "project", :title "React app CV", :subtitle "This CV itself",
     :desc ["Continuing the React-native classes, Kevin learned React to make this CV and published it on GitHub pages. This CV can also be accessed on:"
            (Link {:href "https://keychera.github.io/react-playground/cv"})]
     :time "September - October 2020", :tools ["react" "javascript"]}]
   :education
   [{:name "Institut Teknologi Bandung", :type "university", :time "2015 - 2020", :extra ""}
    {:name "SMANU MH Thamrin", :type "senior high school", :time "2012 - 2015"}],
   :experience
   [{:title "Head of IT", :place "event: Pemilu HMIF", :time "September 2017 - December 2017", :desc "Kevin leads a team to build an attendance system for the event Pemilu HMIF 2018, a QA website, and an E-voting website for the voting process of Pemilu HMIF 2018."}
    {:title "Head of Gamedev community", :place "Inkubator IT HMIF", :time "Feb 2018 - Feb 2019", :desc "Kevin leads a community that dabbles in the art and tech of game development."}
    {:title "Marketing Staff", :place "Inkubator IT HMIF", :time "August 2016 - Feb 2019", :desc "Kevin works in the marketing department of Inkubator IT HMIF. In there, Kevin did activities such as advertising Inkubator IT at events, other student organizations, and clients while learning about communication and presentational skill."}]
   :skills
   [{:title "language", :items ["javascript" "c++" "python" "java" "perl"]}
    {:title "framework / lib", :items ["react" "react-native"]}
    {:title "versioning tools", :items ["git"]}
    {:title "design tools", :items ["figma"]}
    {:title "game engine", :items ["godot" "unity"]}
    {:title "other", :items ["blender" "opengl" "glsl/shading language"]}]
   :extra
   [(list "Have decent skills and great interest in "
          [:> Text {:strong true} "drawing"] " and " [:> Text {:strong true} "animation"])
    (list "Can speak and write in " [:> Text {:strong true} "English"]
          " and has been studying " [:> Text  {:strong true} "Japanese"] " since December 2018")]})

(defn CenterTitle [& children]
  [:div {:style {:textAlign "center" :verticalAlign "center" :paddingTop 6 :backgroundColor "white"}}
   [:h4 {:style {:marginBottom 0 :paddingBottom 2}} children]])

(defn EducationCard [{:keys [name type time extra]}]
  [:> Card {:size "small" :style {:fontSize 12}}
   [:> Row
    [:> Col {:span 18}
     [:div [:> Text {:strong true} name]]
     [:div [:> Text {:code true} type] "・" time]]
    [:> Col {:span 6} extra]]])

(defn ExperienceCard [{:keys [title place time desc]}]
  [:> Card {:size "small" :style {:fontSize 12}}
   [:div
    [:> Text {:strong true} title]
    "・" [:> Text {:type "secondary"} place]]
   [:div time]
   [:div desc]])

(defn Profile [{:keys [big-screen?]}]
  [:> Card
   {:actions (->> content :contact
                  (mapv (fn [{:keys [name display link]}]
                          (as-element
                           [:> Text
                            [:> Text {:strong true} name]
                            (Link {:href link :children display})]))))
    :cover (when-not big-screen?
             (as-element
              [:div {:style {:textAlign "center" :paddingTop 24}}
               [:> Avatar {:src "" :size 128 :style {:display "inline-block"}}]]))}
   (if big-screen?
     [:> Meta
      {:avatar (as-element [:> Avatar {:src "" :size 128}])
       :title (:name content)
       :description (:about content)}]
     [:div
      [:h4 (:name content)]
      [:p {:style {:color "#00000073"}} (:about content)]])])

(defn Educations []
  (list
   (CenterTitle "Education 📚")
   [:> Card {:size "small"}
    (->> content :education
         (map EducationCard))]))

(defn Experiences []
  (list
   (CenterTitle "Experience 🥼")
   [:> Card {:size "small"}
    (->> content :experience
         (map ExperienceCard))]))

(def tool-color
  {"react-native" "blue"
   "react" "blue"
   "expo" "cyan"
   "javascript" "green"
   "godot" "geekblue"
   "c++" "gold"
   "opengl" "gold"
   "blender" "volcano"
   "perl" "red"
   "java" "orange"
   "python" "purple"
   "figma" "lime"})

(defmulti ProjectCard :type)

(defmethod ProjectCard "project"
  [{:keys [title subtitle desc time tools]}]
  [:> Card {:title title :size "small"
            :extra (as-element [:> Text subtitle])}
   [:> Row
    [:> Col {:span 16}
     [:div {:style {:paddingRight 10 :fontSize 12}}
      [:> Text
       (if (vector? desc)
         (->> desc (map (fn [d] [:div d])))
         desc)]]]
    [:> Col {:span 8}
     [:div {:style {:fontSize 12 :marginBottom 8}}
      [:> Text {:strong true} time]]
     [:div
      (->> tools
           (map (fn [tool]
                  [:> Tag {:color (tool-color tool)
                           :style {:marginBottom 4}} tool])))]]]])

(defmethod ProjectCard "group"
  [{:keys [title items]}]
  [:> Card {:title title :size "small"}
   (->> items
        (map ProjectCard))])

(defmethod ProjectCard :default
  [{:keys [title]}]
  [:> Card {:size "small"} (str "undefined type for" title)])

(defn Projects [{:keys [mobile?]}]
  (list
   (CenterTitle "Projects 💻")
   (if mobile?
     (->> content :projects
          (map ProjectCard))
     (let [row-1 (-> content :projects (subvec 0 3))
           row-2 (-> content :projects (subvec 3))]
       [:> Card {:size "small"}
        [:> Row
         [:> Col {:span 12} (->> row-1 (map ProjectCard))]
         [:> Col {:span 12} (->> row-2 (map ProjectCard))]]]))))

(defn SkillCard [{:keys [title items]}]
  [:> Card {:size "small"}
   [:> Text {:strong true} title] "・"
   (->> items
        (map (fn [item]
               [:> Tag {:color (tool-color item)
                        :style {:marginBottom 4}} item])))])

(defn ExtraCard [value]
  [:> Card {:size "small"} value])

(defn Skills []
  (list
   (CenterTitle "Technical skills 🔧")
   [:> Card {:size "small"}
    (->> content :skills
         (map SkillCard))]
   (CenterTitle "Extra skills ➕")
   [:> Card {:size "small"}
    (->> content :extra
         (map ExtraCard))]))

(defn cv []
  (set! (.. js/document -title) "keychera's 2019 CV")
  [:div
   (CenterTitle
    [:h3 "I am a CV"]
    [:p.someclass
     "I have " [:strong "bold"]
     [:span {:style {:color "red"}} " and red"]
     " text."])
   (Profile {:big-screen? false})
   (Educations)
   (Experiences)
   (Projects {:mobile? false})
   (Skills)])
