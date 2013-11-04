(ns simclient.canvas
  (:require [simclient.dom]
            [simclient.transform]
            [simclient.landmark]
            [clojure.string]))

(def canvas-width 300)
(def canvas-height 300)
(def ^:dynamic *id* 0)

(defn record-landmark!
  [landmark id]
  (let [landmark-div (. js/document getElementById "landmark-store")
        inner-html #(. landmark-div -innerHTML)
        landmark-fixed [(double (. (first landmark) toFixed 2))
                        (double (. (second landmark) toFixed 2))]]
    (set! *id* (+ *id* 1))
    (set! (. landmark-div -innerHTML)
          (str (inner-html) "<span id=" *id* ">" *id* ": " landmark-fixed "</span><br />"))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; HTML5 Canvas Functions ;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn get-canvas [canvas-id]
  (. js/document getElementById canvas-id))

(defn get-context [canvas]
   (. canvas getContext "2d"))

(defn draw-point!
  [context x y r g b]
  (set! (. context -strokeStyle) (str "rgb(" r "," g "," b ")"))
  (. context strokeRect x y 1 1))

(defn erase-point!
  [context x y]
  (. context clearRect x y 1 1))

(defn clear-canvas!
  [context]
  (. context clearRect 0 0 600 600))

(defn draw-robot
  []
  (let [pose (simclient.dom/get-robot-pose)
        [x y a] pose
        context (get-context (get-canvas "robots"))
        pose' (simclient.transform/pose-to-screen pose)
        [[leftx lefty]
         [rightx righty]
         [topx topy]] (simclient.transform/triangle-points pose')]
    (. context beginPath)
    (. context moveTo leftx lefty)
    (. context lineTo rightx righty)
    (. context lineTo topx topy)
    (. context lineTo leftx lefty)
    (. context stroke)))

(defn draw-landmarks
  []
  (let [ctx (get-context (get-canvas "landmarks"))
        lmk-count (simclient.dom/get-landmark-count)
        landmarks (simclient.landmark/make-landmarks lmk-count)
        to-screen #(simclient.transform/cart-to-screen
                    (first %) (second %) canvas-height canvas-width)
        screen-lmks (map to-screen landmarks)
        landmark-div (. js/document getElementById "landmark-store")]
    (. js/console log (count landmarks))
    (clear-canvas! ctx)
    (set! *id* 0)
    (set! (. landmark-div -innerHTML) "")
    (doall (map record-landmark! landmarks))
    (doall (map #(draw-point! ctx (first %) (second %) "00" "00" "00")
                screen-lmks))))

(defn draw-waypoints ;;; TODO Make this draw x
  []
  (let [ctx (get-context (get-canvas "waypoints"))
        lmk-count (simclient.dom/get-landmark-count)
        landmarks (simclient.landmark/make-landmarks lmk-count)
        to-screen #(simclient.transform/cart-to-screen
                    (first %) (second %) canvas-height canvas-width)
        screen-lmks (map to-screen landmarks)]

    (doall (map #(draw-point! ctx (first %) (second %) "00" "88" "00")
                screen-lmks))))

(defn draw-sensor
  [context pose sensor])

(defn draw-estimate
  [context map-estimate])
