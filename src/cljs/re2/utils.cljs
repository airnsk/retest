(ns re2.utils
    (:require [re-frame.core :as re-frame]
              [re-frisk.core :refer [enable-re-frisk!]]
              [re2.config :as config]))


(def uri "https://integrationb2b.iitrust.ru/edi")
(def uri-cladr "https://test.iitrust.lk/api/kladr")
(def url_documents "https://integrationb2b.iitrust.ru/edi/documents")

(comment
(use 'figwheel-sidecar.repl-api )
; (start-figwheel! figwheel-config)
(cljs-repl)
)


(defn check-auth []
  (re-frame/dispatch [:login false ]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (println "dev mode")))

 (defn check-s []
        (let [check (re-frame/subscribe [:active-panel])]
             (println @check)))
