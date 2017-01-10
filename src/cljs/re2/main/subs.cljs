(ns re2.main.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :maintab
 (fn [db]
   (:maintab db)))
