(ns re2.root.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 :root/name
 (fn [db]
   (:name db)))


(re-frame/reg-sub
  :root/active-panel
  (fn [db]
    (:active-panel db)))
