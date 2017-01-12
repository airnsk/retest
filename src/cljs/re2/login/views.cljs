(ns re2.login.views
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
            :on-click #(re-frame/dispatch [:login/login true])
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



(defn login-panel []
  (let [name (re-frame/subscribe [:root/name])]
    (fn []
      [:div "Hello from " @name
      [:div [login-page]]]
      )))
