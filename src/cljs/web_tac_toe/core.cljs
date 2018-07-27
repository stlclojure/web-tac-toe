(ns web-tac-toe.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]))

;; -------------------------
;; Model

(def board-state
  (atom [[:empty :empty :empty]
         [:empty :empty :empty]
         [:empty :empty :empty]]))

(def current-turn
  (atom :x))

(defn play! [col-number row-number]
  (locking current-turn
    (swap! board-state assoc-in [row-number col-number] @current-turn)
    (case @current-turn
      :x (reset! current-turn :o)
      :o (reset! current-turn :x))))

;; -------------------------
;; Views

(defn- cell-value-to-string [cell-value]
  (case cell-value
    :x "X"
    :o "O"
    :empty ""))

(defn- draw-cell [row-number col-number value]
  [:td {:on-click #(play! row-number col-number)} (cell-value-to-string value)])

(defn- col->html [drawer container-element col]
  (->> (map-indexed drawer col) vec (cons container-element) vec))

(defn- draw-row [row-number board-row]
  (col->html #(draw-cell row-number %1 %2) :tr board-row))

(defn home-page []
  [:div {:class "board"}
   [:table
    (col->html draw-row :tbody @board-state)]])

;; -------------------------
;; Routes

(defonce page (atom #'home-page))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
