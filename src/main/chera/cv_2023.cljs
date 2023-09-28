(ns chera.cv-2023
  (:require ["antd" :refer [Avatar Card Col Space Row Tag Typography Tabs]]
            ["react-responsive" :refer [useMediaQuery]]
            [reagent.core :refer [as-element]]))

(def Text Typography.Text)
(def Meta Card.Meta)

(defn Link [{:keys [href children]}]
  [:a {:href href :target "_blank" :rel "noopener noreferrer"} (or children href)])

(def content
  {:name "Kevin Erdiza Yogatama"
   :about "I am a software engineer with an experience in a test automation engineering team.
Within my job, I have been exploring backend solutions and implementing them to solve internal teams needs."
   :profile-url "me_2019.png"
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
   [{:title "Bandung Institute of Technology"
     :subtitle "Bachelor of Computer Science"
     :type "university", :time "2015 - 2020"
     :extra "graduated with 3.41 GPA"}],
   :experience
   [{:title "What"
     :place "Where"
     :time "When"
     :desc "How"}]
   :skills
   [{:title "JVM Languages"
     :items ["Kotlin" "Clojure" "Java" "Scala"]}
    {:title "Server"
     :items ["http-kit" "Ring-Jetty (Clojure wrapper of Jetty)"]}
    {:title "Deployment"
     :items ["Docker" "Kuberneter" "GCP" "Gitlab CI"]}
    {:title "Databases"
     :items ["MySQL" "SQLite"]}
    {:title "Testing"
     :items ["Junit5" "Selenium" "Appium" "RestAssured"]}
    {:title "Frontend"
     :items ["HTMX" "Tailwind" "Bootstrap" "React" "Reagent (ClojureScript wrapper of React)"]}
    {:title "Scritping"
     :items ["Bash" "Clojure/Babashka"]}
    {:title "Design tools", :items ["Figma" "Penpot"]}
    {:title "Versioning tool", :items ["git"]}]
   :language
   [[:<> "language " [:> Text {:strong true} "English"]]
    [:<> "language " [:> Text {:strong true} "japanese"]]]
   :extra
   [[:<> "some "
     [:> Text {:strong true} "drawing"] " and " [:> Text {:strong true} "animation"]]]})

(defn CenterTitle [& children]
  [:div {:style {:textAlign "center" :verticalAlign "center" :paddingTop 8 :paddingBottom 8 :backgroundColor "white"}}
   [:h4 {:style {:marginBottom 0 :paddingBottom 2}} children]])

(defn EducationCard [idx {:keys [title subtitle type time extra]}]
  [:> Card {:size "small" :style {:fontSize 12} :key (str idx)}
   [:> Row
    [:> Col {:span 18}
     [:div [:> Text {:strong true} title]]
     [:div [:> Text {:italic true} subtitle]]
     [:div [:> Text {:code true} type] "・" time]]
    [:> Col {:span 6} extra]]])

(defn ExperienceCard [idx {:keys [title place time desc]}]
  [:> Card {:size "small" :style {:fontSize 12} :key (str idx)}
   [:div
    [:> Text {:strong true} title] "・" [:> Text {:type "secondary"} place]]
   [:div time]
   [:div desc]])

(defn Profile
  ([] (Profile {}))
  ([{:keys [big-screen?]}]
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
                [:> Avatar {:src (:profile-url content) :size 128 :style {:display "inline-block"}}]]))}
    (if big-screen?
      [:> Meta
       {:avatar (as-element [:> Avatar {:src "" :size 128}])
        :title (:name content)
        :description (:about content)}]
      [:div
       [:h4 (:name content)]
       [:p {:style {:color "#00000073"}} (:about content)]])]))

(defn Educations []
  [:<>
   (CenterTitle "Education 📚")
   [:> Card {:size "small"}
    (->> content :education
         (map-indexed EducationCard))]])

(defn Experiences []
  [:<>
   (CenterTitle "Experience 🥼")
   [:> Card {:size "small"}
    (->> content :experience
         (map-indexed ExperienceCard))]])

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

(defmulti ProjectCard (fn [_ props] (:type props)))

