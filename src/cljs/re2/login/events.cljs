(ns re2.login.events
    (:require [re-frame.core :as re-frame]
              [ajax.core :as ajax]
              [day8.re-frame.http-fx]
              [re2.login.db :as db]
              [re2.config :as config]))





(re-frame/reg-event-db
 :initialize-db/login
 (fn  [rootdb _]
   (assoc rootdb :logindb db/default-db)))

 (re-frame/reg-event-fx
   :http-login
   (fn
     [{db :db} _]
     {:http-xhrio {:method          :get
                   :uri             (str config/uri "/users/1")
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [:login/login-data]
                   :on-failure      [:bad-response]}
      :db  (assoc db :loading? true)
      }))

(re-frame/reg-event-db
 :login/login-data
 (fn  [rootdb [_ data]]
   (let [logindb (:logindb rootdb)
        newlogindb (assoc logindb :login-data data)]
   (assoc rootdb :logindb newlogindb :loading? false ))))
