(ns re2.shared.components.table
    (:require
              [cljsjs.material-ui]
              [cljs-react-material-ui.core :refer [get-mui-theme color]]
              [cljs-react-material-ui.reagent :as ui]
              [cljs-react-material-ui.icons :as ic]
              [re-frame.core :as re-frame]
              [reagent.core :as reagent :refer [atom]]))


(comment (
primary1Color cyan500,
primary2Color cyan700,
primary3Color grey400,
accent1Color pinkA200,
accent2Color grey100,
accent3Color grey500,
textColor darkBlack,
secondaryTextColor fade(darkBlack, 0.54),
alternateTextColor white,
canvasColor white,
borderColor grey300,
disabledColor fade(darkBlack, 0.3),
pickerHeaderColor cyan500,
clockCircleColor fade(darkBlack, 0.07),
shadowColor fullBlack)
)


(defn status [document]
  (condp = (-> document
                (:mainDocument)
                (:detailsInfo)
                (:label))
  "B2B_SENT" [ui/icon-button {:style {:width 32 :height 32}} (ic/communication-call-made)]
  "B2B_INBOX" [ui/icon-button {:style {:width 32 :height 32}} (ic/communication-call-received)]))

(defn contragents [document]
  (for [comp (-> document
                (:mainDocument)
                (:recipients)
                (:companies))]
        (:name comp)))

(defn naimenovanie [document]
  (-> document
      (:mainDocument)
      (:workflowInfo)
      (:documentName)))

(defn typedoc [document]
  (-> document
      (:mainDocument)
      (:workflowInfo)
      (:type)))

(defn vktable [documents-list]
  [:div {:style {:padding "10px" } }
  [ui/table {:style {} :selectable false }
  [ui/table-header {:displaySelectAll false  :adjustForCheckbox false }
    [ui/table-row
      [ui/table-header-column {:style {:width 10 :padding-left 5 :padding-right 5}} [ui/icon-button {:style {:width 32 :height 32}} (ic/action-swap-horiz)] ]
       [ui/table-header-column {:style {:width 120}} "Наименование"]
        [ui/table-header-column {:style {:width 70}}"Контрагенты"]
         [ui/table-header-column {:style {:width 100}} "Тип документа"]]]
    [ui/table-body {:displayRowCheckbox false}
      (for [document (:documents documents-list)]
        ^{:key (:id document)}[ui/table-row
          [ui/table-row-column {:style {:width 10 :padding-left 5 :padding-right 5}} (status document)]
           [ui/table-row-column {:style {:width 120 } } (naimenovanie document)]
            [ui/table-row-column {:style {:width 70}} (contragents document)]
              [ui/table-row-column {:style {:width 100}} (typedoc document) ]])]]])
