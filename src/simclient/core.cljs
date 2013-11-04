(ns simclient.core
  (:require [simclient.canvas]
            [simclient.robot]))

;;; Simulator Client Interface ;;;
(declare turn-to-waypoint)
(declare goto-waypoint)

(defn make-plan
  [pose waypoints max-velocity]
   (loop [pose pose
          waypoints waypoints]
     (let [waypoint (first waypoints)
           u0 (turn-to-waypoint pose waypoint)
           a (second u0)
           u (get-steps pose waypoint)]
       (if (not (nil? waypoint))
         (into (turn-to-waypoint pose waypoint)
               (goto-waypoint pose waypoint max-velocity))
         (recur (into waypoint a) (rest waypoints))))))


(defn make-waypoints
  [waypoint-count]
  (let [radius 300]
    (vec (take waypoint-count
               (repeatedly
                #(vector (- (* (rand) (* 2 radius)) radius)
                         (- (* (rand) (* 2 radius)) radius)))))))

(defn turn-to-waypoint
  [pose waypoint]
  (simclient.robot/angular-distance pose waypoint))

(defn goto-waypoint
  "Assumes robot is pointing to the waypoint"
  [target-distance max-velocity]
  (let [ticks (int (Math/ceil (/ target-distance max-velocity)))
        velocity (/ target-distance ticks)]
    (take ticks (cycle [velocity]))))

(defn make-plan
  [waypoints]
  (for [waypoint waypoints]
    (let [distance (euclidian-distance pose waypoint)]
      (into (turn-to-waypoint pose waypoint)))))

;;; While we're heading towards a waypoint - go towards the waypoint
;;; Else, pop the next waypoint
