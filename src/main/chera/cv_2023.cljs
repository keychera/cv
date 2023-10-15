(ns chera.cv-2023
  (:require ["antd" :refer [Card Col Space Row Tag Typography Tabs]]
            ["react-responsive" :refer [useMediaQuery]]
            [reagent.core :refer [as-element]]))

(def Text Typography.Text)

(defn Link [{:keys [href children]}]
  [:a {:href href :target "_blank" :rel "noopener noreferrer"} (or children href)])

(def content
  {:name "Kevin Erdiza Yogatama"
   :about [:<>
           [:p "I am a software engineer with an experience in a Test Automation. Within my job, I also explored and implemented backend solutions to solve testing needs."]
           [:p "I can create test automation from either existing stack or building one from scratch. I can also cooperate across different team and I am the kind of person who takes initiative to solve problems."]]
   :profile-url "me_2019.png"
   :contact
   [{:name "twitter", :display "@keychera", :link "https://twitter.com/keychera"}
    {:name "github", :display "keychera", :link "https://github.com/keychera"}
    {:name "linkedin", :display "keychera", :link "https://www.linkedin.com/in/keychera/"}]
   :projects
   [{:title "Virgo"
     :subtitle "A fintech company that has several product including E-money app, B2B payment-related services, and personal money management app."
     :extra "Oktober 2020 - September 2023"
     :items
     [{:title "Test Automation Engineer"
       :extra "April 2022 - September 2023"
       :time "April 2022 - September 2023"
       :items
       [{:type "project", :title "I proposed and developed a pipeline/queue system to aid automated test execution"
         :desc
         ["I implemented custom pipeline/queue server to compile and execute kotlin test scripts."
          "I implemented a test report aggregator, small-scale internal dashboard, and web helper tools in the same server."
          "I decided on a stack that focus on the backend side by lowering front-end development effort with HTMX and Bootstrap."
          "I integrated the server with several development tools such as Gitlab scheduling and Slack."]
         :tools ["Clojure" "HTMX" "SQLite" "Bootstrap" "Kubernetes" "GCP" "Oauth2-proxy"]}
        {:type "project", :title "I developed and maintain a custom testing framework for my QA team"
         :desc
         ["I drove test script standardization, configurability, and readability to the team."
          "I dealt with code distribution (library publishing via Gitlab registry)."
          "I simplified the build system for test scripts (Maven pom.xml generator)."
          "I built a base framework that is reusable for multiple entities."
          "I trained other member to use and help contribute to this framework."]
         :tools ["Kotlin" "Junit5" "Selenium" "Appium" "RestAssured" "GRPC/Protobuf" "Maven" "Gradle" "Clojure/Babashka" "Gitlab CI"]}]}
      {:title "Junior Test Automation Engineer"
       :extra "Oktober 2020  - April 2022"
       :items
       [{:type "project", :title "I introduced Kotlin and spearheaded migration to Kotlin for test scripting"
         :desc ["I proposed Kotlin to write test scripts to the team."
                "I orchestrated code migration and trained other members to use Kotlin for testing."]
         :tools ["Kotlin" "Gradle"]}
        {:type "project", :title "I wrote several code generators to help GRPC/Protobuf testing."
         :desc ["I wrote a code generator that transform protobuf definition into a helpful code wrapper to simplify writing GRPC testing"]
         :tools ["Java" "Kotlin" "GRPC/Protobuf"  "Gradle"]}
        {:type "project", :title "I wrote test automation scripts and implemented a number of wrapper"
         :desc ["I wrote and maintain test scripts withing Java and Junit5 stack."
                "I designed an abstraction that standardize test scripts and introduced them to the team that is still used until now."
                "I dealt with code distribution with Gradle, Maven-publishing, and Gitlab registry."]
         :tools ["Java" "Selenium" "Appium" "RestAssured" "Gradle"]}
        {:type "project", :title "Write load test scripts"
         :desc ["I wrote load test scripts using Gatling, and help preparing load test environment with Docker and Kubernetes."]
         :tools ["Scala" "Docker" "Kubernetes" "GCP" "Gitlab CI"]}]}]}]
   :education
   [{:title "Bandung Institute of Technology"
     :subtitle "Bachelor of Computer Science"
     :type "university", :time "2015 - 2020"
     :extra "graduated with 3.41 GPA"}]
   :skills
   [{:title "JVM Languages"
     :items ["Kotlin" "Clojure" "Java"]}
    {:title "Server"
     :items ["Ring-Jetty" "http-kit"]}
    {:title "Deployment"
     :items ["Docker" "Kubernetes" "GCP" "Gitlab CI"]}
    {:title "Databases"
     :items ["SQLite" "MySQL"]}
    {:title "Testing"
     :items ["Junit5" "Selenium" "Appium" "RestAssured"]}
    {:title "Front-end"
     :items ["HTMX" "Tailwind" "Bootstrap" "React" "Reagent"]}
    {:title "Scripting"
     :items ["Clojure/Babashka" "Bash"]}
    {:title "Design tools", :items ["Figma" "Penpot"]}
    {:title "Versioning tool", :items ["git"]}]
   :language
   [[:<> "I am comfortable using " [:> Text {:strong true} "English"] " daily"]
    [:<> [:> Text {:strong true} "Japanese"] " with JLPT N1 qualification and"
     [:br] "I have been practicing weekly using Italki"
     [:br] (Link {:href "https://www.italki.com/user/18089807"})]]
   :extra
   [[:<> "I created an open source tool for Hot-reloading Clojure/Babahska + HTMX web projects." [:br]
     "repo: " (Link {:href "https://github.com/keychera/panas.reload"})]
    [:<> "I created a Japanese Number converter, language learning tool webapp." [:br]
     "web: " (Link {:href "https://keychera.github.io/kazoeru/"})]
    [:<> "I created this very CV as a static web, which is originally a React project, but converted to ClojureScript/Reagent." [:br]
     "web: " (Link {:href "https://keychera.github.io/cv/2023"})]]})

