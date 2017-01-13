(ns re2.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re-frisk.core :refer [enable-re-frisk!]]
              [re2.root.events]
              [re2.root.subs]
              [re2.root.views :as root-views]
              [re2.config :as config]
              [re2.login.core :as login-core]
              [re2.main.core :as main-core]
              [re2.main.views :as main-views]
              [re2.login.views :as login-views]))


(comment
(use 'figwheel-sidecar.repl-api )
; (start-figwheel! figwheel-config)
(cljs-repl)
)


(defn check-auth []
  (re-frame/dispatch [:login/login true ]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (println "dev mode")))


(defn high-level-view
  []
  (let [active  (re-frame/subscribe [:root/active-panel])]
    (fn []
      [:div
       [:div.title   "Heading"]
       (condp = @active                ;; or you could look up in a map
         :login-panel   [login-views/login-panel]
         :main-panel   [main-views/main-panel])])))


 (defn check-s []
        (let [check (re-frame/subscribe [:root/active-panel])]
             (println @check)))


(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [high-level-view]
                  (.getElementById js/document "app")))


(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db/root ])
  (re-frame/dispatch [:initialize-db/login ])
  (re-frame/dispatch [:initialize-db/main ])
  (dev-setup)
  (check-auth)
  (mount-root)
  (reagent/track! check-s))
