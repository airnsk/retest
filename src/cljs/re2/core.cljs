(ns re2.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re-frisk.core :refer [enable-re-frisk!]]
              [re2.root.events]
              [re2.root.subs]
              [re2.root.views :as views]
              [re2.config :as config]
              [re2.login.core :as login-core]
              [re2.main.core :as main-core]
            ))




(comment
(use 'figwheel-sidecar.repl-api )
; (start-figwheel! figwheel-config)
(cljs-repl)
)

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db/root ])
  (re-frame/dispatch [:initialize-db/login ])
  (re-frame/dispatch [:initialize-db/main ])
  (dev-setup)
  (mount-root))
