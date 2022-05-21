(ns sandbox.footer
  (:require
   [reagent.core :as reagent :refer [atom]]))

;;képek
(defn image []
  [:img {:src "/images/duplaj-logo-dust.png"
              :style {
                      :width "80px"
                      :height "80px"
                      :float "right"}}])

(defn contact []
       [:div
        [:div "További elérhetőségek: "]
        [:a
         {:href "https://www.facebook.com/szigetistudio/"}
         "facebook.com/szigetistudio"]])




(defn view []
  [:div.footer
   [:div.made-by-who
    [:div "All rights reserved.  2022-2025"]
    "This webpage made by: "
    [:a
     {:href "https://www.instagram.com/duplajezus/"}
     "Me :)"]]
   [:div.contact [contact]]
   [:div [image]]])





  ; [:div.footer
  ;   [:div.image-and-content
  ;    [:div.content {:style
  ;                   {:font-size "1.5em"}}
  ;     [content]]
  ;    [image]]])
