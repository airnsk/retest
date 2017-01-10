(ns re2.login.events
    (:require [re-frame.core :as re-frame]
              [re2.login.db :as db]))

(re-frame/reg-event-db
 :initialize-db/login
 (fn  [rootdb _]
   (assoc rootdb :logindb db/default-db)))
(println "login event reged")
