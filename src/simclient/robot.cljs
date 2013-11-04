(ns simclient.robot)

(defn run-step
  [current-pose waypoint]
  (let [plan nil]))

(defn angular-distance
  [pose point]
  (let [[x y a] pose
        [x' y'] point
        xdist (- x' x)
        ydist (- y' y)]
    (Math/atan2 ydist xdist)))

(defn euclidian-distance
  [pose point]
  (let [[x y a] pose
        [x' y'] point
        xdist (- x' x)
        ydist (- y' y)]
   (Math/sqrt (+ (* xdist xdist) (* ydist ydist)))))

(defn plan
  [pose waypoint]
  (let [dist (euclidian-distance pose waypoint)
        angle (angular-distance post waypoint)]
    [(min dist 5)
     (min angle 5)]))

(defn move
  [pose control]
  (let [[x y a] pose
        [v w] control
        sina (Math/sin a)
        cosa (Math/cos a)]
    (if (= w 0)
      (let [vsina (* v sina)
            vcosa (* v cosa)]
        [(+ x vcosa)
         (+ y vsina)
         a])
      (let [vw (/ v w)
            -vw (- vw)
            vwsina (* -vw sina)
            vwcosa (*  vw cosa)
            sinaw (*  vw (Math/sin (+ a w)))
            cosaw (* -vw (Math/cos (+ a w)))]
         [(+ x vwsina sinaw)
          (+ y vwcosa cosaw)
          (+ a w)]))))
