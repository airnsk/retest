(ns re2.main.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re2.main.events]
              [re2.main.subs]
              [re2.main.views :as views]

            ))




(comment
(use 'figwheel-sidecar.repl-api )
; (start-figwheel! figwheel-config)
(cljs-repl)


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
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
)
