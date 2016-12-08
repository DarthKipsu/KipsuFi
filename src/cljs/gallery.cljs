(ns cljs.gallery
  (:require [clojure.string :as str]
            [dommy.core :as dom]))

(enable-console-print!)

(def has-thumbnails "(min-width: 400px) and (min-height: 200px) and (orientation: landscape)")

(def photos-in-gallery (count (dom/sel :.gallery-photo)))

(defn- element-by-class [selector]
  (dom/sel1 (keyword (str "." selector))))

(defn- is-gallery-page-with-thumbnails []
  (if (not (nil? (element-by-class "photos-gallery-page")))
    (.-matches (.matchMedia js/window has-thumbnails))
    false))

(defn- display-id-from-pathname []
  (let [id (get (str/split js/window.location.pathname "/") 4)]
    (if id id "1")))

(def displaying (atom (display-id-from-pathname)))

(defn- photo-by-order [order]
  (first (filter (fn [item] (= order (dom/attr item :data-order)))
                 (dom/sel :.gallery-photo))))

(defn- thumb-by-order [order]
  (first (filter (fn [item] (= order (dom/attr item :data-order))) (dom/sel :img.thumb))))

(defn- next-arrow []
  (.item (.-children (dom/sel1 :.nav.next)) 0))

(defn- prev-arrow []
  (.item (.-children (dom/sel1 :.nav.prev)) 0))

(defn- next-link []
  (.item (.-children (.item (.-children (dom/sel1 :.links.next)) 0)) 0))

(defn- prev-link []
  (.item (.-children (.item (.-children (dom/sel1 :.links.prev)) 0)) 0))

(defn- update-link! [link new-i]
  (let [href-base (str/join "/" (drop-last (str/split (.-href link) "/")))]
    (.setAttribute link "href" (str href-base "/" new-i))))

(defn- next-photo [id]
  (let [next-id (inc (int id))]
    (if (>= next-id photos-in-gallery) 1 next-id)))

(defn- prev-photo [id]
  (let [prev-id (dec (int id))]
    (if (<= next-id 0) photos-in-gallery prev-id)))

(defn- display-photo! [id]
  (let [prev-display (photo-by-order @displaying)
        prev-thumb (thumb-by-order @displaying)
        photo (photo-by-order id)
        thumb (thumb-by-order id)]
    (reset! displaying id)
    (dom/add-class! prev-display :hidden)
    (dom/add-class! prev-thumb :faded)
    (dom/remove-class! photo :hidden)
    (dom/remove-class! thumb :faded)
    (if (is-gallery-page-with-thumbnails) (.replaceState js/window.history {} "" id))))

(defn change-width!
  "Changes the width of img.thumb element or if given n, the nth child of the element, with the 
  given width"
  ([width]
   (let [thumbs (dom/sel :img.thumb)]
     (doseq [thumb thumbs]
       (dom/set-style! thumb :width (str width "px")))))
  ([n width]
   (let [thumbs (dom/sel :img.thumb)]
     (dom/set-style! (first (filter (fn [item] (= (str n) (dom/attr item :data-order))) thumbs))
                     :width (str width "px")))))

(defn calculated-compr-width
  "Returns the width for the smallest compressed image for the thumbnail navigation bar based on 
  the container width"
  [container-width displ]
  (cond (or (= displ 1) (= displ photos-in-gallery))
        (/ (- container-width 210) (inc photos-in-gallery))
        (or (= displ 2) (= displ (dec photos-in-gallery)))
        (/ (- container-width 310) photos-in-gallery)
        (or (= displ 3) (= displ (- photos-in-gallery 2)))
        (/ (- container-width 310) (+ photos-in-gallery 2))
        :else 
        (/ (- container-width 310) (+ photos-in-gallery 3))))

(defn adj-thumb-width!
  "Changes the width of all thumbnails in the thumbnail navigation bar, so that the displayed 
  thumbnail has maximum width and the thumbnails around it have decreasing widths to fit the bar
  in one line"
  [display-id]
  (let [container-width (.-offsetWidth (.querySelector js/document ".thumbs"))
        displ (int display-id)]
    (if (> photos-in-gallery (/ container-width 100))
      (let [compr-width (calculated-compr-width container-width displ)]
        (if (> compr-width 33.33)
          (do (change-width! (str (/ (- container-width 110) (dec photos-in-gallery))))
            (change-width! displ 100))
          (do (change-width! compr-width)
            (change-width! displ 100)
            (change-width! (inc displ) 100)
            (change-width! (dec displ) 100)
            (change-width! (+ displ 2)  (* 3 compr-width))
            (change-width! (- displ 2) (* 3 compr-width))
            (change-width! (+ displ 3) (* 2 compr-width))
            (change-width! (- displ 3) (* 2 compr-width))))))))

(defn- add-thumbnail-listeners! []
  (let [thumbs (dom/sel :.thumb)]
    (doseq [thumb thumbs]
      (dom/listen! thumb :click (fn [e] (display-photo! (dom/attr (.-target e) :data-order))
                                  (adj-thumb-width! @displaying))))))

(defn- add-window-resize-listener! []
  (dom/listen! js/window :resize (fn [] (adj-thumb-width! @displaying))))

(defn- add-arrow-click-listeners! []
  (dom/listen! (next-link) :click (fn [e] 
    (display-photo! (if (>= @displaying photos-in-gallery) "1" (str (inc (int @displaying)))))
    (adj-thumb-width! @displaying)))
  (dom/listen! (next-arrow) :click (fn [e] 
    (display-photo! (if (>= @displaying photos-in-gallery) "1" (str (inc (int @displaying)))))
    (adj-thumb-width! @displaying)))
  (dom/listen! (prev-link) :click (fn [e] 
    (display-photo! (if (<= @displaying 1) (str photos-in-gallery) (str (dec (int @displaying)))))
    (adj-thumb-width! @displaying)))
  (dom/listen! (prev-arrow) :click (fn [e] 
    (display-photo! (if (<= @displaying 1) (str photos-in-gallery) (str (dec (int @displaying)))))
    (adj-thumb-width! @displaying))))

(if (is-gallery-page-with-thumbnails)
  (do (display-photo! (display-id-from-pathname))
    (add-thumbnail-listeners!)
    (adj-thumb-width! @displaying)
    (add-arrow-click-listeners!)
    (add-window-resize-listener!)))