(defn CenterTitle [title]
  [:div {:style {:textAlign "center" :verticalAlign "center"  :paddingBottom 8 :backgroundColor "white"}}
   [:h4 {:style {:marginBottom 0 :paddingBottom 2}} title]])

(defn EducationCard [idx {:keys [title subtitle type time extra]}]
  [:> Card {:size "small" :style {:fontSize 12} :key (str idx)}
   [:> Row
    [:> Col {:span 18}
     [:div [:> Text {:strong true} title]]
     [:div [:> Text {:italic true} subtitle]]
     [:div [:> Text {:code true} type] "・" time]]
    [:> Col {:span 6} extra]]])

(defn Profile []
  [:> Card
   {:actions (->> content :contact
                  (mapv (fn [{:keys [name display link]}]
                          (as-element
                           [:> Text
                            [:> Text {:strong true} name]
                            (Link {:href link :children display})]))))}
   [:div
    [:h3 (:name content)]
    [:p {:style {:color "#00000073"}} (:about content)]]])

(defn Educations []
  [:<>
   (CenterTitle "Education 📚")
   [:> Card {:size "small"}
    (->> content :education
         (map-indexed EducationCard))]])

(def tool-color
  {"Clojure" "green"
   "Clojure/Babashka" "green"
   "Reagent" "green"
   "http-kit" "green"
   "Ring-Jetty" "green"
   "Penpot" "green"
   "Java" "red"
   "Scala" "red"
   "Kotlin" "purple"
   "Junit5" "cyan"
   "HTMX" "cyan"
   "SQLite" "cyan"
   "MySQL" "cyan"
   "Bootstrap" "cyan"
   "Tailwind" "cyan"
   "Selenium" "volcano"
   "Appium" "volcano"
   "RestAssured" "volcano"
   "GRPC/Protobuf" "volcano"
   "Gradle" "geekblue"
   "Maven" "geekblue"
   "Docker" "gold"
   "Kubernetes" "gold"
   "Gitlab CI" "gold"
   "GCP" "gold"
   "React" "blue"
   "Oauth2-proxy" "lime"
   "Figma" "lime"})

