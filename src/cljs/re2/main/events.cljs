(ns re2.main.events
    (:require [re-frame.core :as re-frame]
              [re2.main.db :as db]))

(re-frame/reg-event-db
 :initialize-db/main
 (fn  [rootdb _]
   (assoc rootdb :maindb db/default-db)))
