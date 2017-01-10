(ns re2.main.views
    (:require [re-frame.core :as re-frame]
              [reagent.core :as reagent :refer [atom]]
              [cljsjs.material-ui]
              [cljs-react-material-ui.core :refer [get-mui-theme color]]
              [cljs-react-material-ui.reagent :as ui]
              [cljs-react-material-ui.icons :as ic]
              [re2.shared.components.table :as vkotable]))



(defn login-page []
 [:div [:h2 "please login"]
  [:div [:a {:href "#"
            ;;:on-click #(reset! userauth true)
  } "login"]]])




(defn home-page []
  [:div [:h2 "Welcome to vko111177"]
  [ui/mui-theme-provider
   {:mui-theme (get-mui-theme {:palette {:text-color (color :blue200)
                                          :primary1-color (color :deep-orange-a100)
                                          :secondary1-color (color :blue200) }})}
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




(defn template-page []

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
                                    (ic/action-account-balance-wallet)])}
                                    ]
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
    vkotable/vtable                ]
    [:div "Hello"]
    [ui/mui-theme-provider
     {:mui-theme (get-mui-theme {:palette {:text-color (color :blue200)}})}
     [ui/raised-button {:label "Blue button"}]]
    (ic/action-home {:color (color :grey600)})
    [ui/raised-button {:label        "Click me"
                        :icon         (ic/social-group)
                        :secondary false
                        :on-touch-tap #(println "clicked")}]]]])

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div "Hello from " @name
      [:div [template-page]]]
      )))
