(ns sandbox.home
  (:require
   [reagent.core :as reagent :refer [atom]]))

; .fade-in {
  ; -webkit-animation: fadeIn ease 0.4s;
  ; -moz-animation: fadeIn ease 0.4s;
  ; -o-animation: fadeIn ease 0.4s;
  ; -ms-animation: fadeIn ease 0.4s;
  ; animation: fadeIn ease 0.4s);

;;/////////////// sliding gallery /////////////////////////////////////////
(def sliding-gallery-pictures
  [{:id 0 :image "/images/egy7.jpg"}
   {:id 1 :image "/images/ketto.jpg"}
   {:id 2 :image "/images/harom.jpg"}])

(def sliding-gallery-atom (atom 0))


;; ------------------------- oda és vissza léptetés logikája
(defn next-image [event]
  (let [max-index (dec (count sliding-gallery-pictures))]
    (reset! sliding-gallery-atom
           (if (> max-index @sliding-gallery-atom)
             (inc @sliding-gallery-atom)
             0))))

(defn prev-image [event]
  (let [min-index 0
        max-index (dec (count sliding-gallery-pictures))]
    (reset! sliding-gallery-atom
            (if (< min-index @sliding-gallery-atom)
              (dec @sliding-gallery-atom)
              max-index))))

;; ------------------------- kép animáció
(defn sliding-gallery []
  [:div.gallery-container

;; ------------------------- vissza léptetés, kép animáció
   [:div.arrows
    [:span#arrow-back.material-symbols-outlined.arrow
     {:on-click (fn [event]
                  (js/gsap.to
                      (.getElementById js/document "gsap")
                      (clj->js {:duration 0.5
                                :opacity 0
                                :onComplete (fn [e]
                                              (prev-image event)
                                              (js/gsap.to
                                                (.getElementById js/document "gsap")
                                                (clj->js {:duration 0.5
                                                          :opacity 1})))})))
      :style {:font-size "80px"}}
     "arrow_back_ios"]]

;; ----------------------------- maga a kép

   [:div#gsap.sliding-picture
    ; (str @sliding-gallery-atom)
    [:img
     {:src
      (get-in sliding-gallery-pictures [@sliding-gallery-atom :image])}]]


;; ----------------------------- előre léptetés, kép animáció

   [:div.arrows
    [:span#arrow-forward.material-symbols-outlined.arrow
     {:on-click (fn [event]
                  (js/gsap.to
                    (.getElementById js/document "gsap")
                    (clj->js {:duration 0.5
                              :opacity 0
                              :onComplete (fn [e]
                                            (next-image event)
                                            (js/gsap.to
                                              (.getElementById js/document "gsap")
                                              (clj->js {:duration 0.5
                                                        :opacity 1})))})))
      :style    {:font-size "80px"}}
     "arrow_forward_ios"]]])






;;/////////////// reviews /////////////////////////////////////////

(def velemeny-adat
  [["Gergelyi Márton" "O1G xd de vicces." "/images/pexels-asim-alnamat-343717.jpg"]
   ["Tapaszti Dorina" "Tartsd meg a mocskot te apró!" "/images/pexels-adrienn-1542085.jpg"]
   ["Nagyabaj Sándor" "Nekem tetszik ez az oldal!" "/images/nagyamosoly.jpg"]
   ["Pupák Pali" "Elééég jóóóó!" "/images/indiaipali.jpg"]
   ["Masztur Bátor" "Hol a vajam?" "/images/nathan.jpg"]
   ["Mol Eszter" "Keress ezen a számon: 06 70 tini ninja batman!" "/images/eszter.jpg"]])

(defn velemeny-forma [name review picture]
  [:div.velemeny-container
   [:div.velemeny-name name]
   [:div.velemeny-nextto
    [:blockquote.velemeny-review review]
    [:div {:style {:display "flex"
                   :flex-direction "column"
                   :justify-content "center"
                   :height "100%"
                   :padding-right "10px"}}
     [:img.velemeny-image {:src picture}]]]])



(defn velemeny-mapper [item]
 [velemeny-forma
  (first item)
  (second item)
  (nth item 2)])





;;/////////////// treatments /////////////////////////////////////////
;; ------------------------ auto legörgetés a szöveghez

