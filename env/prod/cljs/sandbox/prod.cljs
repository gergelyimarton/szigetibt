(ns sandbox.prod
  (:require [sandbox.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
