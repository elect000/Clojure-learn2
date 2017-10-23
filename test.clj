(ns test.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def width 800)
(def height 700)
(defn setup []
  (q/frame-rate 20)
  (q/background 200))

(defn draw []
  (q/background 200 30 40)
  (q/pixels
   (for [i (range width) j (range width)]
     (q/color 0 0 200)))
  (q/update-pixels))

(defn update []
  (q/update-pixels
   (for [i (range width) j (range width)]
     (q/color 0 0 200)))
  (q/update-pixels))

(q/defsketch example
  :title "yellow"
  :settings #(q/smooth 2)
  :setup setup
  :update update
  :draw draw
  :size [width height])
