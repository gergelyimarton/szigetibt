(ns sandbox.header
  (:require
   [reagent.core :as reagent :refer [atom]]))

;; kontaktos
(def contacts [["Gergelyi Márton" "gergelyimarci@gmail.com" "2566543"]
               ["Tapaszti Dorina" "dodotapaszti@gmail.com" "4345682"]])

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
    [:div.title
     [:h1 "Cégnév"]]
    [:div
     [:div.email-num "Emails/Phone num.:"]
     (map contact-mapper contacts)]]])
