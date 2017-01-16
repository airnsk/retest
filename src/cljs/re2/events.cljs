(ns re2.events
    (:require [re-frame.core :as re-frame]
              [accountant.core :as accountant]
              [re2.db :as db]
              [re2.config :as config]
              [ajax.core :as ajax]
              [day8.re-frame.http-fx]))


(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))


(re-frame/reg-fx
 :set-page
 (fn [page]
      ;; we don't bother with that nil value
    (accountant/navigate! page)))

(re-frame/reg-fx
 :GryaznyVanya
 (fn [page]
      ;; we don't bother with that nil value
    (js/console.log "Vanya rules")))



(re-frame/reg-event-fx
 :Vanya
 (fn [{db :db} _]
      ;; we don't bother with that nil value
      {:db  (assoc db :maintab "Vanya") }
    ))


(re-frame/reg-event-fx
  :set-panel
  (fn [{db :db} [_ panel]]
      {:db  (assoc db :active-panel panel) }))


(re-frame/reg-event-fx
 :login
 (fn [{db :db} [_ login?]]
   (if login?
     {:db  (assoc db :login true )
     :set-page "/"}
     {:db  (assoc db :login false )
       :set-page "/login" })))


(re-frame/reg-event-fx
 :http-login
 (fn [{db :db} _]
     {:http-xhrio {:method          :post
                   :params          {:login config/login :password config/password}
                   :uri             (str config/uri "/account/login")
                   :format          (ajax/url-request-format)
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [:login-data]
                   :on-failure      [:login-error]}
      :db  (assoc db :loading? true)}))

(re-frame/reg-event-fx
 :http-logout
 (fn [{db :db} _]
     {:http-xhrio {:method          :get
                   :uri             (str config/uri "/account/logout")
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [:logout-data]
                   :on-failure      [:logout-error]}
      :db  (assoc db :loading? true)}))




(re-frame/reg-event-fx
 :login-data
 (fn  [{rootdb :db} [_ data]]
   (let [logindb (:logindb rootdb)
        newlogindb (assoc logindb :login-data data)]
   {:db (assoc rootdb :logindb newlogindb :loading? false)
    :dispatch [:login true]
    })))

(re-frame/reg-event-fx
 :logout-data
 (fn  [{rootdb :db} [_ data]]
   (let [logindb (:logindb rootdb)
        newlogindb (assoc logindb :login-data data)]
   {:db (assoc rootdb :logindb newlogindb :loading? false)
    :dispatch [:login false]
    })))




(re-frame/reg-event-fx
  :login-error
  (fn  [{rootdb :db} [_ data]]
    (let [logindb (:logindb rootdb)
         newlogindb (assoc logindb :error-data data)]
         {:db (assoc rootdb :logindb newlogindb :loading? false)
          :dispatch [:login true]
          :set-page "/login"})))
