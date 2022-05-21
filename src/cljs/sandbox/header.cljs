(ns sandbox.header
  (:require
   [reagent.core :as reagent :refer [atom]]))

;; kontaktos
(def contacts [["Szigeti Ágnes" "agi.szigeti@gmail.com" "+36309604848"]
               ["Gergelyi Márton" "gergelyimarci@gmail.com" "+36302460625"]])


(defn contact-card [name email phone]
  [:div
    (str name " - " email " / " phone)])

(defn contact-mapper [item]
  [contact-card
   (first item)
   (second item)
   (nth item 2)])



(defn view []
  [:div.header
   [:div.header-contents
    [:div.title "Szigeti bt."]
    [:div.header-contacts
     [:div.email-num "Email és telefonszám"]
     (map contact-mapper contacts)]]])
