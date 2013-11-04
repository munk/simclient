(ns simclient.transform)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; Coordinate Transformations ;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn cart-to-screen
  [x y height width]
  (let [x' (+ x width)
        y' (- height y)]
    [x'
     y']))

(defn pose-to-screen
  [pose]
  (let [[x y a] pose
        [x' y'] (cart-to-screen x y)]
    [x' y' a]))

(defn polar-to-cart
  [range bearing]
  (let [x (* range (Math/cos bearing))
        y (* range (Math/sin bearing))]
    [x y]))

(defn cart-to-polar
  [x y]
  [(Math/sqrt (+ (* x x) (* y y)))
   (Math/atan2 y x)])

(defn translate
  [point pose]
  (let [x (first point)
        y (second point)
        x' (first pose)
        y' (second pose)]
   [(+ x x')
    (+ y y')]))

;;;TODO - Reflect points on canvas
(defn triangle-points
  [pose]
  (let [[x y a] pose
        pi-2 (/ Math/PI 2)
        left-a (+ a pi-2)
        right-a (- a pi-2)
        range 5
        left (translate (polar-to-cart range left-a) pose)
        right (translate (polar-to-cart range right-a) pose)
        center (translate (polar-to-cart (- range) a) pose)]
    [left right center]))
