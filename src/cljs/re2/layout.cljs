(ns re2.layout
  (:require [re-frame.core :as re-frame]
            [cljsjs.material-ui]
            [cljs-react-material-ui.core :refer [get-mui-theme color]]
            [cljs-react-material-ui.reagent :as ui]
            [cljs-react-material-ui.icons :as ic]
            [re2.views :as views]
            [re2.controllers :as controllers]
            ))

(defn main-layout []
  (let [page (re-frame/subscribe [:active-panel])]
    (fn []
      (condp = @page
          :main-panel [views/main-view]
          :login-panel [views/login-view]
          :cladr-panel [views/cladr-view]
          ))))
