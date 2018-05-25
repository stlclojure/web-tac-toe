(ns ^:figwheel-no-load web-tac-toe.dev
  (:require
    [web-tac-toe.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
