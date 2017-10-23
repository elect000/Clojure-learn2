(ns mandelbrot.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def width 800)
(def height 700)

(defn setup []
  (q/frame-rate 1)
  {:x-size [2.0 -1.5]
   :y-size [1.5 -1.5]
   :color [10 200 20 255]
   :change true
   })

(defn mandelbrotbean [x y acc times]
  (let [[x2 y2] acc
        new-acc-x (+ (* x2 x2) (- 0.0 (* y2 y2)) x)
        new-acc-y (+ (* 2.0 x2 y2) y)
        ]
    (if (> (+ (* x2 x2) (* y2 y2)) 4.0)
      (* (/ 255.0 30.0) (- 30 times))
      (if (> times 0) 
        (mandelbrotbean x y [new-acc-x new-acc-y] (dec times))
        -1 ))))

(defn draw-state [state]
  (when (:change state)
    (let [[x-max x-min] (:x-size state)
          [y-max y-min] (:y-size state)
          x-len (- x-max x-min)
          y-len (- y-max y-min)
          [red green blue alpha] (:color state)
          pixellist []]
      (q/pixels (for [i (range width) k (range 100)]
                  (q/color 255 204 0))))))


(q/defsketch mandelbrot
  :title "Fractals: Mandelbrot"
  :size [width height]
  :setup setup
;;  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])

