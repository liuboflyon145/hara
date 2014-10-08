(ns hara.reflect.core.hierarchy
  (:require [hara.reflect.common :as common]
            [hara.reflect.element.common :as element]
            [hara.class.inheritance :as inheritance])
  (:refer-clojure :exclude [.% .%>]))

(defmacro .%
  "Lists class information

  (.% String)  ;; or (.%> \"\")
  => (contains {:name \"java.lang.String\"
                :tag :class
                :hash anything
                :container nil
                :modifiers #{:instance :class :public :final}
                :static false
                :delegate java.lang.String})"
  {:added "2.1"}
  [obj]
  `(element/seed :class (common/context-class ~obj)))

(defmacro .%>
  "Lists the class and interface hierarchy for the class

  (.%> String)   ;; or (.%> \"\")
  => [java.lang.String
      [java.lang.Object
       #{java.io.Serializable
         java.lang.Comparable
         java.lang.CharSequence}]]"
  {:added "2.1"}
  [obj & args]
  `(let [t# (common/context-class ~obj)]
     (vec (concat [t#] (inheritance/ancestor-tree t#)))))
