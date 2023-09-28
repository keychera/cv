(ns chera.cv-2023
  (:require ["antd" :refer [Avatar Card Col List Space Row Tag Typography Tabs]]
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
   [{:type "group", :title "Virgo ・ an E-money company"
     :extra "Oktober 2020 - September 2023"
     :items
     [{:type "group2" :title "Test Automation Engineer"
       :extra "April 2022 - September 2023"
       :time "April 2022 - September 2023"
       :items
       [{:type "project", :title "Developed a pipeline/queue system to compile and execute kotlin codebase"
         :desc
         ["Custom pipeline/queue server to compile and execute kotlin codebase"
          "Test report aggregator and small-scale internal helper tools and dashboard"
          "Web development that focus on the backend side by lowering front-end cose leveraging HTMX and Bootstrap"
          "Integration with several development tools such as Gitlab scheduling and Slack"]
         :tools ["Clojure" "HTMX" "SQlite" "Bootstrap" "Kubernetes" "GCP" "Oauth2-proxy"]}
        {:type "project", :title "Developed a custom testing framework for QA team"
         :desc
         ["Drive test script standardization, configurability, and readability"
          "Deal with code distribution (library publishing via Gitlab registry)"
          "Simplifying build system for test scripts (Maven pom.xml generator)"
          "Build base framework that is reusable for multiple entities"
          "Mentor and train other member to use and help contribute to this framework"]
         :tools ["Kotlin" "Junit5" "Selenium" "Appium" "RestAssured" "GRPC/Protobuf" "Maven" "Gradle" "Clojure/Babashka" "Gitlab CI"]}]}
      {:type "group2" :title "Junior Test Automation Engineer"
       :extra "Oktober 2020  - April 2022"
       :items
       [{:type "project", :title "Introduced Kotlin usage for test scripting"
         :desc ["Proposed otlin to write test scripts to the team"
                "Orchestra code migration and train other members to use Kotlin"]
         :tools ["Kotlin" "Gradle"]}
        {:type "project", :title "Write code generators to help GRPC/Protobuf testing"
         :desc ["Write a code generator based on the protobuf definition and the officialy generated Java code to simplify writing test scripts"]
         :tools ["Java" "Kotlin" "GRPC/Protobuf"  "Gradle"]}
        {:type "project", :title "Write wrapper library to standardize test scripts"
         :desc ["Introduce abstraction that standardize test scripts"
                "Deal with code distribution via Gitlab registry"]
         :tools ["Java" "Selenium" "Appium" "RestAssured" "Gradle"]}
        {:type "project", :title "Write load test scripts"
         :desc ["Write scripts using gatling, write Dockerfiles, and help preparing load test environment"]
         :tools ["Scala" "Docker" "Kuberneter" "GCP" "Gitlab CI"]}]}]}]
   :education
   [{:title "Bandung Institute of Technology"
     :subtitle "Bachelor of Computer Science"
     :type "university", :time "2015 - 2020"
     :extra "graduated with 3.41 GPA"}]
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
     :items ["HTMX" "Tailwind" "Bootstrap" "React" "Reagent "]}
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

(defn CenterTitle [title]
  [:div {:style {:textAlign "center" :verticalAlign "center" :paddingTop 8 :paddingBottom 8 :backgroundColor "white"}}
   [:h4 {:style {:marginBottom 0 :paddingBottom 2}} title]])

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
       {:avatar (as-element [:> Avatar {:src (:profile-url content) :size 128}])
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
  [idx {:keys [title extra desc time tools]}]
  [:> Card {:title title :size "small" :key (str idx)
            :extra (as-element [:> Text extra])}
   [:> Row
    [:> Col {:span 15}
     [:> Text
      (if (vector? desc)
        [:> List {:dataSource (->> desc (map #(as-element [:> List.Item %]))) :renderItem identity}]
        desc)]]
    [:> Col {:span 1}]
    [:> Col {:span 8}
     [:div {:style {:fontSize 12 :marginBottom 8}}
      [:> Text {:strong true} time]]
     [:div
      (->> tools
           (map-indexed (fn [idx tool]
                          [:> Tag {:color (tool-color tool) :key (str idx)
                                   :style {:marginBottom 4}} tool])))]]]])

(defmethod ProjectCard "group"
  [idx {:keys [title extra items]}]
  [:> Card {:title title :extra extra :size "small" :key (str idx)}
   (->> items
        (map-indexed ProjectCard))])

(defmethod ProjectCard "group2"
  [idx {:keys [title extra items]}]
  [:> Card {:title title :extra extra :size "small" :key (str idx)}
   (let [size (count items) half (Math/ceil (/ size 2))
         row-1 (-> items (subvec 0 half))
         row-2 (-> items (subvec half))]
     [:> Row
      [:> Col {:span 12} (->> row-1 (map-indexed ProjectCard))]
      [:> Col {:span 12} (->> row-2 (map-indexed ProjectCard))]])])

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
          (CenterTitle "Experience 💻")
          [:<>
           (->> (:projects content) (map-indexed ProjectCard))
           [:> Row
            [:> Col {:span 12} (Language)]
            [:> Col {:span 12} (Educations)]]]]]
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