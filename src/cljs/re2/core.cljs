(ns re2.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [re2.events]
              [re2.subs]
              [re2.utils :as utils]
              [re2.controllers :as controllers]
              [re2.layout :as layout]
               ))




(secretary/defroute "/" []
  (controllers/main-page))

(secretary/defroute "/login" []
  (controllers/login-page))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render   [layout/main-layout ]
                  (.getElementById js/document "app")))


(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (utils/dev-setup)
  (utils/check-auth)
  (accountant/configure-navigation!)
  (accountant/dispatch-current!)
  (mount-root)
  (reagent/track! utils/check-s))
