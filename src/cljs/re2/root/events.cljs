(ns re2.root.events
    (:require [re-frame.core :as re-frame]
              [re2.root.db :as db]))


(re-frame/reg-event-db
 :initialize-db/root
 (fn  [_ _]
   db/default-db))


(re-frame/reg-event-fx
  :root/set-panel
  (fn [{db :db} [_ panel]]

      {:db  (assoc db :active-panel panel) }))
