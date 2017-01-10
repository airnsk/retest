(ns re2.login.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [re2.login.events]
              [re2.login.subs]
              [re2.login.views :as views]

            ))






(re-frame/dispatch [:initialize-db/login])
(println "dblogin dispatched")




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



  )
