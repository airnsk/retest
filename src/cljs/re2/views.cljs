(ns re2.views
    (:require [re-frame.core :as re-frame]
              [reagent.core :as reagent :refer [atom]]
              [goog.events :as events]
              [cljsjs.material-ui]
              [cljs-react-material-ui.core :refer [get-mui-theme color]]
              [cljs-react-material-ui.reagent :as ui]
              [cljs-react-material-ui.icons :as ic]
              [re2.shared.components.table :as vkotable] ))


(def mui
  {:mui-theme (get-mui-theme {:palette {:text-color (color :black)
                                         :primary1-color (color :deep-orange-a100)
                                         :secondary1-color (color :blue200) }})})

(defn login-view []

[:div

   [ui/mui-theme-provider mui
   [ui/paper {:style {:width 650 :margin 20 :padding 20}
              :zDepth 3}
   [:div [:h2 "please login"]
    [:div [:a {:href "/cladr"} "go to cladr page"]]

   [:form
  [ui/text-field {:id "login" :hintText "dfgdfg" :floatingLabelText "login"
                  :on-change #(re-frame/dispatch [:changetext (-> % .-target .-value)]) }]
  [ui/text-field {:id "password"  :floatingLabelText "password"}]
  [ui/raised-button {:label "Войти" :secondary true

                      :on-touch-tap #(re-frame/dispatch [:http-login])}]]]]]])


(defn home-page []
  [:div [:h2 "Welcome to vko111177"]
  [ui/mui-theme-provider mui
   [ui/raised-button {:label "Blue button" :secondary true
                      ;;:on-touch-tap #(reset! userauth true)
                      } ]]
   [:div [:a {:href "/about"} "go to about page"]]
   [:div [:a {:href "/list"} "go to list page"]]])


(defn about-page []
  [:div [:h2 "About vko1"]
  [ui/mui-theme-provider
   {:mui-theme (get-mui-theme {:palette {:text-color (color :blue200)}})}
   [ui/raised-button {:label "Blue button"}]]
   [:div [:a {:href "/"} "go to the home page"]]])


(defn list-mail-page []
 [:div [:h2 "list-mail-page vko1"]
  [:div [:a {:href "/"} "go to the home page"]]])


(defn main-view []
  [:div
  [ui/mui-theme-provider
   {:mui-theme (get-mui-theme
                 {:palette {:text-color (color :black)
                          :primary1-color (color :pinkA200)
                          :accent1-color (color :green600) }})}
   [:div
    [ui/app-bar {:title "ПОЧТА" :style {:margin-bottom 15}
                  :icon-element-right
                   (reagent/as-element [ui/icon-button
                                     {:on-touch-tap #(re-frame/dispatch [:http-logout])}
                                      (ic/action-account-balance-wallet)
                                      ])}]
    [ui/paper {:style { :display "inline-block" :float "left" :margin-right 50 }}
      [ui/list
      [ui/list-item {:primaryText "Все" :left-icon (ic/content-inbox)}]
      [ui/list-item {:primaryText "Входящие" :left-icon (ic/content-mail)}]
      [ui/list-item {:primaryText "Исходящие" :left-icon (ic/content-send)}]
      [ui/list-item {:primaryText "Черновики" :left-icon (ic/content-drafts)}]
      [ui/list-item {:primaryText "Корзина" :left-icon (ic/content-delete-sweep)}]
      ]
    ]
    [:div {:style {}}
    [vkotable/vktable @(re-frame/subscribe [:documents-list])] ] ]]])



(def tags
  [{:textkey "ActionScript" :valuekey 0}
  {:textkey "AppleScript" :valuekey 1}
  {:textkey "Asp" :valuekey 2}
  {:textkey "BASIC" :valuekey 3}
  {:textkey "C" :valuekey 4}
  {:textkey "C++" :valuekey 5}
  {:textkey "Clojure" :valuekey 6}
  {:textkey "COBOL" :valuekey 7}
])

(def tagsconfig
  {:text :textkey :value :valuekey})


(defn create_event_js
  "Doesn't use GClosure, to be more realistic"
  [code]

  (.keydown js/Podium  9)


  )








(defn cladr-view []
(this-as this
  [:div [:h2 "Welcome to cladr forms"]
   [:div [:a {:href "/"} "go to main page"]]


   [ui/mui-theme-provider mui
   [ui/paper {:id "10" :style {:width 330 :margin 20 :padding 20}
              :zDepth 3}
   [:div



  [ui/auto-complete {:floatingLabelText "регион"
                      :dataSource tags
                      :dataSourceConfig tagsconfig
                      :onUpdateInput #(re-frame/dispatch [:change-cladr :region %])
                      :onNewRequest #(do (re-frame/dispatch [:set-cladr-value %] )
                                        (.focus (.getElementById js/document "2"))) }]

  [ui/auto-complete { :id "2"
                      :floatingLabelText "район"
                      ;;:disabled true
                      :dataSource tags
                      :onUpdateInput #(re-frame/dispatch [:change-cladr :raion %])
                      :onNewRequest #(.focus (.getElementById js/document "3")) }]


[ui/auto-complete { :id "3"
                    :floatingLabelText "город"
                    ;;:disabled true
                    :dataSource tags
                    :onUpdateInput #(re-frame/dispatch [:change-cladr :gorod %])
                    :onNewRequest #(.focus (.getElementById js/document "4")) }]

[ui/auto-complete { :id "4"
                    :floatingLabelText "улица"
                    :dataSource tags
                    :onUpdateInput #(re-frame/dispatch [:change-cladr :ulica %])
                    :onNewRequest #(.focus (.getElementById js/document "5")) }]

[ui/auto-complete { :id "5"
                    :floatingLabelText "дом"
                    :dataSource tags
                    :onUpdateInput #(re-frame/dispatch [:change-cladr :dom %])
                    :onNewRequest #(.focus (.getElementById js/document "6")) }]




  [:div {:id "7" :style {:position "relative" :margin-bottom 30 :padding-top 20}}
   [ui/flat-button  {:id "5" :label "отправить" :style {:position "absolute" :right 0}}]
  ]



  ]]]]

  ))
