(ns re2.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))


(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))


(re-frame/reg-sub
  :active-panel
  (fn [db]
    (:active-panel db)))

(re-frame/reg-sub
 :login
 (fn [db]
   (:login db)))


(re-frame/reg-sub
  :maintab
    (fn [db]
      (:maintab db)))
