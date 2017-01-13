(ns re2.root.events
    (:require [re-frame.core :as re-frame]
              [re2.root.db :as db]))


(re-frame/reg-event-db
 :initialize-db/root
 (fn  [_ _]
   db/default-db))