(defn scroll-to [id]
  (.scrollIntoView (.getElementById js/document id)
                  (clj->js {:behavior "smooth"
                            :block "center"
                            :inline "center"})))

; (defn gomb-egy []
;   [:div.egy-container
;    [:div
;     {:class       "egy"
;      :on-click    (fn [event] (scroll-to "here-one"))}
;     "Mit jelent ez a kép?"]])
;
;
; (defn gomb-ketto []
;   [:div.ketto-container
;    [:div
;     {:class       "ketto"
;      :on-click    (fn [event] (scroll-to "here-two"))}
;     "Mit jelent ez a kép?"]])
;
;
; (defn gomb-harom []
;   [:div.harom-container
;    [:div
;     {:class       "harom"
;      :on-click    (fn [event] (scroll-to "here-three"))}
;     "Mit jelent ez a kép?"]])  szemét

(def kezelesek-atom (atom 0))

(def kezelesek [{:title "Arc darálós cuccmák:" :description "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to    distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains."}

                {:title "Szipúúúúáááá:" :description "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."}

                {:title "Pakolásos kezelés:" :description "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"}

                {:title "Ő itt Eszter:" :description "Eszter kicsit szégyenlős, de hamar hozzászokik a pixelekhez. Eszter nagyon okos és ügyes! Szereti a tengerimalacokat és titkos fétise a tejszínhab, ánusszal történő spriccelése."}])


(defn kezeles-button [number]
  [:div.button-container
   [:div
    {:class       "button"
     :on-click    (fn [event] (reset! kezelesek-atom number)
                              (scroll-to "kezelesek-mid"))}
    "Kezelés megtekintése"]])


(defn picked-kezeles []
   [:div
    [:div.kezeles-title
     (get-in kezelesek [@kezelesek-atom :title])]
    [:div.kezeles-description
     (get-in kezelesek [@kezelesek-atom :description])]])




;; ------------------------ kezeléshez tartozó képek és inline css
(def kep-style {
                :border-radius "10px"
                :width "80%"
                :margin "20px"
                :margin-top "100px"})


(defn elso-kep []
  [:img {:src "/images/egy7.jpg"
         :style kep-style}])


(defn masodik-kep []
  [:img {:src "/images/ketto.jpg"
         :style kep-style}])

(defn harmadik-kep []
  [:img {:src "/images/harom.jpg"
         :style kep-style}])

(defn negyedik-kep []
  [:img {:src "/images/eszter.jpg"
         :style kep-style}])



;;/////////////// home page architect /////////////////////////////////////////


(defn view []
  [:div.base
   [:div.home
         ; (map (fn [item] [contact-card (first item) (second item)])
         ;   contacts)
         [:div
          [:div.main-title]
           ; [:h2 "Főcím"]]
          [:div.tablazat
           [:div "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."]
           [:div.vertical-line]
           [:div "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"]]

;; -------------------------------- képek gombokkal
          [:div.services-grid
           [:div.service-container
              [elso-kep]
              [kezeles-button 0]]
           [:div.service-container
             [masodik-kep]
             [kezeles-button 1]]
           [:div.service-container
              [harmadik-kep]
              [kezeles-button 2]]
           [:div.service-container
             [negyedik-kep]
             [kezeles-button 3]]]
;; ------------------------------- kezelések leírása
          [:div.kezelesek-outer
           [:div#kezelesek-mid
            [:div.kezelesek-inner
             (picked-kezeles)]]]
;; ------------------------------- sliding gallery
          [sliding-gallery]
;          [:div.here-outer
          ;  [:div#here-one
          ;   [:div.here-inner
          ;    [:h3 "Ezt jelenti a kép!"]
          ;    [:div "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains."]]]]
          ;
          ; [:div.here-outer
          ;   [:div#here-two
          ;    [:div.here-inner
          ;     [:h3 "Ezt jelenti a kép!"]
          ;     [:div "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."]]]]
          ;
          ; [:div.here-outer
          ;   [:div#here-three
          ;    [:div.here-inner
          ;     [:h3 "Ezt jelenti a kép!"]
          ;     [:div "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"]]]]
;; ------------------------------- vélemények
          [:div
           [:div
            [:div.velemenyek-container
             (map velemeny-mapper velemeny-adat)]]]]]])
