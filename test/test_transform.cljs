(ns simclient.test-transform
  (:require [simclient.transform :refer :all]
            [clojure.test :refer :all]))


(deftest test-cart-to-screen
  (testing "Cartesian coordinates to Screen Coordinates"
    (let [expected [250 250]
          actual (cart-to-screen [0 0])]
      (is (= actual expected)))))