(defn ProjectCard [idx {:keys [title desc tools]}]
  [:> Card {:title title :size "small" :key (str idx)}
   [:> Text
    (if (vector? desc)
      [:ul (->> desc (map (fn [i] [:li i])))]
      desc)
    [:div
     (->> tools
          (map-indexed (fn [idx tool]
                         [:> Tag {:color (tool-color tool) :key (str idx)
                                  :style {:marginBottom 4}} tool])))]]])

(defn SkillCard [idx {:keys [title items]} vertical?]
  [:> Card {:size "small" :key (str idx)}
   [:> Space {:direction (if (and vertical? (> (count items) 3)) "vertical" "horizontal")}
    [:> Text {:strong true :autoSize true} title]
    [:div
     (->> items
          (map-indexed (fn [idx item]
                         [:> Tag {:color (tool-color item) :key (str idx)
                                  :style {:marginBottom 4}} item])))]]])

(defn ExtraCard [idx value]
  [:> Card {:size "small" :key (str idx)} value])

(defn Skills [vertical?]
  [:<>
   (CenterTitle "Technical skills 🔧")
   [:> Card {:size "small"}
    (->> content :skills
         (map-indexed (fn [idx skill] (SkillCard idx skill vertical?))))]])

(defn Language []
  [:<>
   (CenterTitle "Language 📙")
   [:> Card {:size "small"}
    (->> content :language
         (map-indexed ExtraCard))]])

(defn Projects []
  [:<>
   (CenterTitle "Project Experience ➕")
   [:> Card {:size "small"}
    (->> content :extra
         (map-indexed ExtraCard))]])

(defn Experiences []
  [:<>
   (CenterTitle "Professional Experiences 💻")
   (->> (:projects content)
        (map (fn [{job-items :items
                   :keys [title subtitle extra]}]
               [:div {:style {:padding "1rem"}}
                [:div {:style {:display "flex" :align-items "center"}}
                 [:h3 title] [:div {:style {:margin-left "0.5rem"}} extra]]
                [:div subtitle]
                [:div
                 (->> job-items
                      (map (fn [{proj-items :items
                                 :keys [title extra]}]
                             [:<>
                              [:> Space {:align "center"} "≫" [:h4 title] [:span extra]]
                              (->> proj-items
                                   (map-indexed ProjectCard))])))]])))])

(defn cv []
  (set! (.. js/document -title) "keychera's 2023 CV")
  (let [desktop?    (useMediaQuery (clj->js {:minWidth 1224}))
        tablet?     (useMediaQuery (clj->js {:minWidth 900 :maxWidth 1224}))]
    [:<>
     (cond
       desktop?
       [:<>
        [:> Row {:align "center"}
         [:> Col {:span 6}
          (Profile)
          (Language)
          (Skills false)
          (Educations)]
         [:> Col {:span 18}
          (Experiences)
          (Projects)]]]

       tablet?
       [:> Row {:align "center"}
        [:> Col {:span 8}
         (Profile)
         (Language)
         (Skills true)
         (Educations)]
        [:> Col {:span 16}
         (Experiences)
         (Projects)]]

       :else ;; smaller than `tablet?`
       [:<>
        (Profile)
        [:> Tabs
         {:defaultActiveKey "2" :type "card" :centered true
          :items [{:label "📚" :key "1"
                   :children (as-element [:<> (Language) (Educations)])}
                  {:label "💻" :key "2"
                   :children (as-element [:<> (Experiences) (Projects)])}
                  {:label "🔧" :key "3"
                   :children (as-element [:<> (Skills false)])}]}]
        [:> Card]])]))

(defn root []
  [:f> cv])