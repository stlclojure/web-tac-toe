(ns web-tac-toe.prod
  (:require [web-tac-toe.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
