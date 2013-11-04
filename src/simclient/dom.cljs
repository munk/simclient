(ns simclient.dom)

(defn get-landmark-count
  []
  (let [count-field (. js/document getElementById "landmark-count")]
    (int (.-value count-field))))

(defn get-robot-pose
  []
  (let [x-field (. js/document getElementById "x")
        y-field (. js/document getElementById "y")
        a-field (. js/document getElementById "a")]
    [(double (.-value x-field))
     (double (.-value y-field))
     (double (.-value a-field))]))
