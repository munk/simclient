(ns simclient.landmark)

(defn make-landmarks
  [lmk-count]
  (. js/console log lmk-count)
  (let [radius 300]
    (vec (take lmk-count (repeatedly
                          #(vector (- (* (rand) (* 2 radius)) radius)
                                   (- (* (rand) (* 2 radius)) radius)))))))
