(ns kipsufi.svg.common)

(defn arrow [{direction :direction}]
  [:svg {:xmlns "http://www.w3.org/2000/svg" 
         :xmlns:xlink "http://www.w3.org/1999/xlink"
         :viewBox "0 0 50 50"
         :class (str "arrow " direction)}
   [:g {:transform (str "rotate(" (if (= direction "next") 0 180) " 25 25)")}
     [:path {:d "M 25 0 A 25 25 0 0 0 0 25 A 25 25 0 0 0 25 50 A 25 25 0 0 0 50 25 A 25 25 0 0 0 25 0 z M 21.6875 9.875 C 22.257314 9.8822129 22.805956 10.085253 23.267578 10.546875 L 37.939453 25.220703 L 37.939453 25.416016 L 23.267578 40.089844 C 20.805595 42.551828 15.879814 37.628 18.341797 35.166016 L 28.191406 25.318359 L 18.341797 15.470703 C 16.341436 13.470344 19.218304 9.8437439 21.6875 9.875 z"}]]])
