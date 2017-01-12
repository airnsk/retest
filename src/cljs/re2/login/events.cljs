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
   :login/login
   (fn
     [{db :db} [_ login?]]
     (if login?

     {:db  (assoc db :login true :active-panel :main-panel)  }
     {:db  (assoc db :login false :active-panel :login-panel) } ) ) )



 (re-frame/reg-event-fx
   :login/http-login
   (fn
     [{db :db} _]
     {:http-xhrio {:method          :post
                   :params          {:login config/login :password config/password}
                   :uri             (str config/uri "/account/login")
                   :format          (ajax/url-request-format)
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [:login/login-data]
                   :on-failure      [:login/login-error]}
      :db  (assoc db :loading? true)
      }))

(re-frame/reg-event-fx
 :login/login-data
 (fn  [{rootdb :db} [_ data]]
   (let [logindb (:logindb rootdb)
        newlogindb (assoc logindb :login-data data)]
   {:db (assoc rootdb :logindb newlogindb :loading? false)
    :dispatch [:login/login true]})))

 (re-frame/reg-event-fx
  :login/login-error
  (fn  [{rootdb :db} [_ data]]
    (let [logindb (:logindb rootdb)
         newlogindb (assoc logindb :error-data data)]
         {:db (assoc rootdb :logindb newlogindb :loading? false)
          :dispatch [:login/login false]})))
