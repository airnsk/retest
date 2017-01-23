(ns re2.db)

(def default-db
  {:name "re-frame"
  :clicks 0
  :cladrdb {:region {:cod ""
                     :val ""}
            :raion {:cod ""
                    :val ""}
            :gorod {:cod ""
                    :val ""}
            :ulica {:cod ""
                    :val ""}
            :dom {:cod ""
                  :val ""} }
  :loading? false
  :login false
  :active-panel :login-panel
  :maintab "documents"
  :logindb {:login-data ""}
  :Vasya ""
  :documentsdb {}
  :documents-loading? false})