(defmethod ProjectCard "project"
  [idx {:keys [title subtitle desc time tools]}]
  [:> Card {:title title :size "small" :key (str idx)
            :extra (as-element [:> Text subtitle])}
   [:> Row
    [:> Col {:span 16}
     [:div {:style {:paddingRight 10 :fontSize 12}}
      [:> Text
       (if (vector? desc)
         (->> desc (map-indexed (fn [idx d] [:div {:key (str idx)} d])))
         desc)]]]
    [:> Col {:span 8}
     [:div {:style {:fontSize 12 :marginBottom 8}}
      [:> Text {:strong true} time]]
     [:div
      (->> tools
           (map-indexed (fn [idx tool]
                          [:> Tag {:color (tool-color tool) :key (str idx)
                                   :style {:marginBottom 4}} tool])))]]]])

(defmethod ProjectCard "group"
  [idx {:keys [title items]}]
  [:> Card {:title title :size "small" :key (str idx)}
   (->> items
        (map-indexed ProjectCard))])

(defmethod ProjectCard :default
  [idx {:keys [title]}]
  [:> Card {:size "small" :key (str idx)} (str "undefined type for" title)])

(defn Projects
  ([] (Projects {}))
  ([{:keys [mobile?]}]
   [:<>
    (CenterTitle "Projects 💻")
    (if mobile?
      (->> content :projects
           (map-indexed ProjectCard))
      (let [row-1 (-> content :projects (subvec 0 3))
            row-2 (-> content :projects (subvec 3))]
        [:> Card {:size "small"}
         [:> Row
          [:> Col {:span 12} (->> row-1 (map-indexed ProjectCard))]
          [:> Col {:span 12} (->> row-2 (map-indexed ProjectCard))]]]))]))

(defn SkillCard [idx {:keys [title items]}]
  [:> Card {:size "small" :key (str idx)}
   [:> Space #_{:direction "vertical"}
    [:> Text {:strong true :autoSize true} title]
    [:div
     (->> items
          (map-indexed (fn [idx item]
                         [:> Tag {:color (tool-color item) :key (str idx)
                                  :style {:marginBottom 4}} item])))]]])

(defn ExtraCard [idx value]
  [:> Card {:size "small" :key (str idx)} value])

(defn Skills []
  [:<>
   (CenterTitle "Technical skills 🔧")
   [:> Card {:size "small"}
    (->> content :skills
         (map-indexed SkillCard))]])

(defn Language []
  [:<>
   (CenterTitle "Language 📙")
   [:> Card {:size "small"}
    (->> content :language
         (map-indexed ExtraCard))]])

(defn ExtraSkills []
  [:<>
   (CenterTitle "Extra skills ➕")
   [:> Card {:size "small"}
    (->> content :extra
         (map-indexed ExtraCard))]])

(defn cv []
  (set! (.. js/document -title) "keychera's 2019 CV")
  (let [big-screen? (useMediaQuery (clj->js {:minWidth 1500}))
        desktop?    (useMediaQuery (clj->js {:minWidth 1224}))
        tablet?     (useMediaQuery (clj->js {:minWidth 900 :maxWidth 1224}))
        mobile?     (useMediaQuery (clj->js {:maxWidth 900}))]
    [:div
     (cond
       desktop?
       [:<>
        [:> Row {:align "center"}
         [:> Col {:span 6}
          (Profile {:big-screen? big-screen?})
          (Skills)]
         [:> Col {:span 18}
          (CenterTitle "Projects 💻")
          (let [row-1 (-> content :projects (subvec 0 3))
                row-2 (-> content :projects (subvec 3))]
            [:<>
             [:> Row
              [:> Col {:span 12} (->> row-1 (map-indexed ProjectCard))]
              [:> Col {:span 12} (->> row-2 (map-indexed ProjectCard))]]
             [:> Row
              [:> Col {:span 12} (Language)]
              [:> Col {:span 12} (Educations)]]])]]
        [:> Row
         [:> Col {:span 6}]
         [:> Col {:span 18} (ExtraSkills)]]]

       tablet?
       [:> Row {:align "center"}
        [:> Col {:span 6}
         (Profile)
         (Educations)
         (Experiences)]
        [:> Col {:span 18} (Projects) (Skills)]]

       :else
       [:<>
        (Profile {:big-screen? big-screen?})
        [:> Tabs
         {:defaultActiveKey "2" :type "card" :centered true
          :items [{:label "📚" :key "1"
                   :children (as-element [:<> (Educations) (Experiences)])}
                  {:label "💻" :key "2"
                   :children (as-element (Projects {:mobile? mobile?}))}
                  {:label "🔧" :key "3"
                   :children (as-element (Skills))}]}]])]))

(defn root []
  [:f> cv])