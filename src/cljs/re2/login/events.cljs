(ns re2.login.events
    (:require [re-frame.core :as re-frame]
              [ajax.core :as ajax]
              [day8.re-frame.http-fx]
              [re2.login.db :as db]))

(re-frame/reg-event-db
 :initialize-db/login
 (fn  [rootdb _]
   (assoc rootdb :logindb db/default-db)))

 (re-frame/reg-event-fx        ;; <-- note the `-fx` extension
   :http-login        ;; <-- the event id
   (fn                ;; <-- the handler function
     [{db :db} _]     ;; <-- 1st argument is coeffect, from which we extract db

     ;; we return a map of (side) effects
     {:http-xhrio {:method          :get
                   :uri             "http://json.my-endpoint.com/blah"
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [:process-response]
                   :on-failure      [:bad-response]}
      :db  (assoc db :loading? true)}))
