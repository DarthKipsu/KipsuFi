(ns kipsufi.svg.common)

(defn arrow [{rotated :rotated direction :direction}]
  [:svg {:xmlns "http://www.w3.org/2000/svg" 
         :xmlns:xlink "http://www.w3.org/1999/xlink"
         :viewBox "0 0 50 50"
         :class (str "arrow " direction)}
   [:g
     [:circle {:cx "25"
               :cy "25"
               :r "25"}]
     [:path {:d "M 37.940059,25.219766 23.266639,10.546345 c -2.461983,-2.4619851 -7.38595,2.461984 -4.923967,4.923965 l 9.847933,9.847933 -9.847933,9.847933 c -2.461983,2.461984 2.461984,7.385953 4.923967,4.923969 l 14.67342,-14.673419 z"}]]])
