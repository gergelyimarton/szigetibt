(ns sandbox.footer
  (:require
   [reagent.core :as reagent :refer [atom]]))

;;képek
(defn image []
  [:img {:src "/images/duplaj-logo-dust.png"
              :style {
                      :width "100px"
                      :height "100px"
                      :margin-right "20px"}}])

(defn content []
      [:footer
       [:div.footer-content
        [:div "Elérhetőségek: "]
        [:div "Cím, facebook, insta stb.."]
        [:div "This webpage made by: "
         [:a
          {:href "https://www.instagram.com/duplajezus/"}
          "Me :)"]]]])



(defn view []
  [:div.footer
   [:div
    [:div.email-num "Kép a szolgáltatóról:"]
    [:div.image-and-content
     [content]
     [image]]]])
