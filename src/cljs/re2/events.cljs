(ns re2.events
    (:require [re-frame.core :as re-frame]
              [accountant.core :as accountant]
              [re2.db :as db]
              [re2.config :as config]
              [ajax.core :as ajax]
              [re2.utils :as utils]
              [day8.re-frame.http-fx]))


(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))


(re-frame/reg-fx
 :set-page
 (fn [page]
      ;; we don't bother with that nil value
    (accountant/navigate! page)
    ))

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
  :http-get-documents
  (fn [{db :db} _]
      {:http-xhrio {:method          :get
                    ;;:params          {:login config/login :password config/password}
                    :uri             (str utils/url_documents )
                    :response-format (ajax/json-response-format {:keywords? true})
                    :on-success      [:get-documents-data-success]
                    :on-failure      [:get-documents-data-error]}
       :db  (assoc db :documents-loading? true)}))






(re-frame/reg-event-fx
 :http-login
 (fn [{db :db} _]
     {:http-xhrio {:method          :post
                   :params          {:login config/login :password config/password}
                   :uri             (str utils/uri "/account/login")
                   :format          (ajax/url-request-format)
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [:login-data]
                   :on-failure      [:login-error]}
      :db  (assoc db :loading? true)}))

(re-frame/reg-event-fx
 :http-logout
 (fn [{db :db} _]
     {:http-xhrio {:method          :get
                   :uri             (str utils/uri "/account/logout")
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [:logout-data]
                   :on-failure      [:logout-error]}
      :db  (assoc db :loading? true)}))



(re-frame/reg-event-fx
 :get-documents-data-success
 (fn  [{rootdb :db} [_ data]]
   (let [documentsdb (:documentsdb rootdb)
        newdocdb (assoc documentsdb :documents-list data)]
   {:db (assoc rootdb :documentsdb newdocdb :documents-loading? false)

    })))
(re-frame/reg-event-fx
 :get-documents-data-error
 (fn  [{rootdb :db} [_ data]]
   (let [documentsdb (:documentsdb rootdb)
        newdocdb (assoc documentsdb :documents-list {} :error-data data)]
   {:db (assoc rootdb :documentsdb newdocdb :documents-loading? false)

    })))



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




(re-frame/reg-event-fx
 :change-cladr
 (fn [{rootdb :db} [_ data value]]
   (let [cladrdb (:cladrdb rootdb)]
   (condp = data
     :region  (let [newcladrdb (associn cladrdb  :region {:value value} :raion {} :gorod {} :ulica {} :dom {})]
                    {:db (assoc rootdb   newcladrdb )
                     :dispatch [:http-cladr {"0" {"value" value} "go" "0"}]})

     :raion (let [newcladrdb (assoc cladrdb :raion {} :gorod {} :ulica {} :dom {})]
                    {:db (assoc rootdb :cladrdb newcladrdb )
                     :dispatch [:http-cladr {"0" {"code" (:cod (:region cladrdb))}
                                             "1" {"value" value} "go" "1"}]})

     :gorod (let [newcladrdb (assoc cladrdb :gorod {} :ulica {} :dom {})]
                   {:db (assoc rootdb :cladrdb newcladrdb )
                    :dispatch [:http-cladr {"0" {"code" (:cod (:region cladrdb))}
                                            "1" {"code" (:cod (:raion cladrdb))}
                                            "2" {"value" value} "go" "2"}]})

     :ulica (let [newcladrdb (assoc cladrdb :ulica value :dom {})]
                    {:db (assoc rootdb :cladrdb newcladrdb )
                     :dispatch [:http-cladr {"0" {"code" (:cod (:region cladrdb))}
                                             "1" {"code" (:cod (:raion cladrdb))}
                                             "2" {"code" (:cod (:gorod cladrdb))}
                                             "3" {"value" value} "go" "3"}]})

     :dom (let [newcladrdb (assoc cladrdb :dom value)]
                   {:db (assoc rootdb :cladrdb newcladrdb )
                    :dispatch [:http-cladr {"0" {"code" (:cod (:region cladrdb))}
                                            "1" {"code" (:cod (:raion cladrdb))}
                                            "2" {"code" (:cod (:gorod cladrdb))}
                                            "3" {"code" (:cod (:ulica cladrdb))}
                                            "4" {"value" value} "go" "4"}]})




                    ))))


(re-frame/reg-event-fx
 :http-cladr
 (fn [{db :db} [_ value]]
   (let [cladrdb (:cladrdb db)]
   (println value)
     {:http-xhrio {:method          :post
                   :params          value
                   :uri             (str utils/uri-cladr )
                   :format          (ajax/json-request-format )
                   :response-format (ajax/json-response-format {:keywords? true})
                   :on-success      [:cladr-data]
                   :on-failure      [:cladr-error]}
      :db  (assoc db :loading? true)})))
