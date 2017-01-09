(ns re2.events
    (:require [re-frame.core :as re-frame]
              [re2.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
